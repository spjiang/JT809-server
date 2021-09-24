package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.List;

/**
 * 存储音频检索消息体数据格式Model定义
 * <p>
 * 14+0 应答流水号 UINT16 对应的车辆控制消息的流水号<br>
 * 14+2 检索总项数据包大小 UINT32 满足检索条件的音频总项包数据长度其值为总项数×4<br>
 * 14+6 当前检索项在总项数据中的偏移量 UINT32 本包数据在整个图像/视频总项中的偏移量，第一包数据为0<br>
 * 14+10 检索项 UINT32[]
 * 终端根据自身硬件性能确定数据包的大小；后台管理系统应能自适应。建议在网络条件不好的情况下每个数据包不超过512字节。<br>
 *
 * @author fanya 2018年5月2日 下午5:15:37
 * @see
 * @version 1.0
 */
public class P0x0805 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 应答流水号
	 */
	private short response;

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
	private List<Integer> search;

	public short getResponse() {
		return response;
	}

	public void setResponse(short response) {
		this.response = response;
	}

	public int getSearchDataSize() {
		return searchDataSize;
	}

	public void setSearchDataSize(int searchDataSize) {
		this.searchDataSize = searchDataSize;
	}

	public int getCurrentSearchDataOffset() {
		return currentSearchDataOffset;
	}

	public void setCurrentSearchDataOffset(int currentSearchDataOffset) {
		this.currentSearchDataOffset = currentSearchDataOffset;
	}

	/**
	 * @return the search
	 */
	public List<Integer> getSearch() {
		return search;
	}

	/**
	 * @param search
	 *            the search to set
	 */
	public void setSearch(List<Integer> search) {
		this.search = search;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0805 [");
		sb.append("response=").append(this.response).append(",");
		sb.append("searchDataSize=").append(this.searchDataSize).append(",");
		sb.append("currentSearchDataOffset=").append(this.currentSearchDataOffset).append(",");
		sb.append("search=").append(this.search).append("]");
		return sb.toString();
	}
}
