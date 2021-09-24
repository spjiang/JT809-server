package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0301;
import com.ctfo.protocol.support.exception.P905Exception;

/**
 * 事件报告消息体数据格式编解码处理类<code>P0x0301</code>
 *
 * <p>事件ID 0x0301
 *
 *
 * @author xiahancheng 2018年5月2日 下午11:07:29
 * @see
 * @version 1.0
 */
public class P0x0301PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0301消息体编码失败，传入消息体实体对象为空。");
		}
		try{
			P0x0301 p = (P0x0301) model;
			byte[] b = new byte[1];
			b[0] = p.getEventId();
			return b;
		}catch (Exception e) {
			throw new P905Exception("P0x0001编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 0 > data.length) {
			throw new P905Exception("P0x0301消息体解码失败，消息为空或者长度不足。");
		}
		try{
			P0x0301 p = new P0x0301();
			p.setEventId(data[0]);
			return p;
		}catch (Exception e) {
			throw new P905Exception("P0x0301解码失败:", e);
		}
	}


}
