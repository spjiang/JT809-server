package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;


import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.vo.P0x8103VO;

import java.io.Serializable;
import java.util.List;

/**
 * 查询终端参数应答消息数据格式Model定义，参考类<code>P0x8103VO</code>
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0 应答流水号 UINT16 对应的查询终端参数消息的流水号<br>
 * 14+2 参数项列表 参数项格式和定义同设置终端参数<br>
 *
 * @author 凉意 2018年4月26日 下午3:58:05
 * @see
 * @version 1.0
 */
public class P0x0104 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 应答流水号，对应的查询终端参数消息的流水号
	 */
	private short ansFlowNo;

	/**
	 * 参数数据列表
	 */
	private List<P0x8103VO> params;

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
	 * @return the params
	 */
	public List<P0x8103VO> getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(List<P0x8103VO> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0104 [");
		sb.append("ansFlowNo=").append(ansFlowNo).append(",");
		sb.append("params=").append(params).append("]");
		return sb.toString();
	}
}
