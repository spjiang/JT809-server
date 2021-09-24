package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0806;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 音视频上传消息编解码处理类<code>P0x0806</code>
 * <p>
 * 消息ID：0x0806。
 *
 * <p>类详细说明
 * @author fanya 2018年5月3日 下午4:31:13
 * @see
 * @version 1.0
 */
public class P0x0806PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0806消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x0806 p = (P0x0806) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.putShort(p.getUploadSerial());
			buf.putInt(p.getAudioID());
			buf.putInt(p.getVideoDataSize());
			buf.putInt(p.getStartAddr());
			buf.put(ConvUtil.hex2Bytes(p.getDataPacket()));
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x0806编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 14 > data.length) {
			throw new P905Exception("P0x0806消息体解码失败，消息为空或者长度不足。");
		}
		try {
			P0x0806 p = new P0x0806();
			byte[] uploadSerial = new byte[2];
			byte[] audioID = new byte[4];
			byte[] videoDataSize = new byte[4];
			byte[] startAddr = new byte[4];
			System.arraycopy(data, 0, uploadSerial, 0, 2);
			System.arraycopy(data, 2, audioID, 0, 4);
			System.arraycopy(data, 6, videoDataSize, 0, 4);
			System.arraycopy(data, 10, startAddr, 0, 4);
			p.setUploadSerial(ConvUtil.bytes2Short(uploadSerial));
			p.setAudioID(ConvUtil.bytes2Int(audioID));
			p.setVideoDataSize(ConvUtil.bytes2Int(videoDataSize));
			p.setStartAddr(ConvUtil.bytes2Int(startAddr));
			byte[] dataPacket = new byte[data.length - 14];
			System.arraycopy(data, 14, dataPacket, 0, dataPacket.length);
			p.setDataPacket(ConvUtil.bytes2Hex(dataPacket));
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x0806解码失败:", e);
		}
	}

}

