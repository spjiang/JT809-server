package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.List;

/**
 * 位置信息补传消息数据格式Model定义。
 *
 * <p>
 * 起始字节 字段 数据类型 说明 14+0 位置信息1 位置信息汇报(0X0200)消息体中的位置基本信息<br>
 * 14+25 位置信息2 <br>
 * -- 。。 <br>
 * -- 位置信息n <br>
 * 每次补传最多 10 条位置信息，不够10 条则根据消息长度判断； 中心回复为“中心通用应答”命令，终端在收到中心的通用应答后，则继续补传下10
 * 条位置信息；
 *
 * @author 凉意 2018年5月2日 上午11:40:05
 * @see
 * @version 1.0
 */
public class P0x0203 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 位置信息
	 */
	private List<P0x0200> positions;

	/**
	 * @return the positions
	 */
	public List<P0x0200> getPositions() {
		return positions;
	}

	/**
	 * @param positions
	 *            the positions to set
	 */
	public void setPositions(List<P0x0200> positions) {
		this.positions = positions;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0203 [");
		sb.append("positions=").append(positions).append("]");
		return sb.toString();
	}

}
