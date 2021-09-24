package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0105;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 智能终端升级结果报告消息编解码处理类<code>P0x0105</code>
 *
 * <p>
 * 消息ID：0x0105
 *
 * @author 凉意 2018年5月2日 上午10:41:53
 * @see
 * @version 1.0
 */
public class P0x0105PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0105消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x0105 p = (P0x0105) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getDeviceType());
			buf.put(p.getFirmFlag());
			buf.put(ConvUtil.bcd2Bytes(p.getHardwareVesion()));
			buf.put(ConvUtil.bcd2Bytes(p.getSoftwareVesion()));
			buf.put(p.getResult());
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x0105编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 1 > data.length) {
			throw new P905Exception("P0x0105消息体解码失败，消息为空或者长度不足。");
		}
		try {
			P0x0105 p = new P0x0105();
			int pos = 0;
			p.setDeviceType(data[pos]);
			pos ++;
			p.setFirmFlag(data[pos]);
			pos ++;
			p.setHardwareVesion(ConvUtil.byte2Bcd(data[pos]));
			pos ++;
			byte[] softwareVesion = new byte[2];
			System.arraycopy(data, pos, softwareVesion, 0, 2);
			p.setSoftwareVesion(ConvUtil.bytes2Bcd(softwareVesion));
			pos += 2;
			p.setResult(data[pos]);
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x0105解码失败:", e);
		}
	}

}
