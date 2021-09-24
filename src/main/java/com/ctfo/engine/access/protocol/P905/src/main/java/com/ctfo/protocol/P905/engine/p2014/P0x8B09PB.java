package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8B09;
import com.ctfo.protocol.support.exception.P905Exception;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 中心取消订单命令消息编解码处理类<code>P0x8B09</code>
 *
 * <p>
 *消息ID: 0x8B09
 *
 * @author xiahancheng 2018年5月5日 下午1:39:25
 * @see
 * @version 1.0
 */
public class P0x8B09PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8B09消息体编码失败，传入消息体实体对象为空。");
		}
		try{
			P0x8B09 p = (P0x8B09) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.putInt(p.getBusinessId());
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		}catch (Exception e) {
			throw new P905Exception("P0x8B09编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 4 != data.length) {
			throw new P905Exception("P0x8B09消息体解码失败，消息长度必须为4字节。");
		}
		try{
			P0x8B09 p = new P0x8B09();
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(data);
			buf.flip();
			p.setBusinessId(buf.getInt());
			return p;
		}catch (Exception e) {
			throw new P905Exception("P0x8B09编码失败:", e);
		}
	}


}
