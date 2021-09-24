package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.vo;

import java.io.Serializable;

/**
 * 设置电话本消息体VO对象
 *
 * <p>
 * 电话本联系人项数据格式 P+0 标志 UINT8 1：呼入；2：呼出；3：呼入/呼出 P+1 电话号码 STRING 最长为20字节 联系人 STRING
 * 最长为10字节
 *
 *
 * @author xiahancheng 2018年5月3日 上午10:56:18
 * @see
 * @version 1.0
 */
public class P0x8401VO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 标志,Bit0-3：1：呼入；2：呼出；3：呼入/呼出； Bit4-6： 保留为0； Bit7：0-GSM语音号码；1-VOIP号码；
	 *
	 */
	private byte sign;

	/**
	 * 电话号码
	 */
	private String phoneNumber;

	/**
	 * 联系人
	 */
	private String contacts;

	/**
	 * @return the sign
	 */
	public byte getSign() {
		return sign;
	}

	/**
	 * @param sign
	 *            the sign to set
	 */
	public void setSign(byte sign) {
		this.sign = sign;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the contacts
	 */
	public String getContacts() {
		return contacts;
	}

	/**
	 * @param contacts
	 *            the contacts to set
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8401VO [");
		sb.append("sign=").append(sign).append(",");
		sb.append("phoneNumber=").append(phoneNumber);
		sb.append("contacts=").append(contacts).append("]");
		return sb.toString();
	}

}
