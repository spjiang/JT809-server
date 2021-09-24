package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808.model.p2011;


import com.ctfo.protocol.P808.model.PModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 补传分包请求数据格式Model定义
 * <p>
 * 注： 对此消息的应答应采用原始消息将重传包ID列表中的分包重发一次，与原始分包 消息完全一致。
 * <p>
 * 起始字节 字段 数据类型 描述及要求 <br>
 * 0 原始消息流水号 WORD 对应要求补传的原始消息第一包的消息流水号 <br>
 * 2 重传包总数 BYTE n <br>
 * 4 重传包 ID 列表 BYTE[2*n] 重传包序号顺序排列，如“包 ID1 包 ID2......包 IDn”。
 *
 * @author 凉意 2018年5月2日 下午5:26:04
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x8003 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 原始消息流水号 2字节
	 * <p>
	 *     对应要求补传的原始消息第一包的消息流水号
	 * </p>
	 */
	private short originalFlowNo;

	/**
	 * 重传包总数， 1字节
	 */
	private byte againPackTotal;

	/**
	 * 重传包ID列表
	 */
	private List<Short> againPackIds;

}
