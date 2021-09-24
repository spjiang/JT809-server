package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8301;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 事件设置消息体数据格式编解码处理类<code>P0x8301</code>
 *
 * <p>
 * 消息ID: 0x8301
 * @author xiahancheng 2018年5月2日 下午2:00:44
 * @see
 * @version 1.0
 */
public class P0x8301PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8301消息体编码失败，传入消息体实体对象为空。");
		}
		try{
			P0x8301 p = (P0x8301) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getEventNumber());
			if(p.getEventNumber() > 0) {
				Map<Byte, String> eventItems = p.getEventItems();
				for (Entry<Byte, String> e : eventItems.entrySet()) {
					buf.put(e.getKey()); // 事件ID
					buf.put(ConvUtil.string2Bytes(e.getValue())); // 事件内容
					buf.put((byte)0);
				}
			}
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		}catch (Exception e) {
			throw new P905Exception("P0x8301编码失败:", e);
		}

	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if ( null == data || 0 >= data.length) {
			throw new P905Exception("P0x8301消息体编码失败，传入消息体实体对象为空。");
		}
		try{
			P0x8301 p = new P0x8301();
			p.setEventNumber(data[0]);
			if (data[0] == 0) {
				return p;
			}
			byte[] events = new byte[data.length - 1]; // 事件项内容
			System.arraycopy(data, 1, events, 0, events.length);

			Map<Byte, String> eventItemMap = new HashMap<Byte, String>();
			int pos = 0;
			for (int i = 0; i < events.length; i++) {
				if (events[i] == 0) {
					byte[] event = new byte[i - pos]; // 一个事件项
					System.arraycopy(events,pos, event, 0, event.length);
					byte[] eventContent = new byte[event.length - 1];
					System.arraycopy(event,1, eventContent, 0, eventContent.length);
					eventItemMap.put(event[0], ConvUtil.bytes2String(eventContent));
					pos += event.length +1;
				}
			}
			p.setEventItems(eventItemMap);
			return p;
		}catch (Exception e) {
			throw new P905Exception("P0x8301编码失败:", e);
		}
	}


}
