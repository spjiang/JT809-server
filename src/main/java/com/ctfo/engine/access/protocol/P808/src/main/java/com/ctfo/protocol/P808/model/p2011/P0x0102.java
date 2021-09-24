package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808.model.p2011;


import com.ctfo.protocol.P808.model.PModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 终端鉴权消息体数据格式Model定义
 *
 * @author 凉意 2018年5月3日 上午9:17:37
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x0102 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 鉴权码，终端重连后上报鉴权码
	 */
	private String authCode;
}
