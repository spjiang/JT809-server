package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 下班签退信息上传命令消息体数据格式model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0 位置基本信息 详见0x0200交易<br>
 * 14+25 单位代码 ASC[16] 不足16 位右补0x00<br>
 * 14+41 司机代码 ASC[19] IC 卡从业资格证号为身份证号，不足19 位右补0x00<br>
 * 14+60 车牌号 BYTE[6] 车牌号，ASCII字符<br>
 * 14+66 K值 BCD[2] 格式为XXXX，最大9999<br>
 * 14+68 当班开机时间 BCD[6] YYYY-MM-DD-hh-mm<br>
 * 14+74 当班关机时间 BCD[6] YYYY-MM-DD-hh-mm<br>
 * 14+80 当班公里 BCD[3] 格式为XXXXX.X公里<br>
 * 14+83 当班营运公里 BCD[3] 格式为XXXXX.X公里<br>
 * 14+86 车次 BCD[2] 格式为XXXX，最大9999<br>
 * 14+88 计时时间 BCD[3] <br>
 * 14+91 总计金额 BCD[3] 格式XXXXX.X元<br>
 * 14+94 卡收金额 BCD[3] 格式XXXXX.X元<br>
 * 14+97 卡次 BCD[2] 格式为XXXX，最大9999<br>
 * 14+99 班间公里 BCD[2] 格式XXX.X公里（上一班签退到本班签到的公里数）<br>
 * 14+101 总计公里 BCD[4] 格式为XXXXX.X公里（计价器安装后累积的里程）<br>
 * 14+105 总营运公里 BCD[4] 格式为XXXXX.X公里（计价器安装后累积的里程）<br>
 * 14+109 单价 BCD[2] 格式XX.XX元<br>
 * 14+111 总营运次数 UINT32 高位在前，低位在后<br>
 * 14+115 扩展属性 BYTE[N] 可根据实际管理需要进行扩展，当有扩展需求时，则该项有内容，否则该项无内容。 其中第1 字节，签退方式，0-正常签退
 * 1-强制签退
 *
 * @author xiahancheng 2018年5月7日 下午5:42:58
 * @see
 * @version 1.0
 */
