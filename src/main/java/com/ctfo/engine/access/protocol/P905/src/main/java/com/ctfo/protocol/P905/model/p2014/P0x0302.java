package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 提问应答消息体数据格式Model定义
 *
 * <p>
 * 14+0	问题ID	UINT32
 * 14+4	答案ID	UINT8
 *
 * @author xiahancheng 2018年5月3日 上午8:59:07
 * @see
 * @version 1.0
 */
public class P0x0302 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 问题ID
	 */
	private int problemId;

	/**
	 * 答案ID
	 */
	private byte answerId;

	/**
	 * @return the problemId
	 */
	public int getProblemId() {
		return problemId;
	}

	/**
	 * @param problemId the problemId to set
	 */
	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}

	/**
	 * @return the answerId
	 */
	public byte getAnswerId() {
		return answerId;
	}

	/**
	 * @param answerId the answerId to set
	 */
	public void setAnswerId(byte answerId) {
		this.answerId = answerId;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0302 [");
		sb.append("problemId=").append(this.problemId).append(",");
		sb.append("answerId=").append(this.answerId).append("]");
		return sb.toString();
	}

}
