package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 *
 * 实时音视频传输请求 消息体数据格式Model定义
 *
 * <p>
 * 平台向终端设备请求实时音视频传输，包括实时视频传输、主动发起双向语音对讲、单向监听、向所有终端广播语音和特定透传等。消息体数据格式见表16。终端在收到此消息后回复视频终端通用应答，然后通过对应的服务器IP地址和端口号建立传输链路，然后按照音视频流传输协议传输相应的音视频流数据。
 * 平台收到视频终端的特殊报警后，应无须等待人工确认即主动下发本条指令，启动实时音视频传输。
 *
 * @author zx 2018年9月27日 下午4:08:37
 * @see
 * @version 1.0
 */
public class P0x9101 extends PModel implements Serializable {

	private static final long serialVersionUID = -5271882075872742688L;
	/**
	 * 12+0 服务器IP地址长度 BYTE 长度n
	 */
	private byte serverIpLength;
	/**
	 * 12+1 服务器IP地址 STRING 实时视频服务器IP地址
	 */
	private String serverIp;
	/**
	 * 12+1+n 服务器视频通道监听端口号（TCP） WORD 实时视频服务器TCP端口号
	 */
	private short serverTcpPort;
	/**
	 * 12+3+n 服务器视频通道监听端口号（UDP） WORD 实时视频服务器UDP端口号
	 */
	private short serverUdpPort;
	/**
	 * 12+5+n 逻辑通道号 BYTE 参见A.6音视频通道定义表
	 */
	private byte logicalChannel;
	/**
	 * 12+6+n 数据类型 BYTE 0：音视频，1：视频，2：双向对讲，3：监听，4：中心广播，5：透传
	 */
	private byte dateType;
	/**
	 * 12+7+n 码流类型 BYTE 0：主码流，1：子码流
	 */
	private byte codeStreamType;

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
	 * @return the dateType
	 */
	public byte getDateType() {
		return dateType;
	}

	/**
	 * @param dateType
	 *            the dateType to set
	 */
	public void setDateType(byte dateType) {
		this.dateType = dateType;
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

	@Override
	public String toString() {
		return "P0x9101 [serverIpLength=" + serverIpLength + ", serverIp=" + serverIp + ", serverTcpPort=" + serverTcpPort + ", serverUdpPort=" + serverUdpPort
				+ ", logicalChannel=" + logicalChannel + ", dateType=" + dateType + ", codeStreamType=" + codeStreamType + "]";
	}

}
