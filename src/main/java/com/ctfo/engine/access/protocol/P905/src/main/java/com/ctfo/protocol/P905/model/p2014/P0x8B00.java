package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单任务下发命令消息体数据格式model定义
 *
 * <p>
 * 14+0	业务ID	UINT32
 * 14+4	业务类型	UINT8	0：即时召车；1：预约召车；2：车辆指派
 * 14+5	要车时间  BCD[6]	YY-MM-DD-hh-mm-ss
 * 14+11  业务描述  STRING	对乘客要车大概地点的描述
 * @author xiahancheng 2018年5月4日 下午4:22:11
 * @see
 * @version 1.0
 */
public class P0x8B00 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 业务ID
	 */
	private int businessId;

	/**
	 *业务类型，0：即时召车；1：预约召车；2：车辆指派
	 */
	private byte businessType;

	/**
	 * 要车时间，YYMMDDhhmmss
	 */
	private Date catTime;

	/**
	 * 业务描述
	 */
	private String businessDescribe;

	/**
	 * @return the businessId
	 */
	public int getBusinessId() {
		return businessId;
	}

	/**
	 * @param businessId the businessId to set
	 */
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	/**
	 * @return the businessType
	 */
	public byte getBusinessType() {
		return businessType;
	}

	/**
	 * @param businessType the businessType to set
	 */
	public void setBusinessType(byte businessType) {
		this.businessType = businessType;
	}

	/**
	 * @return the catTime
	 */
	public Date getCatTime() {
		return catTime;
	}

	/**
	 * @param catTime the catTime to set
	 */
	public void setCatTime(Date catTime) {
		this.catTime = catTime;
	}

	/**
	 * @return the businessDescribe
	 */
	public String getBusinessDescribe() {
		return businessDescribe;
	}

	/**
	 * @param businessDescribe the businessDescribe to set
	 */
	public void setBusinessDescribe(String businessDescribe) {
		this.businessDescribe = businessDescribe;
	}


	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8B00 [");
		sb.append("businessId=").append(this.businessId).append(",");
		sb.append("businessType=").append(this.businessType).append(",");
		sb.append("catTime=").append(this.catTime).append(",");
		sb.append("businessDescribe=").append(this.businessDescribe).append("]");
		return sb.toString();
	}


}
