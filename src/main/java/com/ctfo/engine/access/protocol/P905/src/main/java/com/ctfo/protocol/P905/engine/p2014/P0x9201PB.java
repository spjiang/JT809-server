package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x9201;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 *
 * 平台下发远程录像回放请求 解码处理类
 *
 * <p>类详细说明
 * @author zx 2018年9月28日 上午10:52:15
 * @version 1.0
 */
public class P0x9201PB implements IPassBody {

	@Override
	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if(null == model) {
			throw new P905Exception("P0x9201消息体转码失败，传入的消息体实体对象为空。");
		}
		try {
			P0x9201 p = (P0x9201) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getServerIpLength());//服务器IP地址长度
			buf.put(ConvUtil.string2Bytes(p.getServerIp()));//服务器IP地址
			buf.putShort(p.getServerTcpPort());//服务器音视频通道监听端口号（TCP）
			buf.putShort(p.getServerUdpPort());//服务器音视频通道监听端口号（UDP）
			buf.put(p.getCameraChannelNumber());//摄像头通道号
			buf.put(p.getAudioVideoType());//音视频类型
			buf.put(p.getCodeStreamType());//码流类型
			buf.put(p.getMemoryType());//存储器类型
			buf.put(p.getReplayMode());//回放方式
			buf.put(p.getFastForwardOrFastMultiple());//快进或快退倍数
			String startTime = DateUtil.formatToString(p.getStartTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(startTime));//开始时间
			String endTime = DateUtil.formatToString(p.getEndTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(endTime));//结束时间
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x9201编码失败:", e);
		}
	}

	@Override
	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if(null == data|| 0 > data.length) {
			throw new P905Exception("P0x9201解析失败，消息长度不足。");
		}
		try {
			P0x9201 p = new P0x9201();
			int pos = 0;
			//服务器IP地址长度
			p.setServerIpLength(data[pos]);
			pos ++;
			//服务器ip地址
			byte[] serverIp = new byte[p.getServerIpLength()];
			System.arraycopy(data, pos, serverIp, 0, p.getServerIpLength());
			p.setServerIp(ConvUtil.bytes2String(serverIp));
			pos += p.getServerIpLength();
			//服务器视频通道监听端口号（TCP）
			byte[] serverTcpPort = new byte[2];
			System.arraycopy(data, pos, serverTcpPort, 0, 2);
			p.setServerTcpPort(ConvUtil.bytes2Short(serverTcpPort));
			pos += 2;
			//服务器视频通道监听端口号（UDP）
			byte[] serverUdpPort = new byte[2];
			System.arraycopy(data, pos, serverUdpPort, 0, 2);
			p.setServerUdpPort(ConvUtil.bytes2Short(serverUdpPort));
			pos += 2;
			//摄像头通道号
			p.setCameraChannelNumber(data[pos]);
			pos ++;
			//音视频类型
			p.setAudioVideoType(data[pos]);
			pos ++;
			//码流类型
			p.setCodeStreamType(data[pos]);
			pos ++;
			//存储器类型
			p.setMemoryType(data[pos]);
			pos ++;
			//回放方式
			p.setReplayMode(data[pos]);
			pos ++;
			//快进或快退倍数
			p.setFastForwardOrFastMultiple(data[pos]);
			pos ++;
			//开始时间
			byte[] startTime = new byte[6];
			System.arraycopy(data, pos, startTime, 0, 6);
			String startTimeStr = "20" + ConvUtil.bytes2Bcd(startTime);
			p.setStartTime(DateUtil.parseToDate(startTimeStr, "yyyyMMddHHmmss"));
			pos += 6;
			//结束时间
			byte[] endTime = new byte[6];
			System.arraycopy(data, pos, endTime, 0, 6);
			String endTimeStr = "20" + ConvUtil.bytes2Bcd(endTime);
			p.setStartTime(DateUtil.parseToDate(endTimeStr, "yyyyMMddHHmmss"));
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x9201编码失败:", e);
		}
	}

}
