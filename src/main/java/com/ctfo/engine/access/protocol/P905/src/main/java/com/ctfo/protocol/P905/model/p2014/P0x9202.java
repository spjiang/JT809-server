package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 平台下发远程录像回放控制  消息体数据格式Model定义
 *
 * <p>类详细说明
 * @author zx 2018年9月28日 上午11:51:52
 * @see
 * @version 1.0
 */
public class P0x9202 extends PModel implements Serializable{

	private static final long serialVersionUID = 8499677652262596257L;
	/**
	 * 12+0	逻辑通道号	BYTE	参见A.6音视频通道定义表
	 */
	private byte logicalChannel;
	/**
	 * 12+1	回放控制	BYTE
	 * 0：开始回放； 1：暂停回放； 2：结束回放； 3：快进回放； 4：关键帧快退回放；5：拖动回放；6：关键帧播放
	 */
	private byte playbackControl;

	/**
	 * 12+2	快进或快退倍数	BYTE
	 * 回放控制为3和4时，此字段内容有效，否则置0  0：无效； 1：1倍   2：2倍； 3：4倍； 4：8倍；	5：16倍
	 */
	private byte fastForwardOrFastMultiple;
	/**
	 * 12+3	拖动回放位置	BCD[6]	YY-MM-DD-HH-MM-SS，回放控制为5时，此字段有效
	 */
	private Date dragPlaybackLocation;

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
	 * @return the playbackControl
	 */
	public byte getPlaybackControl() {
		return playbackControl;
	}


	/**
	 * @param playbackControl the playbackControl to set
	 */
	public void setPlaybackControl(byte playbackControl) {
		this.playbackControl = playbackControl;
	}


	/**
	 * @return the fastForwardOrFastMultiple
	 */
	public byte getFastForwardOrFastMultiple() {
		return fastForwardOrFastMultiple;
	}


	/**
	 * @param fastForwardOrFastMultiple the fastForwardOrFastMultiple to set
	 */
	public void setFastForwardOrFastMultiple(byte fastForwardOrFastMultiple) {
		this.fastForwardOrFastMultiple = fastForwardOrFastMultiple;
	}


	/**
	 * @return the dragPlaybackLocation
	 */
	public Date getDragPlaybackLocation() {
		return dragPlaybackLocation;
	}


	/**
	 * @param dragPlaybackLocation the dragPlaybackLocation to set
	 */
	public void setDragPlaybackLocation(Date dragPlaybackLocation) {
		this.dragPlaybackLocation = dragPlaybackLocation;
	}


	@Override
	public String toString() {
		return "P0x9202 [logicalChannel=" + logicalChannel + ", playbackControl=" + playbackControl
				+ ", fastForwardOrFastMultiple=" + fastForwardOrFastMultiple + ", dragPlaybackLocation="
				+ dragPlaybackLocation + "]";
	}

}
