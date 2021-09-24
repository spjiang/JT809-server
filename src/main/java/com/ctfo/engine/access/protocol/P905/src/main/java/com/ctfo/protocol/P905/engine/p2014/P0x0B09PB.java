package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;


import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0B09;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;

/**
 * 中心取消订单
 *
 * <p>
 * 消息ID:0x0B09
 *
 * @author 凉意 2018年5月16日 下午4:12:30
 * @see
 * @version 1.0
 */
public class P0x0B09PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0B09消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x0B09 p = (P0x0B09) model;
			byte[] b = ConvUtil.int2Bytes(p.getBusinessId());
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x0B09编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 0 >= data.length) {
			throw new P905Exception("P0x0B09消息体解码失败，消息为空或者长度不足。");
		}
		try {
			P0x0B09 p = new P0x0B09();
			byte[] businessId = new byte[4];
			System.arraycopy(data, 0, businessId, 0, 4);
			p.setBusinessId(ConvUtil.bytes2Int(businessId));
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x0B09解码失败:", e);
		}
	}

}
