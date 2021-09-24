package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808.model.p2019;

import com.ctfo.protocol.P808.model.PModel;
import com.ctfo.protocol.P808.vo.P0x8103VO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 查询终端参数应答数据格式Model定义,参数项格式定义参考<code>P0x8103VO</code>
 *
 * @author 凉意 2018年5月3日 下午4:36:57
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x0104 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 应答流水号，对应的终端参数查询消息的流水号
	 */
	private short ansFlowNo;

	/**
	 * 应答参数个数
	 */
	private byte paramCount;

	/**
	 * 参数数据列表
	 */
	private List<P0x8103VO> params;
}
