package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 中心通用应答消息体数据格式Model定义
 * <p>
 * 14+0 应答流水号 UINT16 对应的终端消息的流水号<br>
 * 14+2 应答ID UINT16 对应的终端消息的ID<br>
 * 14+4 结果 UINT8 0：成功/确认；1：失败；2：消息有误； <br>
 *
 * @author 凉意 2018年4月22日 下午2:24:50
 * @see
 * @version 1.0
 */
public class P0x8001 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 应答流水号
	 */
	private short ansFlowNo;

	/**
	 * 应答ID
	 */
	private short ansId;

	/**
	 * 应答结果：0：成功/确认；1：失败；2：消息有误；
	 */
	private byte ansResult;

	/**
	 * @return the ansFlowNo
	 */
	public short getAnsFlowNo() {
		return ansFlowNo;
	}

	/**
	 * @param ansFlowNo
	 *            the ansFlowNo to set
	 */
	public void setAnsFlowNo(short ansFlowNo) {
		this.ansFlowNo = ansFlowNo;
	}

	/**
	 * @return the ansId
	 */
	public short getAnsId() {
		return ansId;
	}

	/**
	 * @param ansId
	 *            the ansId to set
	 */
	public void setAnsId(short ansId) {
		this.ansId = ansId;
	}

	/**
	 * @return the ansResult
	 */
	public byte getAnsResult() {
		return ansResult;
	}

	/**
	 * @param ansResult
	 *            the ansResult to set
	 */
	public void setAnsResult(byte ansResult) {
		this.ansResult = ansResult;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8001 [");
		sb.append("ansFlowNo=").append(this.ansFlowNo).append(",");
		sb.append("ansId=").append(this.ansId).append(",");
		sb.append("ansResult=").append(this.ansResult).append("]");
		return sb.toString();
	}
}