public class P0x0B04 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 位置基本信息
	 */
	private P0x0200 position;

	/**
	 * 单位代码 BYTE [16]	ASCII字符，长度不足16byte，右补0x00
	 */
	private String companyCode;

	/**
	 * 司机代码 BYTE [19]	ASCII字符，长度不足19byte，右补0x00
	 */
	private String driverCode;

	/**
	 * 车牌号
	 */
	private String plateNumber;

	/**
	 * K值,BCD[2]
	 */
	private int kvalue;

	/**
	 * 当班开机时间,BCD[6]
	 */
	private Date startTime;

	/**
	 * 当班关机时间,BCD[6]
	 */
	private Date endTime;

	/**
	 * 当班公里
	 */
	private double beOnduty;

	/**
	 * 当班营运公里
	 */
	private double beOndutyOperate;

	/**
	 * 车次,BCD[2]
	 */
	private int trainNumber;

	/**
	 * 计时时间,BCD[3]
	 */
	private String time;

	/**
	 * 总计金额,BCD[3],格式XXXXX.X元
	 */
	private double totalIncome;

	/**
	 * 卡收金额,BCD[3],格式XXXXX.X元
	 */
	private double cardNumberIncome;

	/**
	 * 卡次,BCD[2],格式为XXXX，最大9999
	 */
	private int cardsecond;

	/**
	 * 班间公里,BCD[2],格式XXX.X公里（上一班签退到本班签到的公里数）
	 */
	private double workMileage;

	/**
	 * 总计公里,BCD[4],格式为XXXXXXX.X公里（计价器安装后累积的里程）
	 */
	private double totalMileage;

	/**
	 * 总营运公里,BCD[4],格式为XXXXXXX.X公里（计价器安装后累积的里程）
	 */
	private double totalOperateMileage;

	/**
	 * 单价,BCD[2],格式XX.XX元
	 */
	private double unitPrice;

	/**
	 * 总营运次数
	 */
	private int totalOperateNumber;

	/**
	 * 签退方式，0x00：正常签退；0x01：强制签退
	 */
	private byte logoutMode;

	/**
	 * 扩展属性,其他可根据实际管理需要进行扩展，当有扩展需求时，则该项有内容，否则该项无内容
	 * <br>
	 * 第一个字节为签退类型：0x01：驾驶员手工签退；0x02:自动签退；0x03:锁表签退
	 */
	private String attribute;


	/**
	 * @return the position
	 */
	public P0x0200 getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(P0x0200 position) {
		this.position = position;
	}

	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param companyCode
	 *            the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * @return the driverCode
	 */
	public String getDriverCode() {
		return driverCode;
	}

	/**
	 * @param driverCode
	 *            the driverCode to set
	 */
	public void setDriverCode(String driverCode) {
		this.driverCode = driverCode;
	}

	/**
	 * @return the plateNumber
	 */
	public String getPlateNumber() {
		return plateNumber;
	}

	/**
	 * @param plateNumber
	 *            the plateNumber to set
	 */
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	/**
	 * @return the kvalue
	 */
	public int getKvalue() {
		return kvalue;
	}

	/**
	 * @param kvalue
	 *            the kvalue to set
	 */
	public void setKvalue(int kvalue) {
		this.kvalue = kvalue;
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
	 * @return the beOnduty
	 */
	public double getBeOnduty() {
		return beOnduty;
	}

	/**
	 * @param beOnduty
	 *            the beOnduty to set
	 */
	public void setBeOnduty(double beOnduty) {
		this.beOnduty = beOnduty;
	}

	/**
	 * @return the beOndutyOperate
	 */
	public double getBeOndutyOperate() {
		return beOndutyOperate;
	}

	/**
	 * @param beOndutyOperate
	 *            the beOndutyOperate to set
	 */
	public void setBeOndutyOperate(double beOndutyOperate) {
		this.beOndutyOperate = beOndutyOperate;
	}

	/**
	 * @return the trainNumber
	 */
	public int getTrainNumber() {
		return trainNumber;
	}

	/**
	 * @param trainNumber
	 *            the trainNumber to set
	 */
	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the totalIncome
	 */
	public double getTotalIncome() {
		return totalIncome;
	}

	/**
	 * @param totalIncome
	 *            the totalIncome to set
	 */
	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	/**
	 * @return the cardNumberIncome
	 */
	public double getCardNumberIncome() {
		return cardNumberIncome;
	}

	/**
	 * @param cardNumberIncome
	 *            the cardNumberIncome to set
	 */
	public void setCardNumberIncome(double cardNumberIncome) {
		this.cardNumberIncome = cardNumberIncome;
	}

	/**
	 * @return the cardsecond
	 */
	public int getCardsecond() {
		return cardsecond;
	}

	/**
	 * @param cardsecond
	 *            the cardsecond to set
	 */
	public void setCardsecond(int cardsecond) {
		this.cardsecond = cardsecond;
	}

	/**
	 * @return the workMileage
	 */
	public double getWorkMileage() {
		return workMileage;
	}

	/**
	 * @param workMileage
	 *            the workMileage to set
	 */
	public void setWorkMileage(double workMileage) {
		this.workMileage = workMileage;
	}

	/**
	 * @return the totalMileage
	 */
	public double getTotalMileage() {
		return totalMileage;
	}

	/**
	 * @param totalMileage
	 *            the totalMileage to set
	 */
	public void setTotalMileage(double totalMileage) {
		this.totalMileage = totalMileage;
	}

	/**
	 * @return the totalOperateMileage
	 */
	public double getTotalOperateMileage() {
		return totalOperateMileage;
	}

	/**
	 * @param totalOperateMileage
	 *            the totalOperateMileage to set
	 */
	public void setTotalOperateMileage(double totalOperateMileage) {
		this.totalOperateMileage = totalOperateMileage;
	}

	/**
	 * @return the unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice
	 *            the unitPrice to set
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the totalOperateNumber
	 */
	public int getTotalOperateNumber() {
		return totalOperateNumber;
	}

	/**
	 * @param totalOperateNumber
	 *            the totalOperateNumber to set
	 */
	public void setTotalOperateNumber(int totalOperateNumber) {
		this.totalOperateNumber = totalOperateNumber;
	}

	/**
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * @param attribute
	 *            the attribute to set
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	/**
	 * @return the logoutMode
	 */
	public byte getLogoutMode() {
		return logoutMode;
	}

	/**
	 * @param logoutMode the logoutMode to set
	 */
	public void setLogoutMode(byte logoutMode) {
		this.logoutMode = logoutMode;
	}


	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0B04 [");
		sb.append("position=").append(this.position).append(",");
		sb.append("companyCode=").append(this.companyCode).append(",");
		sb.append("driverCode=").append(this.driverCode).append(",");
		sb.append("plateNumber=").append(this.plateNumber).append(",");
		sb.append("kvalue=").append(this.kvalue).append(",");
		sb.append("startTime=").append(this.startTime).append(",");
		sb.append("endTime=").append(this.endTime).append(",");
		sb.append("beOnduty=").append(this.beOnduty).append(",");
		sb.append("beOndutyOperate=").append(this.beOndutyOperate).append(",");
		sb.append("trainNumber=").append(this.trainNumber).append(",");
		sb.append("time=").append(this.time).append(",");
		sb.append("totalIncome=").append(this.totalIncome).append(",");
		sb.append("cardNumberIncome=").append(this.cardNumberIncome).append(",");
		sb.append("cardsecond=").append(this.cardsecond).append(",");
		sb.append("workMileage=").append(this.workMileage).append(",");
		sb.append("totalMileage=").append(this.totalMileage).append(",");
		sb.append("totalOperateMileage=").append(this.totalOperateMileage).append(",");
		sb.append("unitPrice=").append(this.unitPrice).append(",");
		sb.append("totalOperateNumber=").append(this.totalOperateNumber).append(",");
		sb.append("logoutMode=").append(this.logoutMode).append(",");
		sb.append("attribute=").append(this.attribute).append("]");
		return sb.toString();
	}

}
