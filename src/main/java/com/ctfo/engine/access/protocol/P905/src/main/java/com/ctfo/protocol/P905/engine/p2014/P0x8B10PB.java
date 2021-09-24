package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8B10;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 外围设备指令下行透传消息编解码处理类<code>P0x8B10</code>
 *
 * <p>
 * 消息ID: 0x8B10
 *
 * @author xiahancheng 2018年5月7日 上午9:03:30
 * @see
 * @version 1.0
 */
public class P0x8B10PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8B10消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x8B10 p = (P0x8B10) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getTypeID());
			buf.putShort(p.getDataType());
			buf.put(ConvUtil.hex2Bytes(p.getDataPacket()));
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x8B10编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 3 > data.length) {
			throw new P905Exception("P0x8B10消息体解码失败，消息为空或者长度不足。");
		}
		try {
			P0x8B10 p = new P0x8B10();
			byte id = data[0];
			byte[] data_type = new byte[2];
			byte[] data_packet = new byte[data.length - 3];

			System.arraycopy(data, 1, data_type, 0, 2);
			System.arraycopy(data, 3, data_packet, 0, data_packet.length);

			p.setTypeID(id);
			p.setDataType(ConvUtil.bytes2Short(data_type));
			p.setDataPacket(ConvUtil.bytes2Hex(data_packet));
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x8B10编码失败:", e);
		}
	}

}
