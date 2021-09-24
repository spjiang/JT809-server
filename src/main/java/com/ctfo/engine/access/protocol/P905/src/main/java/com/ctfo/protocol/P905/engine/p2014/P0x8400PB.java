package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8400;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 电话回拨消息体数据格式编解码处理类<code>P0x8400</code>
 *
 * <p>
 * 消息ID: 0x8400
 *
 *
 * @author xiahancheng 2018年5月3日 上午10:02:28
 * @see
 * @version 1.0
 */
public class P0x8400PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8400消息体编码失败，传入消息体实体对象为空。");
		}
		try{
			P0x8400 p = (P0x8400) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getSign());
			buf.put(ConvUtil.string2Bytes(p.getPhoneNumber()));
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		}catch (Exception e) {
			throw new P905Exception("P0x8400编码失败:", e);
		}

	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 1 > data.length) {
			throw new P905Exception("P0x8400消息体解码失败，消息为空或者长度不足。");
		}
		try{
			P0x8400 p = new P0x8400();
			p.setSign(data[0]);
			byte[] phoneNumber = new byte[data.length - 1];
			System.arraycopy(data, 1, phoneNumber, 0, phoneNumber.length);
			p.setPhoneNumber(ConvUtil.bytes2String(phoneNumber));
			return p;
		}catch (Exception e) {
			throw new P905Exception("P0x8400编码失败:", e);
		}
	}


}
