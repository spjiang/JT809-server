package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 *
 * 音视频传输控制 消息体数据格式Model定义
 *
 * <p>
 * 平台主动关闭音视频传输通道消息
 *
 * @author zx 2018年9月27日 下午5:13:57
 * @see
 * @version 1.0
 */
public class P0x9102 extends PModel implements Serializable {

	private static final long serialVersionUID = 5260098157644348387L;
	/**
	 * 12+0 摄像头通道号 BYTE Bit0:对外摄像头；bit1：前排摄像头； Bit2:后排摄像头；
	 * bit3：后备箱摄像头；其他预留；当对应位为1表示控制对应位置的摄像头
	 */
	private byte cameraChannelNumber;
	/**
	 * 12+1 控制指令 BYTE 平台可以通过该指令对设备的实时音视频进行控制。
	 * 0：关闭音视频传输指令1：切换码流（增加暂停和继续）2：暂停该通道所有流的发送 3：恢复暂停前流的发送，与暂停前的流类型一致 4：关闭双向对讲
	 */
	private byte controlInstruction;
	/**
	 * 12+2 关闭音视频类型 BYTE 0：关闭该通道有关的音视频数据 1：只关闭该通道有关的音频，保留该通道有关的视频
	 * 2：只关闭该通道有关的视频，保留该通道有关的音频
	 */
	private byte closeAudioVideoType;
	/**
	 * 12+3 切换码流类型 BYTE 将之前申请的码流切换为新申请的码流，音频与切换前保持一致。 新申请的码流为：0：主码流；1：子码流
	 */
	private byte switchingBitstreamType;

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
	 * @return the controlInstruction
	 */
	public byte getControlInstruction() {
		return controlInstruction;
	}

	/**
	 * @param controlInstruction
	 *            the controlInstruction to set
	 */
	public void setControlInstruction(byte controlInstruction) {
		this.controlInstruction = controlInstruction;
	}

	/**
	 * @return the closeAudioVideoType
	 */
	public byte getCloseAudioVideoType() {
		return closeAudioVideoType;
	}

	/**
	 * @param closeAudioVideoType
	 *            the closeAudioVideoType to set
	 */
	public void setCloseAudioVideoType(byte closeAudioVideoType) {
		this.closeAudioVideoType = closeAudioVideoType;
	}

	/**
	 * @return the switchingBitstreamType
	 */
	public byte getSwitchingBitstreamType() {
		return switchingBitstreamType;
	}

	/**
	 * @param switchingBitstreamType
	 *            the switchingBitstreamType to set
	 */
	public void setSwitchingBitstreamType(byte switchingBitstreamType) {
		this.switchingBitstreamType = switchingBitstreamType;
	}

	@Override
	public String toString() {
		return "P0x9102 [cameraChannelNumber=" + cameraChannelNumber + ", controlInstruction=" + controlInstruction + ", closeAudioVideoType="
				+ closeAudioVideoType + ", switchingBitstreamType=" + switchingBitstreamType + "]";
	}

}
