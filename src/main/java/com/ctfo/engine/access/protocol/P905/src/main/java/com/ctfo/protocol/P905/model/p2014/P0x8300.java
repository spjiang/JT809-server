package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 文本下发消息体数据格式Model定义
 *
 * <p>
 * 14+0 标志 UINT8 文本信息标志位含义见表 29 文本信息标志位含义 14+1 文本信息 STRING 最长为499字节
 *
 * @author xiahancheng 2018年4月26日 下午3:37:58
 * @see
 * @version 1.0
 */
public class P0x8300 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 标志 <br>
	 * 0 1：紧急 <br>
	 * 1 预留 <br>
	 * 2 1：显示装置显示 <br>
	 * 3 1：语音合成播读 <br>
	 * 4 1：广告屏显示 5～7 预留<br>
	 *
	 */
	private byte sign;

	/**
	 * 文本信息
	 */
	private String textInformation;

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
	 * @return the textInformation
	 */
	public String getTextInformation() {
		return textInformation;
	}

	/**
	 * @param textInformation
	 *            the textInformation to set
	 */
	public void setTextInformation(String textInformation) {
		this.textInformation = textInformation;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8300 [");
		sb.append("sign=").append(this.sign).append(",");
		sb.append("textInformation=").append(this.textInformation).append("]");
		return sb.toString();
	}

}
