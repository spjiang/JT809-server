package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x9205;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 *
 * 查询资源列表 解码处理类
 *
 * <p>
 * 类详细说明
 *
 * @author zx 2018年9月27日 下午7:22:14
 * @see
 * @version 1.0
 */
public class P0x9205PB implements IPassBody {

	@Override
	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x9205消息体转码失败，传入的消息体实体对象为空。");
		}
		try {
			P0x9205 p = (P0x9205) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getCameraChannelNumber());// 摄像头通道号
			String startTime = DateUtil.formatToString(p.getStartTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(startTime));// 开始时间
			String endTime = DateUtil.formatToString(p.getEndTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(endTime));// 结束时间
			buf.putLong(p.getAlarmSign());// 报警标志
			buf.put(p.getAudioVideoType());// 音视频资源类型
			buf.put(p.getCodeStreamType());// 码流类型
			buf.put(p.getMemoryType());// 存储器类型
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x9205编码失败:", e);
		}
	}

	@Override
	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 0 > data.length) {
			throw new P905Exception("P0x9205解析失败，消息长度不足。");
		}
		try {
			P0x9205 p = new P0x9205();
			int pos = 0;
			// 摄像头通道号
			p.setCameraChannelNumber(data[pos]);
			pos++;
			// 开始时间
			byte[] startTime = new byte[6];
			System.arraycopy(data, pos, startTime, 0, 6);
			String startTimeStr = "20" + ConvUtil.bytes2Bcd(startTime);
			p.setStartTime(DateUtil.parseToDate(startTimeStr, "yyyyMMddHHmmss"));
			pos += 6;
			// 结束时间
			byte[] endTime = new byte[6];
			System.arraycopy(data, pos, endTime, 0, 6);
			String endTimeStr = "20" + ConvUtil.bytes2Bcd(endTime);
			p.setStartTime(DateUtil.parseToDate(endTimeStr, "yyyyMMddHHmmss"));
			pos += 6;
			// 报警标志
			byte[] alarmSign = new byte[8];
			System.arraycopy(data, pos, alarmSign, 0, 8);
			p.setAlarmSign(ConvUtil.bytes2Long(alarmSign));
			pos += 8;
			// 音视频资源类型
			p.setAudioVideoType(data[pos]);
			pos++;
			// 码流类型
			p.setCodeStreamType(data[pos]);
			pos++;
			// 存储器类型
			p.setMemoryType(data[pos]);
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x9205编码失败:", e);
		}
	}

}
