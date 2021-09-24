package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 *摄像头立即拍摄命令消息体数据格式model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0	摄像头ID	UINT8	>0
 * 14+1	拍摄命令	UINT16	0表示停止拍摄；0xFFFF表示录像；其它表示拍照张数
 * 14+3	拍照间隔/录像时间	UINT16	秒，0表示按最小间隔拍照或一直录像
 * 14+5	保存标志	UINT8	1：保存；0：实时上传
 * 14+6	分辨率	UINT8	0：320*240；1：640*480；2：800*600；其它保留
 * 14+7	图像/视频质量	UINT8	1～10，1最好
 * 14+8	亮度	UINT8	0～255
 * 14+9	对比度	UINT8	0～127
 * 14+10	饱和度	UINT8	0～127
 * 14+11	色度	UINT8	0～255
 *
 * @author xiahancheng 2018年5月4日 上午9:12:53
 * @see
 * @version 1.0
 */
public class P0x8801 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 摄像头ID
	 */
	private byte cameraId;

	/**
	 * 拍摄命令,0表示停止拍摄；其它表示拍照张数
	 */
	private short shootCommand;

	/**
	 * 拍照间隔/录像时间,秒，0表示按最小拍照间隔
	 */
	private short photographInterval;

	/**
	 * 保存标志,1：保存；0：实时上传
	 */
	private byte preservationSign;

	/**
	 * 分辨率,0：320*240；1：640*480；2：800*600；3:1024*768；4：1280*960；5:1920*1080；其它保留
	 */
	private byte resolutionRatio;

	/**
	 * 图像/视频质量,1～10，1最好
	 */
	private byte image;

	/**
	 * 亮度
	 */
	private byte brightness;

	/**
	 * 对比度
	 */
	private byte contrastRatio;

	/**
	 * 饱和度
	 */
	private byte saturation;

	/**
	 * 色度
	 */
	private byte chroma;

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
	 * @return the shootCommand
	 */
	public short getShootCommand() {
		return shootCommand;
	}

	/**
	 * @param shootCommand the shootCommand to set
	 */
	public void setShootCommand(short shootCommand) {
		this.shootCommand = shootCommand;
	}

	/**
	 * @return the photographInterval
	 */
	public short getPhotographInterval() {
		return photographInterval;
	}

	/**
	 * @param photographInterval the photographInterval to set
	 */
	public void setPhotographInterval(short photographInterval) {
		this.photographInterval = photographInterval;
	}

	/**
	 * @return the preservationSign
	 */
	public byte getPreservationSign() {
		return preservationSign;
	}

	/**
	 * @param preservationSign the preservationSign to set
	 */
	public void setPreservationSign(byte preservationSign) {
		this.preservationSign = preservationSign;
	}

	/**
	 * @return the resolutionRatio
	 */
	public byte getResolutionRatio() {
		return resolutionRatio;
	}

	/**
	 * @param resolutionRatio the resolutionRatio to set
	 */
	public void setResolutionRatio(byte resolutionRatio) {
		this.resolutionRatio = resolutionRatio;
	}

	/**
	 * @return the image
	 */
	public byte getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(byte image) {
		this.image = image;
	}

	/**
	 * @return the brightness
	 */
	public byte getBrightness() {
		return brightness;
	}

	/**
	 * @param brightness the brightness to set
	 */
	public void setBrightness(byte brightness) {
		this.brightness = brightness;
	}

	/**
	 * @return the contrastRatio
	 */
	public byte getContrastRatio() {
		return contrastRatio;
	}

	/**
	 * @param contrastRatio the contrastRatio to set
	 */
	public void setContrastRatio(byte contrastRatio) {
		this.contrastRatio = contrastRatio;
	}

	/**
	 * @return the saturation
	 */
	public byte getSaturation() {
		return saturation;
	}

	/**
	 * @param saturation the saturation to set
	 */
	public void setSaturation(byte saturation) {
		this.saturation = saturation;
	}

	/**
	 * @return the chroma
	 */
	public byte getChroma() {
		return chroma;
	}

	/**
	 * @param chroma the chroma to set
	 */
	public void setChroma(byte chroma) {
		this.chroma = chroma;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8801 [");
		sb.append("cameraId=").append(this.cameraId).append(",");
		sb.append("shootCommand=").append(this.shootCommand).append(",");
		sb.append("photographInterval=").append(this.photographInterval).append(",");
		sb.append("preservationSign=").append(this.preservationSign).append(",");
		sb.append("resolutionRatio=").append(this.resolutionRatio).append(",");
		sb.append("image=").append(this.image).append(",");
		sb.append("brightness=").append(this.brightness).append(",");
		sb.append("contrastRatio=").append(this.contrastRatio).append(",");
		sb.append("saturation=").append(this.saturation).append(",");
		sb.append("chroma=").append(this.chroma).append("]");
		return sb.toString();
	}

}
