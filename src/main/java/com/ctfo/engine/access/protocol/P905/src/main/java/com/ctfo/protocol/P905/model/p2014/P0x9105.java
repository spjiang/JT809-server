package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 *
 * 实时音视频传输状态通知 消息体数据格式Model定义
 *
 * <p>
 * 平台在接收终端上传音视频数据的过程中按照设定的时间间隔向终端发送通知包
 *
 * @author zx 2018年9月27日 下午6:04:02
 * @see
 * @version 1.0
 */
public class P0x9105 extends PModel implements Serializable {

	private static final long serialVersionUID = -853109782403011448L;
	/**
	 * 12+0 逻辑通道号 BYTE 参见A.6音视频通道定义表
	 */
	private byte logicalChannel;
	/**
	 * 12+1 丢包率 BYTE 当前传输通道的丢包率，数值乘以100之后取整数部分。
	 */
	private byte packetLoss;

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
	 * @return the packetLoss
	 */
	public byte getPacketLoss() {
		return packetLoss;
	}

	/**
	 * @param packetLoss
	 *            the packetLoss to set
	 */
	public void setPacketLoss(byte packetLoss) {
		this.packetLoss = packetLoss;
	}

	@Override
	public String toString() {
		return "P0x9105 [logicalChannel=" + logicalChannel + ", packetLoss=" + packetLoss + "]";
	}

}
