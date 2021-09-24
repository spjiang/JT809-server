package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;


import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 智能终端升级结果报告消息数据格式项Model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0 设备类型 UINT8 <br>
 * 14+1 厂商标识 UINT8 <br>
 * 14+2 硬件版本号 BCD <br>
 * 14+3 软件版本号 BCD[2] 第一字节为主版本号；第二字节为副版本号<br>
 * 14+5 升级结果 UINT8 0：软件版本号一致，无需升级 1：升级成功 2：升级失败 3：厂商标识不一致 4：硬件版本号不一致 5：下载升级文件失败
 * 6：升级服务器主动取消升级 7、设备主动放弃升级（非自身程序）
 *
 * @author 凉意 2018年5月2日 上午9:27:22
 * @see
 * @version 1.0
 */
public class P0x0105 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 设备类型,UINT8
	 */
	private byte deviceType;

	/**
	 * 厂商标识，UINT8
	 */
	private byte firmFlag;

	/**
	 * 硬件版本号，BCD[1]
	 */
	private String hardwareVesion;

	/**
	 * 软件版本号，BCD[2]，第一字节为主版本号；第二字节为副版本号
	 */
	private String softwareVesion;

	/**
	 * 升级结果
	 * <p>
	 * 0：软件版本号一致，无需升级<br>
	 * 1：升级成功<br>
	 * 2：升级失败<br>
	 * 3：厂商标识不一致<br>
	 * 4：硬件版本号不一致<br>
	 * 5：下载升级文件失败<br>
	 * 6：升级服务器主动取消升级<br>
	 * 7、设备主动放弃升级（非自身程序）<br>
	 */
	private byte result;

	/**
	 * @return the deviceType
	 */
	public byte getDeviceType() {
		return deviceType;
	}

	/**
	 * @param deviceType
	 *            the deviceType to set
	 */
	public void setDeviceType(byte deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * @return the firmFlag
	 */
	public byte getFirmFlag() {
		return firmFlag;
	}

	/**
	 * @param firmFlag
	 *            the firmFlag to set
	 */
	public void setFirmFlag(byte firmFlag) {
		this.firmFlag = firmFlag;
	}

	/**
	 * @return the hardwareVesion
	 */
	public String getHardwareVesion() {
		return hardwareVesion;
	}

	/**
	 * @param hardwareVesion
	 *            the hardwareVesion to set
	 */
	public void setHardwareVesion(String hardwareVesion) {
		this.hardwareVesion = hardwareVesion;
	}

	/**
	 * @return the softwareVesion
	 */
	public String getSoftwareVesion() {
		return softwareVesion;
	}

	/**
	 * @param softwareVesion
	 *            the softwareVesion to set
	 */
	public void setSoftwareVesion(String softwareVesion) {
		this.softwareVesion = softwareVesion;
	}

	/**
	 * @return the result
	 */
	public byte getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(byte result) {
		this.result = result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0105 [");
		sb.append("deviceType=").append(deviceType).append(",");
		sb.append("firmFlag=").append(firmFlag).append(",");
		sb.append("hardwareVesion=").append(hardwareVesion).append(",");
		sb.append("softwareVesion=").append(softwareVesion).append(",");
		sb.append("result=").append(result).append("]");
		return sb.toString();
	}

}
