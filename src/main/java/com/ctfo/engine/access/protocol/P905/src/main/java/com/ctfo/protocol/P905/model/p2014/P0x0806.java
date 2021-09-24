package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;


import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 音视频上传消息体数据格式Model定义
 * <p>
 * 14+0 存储图像/音视频上传命令流水号 UINT16 <br>
 * 14+2 音视频ID UINT32 >0<br>
 * 14+6 位置/音视频数据大小 UINT32 <br>
 * 14+10 起始地址 UINT32 本包数据在整个位置图像数据中的偏移量，第一包数据为0<br>
 * 14+14 位置/音视频图像数据包 String 终端根据自身硬件性能确定数据包的大小；后台管理系统应能自适应。建议在网络条件不好的情况下每个数据包不超过512字节。<br>
 *
 * @author fanya 2018年5月3日 下午4:15:39
 * @see
 * @version 1.0
 */
public class P0x0806 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 存储图像/音视频上传命令流水号
	 */
	private short uploadSerial;

	/**
	 * 音视频ID
	 */
	private int audioID;

	/**
	 * 位置/音视频数据大小
	 */
	private int videoDataSize;

	/**
	 * 起始地址
	 */
	private int startAddr;

	/**
	 * 位置/音视频图像数据包
	 */
	private String dataPacket;


	public short getUploadSerial() {
		return uploadSerial;
	}


	public void setUploadSerial(short uploadSerial) {
		this.uploadSerial = uploadSerial;
	}


	public int getAudioID() {
		return audioID;
	}


	public void setAudioID(int audioID) {
		this.audioID = audioID;
	}


	public int getVideoDataSize() {
		return videoDataSize;
	}


	public void setVideoDataSize(int videoDataSize) {
		this.videoDataSize = videoDataSize;
	}


	public int getStartAddr() {
		return startAddr;
	}


	public void setStartAddr(int startAddr) {
		this.startAddr = startAddr;
	}


	public String getDataPacket() {
		return dataPacket;
	}


	public void setDataPacket(String dataPacket) {
		this.dataPacket = dataPacket;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0806 [");
		sb.append("uploadSerial=").append(this.uploadSerial).append(",");
		sb.append("audioID=").append(this.audioID).append(",");
		sb.append("videoDataSize=").append(this.videoDataSize).append(",");
		sb.append("startAddr=").append(this.startAddr).append(",");
		sb.append("dataPacket=").append(this.dataPacket).append("]");
		return sb.toString();
	}
}

