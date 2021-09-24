package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8104;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 终端参数查询消息编解码处理类，格式定义参考<code>P0x8104</code>
 *
 * <p>
 * 消息ID：0x8104
 *
 * @author 凉意 2018年4月26日 下午3:00:03
 * @see
 * @version 1.0
 */
public class P0x8104PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8104消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			P0x8104 p = (P0x8104) model;
			List<String> paramIds = p.getParamIds();
			for (String idHex : paramIds) {
				buf.put(ConvUtil.hex2Bytes(idHex));
			}
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x8104编码失败:", e);
		}

	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || data.length < 2) {
			throw new P905Exception("P0x8104消息体解码失败，消息长为空或者消息长度不足。");
		}
		try {
			int dataLen = data.length;
			P0x8104 p = new P0x8104();
			List<String> paramIds = new ArrayList<String>();
			for (int i = 0; i < dataLen; i = i + 2) {
				byte[] id = new byte[2];
				System.arraycopy(data, i, id, 0, 2);
				paramIds.add(ConvUtil.bytes2Hex(id));
			}
			p.setParamIds(paramIds);
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x8104解码失败:", e);
		}
	}

}
