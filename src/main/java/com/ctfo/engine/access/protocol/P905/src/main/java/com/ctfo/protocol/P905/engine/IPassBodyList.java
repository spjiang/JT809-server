package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine;

import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.support.exception.P905Exception;

import java.util.List;


/**
 * 消息体封包/拆包功能接口 定义
 * <p>
 * 此接口定义多消息处理
 *
 * @author 凉意 2018年4月22日 下午1:42:48
 * @version 1.0
 */
public interface IPassBodyList {
	/**
	 * 消息体封包处理
	 *
	 * @author 凉意 2018年4月22日 下午1:39:33
	 * @param models
	 * 				待编码的消息对象列表
	 * @return
	 * 				编码过后的消息字节码
	 * @throws P905Exception 905协议处理异常
	 */
	byte[] bodyToBytes(List<PModel> models) throws P905Exception;

	/**
	 * 消息拆包处理
	 *
	 * @author 凉意 2018年4月22日 下午1:41:21
	 * @param data
	 * 			待解码的消息对象
	 * @return
	 * 			解码过后的消息对象列表
	 * @throws P905Exception 905协议处理异常
	 */
	List<PModel> bodyFromBytes(byte[] data) throws P905Exception;
}
