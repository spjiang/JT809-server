package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 车辆控制应答消息体数据格式Model定义
 *
 * <p>
 * 14+0	应答流水号	UINT16	对应的车辆控制消息的流水号
 * 14+2	位置信息汇报(0X0200)消息体  (0X0200)对象  根据对应得状态位判断控制成功与否
 *
 * @author xiahancheng 2018年5月3日 下午3:43:29
 * @see
 * @version 1.0
 */
public class P0x0500 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 应答流水号
	 */
	private short respondNumber;

	/**
	 * 位置信息汇报(0X0200)消息体
	 */
	private P0x0200 position;


	/**
	 * @return the respondNumber
	 */
	public short getRespondNumber() {
		return respondNumber;
	}


	/**
	 * @param respondNumber the respondNumber to set
	 */
	public void setRespondNumber(short respondNumber) {
		this.respondNumber = respondNumber;
	}

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
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0500 [");
		sb.append("respondNumber=").append(this.respondNumber).append(",");
		sb.append("position=").append(this.position).append("]");
		return sb.toString();
	}


}
