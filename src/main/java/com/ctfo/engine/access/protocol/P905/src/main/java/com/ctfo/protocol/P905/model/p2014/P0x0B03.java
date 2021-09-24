package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;


import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 上班签到信息上传命令消息体数据格式model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0	位置基本信息		详见0x0200交易
 * 14+25	单位代码	ASC[16]	不足16 位右补0x00
 * 14+41	司机代码	ASC[19]	IC 卡从业资格证号为身份证号，不足19 位右补0x00
 * 14+60	车牌号	BYTE[6]	车牌号，ASCII字符
 * 14+66	扩展属性	BYTE[N]	可根据实际管理需要进行扩展，当有扩展需求时，则该项有内容，否则该项无内容
 *
 * @author xiahancheng 2018年5月7日 上午9:49:00
 * @see
 * @version 1.0
 */
public class P0x0B03 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 位置基本信息
	 */
	private P0x0200 position;

	/**
	 * 企业经营许可证号	BYTE [16]	ASCII字符，长度不足16byte，右补0x00
	 */
	private String companyCode;

	/**
	 *司机代码 BYTE [19]	ASCII字符，长度不足19byte，右补0x00
	 */
	private String driverCode;

	/**
	 *车牌号 车牌号	BYTE[6]	车牌号，ASCII字符，不包含汉字
	 */
	private String plateNumber;

	/**
	 * 计价器开机时间,BCD[6],YYYY-MM-DD-hh-mm-ss
	 */
	private Date openMachineTime;
	/**
	 *扩展属性
	 */
	private String attribute;

	/**
	 * @return the position
	 */
	public P0x0200 getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
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
	 * @param companyCode the companyCode to set
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
	 * @param driverCode the driverCode to set
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
	 * @param plateNumber the plateNumber to set
	 */
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}


	/**
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	/**
	 * @return the openMachineTime
	 */
	public Date getOpenMachineTime() {
		return openMachineTime;
	}

	/**
	 * @param openMachineTime the openMachineTime to set
	 */
	public void setOpenMachineTime(Date openMachineTime) {
		this.openMachineTime = openMachineTime;
	}


	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0B03 [");
		sb.append("position=").append(this.position).append(",");
		sb.append("companyCode=").append(this.companyCode).append(",");
		sb.append("driverCode=").append(this.driverCode).append(",");
		sb.append("plateNumber=").append(this.plateNumber).append(",");
		sb.append("openMachineTime=").append(this.openMachineTime).append(",");
		sb.append("attribute=").append(this.attribute).append("]");
		return sb.toString();
	}



}
