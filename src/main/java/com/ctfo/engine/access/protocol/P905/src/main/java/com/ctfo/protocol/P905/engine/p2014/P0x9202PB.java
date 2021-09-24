package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x9202;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 *
 * 平台下发远程录像回放控制 解码处理类
 *
 * <p>
 * 类详细说明
 *
 * @author zx 2018年9月28日 上午11:52:40
 * @see
 * @version 1.0
 */
public class P0x9202PB implements IPassBody {

	@Override
	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x9202消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x9202 p = (P0x9202) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(p.getLogicalChannel());// 逻辑通道号
			buf.put(p.getPlaybackControl());// 回放控制
			buf.put(p.getFastForwardOrFastMultiple());// 快进或快退倍数
			if (5 == p.getFastForwardOrFastMultiple()) {
				String dragPlaybackLocation = DateUtil.formatToString(p.getDragPlaybackLocation(), "yyMMddHHmmss");
				buf.put(ConvUtil.bcd2Bytes(dragPlaybackLocation));// 拖动回放位置
			}
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x9202编码失败:", e);
		}
	}

	@Override
	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 0 > data.length) {
			throw new P905Exception("P0x9202解析失败，消息长度不足。");
		}
		try {
			P0x9202 p = new P0x9202();
			int pos = 0;
			// 逻辑通道号
			p.setLogicalChannel(data[pos]);
			pos += 1;
			// 回放控制
			p.setPlaybackControl(data[pos]);
			pos += 1;
			// 快进或快退倍数
			p.setFastForwardOrFastMultiple(data[pos]);
			pos += 1;
			if (5 == p.getFastForwardOrFastMultiple()) {
				// 拖动回放位置
				byte[] dragPlaybackLocation = new byte[6];
				System.arraycopy(data, pos, dragPlaybackLocation, 0, 6);
				String dragPlaybackLocationStr = "20" + ConvUtil.bytes2Bcd(dragPlaybackLocation);
				p.setDragPlaybackLocation(DateUtil.parseToDate(dragPlaybackLocationStr, "yyMMddHHmmss"));
			}
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x9202解码失败：" + e);
		}
	}

}
