package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808.model.p2011;

import com.ctfo.protocol.P808.model.PModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 终端注册应答数据格式Model定义
 *
 * @author 凉意 2018年5月2日 下午10:03:58
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x8100 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 应答流水号
	 * <p>
	 * 对应的终端注册消息的流水号
	 */
	private short ansFlowNo;

	/**
	 * 应答结果
	 * <p>
	 * 0：成功；1：车辆已被注册；2：数据库中无该车辆； 3：终端已被注册；4：数据库中无该终端
	 */
	private byte result;

	/**
	 * 鉴权码 ，只有在成功后才有该字段
	 */
	private String authCode;

}
