package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Map;

/**
 * 提问下发消息体数据格式Model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0 标志 UINT8 提问下发标志位定义见表 34 提问下发标志位定义 14+1 问题ID UINT32 14+5 问题 STRING
 * 最长100字节
 *
 *
 * @author xiahancheng 2018年5月2日 下午2:51:39
 * @see
 * @version 1.0
 */
public class P0x8302 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 标志<br>
	 * 0 1：紧急<br>
	 * 1 预留<br>
	 * 2 预留<br>
	 * 3 1：语音合成（TTS）播读<br>
	 * 4 1：广告屏显示<br>
	 * 5～7 预留<br>
	 *
	 */
	private byte sign;

	/**
	 * 问题ID
	 */
	private int problemID;

	/**
	 * 问题
	 */
	private String problem;

	/**
	 * 候选答案列表
	 */
	private Map<Byte, String> answer;

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
	 * @return the problemID
	 */
	public int getProblemID() {
		return problemID;
	}

	/**
	 * @param problemID
	 *            the problemID to set
	 */
	public void setProblemID(int problemID) {
		this.problemID = problemID;
	}

	/**
	 * @return the problem
	 */
	public String getProblem() {
		return problem;
	}

	/**
	 * @param problem
	 *            the problem to set
	 */
	public void setProblem(String problem) {
		this.problem = problem;
	}

	/**
	 * @return the answer
	 */
	public Map<Byte, String> getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(Map<Byte, String> answer) {
		this.answer = answer;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8302 [");
		sb.append("sign=").append(this.sign).append(",");
		sb.append("problemID=").append(this.problemID).append(",");
		sb.append("problem=").append(this.problem).append(",");
		sb.append("answer=").append(this.answer).append("]");
		return sb.toString();
	}

}
