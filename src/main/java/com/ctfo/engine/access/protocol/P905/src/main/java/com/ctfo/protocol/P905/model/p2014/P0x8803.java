package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 存储图像/音视频上传命令消息体数据格式model定义
 *
 * <p>
 * 14+0	类型	UINT8	0x00：照片；0x01：音频；0x02：视频 其它：RFU
 * 14+1	文件ID	UINT32
 * 14+5	起始地址	UINT32	本包数据在整个位置图像数据中的偏移量，第一包数据为0
 * @author xiahancheng 2018年5月4日 下午5:07:12
 * @see
 * @version 1.0
 */
public class P0x8803 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 类型0x00：照片；0x01：音频；0x02：视频；其他：RFU
	 */
	private byte type;

	/**
	 * 文件ID
	 */
	private int fileId;

	/**
	 * 起始地址
	 */
	private int startingAddress;

	/**
	 * @return the type
	 */
	public byte getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(byte type) {
		this.type = type;
	}

	/**
	 * @return the fileId
	 */
	public int getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(int fileId) {
		this.fileId = fileId;
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
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8803 [");
		sb.append("type=").append(this.type).append(",");
		sb.append("fileId=").append(this.fileId).append(",");
		sb.append("startingAddress=").append(this.startingAddress).append("]");
		return sb.toString();
	}

}
