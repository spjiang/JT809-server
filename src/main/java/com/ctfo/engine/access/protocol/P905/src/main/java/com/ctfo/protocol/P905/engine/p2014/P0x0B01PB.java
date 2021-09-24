package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0B01;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;

/**
 * 驾驶员抢答命令命令消息编解码处理类<code>P0x0B01</code>
 *
 * <p>
 * 消息ID: 0x0B01
 *
 * @author xiahancheng 2018年5月5日 上午10:09:46
 * @see
 * @version 1.0
 */
public class P0x0B01PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0B01消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x0B01 p = (P0x0B01) model;
			return ConvUtil.int2Bytes(p.getBusinessId());
		} catch (Exception e) {
			throw new P905Exception("P0x0B01编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 4 > data.length) {
			throw new P905Exception("P0x0B01消息体编码失败，消息为空或者长度不足。");
		}
		try {
			P0x0B01 p = new P0x0B01();
			byte[] id = new byte[4];
			System.arraycopy(data, 0, id, 0, 4);
			p.setBusinessId(ConvUtil.bytes2Int(id));
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x0B01编码失败:", e);
		}

	}

}
