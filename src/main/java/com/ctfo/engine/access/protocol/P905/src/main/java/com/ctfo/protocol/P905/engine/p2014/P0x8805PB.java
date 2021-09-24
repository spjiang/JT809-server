package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8805;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 音频检索消息编解码处理类<code>P0x8805</code>
 * <p>
 * 消息ID：0x8805
 *
 *
 * @author fanya 2018年5月2日 下午5:22:46
 * @see
 * @version 1.0
 */
public class P0x8805PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8805消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x8805 p = (P0x8805) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getRecordings());
			String strat_date = DateUtil.formatToString(p.getStartTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(strat_date));
			String end_date = DateUtil.formatToString(p.getEndTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(end_date));
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x8805编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 13 != data.length) {
			throw new P905Exception("P0x8805消息体解码失败，消息长度必须为13字节。");
		}
		try {
			P0x8805 p = new P0x8805();
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(data);
			buf.flip();
			p.setRecordings(buf.get());
			byte[] stratTime = new byte[6]; // 起始时间
			byte[] endTime = new byte[6]; // 结束时间
			System.arraycopy(data, 1, stratTime, 0, 6);
			System.arraycopy(data, 7, endTime, 0, 6);
			String strat_time = "20" + ConvUtil.bytes2Bcd(stratTime);
			p.setStartTime(DateUtil.parseToDate(strat_time, "yyyyMMddHHmmss"));
			String end_time = "20" + ConvUtil.bytes2Bcd(endTime);
			p.setEndTime(DateUtil.parseToDate(end_time, "yyyyMMddHHmmss"));
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x8805解码失败:", e);
		}
	}

}

