package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;


import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8B11;
import com.ctfo.protocol.support.exception.P905Exception;

/**
 * 中心巡检消息编解码处理类<code>P0x8B11</code>
 * <p>
 * 消息ID：0x8B11
 *
 * @author fanya 2018年5月4日 下午3:06:31
 * @see
 * @version 1.0
 */
public class P0x8B11PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8B11消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x8B11 p = (P0x8B11) model;
			return p.getInspection();
		} catch (Exception e) {
			throw new P905Exception("P0x8B11编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 0 > data.length) {
			throw new P905Exception("P0x8B11消息体解码失败，消息为空或者长度不足。");
		}
		try {
			P0x8B11 p = new P0x8B11();
			p.setInspection(data);
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x8B11解码失败:", e);
		}
	}

}
