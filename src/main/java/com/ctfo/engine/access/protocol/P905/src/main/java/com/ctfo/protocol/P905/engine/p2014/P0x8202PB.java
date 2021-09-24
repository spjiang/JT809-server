package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8202;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 位置跟踪控制消息编解码处理类，数据格式定义<code>P0x8202</code>
 *
 * <p>
 * 消息ID:0x8202
 *
 * @author 凉意 2018年5月2日 下午2:35:59
 * @version 1.0
 */
public class P0x8202PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8202消息体编码失败，传入消息体实体对象为空。");
		}
		P0x8202 p = (P0x8202) model;
		try {
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getAttr());
			buf.putShort(p.getInterval());
			buf.putInt(p.getContinued());
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x8202编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 0 >= data.length) {
			throw new P905Exception("P0x8202消息体解码失败，消息为空或者长度不足。");
		}
		P0x8202 p = new P0x8202();
		try {
			int pos = 0;
			p.setAttr(data[pos]);
			pos ++;
			byte[] interval = new byte[2];
			System.arraycopy(data, pos, interval, 0, 2);
			p.setInterval(ConvUtil.bytes2Short(interval));
			pos += 2;
			byte[] continued = new byte[4];
			System.arraycopy(data, pos, continued, 0, 4);
			p.setContinued(ConvUtil.bytes2Int(continued));
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x8202解码失败:", e);
		}
	}

}
