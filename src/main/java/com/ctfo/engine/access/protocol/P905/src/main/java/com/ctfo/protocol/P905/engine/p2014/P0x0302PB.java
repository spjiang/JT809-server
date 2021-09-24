package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0302;
import com.ctfo.protocol.support.exception.P905Exception;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 提问应答消息体数据格式编解码处理类<code>P0x0302</code>
 *
 * <p>
 * 消息ID: 0x0302
 *
 * @author xiahancheng 2018年5月3日 上午9:07:50
 * @see
 * @version 1.0
 */
public class P0x0302PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if(null == model){
			throw new P905Exception("P0x0302消息体编码失败，传入消息体实体对象为空。");
		}
		try{
			P0x0302 p = (P0x0302) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.putInt(p.getProblemId());
			buf.put(p.getAnswerId());
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		}
		catch (Exception e) {
			throw new P905Exception("P0x0302编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 5 != data.length) {
			throw new P905Exception("P0x0302消息体解码失败，消息为空或者长度不足。");
		}
		try{
			P0x0302 p = new P0x0302();
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(data);
			buf.flip();
			p.setProblemId(buf.getInt());
			p.setAnswerId(buf.get());
			return p;
		}catch (Exception e) {
			throw new P905Exception("P0x0302编码失败:", e);
		}
	}


}
