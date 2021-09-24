package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.vo.P0x8103VO;

import java.io.Serializable;
import java.util.List;

/**
 * 终端参数设置消息体数据格式Model定义,参考类<code>P0x8103VO</code>
 *
 * <p>
 * 起始字节 字段 数据类型<br>
 * 0 参数ID UINT16<br>
 * 2 参数长度 UINT8<br>
 * 3 参数值
 *
 * @author 凉意 2018年4月25日 下午5:23:16
 * @see
 * @version 1.0
 */
public class P0x8103 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 参数数据列表
	 */
	private List<P0x8103VO> params;

	/**
	 * @return the params
	 */
	public List<P0x8103VO> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(List<P0x8103VO> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8103 [");
		sb.append("params=").append(params).append("]");
		return sb.toString();
	}

}
