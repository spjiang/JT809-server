package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;


import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 驾驶员电召任务完成确认
 *
 * <p>
 * 应答为中心通用应答。 当司机的本次电召任务完成后，司机通过按键触发终端发送该指令给中心通知该订单完成。
 *
 * @author 凉意 2018年5月16日 下午4:10:30
 * @see
 * @version 1.0
 */
public class P0x0B07 extends PModel implements Serializable {

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
	 * @param businessId
	 *            the businessId to set
	 */
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0B07 [");
		sb.append("businessId=").append(businessId).append("]");
		return sb.toString();
	}

}
