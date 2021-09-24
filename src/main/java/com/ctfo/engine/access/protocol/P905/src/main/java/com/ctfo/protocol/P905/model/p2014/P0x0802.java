package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;


import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.List;

/**
 * 存储图像应答消息体数据格式model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0 应答流水号 UINT16 对应的车辆控制消息的流水号<br>
 * 14+2 检索总项数据包大小 UINT32 满足检索条件的图像/视频总项包数据长度，其值为总项数×4<br>
 * 14+6 当前检索项在总项数据中的偏移量 UINT32 本包数据在整个图像/视频总项中的偏移量，第一包数据为0<br>
 * 14+10 检索项 UINT32[] 终端根据自身硬件性能确定数据包的大小；<br>
 *
 * @author xiahancheng 2018年5月4日 上午11:39:10
 * @see
 * @version 1.0
 */
public class P0x0802 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 答应流水号
	 */
	private short answerNumber;

	/**
	 * 检索总项数据包大小
	 */
	private int searchDataSize;

	/**
	 * 当前检索项在总项数据中的偏移量
	 */
	private int currentSearchDataOffset;

	/**
	 * 检索项
	 */
	private List<Integer> searchItems;

	/**
	 * @return the answerNumber
	 */
	public short getAnswerNumber() {
		return answerNumber;
	}

	/**
	 * @param answerNumber
	 *            the answerNumber to set
	 */
	public void setAnswerNumber(short answerNumber) {
		this.answerNumber = answerNumber;
	}

	/**
	 * @return the searchDataSize
	 */
	public int getSearchDataSize() {
		return searchDataSize;
	}

	/**
	 * @param searchDataSize
	 *            the searchDataSize to set
	 */
	public void setSearchDataSize(int searchDataSize) {
		this.searchDataSize = searchDataSize;
	}

	/**
	 * @return the currentSearchDataOffset
	 */
	public int getCurrentSearchDataOffset() {
		return currentSearchDataOffset;
	}

	/**
	 * @param currentSearchDataOffset
	 *            the currentSearchDataOffset to set
	 */
	public void setCurrentSearchDataOffset(int currentSearchDataOffset) {
		this.currentSearchDataOffset = currentSearchDataOffset;
	}

	/**
	 * @return the searchItems
	 */
	public List<Integer> getSearchItems() {
		return searchItems;
	}

	/**
	 * @param searchItems
	 *            the searchItems to set
	 */
	public void setSearchItems(List<Integer> searchItems) {
		this.searchItems = searchItems;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0802 [");
		sb.append("answerNumber=").append(this.answerNumber).append(",");
		sb.append("searchDataSize=").append(this.searchDataSize).append(",");
		sb.append("currentSearchDataOffset=").append(this.currentSearchDataOffset).append(",");
		sb.append("searchItems=").append(this.searchItems).append("]");
		return sb.toString();
	}

}
