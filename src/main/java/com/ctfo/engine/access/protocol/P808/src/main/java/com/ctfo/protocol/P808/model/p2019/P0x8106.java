package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808.model.p2019;

import com.ctfo.protocol.P808.model.PModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 查询指定终端参数数据格式Model定义
 *
 * <p>
 * 终端采用0x0104指令应答。
 *
 * @author 凉意 2018年5月3日 下午4:03:21
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x8106 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 参数总数
	 */
	private byte paramCount;

	/**
	 * 参数 ID 列表 , BYTE[4*n] ,参数顺序排列，如“参数 ID1 参数 ID2......参数IDn”。
	 */
	private List<Integer> paramIds;
}
