package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8B01;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 驾驶员抢答结果命令消息编解码处理类<code>P0x8B01</code>
 *
 * <p>
 * 消息ID: 0x8B01
 *
 * @author xiahancheng 2018年5月5日 上午11:46:29
 * @see
 * @version 1.0
 */
public class P0x8B01PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8B01消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x8B01 p = (P0x8B01) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.putInt(p.getBusinessId());
			if (null != p.getBusinessType()) {
				buf.put(p.getBusinessType());
			}
			if (null != p.getUseTime()) {
				String date = DateUtil.formatToString(p.getUseTime(), "yyMMddHHmmss");
				buf.put(ConvUtil.bcd2Bytes(date));
			}
			if (null != p.getUseLng()) {
				int useLng = (int)(p.getUseLng() * 600000);
				buf.putInt(useLng);
			}
			if (null != p.getUseLat()) {
				int useLat = (int)(p.getUseLat() * 600000);
				buf.putInt(useLat);
			}
			if (null != p.getDestLng()) {
				int destLng = (int)(p.getDestLng() * 600000);
				buf.putInt(destLng);
			}
			if (null != p.getDestLat()) {
				int destLat = (int)(p.getDestLat() * 600000);
				buf.putInt(destLat);
			}
			if (null != p.getTips()) {
				buf.put(ConvUtil.int2Bcd2Bytes((int) (p.getTips() * 10), 4));
			}
			if (null != p.getPassengerPhone()) {
				buf.put(ConvUtil.string2Bytes(p.getPassengerPhone()));
				buf.put((byte) 0);
			}
			if (null != p.getBusinessDescribe()) {
				buf.put(ConvUtil.string2Bytes(p.getBusinessDescribe()));
				buf.put((byte) 0);
			}
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x8B01编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 4 > data.length) {
			throw new P905Exception("P0x8B01消息体解码失败，消息为空或者长度不足。");
		}
		int pos = 0;
		int dataLen = data.length;
		try {
			P0x8B01 p = new P0x8B01();
			byte[] id = new byte[4]; // 业务ID
			System.arraycopy(data, pos, id, 0, 4);
			if (4 == dataLen) {
				p.setBusinessId(ConvUtil.bytes2Int(id));
				return p;
			}
			pos += 4;
			byte type = data[pos]; // 业务类型
			p.setBusinessType(type);
			pos += 1;
			byte[] useTime = new byte[6]; // 用车时间,BCD[6],YYMMDDHHNNSS，全零表示不启用
			System.arraycopy(data, pos, useTime, 0, 6);
			String useTimeStr = ConvUtil.bytes2Bcd(useTime);
			if (!"000000000000".equals(useTimeStr)) {
				String dateStr = "20" + ConvUtil.bytes2Bcd(useTime);
				p.setUseTime(DateUtil.parseToDate(dateStr, "yyyyMMddHHmmss"));
			}
			pos += 6;
			byte[] useLng = new byte[4]; // 乘客位置经度,0.0001’，填充为零表示不启用
			System.arraycopy(data, pos, useLng, 0, 4);
			int useLngV = ConvUtil.bytes2Int(useLng);
			if (0 == useLngV) {
				p.setUseLng(0d);
			} else {
				p.setUseLng(useLngV / 600000d);
			}
			pos += 4;
			byte[] useLat = new byte[4]; // 乘客位置纬度，0.0001’，填充为零表示不启用
			System.arraycopy(data, pos, useLat, 0, 4);
			int useLatV = ConvUtil.bytes2Int(useLat);
			if (0 == useLatV) {
				p.setUseLat(0d);
			} else {
				p.setUseLat(useLatV / 600000d);
			}
			pos += 4;
			byte[] destLng = new byte[4]; // 目的地经度,0.0001’，填充为零表示不启用
			System.arraycopy(data, pos, destLng, 0, 4);
			int destLngV = ConvUtil.bytes2Int(destLng);
			if (0 == destLngV) {
				p.setDestLng(0d);
			} else {
				p.setDestLng(destLngV / 600000d);
			}
			pos += 4;
			byte[] destLat = new byte[4]; // 目的地纬度,0.0001’，填充为零表示不启用
			System.arraycopy(data, pos, destLat, 0, 4);
			int destLatV = ConvUtil.bytes2Int(destLat);
			if (0 == destLatV) {
				p.setDestLat(0d);
			} else {
				p.setDestLat(destLatV / 600000d);
			}
			pos += 4;
			byte[] tips = new byte[2];
			System.arraycopy(data, pos, tips, 0, 2); // 电召服务费,BCD[2],格式为×××.×元，全0表示无服务费
			p.setTips(ConvUtil.bytes2Bcd2Int(tips, 4) / 10.0f);
			pos += 2;
			// 处理乘客电话号码和业务描述
			int flag = 0;
			int len = 0;
			while (pos < dataLen) {
				if (data[pos] == 0) {
					byte[] d = new byte[len];
					System.arraycopy(data, pos, d, 0, len);
					if (flag == 0) {
						p.setPassengerPhone(ConvUtil.bytes2String(d));
						len = 0;
						flag = 1;
					} else {
						p.setBusinessDescribe(ConvUtil.bytes2String(d));
						len = 0;
						flag = 0;
					}
				}
				len++;
				pos++;
			}
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x8B01解码失败:", e);
		}
	}

}
