package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808.engine.p2011;

import com.ctfo.protocol.P808.engine.IPassBody;
import com.ctfo.protocol.P808.model.PModel;
import com.ctfo.protocol.P808.model.p2011.P0x0001;
import com.ctfo.protocol.support.exception.P808Exception;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 终端通用应答消息编解码处理类<code>P0x0001</code>
 * <p>
 * 消息ID：0x0001
 *
 *
 * @author 凉意 2018年4月22日 下午1:56:12
 * @version 1.0
 */
public class P0x0001PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P808Exception {
		if (null == model) {
			throw new P808Exception("P0x0001消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x0001 p = (P0x0001) model;
			IoBuffer buf = IoBuffer.allocate(5).setAutoExpand(true);
			buf.putShort(p.getAnsFlowNo());
			buf.putShort(p.getAnsId());
			buf.put(p.getAnsResult());
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P808Exception("P0x0001编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P808Exception {
		if (null == data || 5 != data.length) {
			throw new P808Exception("P0x0001消息体解码失败，消息长度必须为5字节。");
		}
		try {
			P0x0001 p = new P0x0001();
			IoBuffer buf = IoBuffer.allocate(5).setAutoExpand(true);
			buf.put(data);
			buf.flip();
			p.setAnsFlowNo(buf.getShort());
			p.setAnsId(buf.getShort());
			p.setAnsResult(buf.get());
			return p;
		} catch (Exception e) {
			throw new P808Exception("P0x0001解码失败:", e);
		}
	}

}
