package com.ctfo.engine.access.protocol.support.src.main.java.com.ctfo.protocol.support.utils;

/**
 * 类简述。
 *
 * <p>类详细说明
 * @author 凉意 2018年5月30日 下午8:06:56
 * @version 1.0
 */
public class FlowNoUtil {


	/**
	 * 消息流水号
	 */
	private static short msgFlowNo905 = Short.MIN_VALUE;

	/**
	 * 动态获取消息流水号(905)
	 *
	 * @author 凉意 2018年5月15日 下午3:44:20
	 * @return 流水号
	 */
	public static short getMsgFlowNo905() {
		if (FlowNoUtil.msgFlowNo905 <= Short.MAX_VALUE) {
			FlowNoUtil.msgFlowNo905++;
		} else {
			FlowNoUtil.msgFlowNo905 = Short.MIN_VALUE;
		}
		return FlowNoUtil.msgFlowNo905;
	}


	/**
	 * 消息流水号
	 */
	private static short msgFlowNo808 = Short.MIN_VALUE;

	/**
	 * 动态获取消息流水号(808)
	 *
	 * @author 凉意 2018年5月15日 下午3:44:20
	 * @return 流水号
	 */
	public static short getMsgFlowNo808() {
		if (msgFlowNo808 <= Short.MAX_VALUE) {
			msgFlowNo808++;
		} else {
			msgFlowNo808 = Short.MIN_VALUE;
		}
		return msgFlowNo808;
	}

}
