package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;


import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8201;
import com.ctfo.protocol.support.exception.P905Exception;

/**
 * 类简述。
 *
 * <p>类详细说明
 * @author 凉意 2018年12月18日 下午4:59:23
 * @version 1.0
 */
public class P0x8201PB implements IPassBody {

	@Override
	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			return null;
		}
		return new byte[]{};
	}

	@Override
	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data) {
			return null;
		}
		return new P0x8201();
	}

}
