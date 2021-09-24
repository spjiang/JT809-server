package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;


import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x9105;
import com.ctfo.protocol.support.exception.P905Exception;

/**
 *
 * 实时音视频传输状态通知 解码处理类
 *
 * <p>类详细说明
 * @author zx 2018年9月27日 下午6:04:48
 * @version 1.0
 */
public class P0x9105PB implements IPassBody {

	@Override
	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if(null == model) {
			throw new P905Exception("P0x9105消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x9105 p = (P0x9105)model;
			byte[] b = new byte[2];
			b[0] = p.getLogicalChannel();//逻辑通道号
			b[1] = p.getPacketLoss();//丢包率
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x9105编码失败:", e);
		}
	}

	@Override
	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if(null == data || 0 > data.length) {
			throw new P905Exception("P0x9105解析失败，消息长度不足。");
		}
		try {
			P0x9105 p = new P0x9105();
			//逻辑通道号
			p.setLogicalChannel(data[0]);
			//丢包率
			p.setPacketLoss(data[1]);
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x9105解码失败:"+e);
		}
	}

}
