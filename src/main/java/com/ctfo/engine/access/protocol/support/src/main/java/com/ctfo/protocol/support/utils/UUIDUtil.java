package com.ctfo.engine.access.protocol.support.src.main.java.com.ctfo.protocol.support.utils;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author 凉意 2018年9月3日 下午3:52:54
 * @version 1.0
 */
public class UUIDUtil {

	/**
	 * 自动生成32位的UUid，对应数据库的主键id进行插入用。
	 *
	 * @author 凉意 2018年6月14日 上午11:46:21
	 * @return 32位UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
