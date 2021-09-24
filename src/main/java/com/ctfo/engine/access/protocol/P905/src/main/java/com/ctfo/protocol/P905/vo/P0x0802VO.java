package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.vo;

import java.io.Serializable;

/**
 * A.2.7.30 存储图像检索应答 - 检索项值存储对象
 *
 * @author 凉意 2019年8月9日 下午9:09:13
 * @see
 * @version 1.0
 */
public class P0x0802VO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 12+0 图像ID UINT32 符合条件的文件ID列表
	 */
	private int imgId;

	/**
	 * 摄像头逻辑通道号，表A.6
	 */
	private byte cameraId;

	/**
	 * @return the imgId
	 */
	public int getImgId() {
		return imgId;
	}

	/**
	 * @param imgId
	 *            the imgId to set
	 */
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	/**
	 * @return the cameraId
	 */
	public byte getCameraId() {
		return cameraId;
	}

	/**
	 * @param cameraId
	 *            the cameraId to set
	 */
	public void setCameraId(byte cameraId) {
		this.cameraId = cameraId;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0802VO [");
		sb.append("imgId=").append(imgId).append(",");
		sb.append("cameraId=").append(cameraId).append("]");
		return sb.toString();
	}
}
