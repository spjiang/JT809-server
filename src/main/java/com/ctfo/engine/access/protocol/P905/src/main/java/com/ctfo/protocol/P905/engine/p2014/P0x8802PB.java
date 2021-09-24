package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8802;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 *存储图像检索命令消息编解码处理类<code>P0x8802</code>
 *
 * <p>
 * 消息ID: 0x8802
 * @author xiahancheng 2018年5月4日 上午11:20:49
 * @see
 * @version 1.0
 */
public class P0x8802PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8802消息体编码失败，传入消息体实体对象为空。");
		}
		try{
			P0x8802 p = (P0x8802) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getCameraId());
			buf.put(p.getPhotographReason());
			String strat_date = DateUtil.formatToString(p.getStartTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(strat_date));
			String end_date = DateUtil.formatToString(p.getEndTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(end_date));
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;

		}catch (Exception e) {
			throw new P905Exception("P0x8802编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 14 != data.length) {
			throw new P905Exception("P0x8802消息体解码失败，消息为空或者长度不足。");
		}
		try{
			P0x8802 p = new P0x8802();
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(data);
			buf.flip();
			p.setCameraId(buf.get());
			p.setPhotographReason(buf.get());
			byte[] stratTime = new byte[6]; // 起始时间
			byte[] endTime = new byte[6]; // 结束时间
			System.arraycopy(data, 1, stratTime, 0, 6);
			System.arraycopy(data, 7, endTime, 0, 6);
			String strat_time = "20" + ConvUtil.bytes2Bcd(stratTime);
			p.setStartTime(DateUtil.parseToDate(strat_time, "yyyyMMddHHmmss"));
			String end_time = "20" + ConvUtil.bytes2Bcd(endTime);
			p.setEndTime(DateUtil.parseToDate(end_time, "yyyyMMddHHmmss"));
			return p;
		}
		catch (Exception e) {
			throw new P905Exception("P0x8802编码失败:", e);
		}
	}


}
