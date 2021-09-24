package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;


import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 *中心取消订单命令消息体数据格式model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0	业务ID	UINT32
 *
 * @author xiahancheng 2018年5月5日 下午1:36:52
 * @see
 * @version 1.0
 */
public class P0x8B09 extends PModel implements Serializable {
private static final long serialVersionUID = 1L;

	/**
	 * 业务ID
	 */
	private int businessId;

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
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8B09 [");
		sb.append("businessId=").append(this.businessId).append("]");
		return sb.toString();
	}

}
