package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 音频检索消息体数据格式Model定义
 * <p>
 * 14+0 录音原因 UINT8 0:正常录音 1:乘客投诉 2：报警录音<br>
 * 14+1 起始时间 BCD[6] YY-MM-DD-hh-mm-ss<br>
 * 14+7 结束时间 BCD[6] YY-MM-DD-hh-mm-ss<br>
 *
 * @author fanya 2018年5月2日 下午5:15:37
 * @see
 * @version 1.0
 */
public class P0x8805 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 录音原因,0：正常录音；1：乘客投诉；2：报警录音
	 */
	private byte recordings;

	/**
	 * 起始时间
	 */
	private Date startTime;

	/**
	 * 结束时间
	 */
	private Date endTime;

	public byte getRecordings() {
		return recordings;
	}

	public void setRecordings(byte recordings) {
		this.recordings = recordings;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8805 [");
		sb.append("recordings=").append(this.recordings).append(",");
		sb.append("startTime=").append(this.startTime).append(",");
		sb.append("endTime=").append(this.endTime).append("]");
		return sb.toString();
	}
}
