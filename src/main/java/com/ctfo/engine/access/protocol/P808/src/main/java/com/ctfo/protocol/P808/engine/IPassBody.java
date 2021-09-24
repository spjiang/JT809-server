package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808.engine;

import com.ctfo.protocol.P808.model.PModel;
import com.ctfo.protocol.support.exception.P808Exception;

/**
 * 消息体封包/拆包功能接口 定义
 * <p>
 * 次接口定义单消息处理
 *
 * @author 凉意 2018年4月22日 下午1:15:42
 * @version 1.0
 */
public interface IPassBody {

	/**
	 * 消息体封包处理
	 *
	 * @author 凉意 2018年4月22日 下午1:39:33
	 * @param model 待编码的消息对象
	 * @return 编码后的字节码
	 * @throws P808Exception 808协议异常
	 */
	byte[] bodyToBytes(PModel model) throws P808Exception;

	/**
	 * 消息拆包处理
	 *
	 * @author 凉意 2018年4月22日 下午1:41:21
	 * @param data 待解码的消息字节码
	 * @return 解码后的消息对象
	 * @throws P808Exception
	 */
	PModel bodyFromBytes(byte[] data) throws P808Exception;
}
