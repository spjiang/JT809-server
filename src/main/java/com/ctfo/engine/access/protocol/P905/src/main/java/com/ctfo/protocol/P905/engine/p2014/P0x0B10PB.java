package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0B10;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 外围设备指令下行透传消息编解码处理类<code>P0x0B10</code>
 *
 * <p>
 * 消息: P0x0B10
 *
 *
 * @author xiahancheng 2018年5月7日 上午9:30:50
 * @see
 * @version 1.0
 */
public class P0x0B10PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0B10消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x0B10 p = (P0x0B10) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getTypeID());
			buf.put(p.getQuotientIdentification());
			buf.putShort(p.getCommandType());
			buf.put(ConvUtil.hex2Bytes(p.getDataPacket()));
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x0B10编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 4 > data.length) {
			throw new P905Exception("P0x0B10消息体解码失败，消息为空或者长度不足。");
		}
		try {
			P0x0B10 p = new P0x0B10();
			byte id = data[0];
			byte quotient = data[1];
			byte[] command_Type = new byte[2];
			byte[] data_packet = new byte[data.length - 4];

			System.arraycopy(data, 2, command_Type, 0, 2);
			System.arraycopy(data, 4, data_packet, 0, data_packet.length);

			p.setTypeID(id);
			p.setQuotientIdentification(quotient);
			p.setCommandType(ConvUtil.bytes2Short(command_Type));
			p.setDataPacket(ConvUtil.bytes2Hex(data_packet));
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x0B10解码失败:", e);
		}
	}

}
