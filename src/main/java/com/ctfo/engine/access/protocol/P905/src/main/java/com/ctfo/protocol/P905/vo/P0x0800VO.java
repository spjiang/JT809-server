package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 置图像数据的说明（照片文件头定义）:
 * 每幅图像数据前需附加拍摄时的位置等基本信息（照片文件头）。VST上传到中心的图像文件均为添加过该照片文件头的数据，照片文件头格式暂定义如下
 *
 * @author 凉意 2018年10月26日 下午2:04:46
 * @see
 * @version 1.0
 */
public class P0x0800VO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * VST的设备编号,BCD[5]
	 */
	private String vtsId;

	/**
	 * 编码方式,BCD,各厂商自定义，需根据设备编号中的厂商编码及编码方式 提供相应的解压缩接口
	 */
	private String codecMode;

	/**
	 * 车牌号,STRING[10],车牌号，不足长度右补0x00
	 */
	private String vehicleNo;

	/**
	 * 营运ID,
	 */
	private int operId;

	/**
	 * 拍照原因，0：进入重车拍照 1：服务评价拍照 2：报警拍照 3：中心主动拍照 4：进入空车拍照 5：不打表营运拍照 6: 预留 7：人脸识别拍照
	 * 8：动态查岗拍照 9：乘客人脸拍照 10：疲劳驾驶拍照 11：分心驾驶拍照 12：开车抽烟拍照 13：开车打电话拍照 14：出城拍照
	 * 15：超速拍照 16：定时拍照
	 */
	private byte photoReason;

	/**
	 * 拍照时间,BCD[6],北京时间 YYMMDDHHMMSS
	 */
	private Date photoTime;
	/**
	 * 拍照地点的经度,0.0001’
	 */
	private int photoLng;
	/**
	 * 拍照地点的纬度,0.0001’
	 */
	private int photoLat;
	/**
	 * 图像ID号,照片文件在VST中的文件ID号
	 */
	private int photoId;
	/**
	 * 图像数据的大小,图像数据的大小，单位：byte
	 */
	private int photoSize;

	/**
	 * 摄像头ID,逻辑通道编号，参见表A.6
	 */
	private byte cameraId;
	/**
	 * 照片拍摄时，签到的驾驶员身份信息,BYTE[19]
	 */
	private String driverId;
	/**
	 * 报警ID,特殊报警事件对应的报警时间,非特殊报警图片填0
	 */
	private String alarmId;
	/**
	 * 照片数据
	 */
	private String photoData;
	/**
	 * @return the vtsId
	 */
	public String getVtsId() {
		return vtsId;
	}
	/**
	 * @param vtsId the vtsId to set
	 */
	public void setVtsId(String vtsId) {
		this.vtsId = vtsId;
	}
	/**
	 * @return the codecMode
	 */
	public String getCodecMode() {
		return codecMode;
	}
	/**
	 * @param codecMode the codecMode to set
	 */
	public void setCodecMode(String codecMode) {
		this.codecMode = codecMode;
	}
	/**
	 * @return the vehicleNo
	 */
	public String getVehicleNo() {
		return vehicleNo;
	}
	/**
	 * @param vehicleNo the vehicleNo to set
	 */
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	/**
	 * @return the operId
	 */
	public int getOperId() {
		return operId;
	}
	/**
	 * @param operId the operId to set
	 */
	public void setOperId(int operId) {
		this.operId = operId;
	}
	/**
	 * @return the photoReason
	 */
	public byte getPhotoReason() {
		return photoReason;
	}
	/**
	 * @param photoReason the photoReason to set
	 */
	public void setPhotoReason(byte photoReason) {
		this.photoReason = photoReason;
	}
	/**
	 * @return the photoTime
	 */
	public Date getPhotoTime() {
		return photoTime;
	}
	/**
	 * @param photoTime the photoTime to set
	 */
	public void setPhotoTime(Date photoTime) {
		this.photoTime = photoTime;
	}
	/**
	 * @return the photoLng
	 */
	public int getPhotoLng() {
		return photoLng;
	}
	/**
	 * @param photoLng the photoLng to set
	 */
	public void setPhotoLng(int photoLng) {
		this.photoLng = photoLng;
	}
	/**
	 * @return the photoLat
	 */
	public int getPhotoLat() {
		return photoLat;
	}
	/**
	 * @param photoLat the photoLat to set
	 */
	public void setPhotoLat(int photoLat) {
		this.photoLat = photoLat;
	}
	/**
	 * @return the photoId
	 */
	public int getPhotoId() {
		return photoId;
	}
	/**
	 * @param photoId the photoId to set
	 */
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	/**
	 * @return the photoSize
	 */
	public int getPhotoSize() {
		return photoSize;
	}
	/**
	 * @param photoSize the photoSize to set
	 */
	public void setPhotoSize(int photoSize) {
		this.photoSize = photoSize;
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
	 * @return the driverId
	 */
	public String getDriverId() {
		return driverId;
	}
	/**
	 * @param driverId the driverId to set
	 */
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	/**
	 * @return the alarmId
	 */
	public String getAlarmId() {
		return alarmId;
	}
	/**
	 * @param alarmId the alarmId to set
	 */
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}
	/**
	 * @return the photoData
	 */
	public String getPhotoData() {
		return photoData;
	}
	/**
	 * @param photoData the photoData to set
	 */
	public void setPhotoData(String photoData) {
		this.photoData = photoData;
	}
	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0800VO [");
		sb.append("vtsId=").append(vtsId).append(",");
		sb.append("codecMode=").append(codecMode).append(",");
		sb.append("vehicleNo=").append(vehicleNo).append(",");
		sb.append("operId=").append(operId).append(",");
		sb.append("photoReason=").append(photoReason).append(",");
		sb.append("photoTime=").append(photoTime).append(",");
		sb.append("photoLng=").append(photoLng).append(",");
		sb.append("photoLat=").append(photoLat).append(",");
		sb.append("photoId=").append(photoId).append(",");
		sb.append("photoSize=").append(photoSize).append(",");
		sb.append("cameraId=").append(cameraId).append(",");
		sb.append("driverId=").append(driverId).append(",");
		sb.append("alarmId=").append(alarmId).append(",");
		sb.append("photoData=").append(photoData).append(",");
		sb.append("vehicleNo=").append(vehicleNo).append("]");
		return sb.toString();
	}
}
