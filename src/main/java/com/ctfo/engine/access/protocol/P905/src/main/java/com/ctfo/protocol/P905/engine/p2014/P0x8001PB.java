package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8001;
import com.ctfo.protocol.support.exception.P905Exception;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 中心通用应答消息编解码处理类<code>P0x8001</code>
 * <p>
 * 消息ID：0x8001
 *
 *
 * @author 凉意 2018年4月22日 下午1:56:12
 * @see
 * @version 1.0
 */
public class P0x8001PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8001消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x8001 p = (P0x8001) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.putShort(p.getAnsFlowNo());
			buf.putShort((short) p.getAnsId());
			buf.put(p.getAnsResult());
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x8001编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 5 != data.length) {
			throw new P905Exception("P0x8001消息体解码失败，消息长度必须为5字节。");
		}
		P0x8001 p = new P0x8001();
		try {
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(data);
			buf.flip();
			p.setAnsFlowNo(buf.getShort());
			p.setAnsId(buf.getShort());
			p.setAnsResult(buf.get());
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x8001解码失败:", e);
		}
	}

}
