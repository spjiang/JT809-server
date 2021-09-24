package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine;


import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.support.exception.P809Exception;

/**
 * 消息体封包/拆包功能接口 定义
 * <p>
 * 此接口定义单消息处理
 *
 * @author 凉意 2018年4月22日 下午1:15:42
 * @version 1.0
 */
public interface IPassBody {

	/**
	 * 消息体封包处理
	 *
	 * @author 凉意 2018年4月22日 下午1:39:33
	 * @param model
	 * 			待编码的消息对象
	 * @return
	 * 			编码过后的消息字节码
	 * @throws P809Exception 809协议处理异常
	 */
	byte[] bodyToBytes(PModel model) throws P809Exception;

	/**
	 * 消息拆包处理
	 *
	 * @author 凉意 2018年4月22日 下午1:41:21
	 * @param data
	 * 			待解码的消息对象
	 * @return
	 * 			解码过后的消息对象
	 * @throws P809Exception 809协议处理异常
	 */
	PModel bodyFromBytes(byte[] data) throws P809Exception;
}
