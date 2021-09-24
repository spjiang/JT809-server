package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0200;
import com.ctfo.protocol.P905.model.p2014.P0x0B05;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 运营数据上传命令消息编解码处理类<code>P0x0B05</code>
 *
 * <p>
 * 消息ID: 0x0B05
 *
 * @author xiahancheng 2018年5月9日 下午5:09:00
 * @see
 * @version 1.0
 */
public class P0x0B05PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0B05消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x0B05 p = (P0x0B05) model;
			P0x0200PB pb = new P0x0200PB();
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(pb.bodyToBytes(p.getK2zPosition()));
			buf.put(pb.bodyToBytes(p.getZ2kPosition()));
			buf.putInt(p.getOperId());
			buf.putInt(p.getAssessId());
			buf.put(p.getAssess());
			buf.putShort(p.getAssessK());
			buf.putInt(p.getOrderId());
			// 计价器营运数据
			byte[] car_num = ConvUtil.string2Bytes(p.getCarNum());
			buf.put(car_num);
			if (6 > car_num.length) {
				int c = 6 - car_num.length;
				for (int i = 0; i < c; i++) {
					buf.put((byte) 0);
				}
			}
			byte[] company_Code = ConvUtil.string2Bytes(p.getUnitId());
			buf.put(company_Code);
			if (16 > company_Code.length) {
				int c = 16 - company_Code.length;
				for (int i = 0; i < c; i++) {
					buf.put((byte) 0);
				}
			}
			byte[] driver_Code = ConvUtil.string2Bytes(p.getDriverId());
			buf.put(driver_Code);
			if (19 > driver_Code.length) {
				int c = 19 - driver_Code.length;
				for (int i = 0; i < c; i++) {
					buf.put((byte) 0);
				}
			}
			String inCarTime = DateUtil.formatToString(p.getInCarTime(), "yyMMddHHmm");
			buf.put(ConvUtil.bcd2Bytes(inCarTime));
			String outCarTime = DateUtil.formatToString(p.getOutCarTime(), "hhmm");
			buf.put(ConvUtil.bcd2Bytes(outCarTime));
			buf.put(ConvUtil.int2Bcd2Bytes((int) (p.getKilomeNum() * 10), 6));
			buf.put(ConvUtil.int2Bcd2Bytes((int) (p.getKongKilome() * 10), 4));
			buf.put(ConvUtil.int2Bcd2Bytes((int) (p.getPlusMoney() * 10), 6));
			buf.put(ConvUtil.bcd2Bytes(p.getWaitTime()));
			buf.put(ConvUtil.int2Bcd2Bytes((int) (p.getMoney() * 10), 6));
			buf.putInt(p.getCoachNum());
			// 交易卡类型 BYTE
			// 0x00：现金交易；0x01：M1卡交易；0x03：CPU卡交易；0x09：其他
			buf.put((byte) p.getCardType());
			buf.flip();
			byte b[] = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x0B05编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 50 > data.length) {
			throw new P905Exception("P0x0B05消息体解码失败，消息为空或者长度不足。");
		}
		try {
			P0x0B05 p = new P0x0B05();
			P0x0200PB pb = new P0x0200PB();
			int pos = 0;
			byte[] k2zPosition = new byte[25];
			System.arraycopy(data, pos, k2zPosition, 0, 25);
			p.setK2zPosition((P0x0200) pb.bodyFromBytes(k2zPosition));
			pos += 25;
			byte[] z2kPosition = new byte[25];
			System.arraycopy(data, pos, z2kPosition, 0, 25);
			p.setZ2kPosition((P0x0200) pb.bodyFromBytes(z2kPosition));
			pos += 25;
			byte[] operId = new byte[4];
			System.arraycopy(data, pos, operId, 0, 4);
			p.setOperId(ConvUtil.bytes2Int(operId));
			pos += 4;
			byte[] assessId = new byte[4];
			System.arraycopy(data, pos, assessId, 0, 4);
			p.setAssessId(ConvUtil.bytes2Int(assessId));
			pos += 4;
			p.setAssess(data[pos]);
			pos++;
			byte[] assessK = new byte[2];
			System.arraycopy(data, pos, assessK, 0, 2);
			p.setAssessK(ConvUtil.bytes2Short(assessK));
			pos += 2;
			byte[] orderId = new byte[4];
			System.arraycopy(data, pos, orderId, 0, 4);
			p.setOrderId(ConvUtil.bytes2Int(orderId));
			pos += 4;
			// 计价器营运数据
			byte[] carNum = new byte[6];
			System.arraycopy(data, pos, carNum, 0, 6);
			p.setCarNum(ConvUtil.bytes2String(carNum));
			pos += 6;
			byte[] unitId = new byte[16];
			System.arraycopy(data, pos, unitId, 0, 16);
			p.setUnitId(ConvUtil.bytes2String(unitId));
			pos += 16;
			byte[] driverId = new byte[19];
			System.arraycopy(data, pos, driverId, 0, 19);
			p.setDriverId(ConvUtil.bytes2String(driverId));
			pos += 19;
			byte[] inCarTime = new byte[5];
			System.arraycopy(data, pos, inCarTime, 0, 5);
			String inCarTimeStr = "20" + ConvUtil.bytes2Bcd(inCarTime);
			p.setInCarTime(DateUtil.parseToDate(inCarTimeStr, "yyyyMMddHHmm"));
			pos += 5;
			byte[] _outCarTime = new byte[2];
			System.arraycopy(data, pos, _outCarTime, 0, 2); // 取时分
			byte[] _outCarTimeExt = new byte[5];
		    System.arraycopy(inCarTime, 0, _outCarTimeExt, 0, 3);
		    System.arraycopy(_outCarTime, 0, _outCarTimeExt, 3, 2);
		    String outCarTimeStr = "20" + ConvUtil.bytes2Bcd(_outCarTimeExt);
		    p.setOutCarTime(DateUtil.parseToDate(outCarTimeStr, "yyyyMMddHHmm"));
			// 当上车时间的小时数>下车时间的小时数，下车时间的天数加1
			if (inCarTime[3] > _outCarTime[0]) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(p.getOutCarTime());
				calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
				p.setOutCarTime(calendar.getTime()); // 这个时间就是日期往后推一天的结果
			}
			pos += 2;
			byte[] kilomeNum = new byte[3];
			System.arraycopy(data, pos, kilomeNum, 0, 3);
			p.setKilomeNum(ConvUtil.bytes2Bcd2Int(kilomeNum, 6) / 10.0);
			pos += 3;
			byte[] kongKilome = new byte[2];
			System.arraycopy(data, pos, kongKilome, 0, 2);
			p.setKongKilome(ConvUtil.bytes2Bcd2Int(kongKilome, 4) / 10.0);
			pos += 2;
			byte[] plusMoney = new byte[3];
			System.arraycopy(data, pos, plusMoney, 0, 3);
			p.setPlusMoney(ConvUtil.bytes2Bcd2Int(plusMoney, 6) / 10.0);
			pos += 3;
			byte[] waitTime = new byte[2];
			System.arraycopy(data, pos, waitTime, 0, 2);
			p.setWaitTime(ConvUtil.bytes2Bcd(waitTime));
			pos += 2;
			byte[] money = new byte[3];
			System.arraycopy(data, pos, money, 0, 3);
			p.setMoney(ConvUtil.bytes2Bcd2Int(money, 6) / 10.0);
			pos += 3;
			byte[] coachNum = new byte[4];
			System.arraycopy(data, pos, coachNum, 0, 4);
			p.setCoachNum(ConvUtil.bytes2Int(coachNum));
			pos += 4;
			p.setCardType(data[pos]);
			pos++;
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x0B05解码失败:", e);
		}
	}

}
