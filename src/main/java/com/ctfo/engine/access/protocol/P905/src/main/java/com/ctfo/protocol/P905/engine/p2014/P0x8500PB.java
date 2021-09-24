package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8500;
import com.ctfo.protocol.support.exception.P905Exception;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 车辆控制消息体数据格式编解码处理类<code>P0x8500</code>
 *
 * <p>
 * 消息ID: 0x8500
 *
 * @author xiahancheng 2018年5月3日 下午3:13:35
 * @see
 * @version 1.0
 */
public class P0x8500PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8500消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x8500 p = (P0x8500) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getControlItem());
			buf.put(p.getControlCommand());
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x8500编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 2 > data.length) {
			throw new P905Exception("P0x8400消息体解码失败，消息为空或者长度不足。");
		}
		try {
			P0x8500 p = new P0x8500();
			p.setControlItem(data[0]);
			p.setControlCommand(data[1]);
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x8500编码失败:", e);
		}
	}

}
