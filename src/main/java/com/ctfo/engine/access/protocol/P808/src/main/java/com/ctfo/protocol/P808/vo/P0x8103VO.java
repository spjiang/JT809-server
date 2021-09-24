package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 终端参数设置VO对象
 * <p>
 * 本对象定义参数项数据格式
 *
 * @author 凉意 2018年4月25日 下午5:33:00
 * @version 1.0
 */
@Data
@ToString
public class P0x8103VO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 参数ID,DWORD
	 * <p>
	 * 具体参见808协议文档
	 */
	private int paramId;

	/**
	 * 参数长度
	 */
	private byte paramLen;

	/**
	 * 参数值，这里统一定义为字符串。需要使用到本属性时，再进行数据类型转换
	 * <p>
	 * 若为多值参数，则消息中使用多个相同 ID 的参数项，
	 */
	private String paramVal;
}
