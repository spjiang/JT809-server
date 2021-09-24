package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x9207;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 *
 * 文件上传控制 解码处理类
 *
 * <p>类详细说明
 * @author zx 2018年9月28日 下午5:31:41
 * @see
 * @version 1.0
 */
public class P0x9207PB implements IPassBody {

	@Override
	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if(null == model) {
			throw new P905Exception("P0x9207消息体转码失败，传入的消息体实体对象为空。");
		}
		try {
			P0x9207 p = (P0x9207) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.putShort(p.getAnsFlowNo());//流水号
			buf.put(p.getUploadControl());//上传控制
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x9207编码失败:", e);
		}
	}

	@Override
	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if(null == data|| 0 > data.length) {
			throw new P905Exception("P0x9207解析失败，消息长度不足。");
		}
		try {
			P0x9207 p = new P0x9207();
			int pos = 0;
			//应答流水号
			byte[] ansFlowNo = new byte[2];
			System.arraycopy(data, pos, ansFlowNo, 0, 2);
			p.setAnsFlowNo(ConvUtil.bytes2Short(ansFlowNo));
			pos += 2;
			//上传控制
			p.setUploadControl(data[pos]);
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x9207编码失败:", e);
		}
	}

}
