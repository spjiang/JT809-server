package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8B00;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 订单任务下发命令消息编解码处理类<code>P0x8B00</code>
 *
 * <p>
 * 消息ID: 0x8B00
 *
 * @author xiahancheng 2018年5月4日 下午4:30:23
 * @see
 * @version 1.0
 */
public class P0x8B00PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8B00消息体编码失败，传入消息体实体对象为空。");
		}
		try{
			P0x8B00 p = (P0x8B00) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.putInt(p.getBusinessId());
			buf.put(p.getBusinessType());
			String car_Time = DateUtil.formatToString(p.getCatTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(car_Time));
			buf.put(ConvUtil.string2Bytes(p.getBusinessDescribe()));
			buf.put((byte)0);
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		}catch (Exception e) {
			throw new P905Exception("P0x8B00编码失败:", e);
		}

	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 11 > data.length) {
			throw new P905Exception("P0x8B00消息体解码失败，消息为空或者长度不足。");
		}
		try{
			P0x8B00 p = new P0x8B00();
			byte[] id = new byte[4]; //业务ID
			byte type = data [4]; //业务类型
			byte[] time = new byte[6]; //要车时间
			byte[] describe = new byte[data.length - 12]; //业务描述, 前面数据11字节，加上最后一个0 = 12字节
			System.arraycopy(data, 0, id, 0, 4);
			System.arraycopy(data, 5, time, 0, 6);
			System.arraycopy(data, 11, describe, 0, describe.length);
			p.setBusinessId(ConvUtil.bytes2Int(id));
			p.setBusinessType(type);
			String car_Time = "20" + ConvUtil.bytes2Bcd(time);
			p.setCatTime(DateUtil.parseToDate(car_Time, "yyyyMMddHHmmss"));
			p.setBusinessDescribe(ConvUtil.bytes2String(describe));
			return p;

		}catch (Exception e) {
			throw new P905Exception("P0x8B00编码失败:", e);
		}
	}


}
