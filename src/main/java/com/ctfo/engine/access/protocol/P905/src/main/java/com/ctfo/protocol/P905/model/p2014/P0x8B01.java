package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 驾驶员抢答结果命令消息体数据格式model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0 业务ID UINT32 根据消息体长度若无后面字段表示未中标 14+4 业务类型 UINT8 0：即时召车；1：预约召车；2：车辆指派 14+5
 * 乘客电话号码 STRING 业务描述 STRING 对乘客要车详细地点的描述
 *
 *
 * @author xiahancheng 2018年5月5日 上午11:36:21
 * @see
 * @version 1.0
 */
public class P0x8B01 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 业务ID
	 */
	private int businessId;

	/**
	 * 业务类型，0：即时召车；1：预约召车；2：车辆指派
	 */
	private Byte businessType;

	// ----ADD 2018-10-17 by liangyi 兼容新终端标准 START

	/**
	 * 用车时间,BCD[6],YYMMDDHHNNSS，全零表示不启用
	 */
	private Date useTime;

	/**
	 * 乘客位置经度,0.0001’，填充为零表示不启用
	 */
	private Double useLng;

	/**
	 * 乘客位置纬度，0.0001’，填充为零表示不启用
	 */
	private Double useLat;

	/**
	 * 目的地经度,0.0001’，填充为零表示不启用
	 */
	private Double destLng;

	/**
	 * 目的地纬度,0.0001’，填充为零表示不启用
	 */
	private Double destLat;

	/** 电召服务费,BCD[2],格式为×××.×元，全0表示无服务费 */
	private Float tips;

	// ----ADD 2018-10-17 by liangyi 兼容新终端标准 END

	/**
	 * 乘客电话号码
	 */
	private String passengerPhone;

	/**
	 * 业务描述，对乘客要车详细地点的描述
	 */
	private String businessDescribe;

	/**
	 * @return the businessId
	 */
	public int getBusinessId() {
		return businessId;
	}

	/**
	 * @param businessId
	 *            the businessId to set
	 */
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	/**
	 * @return the businessType
	 */
	public Byte getBusinessType() {
		return businessType;
	}

	/**
	 * @param businessType
	 *            the businessType to set
	 */
	public void setBusinessType(Byte businessType) {
		this.businessType = businessType;
	}

	/**
	 * @return the passengerPhone
	 */
	public String getPassengerPhone() {
		return passengerPhone;
	}

	/**
	 * @param passengerPhone
	 *            the passengerPhone to set
	 */
	public void setPassengerPhone(String passengerPhone) {
		this.passengerPhone = passengerPhone;
	}

	/**
	 * @return the businessDescribe
	 */
	public String getBusinessDescribe() {
		return businessDescribe;
	}

	/**
	 * @param businessDescribe
	 *            the businessDescribe to set
	 */
	public void setBusinessDescribe(String businessDescribe) {
		this.businessDescribe = businessDescribe;
	}
	/**
	 * @return the useTime
	 */
	public Date getUseTime() {
		return useTime;
	}

	/**
	 * @param useTime the useTime to set
	 */
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	/**
	 * @return the useLng
	 */
	public Double getUseLng() {
		return useLng;
	}

	/**
	 * @param useLng the useLng to set
	 */
	public void setUseLng(Double useLng) {
		this.useLng = useLng;
	}

	/**
	 * @return the useLat
	 */
	public Double getUseLat() {
		return useLat;
	}

	/**
	 * @param useLat the useLat to set
	 */
	public void setUseLat(Double useLat) {
		this.useLat = useLat;
	}

	/**
	 * @return the destLng
	 */
	public Double getDestLng() {
		return destLng;
	}

	/**
	 * @param destLng the destLng to set
	 */
	public void setDestLng(Double destLng) {
		this.destLng = destLng;
	}

	/**
	 * @return the destLat
	 */
	public Double getDestLat() {
		return destLat;
	}

	/**
	 * @param destLat the destLat to set
	 */
	public void setDestLat(Double destLat) {
		this.destLat = destLat;
	}

	/**
	 * @return the tips
	 */
	public Float getTips() {
		return tips;
	}

	/**
	 * @param tips the tips to set
	 */
	public void setTips(Float tips) {
		this.tips = tips;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8B01 [");
		sb.append("businessId=").append(this.businessId).append(",");
		sb.append("businessType=").append(this.businessType).append(",");
		sb.append("useTime=").append(this.useTime).append(",");
		sb.append("useLng=").append(this.useLng).append(",");
		sb.append("useLat=").append(this.useLat).append(",");
		sb.append("destLng=").append(this.destLng).append(",");
		sb.append("destLat=").append(this.destLat).append(",");
		sb.append("tips=").append(this.tips).append(",");
		sb.append("passengerPhone=").append(this.passengerPhone).append(",");
		sb.append("businessDescribe=").append(businessDescribe).append("]");
		return sb.toString();
	}

}
