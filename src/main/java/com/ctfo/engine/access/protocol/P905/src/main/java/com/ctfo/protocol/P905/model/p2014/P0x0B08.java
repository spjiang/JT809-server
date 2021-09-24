package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 终端请求抢答任务的取消命令消息体数据格式model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0 业务ID UINT32 14+4 取消原因 UINT8 0:事故 1：路堵 2：其他或采用下发取消原因
 *
 * @author xiahancheng 2018年5月5日 下午12:29:36
 * @see
 * @version 1.0
 */
public class P0x0B08 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 业务ID
	 */
	private int businessId;

	/**
	 * 取消原因,0:事故 1：路堵 2：其他 或采用下发取消原因
	 */
	private byte cancel;

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
	 * @return the cancel
	 */
	public byte getCancel() {
		return cancel;
	}

	/**
	 * @param cancel
	 *            the cancel to set
	 */
	public void setCancel(byte cancel) {
		this.cancel = cancel;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0B08 [");
		sb.append("businessId=").append(this.businessId).append(",");
		sb.append("cancel=").append(this.cancel).append("]");
		return sb.toString();
	}

}
