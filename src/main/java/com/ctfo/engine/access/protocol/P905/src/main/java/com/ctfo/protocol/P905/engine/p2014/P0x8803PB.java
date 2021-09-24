package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8803;
import com.ctfo.protocol.support.exception.P905Exception;
import org.apache.mina.core.buffer.IoBuffer;

/**
 *存储图像/音视频上传命令消息编解码处理类<code>P0x8803PB</code>
 *
 * <p>
 * 消息ID: 0x8803
 * @author xiahancheng 2018年5月4日 下午5:16:58
 * @see
 * @version 1.0
 */
public class P0x8803PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8803消息体编码失败，传入消息体实体对象为空。");
		}
		try{
			P0x8803 p = (P0x8803) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getType());
			buf.putInt(p.getFileId());
			buf.putInt(p.getStartingAddress());
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		}catch (Exception e) {
			throw new P905Exception("P0x8803编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data) {
			throw new P905Exception("P0x8803消息体解码失败，消息长度不够。");
		}
		try {
			P0x8803 p = new P0x8803();
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(data);
			buf.flip();
			p.setType(buf.get());
			p.setFileId(buf.getInt());
			p.setStartingAddress(buf.getInt());
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x8803解码失败:", e);
		}
	}

}
