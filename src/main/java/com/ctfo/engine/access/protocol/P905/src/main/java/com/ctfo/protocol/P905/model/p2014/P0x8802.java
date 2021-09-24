package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 存储图像检索命令消息体数据格式model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0 摄像头ID UINT8 0表示检索所有摄像头 14+1 拍照原因 UINT8 0:进入重车拍照 1:服务评价拍照2：报警拍照 3：中心主动拍照
 * 14+2 起始时间 BCD[6] YY-MM-DD-hh-mm-ss 14+8 结束时间 BCD[6] YY-MM-DD-hh-mm-ss
 *
 * @author xiahancheng 2018年5月4日 上午11:09:01
 * @see
 * @version 1.0
 */
public class P0x8802 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 摄像头ID
	 */
	private byte cameraId;

	/**
	 * 拍照原因
	 * <p>
	 * 0：进入重车拍照<br>
	 * 1：服务评价拍照<br>
	 * 2：报警拍照<br>
	 * 3：中心主动拍照<br>
	 *
	 */
	private byte photographReason;

	/**
	 * 起始时间YYMMDDhhmmss
	 */
	private Date startTime;

	/**
	 * 结束时间YYMMDDhhmmss
	 */
	private Date endTime;

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
	 * @return the photographReason
	 */
	public byte getPhotographReason() {
		return photographReason;
	}

	/**
	 * @param photographReason
	 *            the photographReason to set
	 */
	public void setPhotographReason(byte photographReason) {
		this.photographReason = photographReason;
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

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8802 [");
		sb.append("cameraId=").append(this.cameraId).append(",");
		sb.append("photographReason=").append(this.photographReason).append(",");
		sb.append("startTime=").append(this.startTime).append(",");
		sb.append("endTime=").append(this.endTime).append("]");
		return sb.toString();
	}
}
