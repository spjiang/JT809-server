package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x9206;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 *
 * 文件上传指令 解码处理类
 *
 * <p>类详细说明
 * @author zx 2018年9月28日 下午3:25:10
 * @version 1.0
 */
public class P0x9206PB implements IPassBody {

	@Override
	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if(null == model) {
			throw new P905Exception("P0x9206消息体转码失败，传入的消息体实体对象为空。");
		}
		try {
			P0x9206 p = (P0x9206) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getServerIpLength());//服务器地址长度
			buf.put(ConvUtil.string2Bytes(p.getServerIp()));//服务器地址
			buf.putShort(p.getPort());//端口号
			buf.put(p.getUserNameLength());//用户名长度
			buf.put(ConvUtil.string2Bytes(p.getUserName()));//用户名
			buf.put(p.getPassWordLength());//密码长度
			buf.put(ConvUtil.string2Bytes(p.getPassWord()));//密码
			buf.put(p.getDataUploadUrlLength());//文件上传路径长度
			buf.put(ConvUtil.string2Bytes(p.getDataUploadUrl()));//文件上传路径
			buf.put(p.getLogicalChannel());//逻辑通道号
			String startTime = DateUtil.formatToString(p.getStartTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(startTime));//开始时间
			String endTime = DateUtil.formatToString(p.getEndTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(endTime));//结束时间
			buf.putLong(p.getAlarmSign());//报警标志
			buf.put(p.getAudioVideoType());//音视频资源类型
			buf.put(p.getCodeStreamType());//码流类型
			buf.put(p.getMemorLocation());//存储位置
			buf.put(p.getTaskExecutionConditions());//任务执行条件
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x9206编码失败:", e);
		}
	}

	@Override
	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if(null == data|| 0 > data.length) {
			throw new P905Exception("P0x9206解析失败，消息长度不足。");
		}
		try {
			P0x9206 p = new P0x9206();
			int pos = 0;
			//服务器地址长度
			p.setServerIpLength(data[pos]);
			pos ++;
			//服务器地址
			byte[] serverIp = new byte[p.getServerIpLength()];
			System.arraycopy(data, pos, serverIp, 0, p.getServerIpLength());
			p.setServerIp(ConvUtil.bytes2String(serverIp));
			pos += p.getServerIpLength();
			//端口号
			byte[] port = new byte[2];
			System.arraycopy(data, pos, port, 0, 2);
			p.setPort(ConvUtil.bytes2Short(port));
			pos += 2;
			//用户名长度
			p.setUserNameLength(data[pos]);
			pos += 1;
			//用户名
			byte[] userName = new byte[p.getUserNameLength()];
			System.arraycopy(data, pos, userName, 0, p.getUserNameLength());
			p.setUserName(ConvUtil.bytes2String(userName));
			pos += p.getUserNameLength();
			//密码长度
			p.setPassWordLength(data[pos]);
			pos += 1;
			//密码
			byte[] passWord = new byte[p.getPassWordLength()];
			System.arraycopy(data, pos, passWord, 0, p.getPassWordLength());
			p.setPassWord(ConvUtil.bytes2String(passWord));
			pos += p.getPassWordLength();
			//文件上传路径长度
			p.setDataUploadUrlLength(data[pos]);
			pos += 1;
			//文件上传路径
			byte[] dataUploadUrl = new byte[p.getDataUploadUrlLength()];
			System.arraycopy(data, pos, dataUploadUrl, 0, p.getDataUploadUrlLength());
			p.setDataUploadUrl(ConvUtil.bytes2String(dataUploadUrl));
			pos += p.getDataUploadUrlLength();
			//逻辑通道号
			p.setLogicalChannel(data[pos]);
			pos += 1;
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
			pos += 6;
			//报警标志
			byte[] alarmSign = new byte[8];
			System.arraycopy(data, pos, alarmSign, 0, 8);
			p.setAlarmSign(ConvUtil.bytes2Long(alarmSign));
			pos += 8;
			//音视频资源类型
			p.setAudioVideoType(data[pos]);
			pos += 1;
			//码流类型
			p.setCodeStreamType(data[pos]);
			pos += 1;
			//存储位置
			p.setMemorLocation(data[pos]);
			pos += 1;
			//任务执行条件
			p.setTaskExecutionConditions(data[pos]);
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x9206编码失败:", e);
		}
	}

}
