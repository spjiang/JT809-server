package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 远程事件录像操作  消息体数据格式Model定义
 *
 * <p>类详细说明
 * @author zx 2018年9月28日 下午5:35:57
 * @see
 * @version 1.0
 */
public class P0x9208 extends PModel implements Serializable{

	private static final long serialVersionUID = 7573093573933983128L;
	/**
	 * 12+0	录像的起始时间	BCD[6]	YYMMDDHHmmSS 开始时间
	 */
	private Date videoStartTime;
	/**
	 * 12+6	录像的结束时间	BCD[6]	YYMMDDHHmmSS 结束时间
	 */
	private Date videoEndTime;
	/**
	 * 12+12	逻辑通道编号	UINT8	参见表A.6
	 */
	private byte logicalChannel;
	/**
	 * 12+13	录像操作	UINT8	0-在终端上播放指定时段录像；1-锁定指定时段录像；2-255：功能保留
	 */
	private byte videoOperation;


	/**
	 * @return the videoStartTime
	 */
	public Date getVideoStartTime() {
		return videoStartTime;
	}



	/**
	 * @param videoStartTime the videoStartTime to set
	 */
	public void setVideoStartTime(Date videoStartTime) {
		this.videoStartTime = videoStartTime;
	}



	/**
	 * @return the videoEndTime
	 */
	public Date getVideoEndTime() {
		return videoEndTime;
	}



	/**
	 * @param videoEndTime the videoEndTime to set
	 */
	public void setVideoEndTime(Date videoEndTime) {
		this.videoEndTime = videoEndTime;
	}



	/**
	 * @return the logicalChannel
	 */
	public byte getLogicalChannel() {
		return logicalChannel;
	}



	/**
	 * @param logicalChannel the logicalChannel to set
	 */
	public void setLogicalChannel(byte logicalChannel) {
		this.logicalChannel = logicalChannel;
	}



	/**
	 * @return the videoOperation
	 */
	public byte getVideoOperation() {
		return videoOperation;
	}



	/**
	 * @param videoOperation the videoOperation to set
	 */
	public void setVideoOperation(byte videoOperation) {
		this.videoOperation = videoOperation;
	}



	@Override
	public String toString() {
		return "P0x9208 [videoStartTime=" + videoStartTime + ", videoEndTime=" + videoEndTime + ", logicalChannel="
				+ logicalChannel + ", videoOperation=" + videoOperation + "]";
	}

}
