package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0802;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储图像应答消息编解码处理类<code>P0x0802</code>
 *
 * <p>
 * 消息ID: 0x0802
 *
 * @author xiahancheng 2018年5月4日 下午4:14:49
 * @see
 * @version 1.0
 */
public class P0x0802PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0802消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x0802 p = (P0x0802) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.putShort(p.getAnswerNumber());
			buf.putInt(p.getSearchDataSize());
			buf.putInt(p.getCurrentSearchDataOffset());
			List<Integer> searchItems = p.getSearchItems();
			for (Integer item : searchItems) {
				buf.putInt(item);
			}
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x0802编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 10 > data.length) {
			throw new P905Exception("P0x0802消息体解码失败，消息为空或者长度不足。");
		}
		try {
			P0x0802 p = new P0x0802();
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(data);
			buf.flip();
			p.setAnswerNumber(buf.getShort());
			byte[] searchDataSize = new byte[4];
			byte[] currentSearchDataOffset = new byte[4];
			System.arraycopy(data, 2, searchDataSize, 0, 4);
			System.arraycopy(data, 6, currentSearchDataOffset, 0, 4);
			p.setSearchDataSize(ConvUtil.bytes2Int(searchDataSize));
			p.setCurrentSearchDataOffset(ConvUtil.bytes2Int(currentSearchDataOffset));
			byte[] search = new byte[data.length - 10];
			System.arraycopy(data, 10, search, 0, search.length);
			List<Integer> searchItems = new ArrayList<Integer>();
			int pos = 0;
			while (pos < search.length) {
				byte[] item = new byte[4];
				System.arraycopy(search, pos, item, 0, 4);
				searchItems.add(ConvUtil.bytes2Int(item));
				pos += 4;
			}
			p.setSearchItems(searchItems);
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x0802解码失败:", e);
		}
	}

}
