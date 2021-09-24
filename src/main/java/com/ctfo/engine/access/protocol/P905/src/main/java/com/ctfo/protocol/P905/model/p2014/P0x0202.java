package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 位置跟踪信息汇报数据格式Model定义
 *
 * <p>
 * 使用位置信息汇报(0x0200)消息体
 *
 * @author 凉意 2018年5月2日 下午2:59:30
 * @see
 * @version 1.0
 */
public class P0x0202 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private P0x0200 position;

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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0202 [");
		sb.append("position=").append(position).append("]");
		return sb.toString();
	}
}
