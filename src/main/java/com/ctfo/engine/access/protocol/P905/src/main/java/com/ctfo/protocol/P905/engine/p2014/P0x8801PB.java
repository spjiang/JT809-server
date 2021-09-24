package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8801;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 摄像头立即拍摄命令消息体数据格式编解码处理类<code>P0x8801</code>
 *
 * <p>
 * 消息ID: 0x8801
 * @author xiahancheng 2018年5月4日 上午9:51:19
 * @see
 * @version 1.0
 */
public class P0x8801PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8801消息体编码失败，传入消息体实体对象为空。");
		}
		try{
			P0x8801 p = (P0x8801) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getCameraId());
			buf.putShort(p.getShootCommand());
			buf.putShort(p.getPhotographInterval());
			buf.put(p.getPreservationSign());
			buf.put(p.getResolutionRatio());
			buf.put(p.getImage());
			buf.put(p.getBrightness());
			buf.put(p.getContrastRatio());
			buf.put(p.getSaturation());
			buf.put(p.getChroma());
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		}catch (Exception e) {
			throw new P905Exception("P0x8801编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 0 > data.length) {
			throw new P905Exception("P0x8801解析失败，消息为空或者长度不足。");
		}
		P0x8801 p = new P0x8801();
		try{
			int pos = 0;
			p.setCameraId(data[pos]);
			pos++;
			byte[] ShootCommand = new byte[2];
			System.arraycopy(data, pos, ShootCommand, 0, 2);
			p.setPhotographInterval(ConvUtil.bytes2Short(ShootCommand));
			pos += 2;
			byte[] PhotographInterval = new byte[2];
			System.arraycopy(data, pos, PhotographInterval, 0, 2);
			p.setPhotographInterval(ConvUtil.bytes2Short(PhotographInterval));
			pos += 2;
			p.setPreservationSign(data[pos]);
			pos++;
			p.setResolutionRatio(data[pos]);
			pos++;
			p.setImage(data[pos]);
			pos++;
			p.setBrightness(data[pos]);
			pos++;
			p.setContrastRatio(data[pos]);
			pos++;
			p.setSaturation(data[pos]);
			pos++;
			p.setChroma(data[pos]);
			return p;
		}
		catch (Exception e) {
			throw new P905Exception("P0x8801编码失败:", e);
		}
	}


}
