package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;


import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * model模板
 *
 * <p>
 * 14+0	上传原因	UINT16	该命令如果是查询上传的后续，该字段填充0如果是立即拍摄命令的后续，则填充立即拍摄命令的命令流水号。
 * 14+2	图像	UINT32	>0
 * 14+6	摄像头ID	 UINT8	在此默认为0x00
 * 14+7	位置图像数据大小	UINT32
 * 14+11	起始地址	UINT32	本包数据在整个位置图像数据中的偏移量，第一包数据为0
 * 14+15	位置图像数据包
 *
 *
 * @author xiahancheng 2018年5月4日 上午9:06:43
 * @see
 * @version 1.0
 */
public class P0x0800 extends PModel implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 上传原因
	 */
	private short uploadReason;

	/**
	 * 图像
	 */
	private int image;

	/**
	 * 摄像头ID,逻辑通道编号，参见表A.6
	 */
	private byte cameraId;

	/**
	 *位置图像数据大小
	 */
	private int dataSize;

	/**
	 * 起始地址
	 */
	private int startingAddress;

	/**
	 *位置图像数据包
	 */
	private String dataPacketHex;

	/**
	 * @return the uploadReason
	 */
	public short getUploadReason() {
		return uploadReason;
	}

	/**
	 * @param uploadReason the uploadReason to set
	 */
	public void setUploadReason(short uploadReason) {
		this.uploadReason = uploadReason;
	}

	/**
	 * @return the image
	 */
	public int getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(int image) {
		this.image = image;
	}

	/**
	 * @return the cameraId
	 */
	public byte getCameraId() {
		return cameraId;
	}

	/**
	 * @param cameraId the cameraId to set
	 */
	public void setCameraId(byte cameraId) {
		this.cameraId = cameraId;
	}

	/**
	 * @return the dataSize
	 */
	public int getDataSize() {
		return dataSize;
	}

	/**
	 * @param dataSize the dataSize to set
	 */
	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}

	/**
	 * @return the startingAddress
	 */
	public int getStartingAddress() {
		return startingAddress;
	}

	/**
	 * @param startingAddress the startingAddress to set
	 */
	public void setStartingAddress(int startingAddress) {
		this.startingAddress = startingAddress;
	}


	/**
	 * @return the dataPacketHex
	 */
	public String getDataPacketHex() {
		return dataPacketHex;
	}

	/**
	 * @param dataPacketHex the dataPacketHex to set
	 */
	public void setDataPacketHex(String dataPacketHex) {
		this.dataPacketHex = dataPacketHex;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0800 [");
		sb.append("uploadReason=").append(this.uploadReason).append(",");
		sb.append("image=").append(this.image).append(",");
		sb.append("cameraId=").append(this.cameraId).append(",");
		sb.append("dataSize=").append(this.dataSize).append(",");
		sb.append("startingAddress=").append(this.startingAddress).append(",");
		sb.append("dataPacketHex=").append(this.dataPacketHex).append("]");
		return sb.toString();
	}


}
