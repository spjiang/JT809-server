package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 中心取消订单
 * <p>
 * 应答为终端通用应答。 当因乘客原因或司机请求取消订单时，中心发送该指令给终端通知司机订单被取消（或取消请求被处理）。终端语音提示司机。
 *
 * @author 凉意 2018年5月16日 下午4:18:20
 * @see
 * @version 1.0
 */
public class P0x0B09 extends PModel implements Serializable {

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
		StringBuilder sb = new StringBuilder("P0x0B09 [");
		sb.append("businessId=").append(businessId).append("]");
		return sb.toString();
	}
}
