package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 查询资源列表 消息体数据格式Model定义
 *
 * <p>
 * 平台按照音视频类型、通道号、报警类型和起止时间等组合条件从终端中查询录像文件列表
 *
 * @author zx 2018年9月27日 下午7:20:44
 * @see
 * @version 1.0
 */
public class P0x9205 extends PModel implements Serializable {

	private static final long serialVersionUID = -5926633622022345577L;
	/**
	 * 12+0 摄像头通道号 BYTE Bit0:对外摄像头；bit1：前排摄像头；Bit2:后排摄像头；
	 * bit3：后备箱摄像头；其他留；当对应位为1表示查询对应位置的摄像头
	 */
	private byte cameraChannelNumber;
	/**
	 * 12+1 开始时间 BCD[6] YY-MM-DD-HH-MM-SS，全0表示无起始时间条件
	 */
	private Date startTime;
	/**
	 * 12+7 结束时间 BCD[6] YY-MM-DD-HH-MM-SS，全0表示无终止时间条件
	 */
	private Date endTime;
	/**
	 * 12+13 报警标志 64BITS bit0～bit31见JT/T905 表20 报警标志位定义，bit32～bit63见10.2.5
	 * 特殊报警标识位定义表；全0表示无报警类型条件
	 */
	private long alarmSign;
	/**
	 * 12+21 音视频资源类型 BYTE 0：音视频，1：音频，2：视频，3：视频或音视频
	 */
	private byte audioVideoType;
	/**
	 * 12+22 码流类型 BYTE 0：所有码流，1：主码流，2：子码流
	 */
	private byte codeStreamType;
	/**
	 * 12+23 存储器类型 BYTE 保留为0
	 */
	private byte memoryType;

	/**
	 * @return the cameraChannelNumber
	 */
	public byte getCameraChannelNumber() {
		return cameraChannelNumber;
	}

	/**
	 * @param cameraChannelNumber
	 *            the cameraChannelNumber to set
	 */
	public void setCameraChannelNumber(byte cameraChannelNumber) {
		this.cameraChannelNumber = cameraChannelNumber;
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
	 * @param alarmSign the alarmSign to set
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

	@Override
	public String toString() {
		return "P0x9205 [cameraChannelNumber=" + cameraChannelNumber + ", startTime=" + startTime + ", endTime=" + endTime + ", alarmSign=" + alarmSign
				+ ", audioVideoType=" + audioVideoType + ", codeStreamType=" + codeStreamType + ", memoryType=" + memoryType + "]";
	}

}
