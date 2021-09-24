package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;


import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0002;
import com.ctfo.protocol.support.exception.P905Exception;

/**
 * 终端心跳消息编解码处理类，数据格式定义参考<code>P0x0002</code>
 *
 * @author 凉意 2018年5月2日 下午5:14:31
 * @see
 * @version 1.0
 */
public class P0x0002PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			return null;
		}
		return new byte[]{};
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data) {
			return null;
		}
		return new P0x0002();
	}

}
