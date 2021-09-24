package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Map;

/**
 * 事件设置消息体数据格式Model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0	事件项个数	UINT8	0：删除终端现有所有事件
 * 14+1	事件项列表	BYTE	长度不大于499字节，否则分多条消息下发;数据项组成格式见表 31 事件项组成数据格式
 *
 * @author xiahancheng 2018年5月2日 上午9:39:30
 * @see
 * @version 1.0
 */
public class P0x8301 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 事件项组成数据格式
	 *  <p>
	 *  起始字节 字段 数据类型 说明<br>
	 *  P+0	事件ID	UINT8	若终端已有同ID的事件，则被覆盖
	 *  P+1	事件内容	STRING	最长为20字节
	 */


	/**
	 * 事件项个数
	 */
	private byte eventNumber;

	/**
	 * 事件列表
	 */
	private Map<Byte, String> eventItems;

	/**
	 * @return the eventNumber
	 */
	public byte getEventNumber() {
		return eventNumber;
	}

	/**
	 * @param eventNumber the eventNumber to set
	 */
	public void setEventNumber(byte eventNumber) {
		this.eventNumber = eventNumber;
	}

	/**
	 * @return the eventItems
	 */
	public Map<Byte, String> getEventItems() {
		return eventItems;
	}

	/**
	 * @param eventItems the eventItems to set
	 */
	public void setEventItems(Map<Byte, String> eventItems) {
		this.eventItems = eventItems;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8301 [");
		sb.append("eventNumber=").append(this.eventNumber).append(",");
		sb.append("eventItems=").append(this.eventItems).append("]");
		return sb.toString();
	}







}
