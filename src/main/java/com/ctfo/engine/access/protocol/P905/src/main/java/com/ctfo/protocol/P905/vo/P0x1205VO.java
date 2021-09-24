package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.vo;

import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 终端上传音视频资源列表 - 音视频资源列表 消息体数据格式Model定义
 *
 * <p>
 * 类详细说明
 *
 * @author zx 2018年9月28日 上午9:49:15
 * @see
 * @version 1.0
 */
public class P0x1205VO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 0 逻辑通道号 BYTE 参见A.6音视频通道定义表
	 */
	private byte logicalChannel;

	/**
	 * 1 开始时间 BCD[6] YY-MM-DD-HH-MM-SS
	 */
	private Date startTime;
	/**
	 * 7 结束时间 BCD[6] YY-MM-DD-HH-MM-SS
	 */
	private Date endTime;
	/**
	 * 13 报警标志 64BITS bit0～bit31见JT/T905 表20 报警标志位定义，bit32～bit63见10.2.5
	 * 特殊报警标识位定义表；
	 */
	private long alarmSign;
	/**
	 * 21 音视频资源类型 BYTE 0：音视频，1：音频，2：视频
	 */
	private byte audioVideoType;
	/**
	 * 22 码流类型 BYTE 1：主码流，2：子码流
	 */
	private byte codeStreamType;
	/**
	 * 23 存储器类型 BYTE 保留为0
	 */
	private byte memoryType;
	/**
	 * 24 文件大小 DWORD 单位字节（BYTE）
	 */
	private int dataSize;

	public byte[] bodyToBytes() throws P905Exception {
		IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
		buf.put(this.logicalChannel);
		buf.put(ConvUtil.bcd2Bytes(DateUtil.formatToString(this.startTime, "yyMMddHHmmss")));
		buf.put(ConvUtil.bcd2Bytes(DateUtil.formatToString(this.endTime, "yyMMddHHmmss")));
		buf.putLong(this.alarmSign);
		buf.put(this.audioVideoType);
		buf.put(this.codeStreamType);
		buf.put(this.memoryType);
		buf.putInt(this.dataSize);
		buf.flip();
		byte[] b = new byte[buf.limit()];
		buf.get(b);
		return b;
	}

	public void bodyFromBytes(byte[] data) throws P905Exception {
		int pos = 0;
		this.logicalChannel = data[pos];
		pos ++;
		byte[] startTime = new byte[6];
		System.arraycopy(data, pos, startTime, 0, 6);
		this.startTime = DateUtil.parseToDate(ConvUtil.bytes2Bcd(startTime), "yyMMddHHmmss");
		pos += 6;
		byte[] endTime = new byte[6];
		System.arraycopy(data, pos, endTime, 0, 6);
		this.endTime = DateUtil.parseToDate(ConvUtil.bytes2Bcd(endTime), "yyMMddHHmmss");
		pos += 6;
		byte[] alarmSign = new byte[8];
		System.arraycopy(data, pos, alarmSign, 0, 8);
		this.alarmSign = ConvUtil.bytes2Long(alarmSign);
		pos += 8;
		this.audioVideoType = data[pos];
		pos ++;
		this.codeStreamType = data[pos];
		pos ++;
		this.memoryType = data[pos];
		pos ++;
		byte[] dataSize = new byte[4];
		System.arraycopy(data, pos, dataSize, 0, 4);
		this.dataSize = ConvUtil.bytes2Int(dataSize);
	}

	/**
	 * @return the logicalChannel
	 */
	public byte getLogicalChannel() {
		return logicalChannel;
	}

	/**
	 * @param logicalChannel
	 *            the logicalChannel to set
	 */
	public void setLogicalChannel(byte logicalChannel) {
		this.logicalChannel = logicalChannel;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the alarmSign
	 */
	public long getAlarmSign() {
		return alarmSign;
	}

	/**
	 * @param alarmSign
	 *            the alarmSign to set
	 */
	public void setAlarmSign(long alarmSign) {
		this.alarmSign = alarmSign;
	}

	/**
	 * @return the audioVideoType
	 */
	public byte getAudioVideoType() {
		return audioVideoType;
	}

	/**
	 * @param audioVideoType
	 *            the audioVideoType to set
	 */
	public void setAudioVideoType(byte audioVideoType) {
		this.audioVideoType = audioVideoType;
	}

	/**
	 * @return the codeStreamType
	 */
	public byte getCodeStreamType() {
		return codeStreamType;
	}

	/**
	 * @param codeStreamType
	 *            the codeStreamType to set
	 */
	public void setCodeStreamType(byte codeStreamType) {
		this.codeStreamType = codeStreamType;
	}

	/**
	 * @return the memoryType
	 */
	public byte getMemoryType() {
		return memoryType;
	}

	/**
	 * @param memoryType
	 *            the memoryType to set
	 */
	public void setMemoryType(byte memoryType) {
		this.memoryType = memoryType;
	}

	/**
	 * @return the dataSize
	 */
	public int getDataSize() {
		return dataSize;
	}

	/**
	 * @param dataSize
	 *            the dataSize to set
	 */
	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("P0x1205VO [");
		buf.append("logicalChannel=").append(this.logicalChannel).append(",");
		buf.append("startTime=").append(this.startTime).append(",");
		buf.append("endTime=").append(this.endTime).append(",");
		buf.append("alarmSign=").append(this.alarmSign).append(",");
		buf.append("audioVideoType=").append(this.audioVideoType).append(",");
		buf.append("memoryType=").append(this.memoryType).append(",");
		buf.append("dataSize=").append(this.dataSize).append("]");
		return buf.toString();
	}

}
