package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;


import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 终端控制消息数据格式Model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0 命令字 UINT8 详见后面描述<br>
 * 14+1 命令参数 命令参数的使用见表 18 终端控制命令字说明、表 19 无线升级命令参数格式描述<br>
 *
 * @author 凉意 2018年4月26日 下午4:33:15
 * @see
 * @version 1.0
 */
public class P0x8105 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 命令字
	 * <p>
	 * 命令字 命令参数 说明<br>
	 * 1 无线升级<br>
	 * 2 不使用 ISU关机<br>
	 * 3 不使用 ISU复位<br>
	 * 4 不使用 ISU恢复出厂设置<br>
	 * 5 不使用 关闭数据通信<br>
	 * 6 不使用 关闭所有无线通信<br>
	 */
	private byte commandWord;

	// 无线升级命令参数格式定义

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
	 * APN,STRING,以0x00结尾
	 */
	private String apn;

	/**
	 * 拨号用户名,以0x00结尾
	 */
	private String userName;

	/**
	 * 拨号密码,以0x00结尾
	 */
	private String password;

	/**
	 * 升级服务器IP地址,以0x00结尾
	 */
	private String upgradeServerIp;

	/**
	 * 升级服务器开放端口,UINT16
	 */
	private short upgradeServerPort;

	/**
	 * @return the commandWord
	 */
	public byte getCommandWord() {
		return commandWord;
	}

	/**
	 * @param commandWord
	 *            the commandWord to set
	 */
	public void setCommandWord(byte commandWord) {
		this.commandWord = commandWord;
	}

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
	 * @return the apn
	 */
	public String getApn() {
		return apn;
	}

	/**
	 * @param apn
	 *            the apn to set
	 */
	public void setApn(String apn) {
		this.apn = apn;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the upgradeServerIp
	 */
	public String getUpgradeServerIp() {
		return upgradeServerIp;
	}

	/**
	 * @param upgradeServerIp
	 *            the upgradeServerIp to set
	 */
	public void setUpgradeServerIp(String upgradeServerIp) {
		this.upgradeServerIp = upgradeServerIp;
	}

	/**
	 * @return the upgradeServerPort
	 */
	public short getUpgradeServerPort() {
		return upgradeServerPort;
	}

	/**
	 * @param upgradeServerPort
	 *            the upgradeServerPort to set
	 */
	public void setUpgradeServerPort(short upgradeServerPort) {
		this.upgradeServerPort = upgradeServerPort;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8105 [");
		sb.append("commandWord=").append(commandWord).append(",");
		sb.append("deviceType=").append(deviceType).append(",");
		sb.append("firmFlag=").append(firmFlag).append(",");
		sb.append("hardwareVesion=").append(hardwareVesion).append(",");
		sb.append("softwareVesion=").append(softwareVesion).append(",");
		sb.append("apn=").append(apn).append(",");
		sb.append("userName=").append(userName).append(",");
		sb.append("password=").append(password).append(",");
		sb.append("upgradeServerIp=").append(upgradeServerIp).append(",");
		sb.append("upgradeServerPort=").append(upgradeServerPort).append("]");
		return sb.toString();
	}
}
