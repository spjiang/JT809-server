package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 位置信息查询应答
 *
 * @author 凉意 2018年5月18日 下午1:16:54
 * @see
 * @version 1.0
 */
public class P0x0201 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 应答流水号
	 */
	private short ansFlowNo;

	/**
	 * 位置定位数据
	 */
	private P0x0200 position;

	/**
	 * @return the ansFlowNo
	 */
	public short getAnsFlowNo() {
		return ansFlowNo;
	}

	/**
	 * @param ansFlowNo
	 *            the ansFlowNo to set
	 */
	public void setAnsFlowNo(short ansFlowNo) {
		this.ansFlowNo = ansFlowNo;
	}

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
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0201 [");
		sb.append("ansFlowNo=").append(ansFlowNo).append(",");
		sb.append("position=").append(position).append("]");
		return sb.toString();
	}
}
