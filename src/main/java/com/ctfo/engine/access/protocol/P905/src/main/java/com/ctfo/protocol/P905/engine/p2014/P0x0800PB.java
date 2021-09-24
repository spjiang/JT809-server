package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0800;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 摄像头图像上传消息体数据格式编解码处理类<code>P0x0800</code>
 *
 * <p>
 * 消息ID: 0x0800
 *
 * @author xiahancheng 2018年5月4日 上午9:10:08
 * @see
 * @version 1.0
 */
public class P0x0800PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0800消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x0800 p = (P0x0800) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.putShort(p.getUploadReason());
			buf.putInt(p.getImage());
			buf.put(p.getCameraId());
			buf.putInt(p.getDataSize());
			buf.putInt(p.getStartingAddress());
			buf.put(ConvUtil.hex2Bytes(p.getDataPacketHex()));
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;

		} catch (Exception e) {
			throw new P905Exception("P0x0800编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 15 > data.length) {
			throw new P905Exception("P0x0800消息体解码失败，消息为空或者长度不足。");
		}
		try {
			P0x0800 p = new P0x0800();
			byte[] uploadreason = new byte[2]; // 上传原因
			byte[] image = new byte[4]; // 图像
			byte id = data[6]; // 摄像头ID
			byte[] datasize = new byte[4]; // 位置图像数据大小
			byte[] startingaddress = new byte[4]; // 起始地址
			byte[] datapacket = new byte[data.length - 15]; // 位置图像数据包

			System.arraycopy(data, 0, uploadreason, 0, 2);
			System.arraycopy(data, 2, image, 0, 4);
			System.arraycopy(data, 7, datasize, 0, 4);
			System.arraycopy(data, 11, startingaddress, 0, 4);
			System.arraycopy(data, 15, datapacket, 0, datapacket.length);

			p.setUploadReason(ConvUtil.bytes2Short(uploadreason));
			p.setImage(ConvUtil.bytes2Int(image));
			p.setCameraId(id);
			p.setDataSize(ConvUtil.bytes2Int(datasize));
			p.setStartingAddress(ConvUtil.bytes2Int(startingaddress));
			p.setDataPacketHex(ConvUtil.bytes2Hex(datapacket));
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x0800编码失败:", e);
		}

	}

}
