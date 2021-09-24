package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x9208;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 *
 * 远程事件录像操作 解码处理类
 *
 * <p>
 * 类详细说明
 *
 * @author zx 2018年9月28日 下午5:53:49
 * @version 1.0
 */
public class P0x9208PB implements IPassBody {

	@Override
	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x9208消息体转码失败，传入的消息体实体对象为空。");
		}
		try {
			P0x9208 p = (P0x9208) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			String videoStartTime = DateUtil.formatToString(p.getVideoStartTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(videoStartTime));// 开始时间
			String videoEndTime = DateUtil.formatToString(p.getVideoEndTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(videoEndTime));// 结束时间
			buf.put(p.getLogicalChannel());// 逻辑通道编号
			buf.put(p.getVideoOperation());// 录像操作
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x9208编码失败:", e);
		}
	}

	@Override
	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 0 > data.length) {
			throw new P905Exception("P0x9208解析失败，消息长度不足。");
		}
		try {
			P0x9208 p = new P0x9208();
			int pos = 0;
			// 开始时间
			byte[] videoStartTime = new byte[6];
			System.arraycopy(data, pos, videoStartTime, 0, 6);
			String videoStartTimeStr = "20" + ConvUtil.bytes2Bcd(videoStartTime);
			p.setVideoStartTime(DateUtil.parseToDate(videoStartTimeStr, "yyMMddHHmmss"));
			pos += 6;
			// 结束时间
			byte[] videoEndTime = new byte[6];
			System.arraycopy(data, pos, videoEndTime, 0, 6);
			String videoEndTimeStr = "20" + ConvUtil.bytes2Bcd(videoEndTime);
			p.setVideoEndTime(DateUtil.parseToDate(videoEndTimeStr, "yyMMddHHmmss"));
			pos += 6;
			// 逻辑通道编号
			p.setLogicalChannel(data[pos]);
			pos += 1;
			// 录像操作
			p.setVideoOperation(data[pos]);
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x9201编码失败:", e);
		}
	}

}
