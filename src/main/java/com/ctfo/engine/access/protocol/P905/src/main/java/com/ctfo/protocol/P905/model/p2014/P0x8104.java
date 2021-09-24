package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.List;

/**
 * 查询终端参数消息体格式Model定义
 *
 * <p>
 * 14+0 参数ID UINT16[N] 一次可以查询多个参数
 *
 * @author 凉意 2018年4月26日 下午3:08:19
 * @see
 * @version 1.0
 */
public class P0x8104 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 参数ID列表，存储为16进制ID
	 */
	private List<String> paramIds;

	/**
	 * @return the paramIds
	 */
	public List<String> getParamIds() {
		return paramIds;
	}

	/**
	 * @param paramIds the paramIds to set
	 */
	public void setParamIds(List<String> paramIds) {
		this.paramIds = paramIds;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8104 [");
		sb.append("paramIds=").append(paramIds).append("]");
		return sb.toString();
	}
}
