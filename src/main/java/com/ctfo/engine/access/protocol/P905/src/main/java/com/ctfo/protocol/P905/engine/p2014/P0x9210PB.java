package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x9210;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * A.2.7.73 人脸识别结果汇报 编解码处理类
 *
 * @author 凉意 2019年8月9日 下午9:56:25
 * @version 1.0
 */
public class P0x9210PB implements IPassBody {

	@Override
	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x9210消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x9210 p = (P0x9210) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.putInt(p.getDataLen());
			buf.putInt(p.getCurrentDataOffset());
//			buf.put(p.getEventType());
//			buf.put(p.getDefaultMatchRatio());
//			buf.put(p.getMatchResult());
//			buf.put(ConvUtil.bcd2Bytes(DateUtil.formatToString(p.getCheckTime(), "yyMMddHHmmss")));
//			byte[] driverId = ConvUtil.string2Bytes(p.getDriverId());
//			buf.put(driverId);
//			int forLen = 19 - driverId.length;
//			for (int i = 0; i < forLen; i++) {
//				buf.put((byte)0);
//			}
			buf.put(ConvUtil.hex2Bytes(p.getDataHex()));
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x8805编码失败:", e);
		}
	}

	@Override
	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 0 >= data.length) {
			throw new P905Exception("P0x9210消息体解码失败，消息长度不够。");
		}
		try {
			P0x9210 p = new P0x9210();
			int pos = 0;
			byte[] dataLen = new byte[4];
			System.arraycopy(data, pos, dataLen, 0, 4);
			p.setDataLen(ConvUtil.bytes2Int(dataLen));
			pos += 4;
			byte[] currentDataOffset = new byte[4];
			System.arraycopy(data, pos, currentDataOffset, 0, 4);
			p.setCurrentDataOffset(ConvUtil.bytes2Int(currentDataOffset));
			pos += 4;
//			p.setEventType(data[pos]);
//			pos++;
//			p.setDefaultMatchRatio(data[pos]);
//			pos++;
//			p.setCheckMatchRatio(data[pos]);
//			pos++;
//			p.setMatchResult(data[pos]);
//			pos++;
//			byte[] checkTime = new byte[6];
//			System.arraycopy(data, pos, checkTime, 0, 6);
//			p.setCheckTime(DateUtil.parseToDate("20" + ConvUtil.bytes2Bcd(checkTime)));
//			pos += 6;
//			byte[] driverId = new byte[19];
//			System.arraycopy(data, pos, driverId, 0, 19);
//			p.setDriverId(ConvUtil.bytes2String(driverId));
//			pos += 19;
			byte[] datapacket = new byte[data.length - 8]; // 图像数据包
			System.arraycopy(data, pos, datapacket, 0, datapacket.length);
			p.setDataHex(ConvUtil.bytes2Hex(datapacket));
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x9210解码失败：" + e);
		}
	}

}
