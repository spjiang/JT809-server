package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808.model.p2019;


import com.ctfo.protocol.P808.model.PModel;
import com.ctfo.protocol.P808.vo.P0x8103VO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 设置终端参数消息体数据格式Model定义,终端参数数据项格式定义<code>P0x8103VO</code>
 *
 *
 * @author 凉意 2018年5月3日 上午10:25:16
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x8103 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 参数总数
	 */
	private byte paramCount;

	/**
	 * 参数数据列表
	 */
	private List<P0x8103VO> params;
}
