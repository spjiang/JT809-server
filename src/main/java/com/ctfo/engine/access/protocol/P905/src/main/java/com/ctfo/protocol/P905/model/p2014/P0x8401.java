package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;


import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.vo.P0x8401VO;

import java.io.Serializable;
import java.util.List;

/**
 * 设置电话本消息体数据格式Model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0	联系人总数	UINT8	本条消息中联系人总数，0表示删除终端上所有存储的联系人
 * 14+1	联系人项		最长为499字节，超过则采用多条消息；数据项格式见表 39 电话本联系人项数据格式
 * @author xiahancheng 2018年5月3日 上午10:21:48
 * @see
 * @version 1.0
 */
public class P0x8401 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 联系人总数
	 */
	private byte contactsSum;

	/**
	 * 联系人项列表
	 */
	private List<P0x8401VO> contactsItems;

	/**
	 * @return the contactsSum
	 */
	public byte getContactsSum() {
		return contactsSum;
	}

	/**
	 * @param contactsSum the contactsSum to set
	 */
	public void setContactsSum(byte contactsSum) {
		this.contactsSum = contactsSum;
	}

	/**
	 * @return the contactsItems
	 */
	public List<P0x8401VO> getContactsItems() {
		return contactsItems;
	}

	/**
	 * @param contactsItems the contactsItems to set
	 */
	public void setContactsItems(List<P0x8401VO> contactsItems) {
		this.contactsItems = contactsItems;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8401 [");
		sb.append("contactsSum=").append(this.contactsSum).append(",");
		sb.append("contactsItems=").append(this.contactsItems).append("]");
		return sb.toString();
	}
}
