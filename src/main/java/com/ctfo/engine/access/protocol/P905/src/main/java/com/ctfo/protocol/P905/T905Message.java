package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905;

import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.FlowNoUtil;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * 905协议消息结构定义
 *
 * @author 凉意 2018年5月14日 下午4:40:24
 * @version 1.0
 */
@Data
@ToString
public class T905Message implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 905协议分包长度限制，单位:字节 */
	public static final int SUB_PACK_LEN_LIMIT = 2048;

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
	 * 当终端标识第一字节为’10’时，消息体属性目前存储的为消息包的大小
	 */
	private short msgAttr;

	/**
	 * 终端ID,BCD[6]
	 * <p>
	 * 终端标识第一字节为’10’，后5字节为智能服务终端ID，其编码规则参见10.2.1设备编号编码规则
	 */
	private String terminalId;

	/**
	 * 消息流水号，按发送顺序从 0 开始循环累加
	 */
	private short msgFlowNo = 0;

	/**
	 * 是否分包,true==>有消息包封装项
	 */
	private boolean hasSubPackage = false;

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
	 * @return 消息头字节码
	 */
	private byte[] getHeadBytes(int bodyLength) {
		byte[] head;
		// 设置消息头 判断是否分包; 如果有分包:消息头16位, 没分包,消息头12位
		if (bodyLength > SUB_PACK_LEN_LIMIT) {
			head = new byte[16];
		} else {
			head = new byte[12];
		}
		// 消息ID
		System.arraycopy(ConvUtil.hex2Bytes(this.msgIdHex), 0, head, 0, 2);
		// 终端ID，终端标识第一字节为’10’，后5字节为智能服务终端ID，其编码规则参见10.2.1设备编号编码规则
		if (this.terminalId.length() == 10) {
			this.terminalId = "10" + this.terminalId;
		}
		// 如果存在分包，终端标识不能以10开头。
		if (bodyLength > SUB_PACK_LEN_LIMIT) {
			this.terminalId = "00" + this.terminalId.substring(2);
		}
		System.arraycopy(ConvUtil.bcd2Bytes(this.terminalId), 0, head, 4, 6);
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
	 * @return 消息字节码（含分包信息）
	 * @throws P905Exception 905协议处理异常
	 */
	public List<byte[]> toBytes(byte[] body) throws P905Exception {
		List<byte[]> result = new ArrayList<>();
		int bodyLen = 0;
		if (null != body) {
			bodyLen = body.length;
		}
		try {
			// 获取消息头。设置消息分包总数，包数由下面封装，不在getHeadBytes里操作。
			byte[] head = this.getHeadBytes(bodyLen);
			int realLength = bodyLen;
			int length;
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
					System.arraycopy(ConvUtil.short2Bytes(FlowNoUtil.getMsgFlowNo905()), 0, head, 10, 2);
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
			throw new P905Exception("T905Message.toBytes编码失败:", e);
		}
		return result;
	}

}
