package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0200;
import com.ctfo.protocol.P905.model.p2014.P0x0B04;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 下班签退信息上传命令消息编解码处理类<code>P0x0B04</code>
 *
 * <p>
 * 消息ID: 0x0B04
 *
 * @author xiahancheng 2018年5月8日 上午10:21:58
 * @see
 * @version 1.0
 */
public class P0x0B04PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0B04消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x0B04 p = (P0x0B04) model;
			P0x0200PB pb = new P0x0200PB();
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(pb.bodyToBytes(p.getPosition()));
			byte[] company_Code = ConvUtil.string2Bytes(p.getCompanyCode());
			buf.put(company_Code);
			if (16 > company_Code.length) {
				int c = 16 - company_Code.length;
				for (int i = 0; i < c; i++) {
					buf.put((byte) 0);
				}
			}
			byte[] driver_Code = ConvUtil.string2Bytes(p.getDriverCode());
			buf.put(driver_Code);
			if (19 > driver_Code.length) {
				int c = 19 - driver_Code.length;
				for (int i = 0; i < c; i++) {
					buf.put((byte) 0);
				}
			}
			byte[] plate_number = ConvUtil.string2Bytes(p.getPlateNumber());
			buf.put(plate_number);
			if (6 > plate_number.length) {
				int c = 6 - plate_number.length;
				for (int i = 0; i < c; i++) {
					buf.put((byte) 0);
				}
			}
			buf.put(ConvUtil.int2Bcd2Bytes(p.getKvalue(), 4));
			String strat_date = DateUtil.formatToString(p.getStartTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(strat_date));
			String end_date = DateUtil.formatToString(p.getEndTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(end_date));
			buf.put(ConvUtil.int2Bcd2Bytes((int) (p.getBeOnduty() * 10), 6));
			buf.put(ConvUtil.int2Bcd2Bytes((int) (p.getBeOndutyOperate() * 10), 6));
			buf.put(ConvUtil.int2Bcd2Bytes(p.getTrainNumber(), 4));
			buf.put(ConvUtil.bcd2Bytes(p.getTime()));
			buf.put(ConvUtil.int2Bcd2Bytes((int) (p.getTotalIncome() * 10), 6));
			buf.put(ConvUtil.int2Bcd2Bytes((int) (p.getCardNumberIncome() * 10), 6));
			buf.put(ConvUtil.int2Bcd2Bytes(p.getCardsecond(), 4));
			buf.put(ConvUtil.int2Bcd2Bytes((int) (p.getWorkMileage() * 10), 4));
			buf.put(ConvUtil.int2Bcd2Bytes((int) (p.getTotalMileage() * 10), 8));
			buf.put(ConvUtil.int2Bcd2Bytes((int) (p.getTotalOperateMileage() * 10), 8));
			buf.put(ConvUtil.int2Bcd2Bytes((int) (p.getUnitPrice() * 100), 4));
			buf.putInt(p.getTotalOperateNumber());
			buf.put(p.getLogoutMode());
			if (StringUtils.isNotBlank(p.getAttribute())) {
				buf.put(ConvUtil.hex2Bytes(p.getAttribute()));
			}
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x0B04编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 115 > data.length) {
			throw new P905Exception("P0x0B04消息体解码失败，消息为空或者长度不足。");
		}
		try {

			P0x0B04 p = new P0x0B04();
			int pos = 0;
			P0x0200PB pb = new P0x0200PB();
			byte[] position = new byte[25];
			System.arraycopy(data, 0, position, 0, 25);
			p.setPosition((P0x0200) pb.bodyFromBytes(position));
			pos += 25;

			byte[] company = new byte[16]; // 单位代码
			byte[] driver = new byte[19]; // 司机代码
			byte[] platenumber = new byte[6]; // 车牌号
			byte[] kvalue = new byte[2]; // k值
			byte[] stratTime = new byte[6]; // 当班开机时间
			byte[] endTime = new byte[6]; // 当班关机时间
			byte[] beOnduty = new byte[3]; // 当班公里
			byte[] beOndutyOperate = new byte[3]; // 当班营运公里
			byte[] trainNumber = new byte[2]; // 车次
			byte[] time = new byte[3]; // 计时时间
			byte[] totalIncome = new byte[3]; // 总计金额
			byte[] cardNumberIncome = new byte[3]; // 卡收金额
			byte[] cardsecond = new byte[2]; // 卡次
			byte[] workMileage = new byte[2]; // 班间公里
			byte[] totalMileage = new byte[4]; // 总计公里
			byte[] totalOperateMileage = new byte[4]; // 总营运公里
			byte[] unitPrice = new byte[2]; // 单价
			byte[] totalOperateNumber = new byte[4]; // 总运营次数

			System.arraycopy(data, pos, company, 0, 16);
			pos += 16;
			System.arraycopy(data, pos, driver, 0, 19);
			pos += 19;
			System.arraycopy(data, pos, platenumber, 0, 6);
			pos += 6;
			System.arraycopy(data, pos, kvalue, 0, 2);
			pos += 2;
			System.arraycopy(data, pos, stratTime, 0, 6);
			pos += 6;
			System.arraycopy(data, pos, endTime, 0, 6);
			pos += 6;
			System.arraycopy(data, pos, beOnduty, 0, 3);
			pos += 3;
			System.arraycopy(data, pos, beOndutyOperate, 0, 3);
			pos += 3;
			System.arraycopy(data, pos, trainNumber, 0, 2);
			pos += 2;
			System.arraycopy(data, pos, time, 0, 3);
			pos += 3;
			System.arraycopy(data, pos, totalIncome, 0, 3);
			pos += 3;
			System.arraycopy(data, pos, cardNumberIncome, 0, 3);
			pos += 3;
			System.arraycopy(data, pos, cardsecond, 0, 2);
			pos += 2;
			System.arraycopy(data, pos, workMileage, 0, 2);
			pos += 2;
			System.arraycopy(data, pos, totalMileage, 0, 4);
			pos += 4;
			System.arraycopy(data, pos, totalOperateMileage, 0, 4);
			pos += 4;
			System.arraycopy(data, pos, unitPrice, 0, 2);
			pos += 2;
			System.arraycopy(data, pos, totalOperateNumber, 0, 4);
			pos += 4;
			p.setCompanyCode(ConvUtil.bytes2String(company));
			p.setDriverCode(ConvUtil.bytes2String(driver));
			p.setPlateNumber(ConvUtil.bytes2String(platenumber));
			p.setKvalue(ConvUtil.bytes2Bcd2Int(kvalue, 4));
			p.setStartTime(DateUtil.parseToDate("20" + ConvUtil.bytes2Bcd(stratTime), "yyyyMMddHHmmss"));
			p.setEndTime(DateUtil.parseToDate("20" + ConvUtil.bytes2Bcd(endTime), "yyyyMMddHHmmss"));
			p.setBeOnduty(ConvUtil.bytes2Bcd2Int(beOnduty, 6) / 10.0);
			p.setBeOndutyOperate(ConvUtil.bytes2Bcd2Int(beOndutyOperate, 6) / 10.0);
			p.setTrainNumber(ConvUtil.bytes2Bcd2Int(trainNumber, 4));
			p.setTime(ConvUtil.bytes2Bcd(time));
			p.setTotalIncome(ConvUtil.bytes2Bcd2Int(totalIncome, 6) / 10.0);
			p.setCardNumberIncome(ConvUtil.bytes2Bcd2Int(cardNumberIncome, 6) / 10.0);
			p.setCardsecond(ConvUtil.bytes2Bcd2Int(cardsecond, 4));
			p.setWorkMileage(ConvUtil.bytes2Bcd2Int(workMileage, 2) / 10.0);
			p.setTotalMileage(ConvUtil.bytes2Bcd2Int(totalMileage, 8) / 10.0);
			p.setTotalOperateMileage(ConvUtil.bytes2Bcd2Int(totalOperateMileage, 8) / 10.0);
			p.setUnitPrice(ConvUtil.bytes2Bcd2Int(unitPrice, 4) / 100.0);
			p.setTotalOperateNumber(ConvUtil.bytes2Int(totalOperateNumber));
			p.setLogoutMode(data[pos]);
			pos++;
			if (data.length > pos) {
				byte[] attribute = new byte[data.length - pos]; // 扩展属性
				System.arraycopy(data, pos, attribute, 0, attribute.length);
				p.setAttribute(ConvUtil.bytes2Hex(attribute));
			}
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x0B04编码失败:", e);
		}
	}

}
