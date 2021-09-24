package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808.model.p2011;


import com.ctfo.protocol.P808.model.PModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 终端通用应答消息体数据格式Model定义
 * <p>
 * 起始字节 字段 数据类型 描述及要求 <br>
 * 0 应答流水号 WORD 对应的平台消息的流水号 <br>
 * 2 应答 ID WORD 对应的平台消息的 ID <br>
 * 4 结果 BYTE 0：成功/确认；1：失败；2：消息有误；3：不支持 <br>
 *
 * @author 凉意 2018年4月22日 下午2:01:28
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x0001 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 应答流水号
	 */
	private short ansFlowNo;

	/**
	 * 应答ID
	 */
	private short ansId;

	/**
	 * 应答结果：0：成功/确认；1：失败；2：消息有误；3：不支持
	 */
	private byte ansResult;
}
