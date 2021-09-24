package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.List;

/**
 * 设备巡检应答
 *
 * <p>
 * T为设备类型，参见10.2编号编码规则-设备类型代码定义；<br>
 * L为对应设备巡检结果的长度，L的数据类型为UINT8；<br>
 * V为对应设备巡检内容（查询设备状态指令中，设备返回的数据区）；<br>
 * 当T=0x00时标识对终端进行巡检，终端巡检返回的数据格式定义见表 66 终端巡检应答消息体数据格式。
 *
 * @author 凉意 2018年5月16日 下午8:07:48
 * @see
 * @version 1.0
 */
public class P0x0B11 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<P0x0B11> list;

	/**
	 * 设备类型
	 */
	private int equipType;
	/**
	 * 获取值
	 */
	private String getValue;

	/**
	 * 终端设备序列号 BCD[5]
	 */
	private String equipSerId;
	/**
	 * 硬件版本号 BCD
	 */
	private String hardwareVersion;

	/**
	 * 软件版本号 BCD[2] MCU参数长度为 2 计价器参数长度为 1 顶灯参数长度1
	 */
	private String softwareVersion;

	/**
	 * 终端设备状态 UNIT32
	 */
	private int equipStatus;

	/**
	 * 终端报警标志 UNIT32
	 */
	private int equipAlarm;
	/**
	 * 签到缓存数据条数 INT8
	 */
	private byte loginCacheLine;
	/**
	 * 签退缓存数据条数 INT8
	 */
	private byte logOutCacheLine;
	/**
	 * 营运记录缓存条数 INT8
	 */
	private byte opDataCacheLine;
	/**
	 * 一卡通交易缓存条数 INT8
	 */
	private byte cardOpDataCacheLine;


	/**
	 * @return the equipType
	 */
	public int getEquipType() {
		return equipType;
	}

	/**
	 * @param equipType the equipType to set
	 */
	public void setEquipType(int equipType) {
		this.equipType = equipType;
	}

	/**
	 * @return the getValue
	 */
	public String getGetValue() {
		return getValue;
	}

	/**
	 * @param getValue the getValue to set
	 */
	public void setGetValue(String getValue) {
		this.getValue = getValue;
	}

	/**
	 * @return the equipSerId
	 */
	public String getEquipSerId() {
		return equipSerId;
	}

	/**
	 * @param equipSerId
	 *            the equipSerId to set
	 */
	public void setEquipSerId(String equipSerId) {
		this.equipSerId = equipSerId;
	}

	/**
	 * @return the hardwareVersion
	 */
	public String getHardwareVersion() {
		return hardwareVersion;
	}

	/**
	 * @param hardwareVersion
	 *            the hardwareVersion to set
	 */
	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}

	/**
	 * @return the softwareVersion
	 */
	public String getSoftwareVersion() {
		return softwareVersion;
	}

	/**
	 * @param softwareVersion
	 *            the softwareVersion to set
	 */
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	/**
	 * @return the equipStatus
	 */
	public int getEquipStatus() {
		return equipStatus;
	}

	/**
	 * @param equipStatus
	 *            the equipStatus to set
	 */
	public void setEquipStatus(int equipStatus) {
		this.equipStatus = equipStatus;
	}

	/**
	 * @return the equipAlarm
	 */
	public int getEquipAlarm() {
		return equipAlarm;
	}

	/**
	 * @param equipAlarm
	 *            the equipAlarm to set
	 */
	public void setEquipAlarm(int equipAlarm) {
		this.equipAlarm = equipAlarm;
	}

	/**
	 * @return the loginCacheLine
	 */
	public byte getLoginCacheLine() {
		return loginCacheLine;
	}

	/**
	 * @param loginCacheLine
	 *            the loginCacheLine to set
	 */
	public void setLoginCacheLine(byte loginCacheLine) {
		this.loginCacheLine = loginCacheLine;
	}

	/**
	 * @return the logOutCacheLine
	 */
	public byte getLogOutCacheLine() {
		return logOutCacheLine;
	}

	/**
	 * @param logOutCacheLine
	 *            the logOutCacheLine to set
	 */
	public void setLogOutCacheLine(byte logOutCacheLine) {
		this.logOutCacheLine = logOutCacheLine;
	}

	/**
	 * @return the opDataCacheLine
	 */
	public byte getOpDataCacheLine() {
		return opDataCacheLine;
	}

	/**
	 * @param opDataCacheLine
	 *            the opDataCacheLine to set
	 */
	public void setOpDataCacheLine(byte opDataCacheLine) {
		this.opDataCacheLine = opDataCacheLine;
	}

	/**
	 * @return the cardOpDataCacheLine
	 */
	public byte getCardOpDataCacheLine() {
		return cardOpDataCacheLine;
	}

	/**
	 * @param cardOpDataCacheLine
	 *            the cardOpDataCacheLine to set
	 */
	public void setCardOpDataCacheLine(byte cardOpDataCacheLine) {
		this.cardOpDataCacheLine = cardOpDataCacheLine;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the list
	 */
	public List<P0x0B11> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<P0x0B11> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0B11 [");
		sb.append("equipType=").append(equipType).append(",");
		sb.append("getValue=").append(getValue).append(",");
		sb.append("equipSerId=").append(equipSerId).append(",");
		sb.append("hardwareVersion=").append(hardwareVersion).append(",");
		sb.append("softwareVersion=").append(softwareVersion).append(",");
		sb.append("equipStatus=").append(equipStatus).append(",");
		sb.append("equipAlarm=").append(equipAlarm).append(",");
		sb.append("loginCacheLine=").append(loginCacheLine).append(",");
		sb.append("logOutCacheLine=").append(logOutCacheLine).append(",");
		sb.append("opDataCacheLine=").append(opDataCacheLine).append(",");
		sb.append("cardOpDataCacheLine=").append(cardOpDataCacheLine).append("]");
		return sb.toString();
	}
}
