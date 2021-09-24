package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Date;

/**
 * A.2.7.73 人脸识别结果汇报
 *
 * @author 凉意 2019年8月9日 下午9:47:32
 * @see
 * @version 1.0
 */
public class P0x9210 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 12+0 总长度 DWORD 人脸识别结果数据总长度
	 */
	private int dataLen;

	/**
	 * 12+4 当前偏移量 DWORD 本包数据的起始地址，第一包从0开始
	 */
	private int currentDataOffset;

	/**
	 * 事件类型 UINT8 0：签到匹配；1：动态查岗；
	 */
	private byte eventType;

	/**
	 * 默认匹配率 UINT8 单位：百分比；
	 */
	private byte defaultMatchRatio;

	/**
	 * 检测匹配率 UINT8 单位：百分比；
	 */
	private byte checkMatchRatio;

	/**
	 * 匹配结果 UINT8 0-失败，1：成功；
	 */
	private byte matchResult;

	/**
	 * 检测时间 BCD[6] 格式：YYMMDDhhmmss
	 */
	private Date checkTime;

	/**
	 * 驾驶员从业资格证号 BYTE [19] 匹配成功驾驶员从业资格证号，不足右补0x00; 匹配失败则填匹配率最高的驾驶员从业资格证号；
	 */
	private String driverId;

	/**
	 * 人脸图片数据
	 */
	private String dataHex;

	/**
	 * @return the dataLen
	 */
	public int getDataLen() {
		return dataLen;
	}

	/**
	 * @param dataLen
	 *            the dataLen to set
	 */
	public void setDataLen(int dataLen) {
		this.dataLen = dataLen;
	}

	/**
	 * @return the currentDataOffset
	 */
	public int getCurrentDataOffset() {
		return currentDataOffset;
	}

	/**
	 * @param currentDataOffset
	 *            the currentDataOffset to set
	 */
	public void setCurrentDataOffset(int currentDataOffset) {
		this.currentDataOffset = currentDataOffset;
	}

	/**
	 * @return the eventType
	 */
	public byte getEventType() {
		return eventType;
	}

	/**
	 * @param eventType
	 *            the eventType to set
	 */
	public void setEventType(byte eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the defaultMatchRatio
	 */
	public byte getDefaultMatchRatio() {
		return defaultMatchRatio;
	}

	/**
	 * @param defaultMatchRatio
	 *            the defaultMatchRatio to set
	 */
	public void setDefaultMatchRatio(byte defaultMatchRatio) {
		this.defaultMatchRatio = defaultMatchRatio;
	}

	/**
	 * @return the checkMatchRatio
	 */
	public byte getCheckMatchRatio() {
		return checkMatchRatio;
	}

	/**
	 * @param checkMatchRatio
	 *            the checkMatchRatio to set
	 */
	public void setCheckMatchRatio(byte checkMatchRatio) {
		this.checkMatchRatio = checkMatchRatio;
	}

	/**
	 * @return the matchResult
	 */
	public byte getMatchResult() {
		return matchResult;
	}

	/**
	 * @param matchResult
	 *            the matchResult to set
	 */
	public void setMatchResult(byte matchResult) {
		this.matchResult = matchResult;
	}

	/**
	 * @return the checkTime
	 */
	public Date getCheckTime() {
		return checkTime;
	}

	/**
	 * @param checkTime
	 *            the checkTime to set
	 */
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	/**
	 * @return the driverId
	 */
	public String getDriverId() {
		return driverId;
	}

	/**
	 * @param driverId
	 *            the driverId to set
	 */
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	/**
	 * @return the dataHex
	 */
	public String getDataHex() {
		return dataHex;
	}

	/**
	 * @param dataHex
	 *            the dataHex to set
	 */
	public void setDataHex(String dataHex) {
		this.dataHex = dataHex;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("P0x9210 [");
		buf.append("dataLen=").append(dataLen).append(",");
		buf.append("currentDataOffset=").append(currentDataOffset).append(",");
		buf.append("eventType=").append(eventType).append(",");
		buf.append("defaultMatchRatio=").append(defaultMatchRatio).append(",");
		buf.append("checkMatchRatio=").append(checkMatchRatio).append(",");
		buf.append("matchResult=").append(matchResult).append(",");
		buf.append("checkTime=").append(checkTime).append(",");
		buf.append("driverId=").append(driverId).append(",");
		buf.append("dataHex=").append(dataHex).append("]");
		return buf.toString();
	}
}
