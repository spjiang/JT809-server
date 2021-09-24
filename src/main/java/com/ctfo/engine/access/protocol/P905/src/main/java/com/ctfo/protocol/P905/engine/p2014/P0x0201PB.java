package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0200;
import com.ctfo.protocol.P905.model.p2014.P0x0201;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 位置信息查询应答消息体编解码处理类，数据格式定义参考<code>P0x0201</code>
 *
 * <p>
 * 消息ID：0x0201
 *
 * @author 凉意 2018年5月18日 下午1:39:56
 * @see
 * @version 1.0
 */
public class P0x0201PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0201编码失败,传入消息对象为空。");
		}
		try {
			P0x0201 p = (P0x0201) model;
			P0x0200PB pb = new P0x0200PB();
			IoBuffer buf = IoBuffer.allocate(25).setAutoExpand(true);
			buf.putShort(p.getAnsFlowNo());
			buf.put(pb.bodyToBytes(p.getPosition()));
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x0201编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 0 >= data.length) {
			throw new P905Exception("P0x0201解析失败，消息长度不足25字节。");
		}
		try {
			P0x0201 p = new P0x0201();
			P0x0200PB pb = new P0x0200PB();
			int pos = 0;
			byte[] ansFlowNo = new byte[2];
			System.arraycopy(data, pos, ansFlowNo, 0, 2);
			p.setAnsFlowNo(ConvUtil.bytes2Short(ansFlowNo));
			pos += 2;
			byte[] position = new byte[data.length - pos];
			System.arraycopy(data, pos, position, 0, position.length);
			p.setPosition((P0x0200) pb.bodyFromBytes(position));
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x0201解码失败:", e);
		}
	}

}
