package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 协议实体基础类，所有协议实体都集成此类。
 *
 * @author 凉意 2018年4月22日 下午1:19:28
 * @version 1.0
 */
@Data
@ToString
public class PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 数据库唯一标识
	 */
	private String uuid;

}
