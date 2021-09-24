package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0805;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储音频检索消息编解码处理类<code>P0x0805</code>
 * <p>
 * 消息ID：0x0805。
 *
 *
 * @author fanya 2018年5月2日 下午6:18:55
 * @see
 * @version 1.0
 */
public class P0x0805PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0805消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x0805 p = (P0x0805) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.putShort(p.getResponse());
			buf.putInt(p.getSearchDataSize());
			buf.putInt(p.getCurrentSearchDataOffset());
			List<Integer> search = p.getSearch();
			for (Integer integer : search) {
				buf.putInt(integer);
			}
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x0805编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 10 > data.length) {
			throw new P905Exception("P0x0805消息体解码失败，消息为空或者长度不足。");
		}
		try {
			P0x0805 p = new P0x0805();
			int pos = 0;
			byte[] response = new byte[2];
			System.arraycopy(data, pos, response, 0, 2);
			p.setResponse(ConvUtil.bytes2Short(response));
			pos += 2;
			byte[] searchDataSize = new byte[4];
			System.arraycopy(data, pos, searchDataSize, 0, 4);
			p.setSearchDataSize(ConvUtil.bytes2Int(searchDataSize));
			pos += 4;
			byte[] currentSearchDataOffset = new byte[4];
			System.arraycopy(data, pos, currentSearchDataOffset, 0, 4);
			p.setCurrentSearchDataOffset(ConvUtil.bytes2Int(currentSearchDataOffset));
			pos += 4;
			List<Integer> search = new ArrayList<Integer>();
			int dataLen = data.length;
			while (pos < dataLen) {
				byte[] searchB = new byte[4];
				System.arraycopy(data, pos, searchB, 0, 4);
				search.add(ConvUtil.bytes2Int(searchB));
				pos += 4;
			}
			p.setSearch(search);
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x0805解码失败:", e);
		}
	}

}
