package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 平台下发远程录像回放请求 消息体数据格式Model定义
 *
 * <p>
 * 平台向终端设备请求音视频录像回放，终端应采用0x1205（终端上传录像文件列表）指令应答，然后传输录像数据采用表18实时音视频流数据传输RTP协议负载包格式所定义的封包格式
 *
 * @author zx 2018年9月28日 上午10:37:03
 * @see
 * @version 1.0
 */
public class P0x9201 extends PModel implements Serializable {

	private static final long serialVersionUID = -8055385722317538516L;
	/**
	 * 12+0 服务器IP地址长度 BYTE 长度n
	 */
	private byte serverIpLength;
	/**
	 * 12+1 服务器IP地址 STRING 实时音视频服务器IP地址
	 */
	private String serverIp;
	/**
	 * 12+1+n 服务器音视频通道监听端口号（TCP） WORD 实时音视频服务器端口号，不使用TCP传输时置0
	 */
	private short serverTcpPort;
	/**
	 * 12+3+n 服务器音视频通道监听端口号（UDP） WORD 实时音视频服务器端口号，不使用UDP传输时置0
	 */
	private short serverUdpPort;
	/**
	 * 12+5+n 摄像头通道号 BYTE Bit0:对外摄像头；bit1：前排摄像头；Bit2:后排摄像头；
	 * bit3：后备箱摄像头；其他留；当对应位为1表示查询对应位置的摄像头
	 */
	private byte cameraChannelNumber;
	/**
	 * 12+6+n 音视频类型 BYTE 0：音视频，1：音频，2：视频，3：视频或音视频
	 */
	private byte audioVideoType;
	/**
	 * 12+7+n 码流类型 BYTE 0：主码流或子码流，1：主码流，2：子码流；如果此通道只传输音频，此字段置0
	 */
	private byte codeStreamType;
	/**
	 * 12+8+n 存储器类型 BYTE 保留为0
	 */
	private byte memoryType;
	/**
	 * 12+9+n 回放方式 BYTE 0：正常回放；1：快进回放；2：关键帧快退回放； 3：关键帧播放； 4、单帧上传；
	 */
	private byte replayMode;
	/**
	 * 12+10+n 快进或快退倍数 BYTE 回放方式为1和2时，此字段内容有效，否则置0 0：无效； 1：1倍 2：2倍； 3：4倍； 4：8倍；
	 * 5：16倍
	 */
	private byte fastForwardOrFastMultiple;
	/**
	 * 12+11+n 开始时间 BCD[6] YY-MM-DD-HH-MM-SS，回放方式为4时，该字段表示单帧上传时间
	 */
	private Date startTime;
	/**
	 * 12+17+n 结束时间 BCD[6] YY-MM-DD-HH-MM-SS，为0表示一直回放，回放方式为4时，该字段无效
	 */
	private Date endTime;

	/**
	 * @return the serverIpLength
	 */
	public byte getServerIpLength() {
		return serverIpLength;
	}

	/**
	 * @param serverIpLength
	 *            the serverIpLength to set
	 */
	public void setServerIpLength(byte serverIpLength) {
		this.serverIpLength = serverIpLength;
	}

	/**
	 * @return the serverIp
	 */
	public String getServerIp() {
		return serverIp;
	}

	/**
	 * @param serverIp
	 *            the serverIp to set
	 */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	/**
	 * @return the serverTcpPort
	 */
	public short getServerTcpPort() {
		return serverTcpPort;
	}

	/**
	 * @param serverTcpPort
	 *            the serverTcpPort to set
	 */
	public void setServerTcpPort(short serverTcpPort) {
		this.serverTcpPort = serverTcpPort;
	}

	/**
	 * @return the serverUdpPort
	 */
	public short getServerUdpPort() {
		return serverUdpPort;
	}

	/**
	 * @param serverUdpPort
	 *            the serverUdpPort to set
	 */
	public void setServerUdpPort(short serverUdpPort) {
		this.serverUdpPort = serverUdpPort;
	}

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
	 * @return the replayMode
	 */
	public byte getReplayMode() {
		return replayMode;
	}

	/**
	 * @param replayMode
	 *            the replayMode to set
	 */
	public void setReplayMode(byte replayMode) {
		this.replayMode = replayMode;
	}

	/**
	 * @return the fastForwardOrFastMultiple
	 */
	public byte getFastForwardOrFastMultiple() {
		return fastForwardOrFastMultiple;
	}

	/**
	 * @param fastForwardOrFastMultiple
	 *            the fastForwardOrFastMultiple to set
	 */
	public void setFastForwardOrFastMultiple(byte fastForwardOrFastMultiple) {
		this.fastForwardOrFastMultiple = fastForwardOrFastMultiple;
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

	@Override
	public String toString() {
		return "P0x9201 [serverIpLength=" + serverIpLength + ", serverIp=" + serverIp + ", serverTcpPort=" + serverTcpPort + ", serverUdpPort=" + serverUdpPort
				+ ", cameraChannelNumber=" + cameraChannelNumber + ", audioVideoType=" + audioVideoType + ", codeStreamType=" + codeStreamType + ", memoryType="
				+ memoryType + ", replayMode=" + replayMode + ", fastForwardOrFastMultiple=" + fastForwardOrFastMultiple + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}

}
