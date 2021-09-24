package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8300;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 文本信息消息体数据格式
 *
 * <p>类详细说明
 * @author xiahancheng 2018年5月2日 上午10:32:25
 * @see
 * @version 1.0
 */
public class P0x8300PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if(null == model){
			throw new P905Exception("P0x8300消息体编码失败，传入消息体实体对象为空。");
		}
		try{
			P0x8300 p = (P0x8300) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getSign());
			buf.put(ConvUtil.string2Bytes(p.getTextInformation()));
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		}
		catch (Exception e) {
			throw new P905Exception("P0x8300编码失败:", e);
		}

	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 1 > data.length) {
			throw new P905Exception("P0x8300消息体解码失败，消息为空或者长度不足。");
		}
		try{
			P0x8300 p = new P0x8300();
			p.setSign(data[0]);
			byte[] textInformation = new byte[data.length - 1];
			System.arraycopy(data, 1, textInformation, 0, textInformation.length);
			p.setTextInformation(ConvUtil.bytes2String(textInformation));
			return p;
		}
		catch (Exception e) {
			throw new P905Exception("P0x8300编码失败:", e);
		}

	}

}
