package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 电话回拨消息体数据格式Model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0	标志	UINT8	0:普通通话；1:监听
 * 14+1	电话号码	STRING	最长为20字节
 *
 * @author xiahancheng 2018年5月3日 上午9:55:15
 * @see
 * @version 1.0
 */
public class P0x8400 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 标志,0:普通通话；1:监听
	 */
	private byte sign;

	/**
	 * 电话号码,最长为19字节
	 */
	private String phoneNumber;

	/**
	 * @return the sign
	 */
	public byte getSign() {
		return sign;
	}

	/**
	 * @param sign the sign to set
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
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8400 [");
		sb.append("sign=").append(this.sign).append(",");
		sb.append("phoneNumber=").append(this.phoneNumber).append("]");
		return sb.toString();
	}

}
