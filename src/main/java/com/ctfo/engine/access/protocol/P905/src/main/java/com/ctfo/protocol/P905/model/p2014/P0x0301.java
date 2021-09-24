package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 事件报告消息体数据格式Model定义
 *
 * <p>
 * 14+0	事件ID	UINT8
 * @author xiahancheng 2018年5月2日 下午10:59:11
 * @see
 * @version 1.0
 */
public class P0x0301 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 事件ID
	 */
	private byte eventId;
	/**
	 * @return the eventId
	 */
	public byte getEventId() {
		return eventId;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(byte eventId) {
		this.eventId = eventId;
	}
	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0301 [");
		sb.append("eventId=").append(this.eventId).append("]");
		return sb.toString();
	}

}
