package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808;

import com.ctfo.protocol.P808.model.PModel;
import com.ctfo.protocol.support.exception.P808Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.FlowNoUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 808协议消息结构定义
 *
 * @author 凉意 2018年5月14日 下午4:40:33
 * @version 1.0
 */
public class T808Message implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 808协议分包长度限制，单位:字节 */
	public static final int SUB_PACK_LEN_LIMIT = 1023;

	/**
	 * 消息ID
	 */
	private short msgId;

	/**
	 * 消息ID十六进制字符串
	 */
	private String msgIdHex;

	/**
	 * 消息体属性
	 * <p>
	 * 15-14位：保留<br>
	 * 13位：分包<br>
	 * 12-10位：数据加密方式<br>
	 * 9-0位消息长度
	 * <p>
	 * 数据加密方式： <br>
	 * ——bit10~bit12 为数据加密标识位； <br>
	 * ——当此三位都为 0，表示消息体不加密； <br>
	 * ——当第 10 位为 1，表示消息体经过 RSA 算法加密； <br>
	 * ——其他保留。 <br>
	 * <p>
	 * 分包：<br>
	 * 当消息体属性中第 13 位为 1 时表示消息体为长消息，进行分包发送处理，具体分包信息由消息包封装项决定；若第 13 位为
	 * 0，则消息头中无消息包封装项字段。<br>
	 * 起始字节 字段 数据类型 描述及要求 <br>
	 * 0 消息总包数 WORD 该消息分包后的总包数 <br>
	 * 2 包序号 WORD 从 1 开始 <br>
	 */
	private short msgAttr;

	/**
	 * 终端手机号，BCD[6]，根据安装后终端自身的手机号转换。手机号不足 12 位，则在前补充数字，大陆手机号补充数字 0，港澳
	 * 台则根据其区号进行位数补充。
	 */
	private String sim;

	/**
	 * 消息流水号，按发送顺序从 0 开始循环累加
	 */
	private short msgFlowNo = 0;

	/**
	 * 是否分包,true==>有消息包封装项
	 */
	private boolean hasSubPackage;

	// 消息包封装项 --- 开始
	/**
	 * 消息总包数
	 */
	private short msgPackCount;

	/**
	 * 序号(分包ID)
	 */
	private short seqNo;

	// 消息包封装项 --- 结束

	/**
	 * 是否加密传输，true===>加密传输
	 */
	private boolean hasEncryption = false;

	/**
	 * 消息体
	 */
	private PModel model;

	/**
	 * 十六进制消息
	 */
	private String msgHex;

	/**
	 * 获取消息头BYTE
	 * <p>
	 * 存在分包消息头长度为 16字节，不存在分包消息头长度为12字节。
	 *
	 * @author 凉意 2018年5月14日 下午5:27:33
	 * @param bodyLength
	 *            消息体长度，可以判断是否分包
	 * @return 808消息头字节码
	 */
	private byte[] getHeadBytes(int bodyLength) {
		byte[] head = null;
		// 设置消息头 判断是否分包; 如果有分包:消息头16位, 没分包,消息头12位
		if (bodyLength > SUB_PACK_LEN_LIMIT) {
			head = new byte[16];
		} else {
			head = new byte[12];
		}
		// 消息ID
		System.arraycopy(ConvUtil.hex2Bytes(this.msgIdHex), 0, head, 0, 2);
		// SIM卡号，不足12位前面补0
		int simLen = this.sim.length();
		if (simLen < 12) {
			int c = 12 - simLen;
			for (int i = 0; i < c; i++) {
				this.sim = "0" + this.sim;
			}
		}
		System.arraycopy(ConvUtil.bcd2Bytes(this.sim), 0, head, 4, 6);
		// 消息流水号,808协议要求分包【消息采用分包发送时，其分包消息应采用连续递增的流水号。】
		// 所有流水号不在这里处理
		// System.arraycopy(ConvUtil.short2Bytes(this.msgFlowNo), 0, head, 10,
		// 2);
		// 消息总包数
		if (bodyLength > SUB_PACK_LEN_LIMIT) {
			this.msgPackCount = (short) (bodyLength / SUB_PACK_LEN_LIMIT + 1);
			System.arraycopy(ConvUtil.short2Bytes(this.msgPackCount), 0, head, 12, 2);
		} else {
			this.msgPackCount = 1;
		}

		return head;
	}

	/**
	 * 整体消息编码发送，如果存在分包则进行分包发送
	 *
	 * @author 凉意 2018年5月15日 下午1:52:52
	 * @return 消息编码后的字节码列表
	 */
	public List<byte[]> toBytes(byte[] body) throws P808Exception {
		List<byte[]> result = new ArrayList<byte[]>();
		int bodyLen = 0;
		if (null != body) {
			bodyLen = body.length;
		}
		try {
			// 获取消息头。设置消息分包总数，包数由下面封装，不在getHeadBytes里操作。
			byte[] head = this.getHeadBytes(bodyLen);

			int realLength = bodyLen;
			int length = 0;
			// 根据消息长度与规定的包长度对比，自动组装分包
			for (int i = 0; i < msgPackCount; i++) {
				if (realLength > SUB_PACK_LEN_LIMIT) {
					realLength = realLength - SUB_PACK_LEN_LIMIT;
					length = SUB_PACK_LEN_LIMIT;
				} else {
					length = realLength;
				}
				// 消息总长度
				int msgLength = length + head.length + 3;
				byte[] msg = new byte[msgLength];
				// 同步头
				msg[0] = 0x7e;
				// 消息体属性
				this.msgAttr = 0;
				if (1 < msgPackCount) {
					msgAttr += 1 << 13; // 有分包
					// 添加分包消息序列号,递增
					System.arraycopy(ConvUtil.short2Bytes((short) (i + 1)), 0, head, 14, 2);
				}
				msgAttr += 0;// 不加密, 当第 10 位为 1，表示消息体经过 RSA 算法加密；
				msgAttr += length; // 消息体长度[9-0位]。10位以下的Bit只能是小于1024的数值。
				System.arraycopy(ConvUtil.short2Bytes(msgAttr), 0, head, 2, 2);
				// 消息流水号
				if (0 == msgFlowNo) {
					System.arraycopy(ConvUtil.short2Bytes(FlowNoUtil.getMsgFlowNo808()), 0, head, 10, 2);
				} else {
					System.arraycopy(ConvUtil.short2Bytes(msgFlowNo), 0, head, 10, 2);
				}

				// 封装消息头,注意这里head.length。因为有个同步头占了1位；
				System.arraycopy(head, 0, msg, 1, head.length);
				// 封装消息体
				if (null != body) {
					System.arraycopy(body, SUB_PACK_LEN_LIMIT * i, msg, head.length + 1, length);
				}
				// 校验位，校验码指从消息头开始，同后一字节异或，直到校验码前一个字节，占用一个字节。
				byte bcc = msg[1];
				int forNum = msg.length - 2;
				for (int j = 2; j < forNum; j++) {
					bcc ^= msg[j];
				}
				msg[msgLength - 2] = bcc;
				// 同步尾
				msg[msgLength - 1] = 0x7e;
				// 转义
				msg = ConvUtil.transEncodeBody(msg);
				this.msgHex = ConvUtil.bytes2Hex(msg);
				result.add(msg);
			}
		} catch (Exception e) {
			throw new P808Exception("T808Message.toBytes编码失败:", e);
		}
		return result;
	}

	/**
	 * @return the msgId
	 */
	public short getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId
	 *            the msgId to set
	 */
	public void setMsgId(short msgId) {
		this.msgId = msgId;
	}

	/**
	 * @return the msgIdHex
	 */
	public String getMsgIdHex() {
		return msgIdHex;
	}

	/**
	 * @param msgIdHex
	 *            the msgIdHex to set
	 */
	public void setMsgIdHex(String msgIdHex) {
		this.msgIdHex = msgIdHex;
	}

	/**
	 * @return the msgAttr
	 */
	public short getMsgAttr() {
		return msgAttr;
	}

	/**
	 * @param msgAttr
	 *            the msgAttr to set
	 */
	public void setMsgAttr(short msgAttr) {
		this.msgAttr = msgAttr;
	}

	/**
	 * @return the sim
	 */
	public String getSim() {
		return sim;
	}

	/**
	 * @param sim
	 *            the sim to set
	 */
	public void setSim(String sim) {
		this.sim = sim;
	}

	/**
	 * @return the msgFlowNo
	 */
	public short getMsgFlowNo() {
		return msgFlowNo;
	}

	/**
	 * @param msgFlowNo
	 *            the msgFlowNo to set
	 */
	public void setMsgFlowNo(short msgFlowNo) {
		this.msgFlowNo = msgFlowNo;
	}

	/**
	 * @return the msgPackCount
	 */
	public short getMsgPackCount() {
		return msgPackCount;
	}

	/**
	 * @param msgPackCount
	 *            the msgPackCount to set
	 */
	public void setMsgPackCount(short msgPackCount) {
		this.msgPackCount = msgPackCount;
	}

	/**
	 * @return the seqNo
	 */
	public short getSeqNo() {
		return seqNo;
	}

	/**
	 * @param seqNo
	 *            the seqNo to set
	 */
	public void setSeqNo(short seqNo) {
		this.seqNo = seqNo;
	}

	/**
	 * @return the model
	 */
	public PModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(PModel model) {
		this.model = model;
	}

	/**
	 * @return the msgHex
	 */
	public String getMsgHex() {
		return msgHex;
	}

	/**
	 * @param msgHex
	 *            the msgHex to set
	 */
	public void setMsgHex(String msgHex) {
		this.msgHex = msgHex;
	}

	/**
	 * @return the hasSubPackage
	 */
	public boolean isHasSubPackage() {
		return hasSubPackage;
	}

	/**
	 * @param hasSubPackage the hasSubPackage to set
	 */
	public void setHasSubPackage(boolean hasSubPackage) {
		this.hasSubPackage = hasSubPackage;
	}

	/**
	 * @return the hasEncryption
	 */
	public boolean isHasEncryption() {
		return hasEncryption;
	}

	/**
	 * @param hasEncryption the hasEncryption to set
	 */
	public void setHasEncryption(boolean hasEncryption) {
		this.hasEncryption = hasEncryption;
	}
}
