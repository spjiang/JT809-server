package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;


import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0200;
import com.ctfo.protocol.P905.model.p2014.P0x0500;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 车辆控制应答消息体数据格式编解码处理类<code>P0x0500</code>
 *
 * <p>
 * 消息ID: 0x0500
 * @author xiahancheng 2018年5月3日 下午3:56:01
 * @see
 * @version 1.0
 */
public class P0x0500PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0500消息体编码失败，传入消息体实体对象为空。");
		}
		try{
			P0x0500 p = (P0x0500) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.putShort(p.getRespondNumber());
			P0x0200PB pb = new P0x0200PB();
			buf.put(pb.bodyToBytes(p.getPosition()));
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		}catch (Exception e) {
			throw new P905Exception("P0x0500编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 0 > data.length) {
			throw new P905Exception("P0x0500解析失败，消息长度不足。");
		}
		try{
			P0x0500 p = new P0x0500();
			byte[] respondNumber = new byte[2];
			System.arraycopy(data, 0, respondNumber, 0, 2);
			p.setRespondNumber(ConvUtil.bytes2Short(respondNumber));
			byte[] position = new byte[data.length - 2];
			System.arraycopy(data, 2, position, 0, data.length - 2);
			P0x0200PB pb = new P0x0200PB();
			p.setPosition((P0x0200)pb.bodyFromBytes(position));
			return p;
		}
		catch (Exception e) {
			throw new P905Exception("P0x0500编码失败:", e);
		}
	}


}
