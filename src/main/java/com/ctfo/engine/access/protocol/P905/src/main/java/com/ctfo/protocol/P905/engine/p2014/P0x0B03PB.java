package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0200;
import com.ctfo.protocol.P905.model.p2014.P0x0B03;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 上班签到信息上传命令消息编解码处理类<code>P0x0B03</code>
 *
 * <p>
 * 消息ID: 0x0B03
 *
 * @author xiahancheng 2018年5月7日 上午10:17:12
 * @see
 * @version 1.0
 */
public class P0x0B03PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0B03消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x0B03 p0x0B03 = (P0x0B03) model;
			P0x0200PB pb = new P0x0200PB();
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.put(pb.bodyToBytes(p0x0B03.getPosition()));
			byte[] company_Code = ConvUtil.string2Bytes(p0x0B03.getCompanyCode());
			buf.put(company_Code);
			if (16 > company_Code.length) {
				int c = 16 - company_Code.length;
				for (int i = 0; i < c; i++) {
					buf.put((byte) 0);
				}
			}
			byte[] driver_Code = ConvUtil.string2Bytes(p0x0B03.getDriverCode());
			buf.put(driver_Code);
			if (19 > driver_Code.length) {
				int c = 19 - driver_Code.length;
				for (int i = 0; i < c; i++) {
					buf.put((byte) 0);
				}
			}
			byte[] plate_number = ConvUtil.string2Bytes(p0x0B03.getPlateNumber());
			buf.put(plate_number);
			if (6 > plate_number.length) {
				int c = 6 - plate_number.length;
				for (int i = 0; i < c; i++) {
					buf.put((byte) 0);
				}
			}
			// 计价器开机时间
			String date = DateUtil.formatToString(p0x0B03.getOpenMachineTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(date));
			// 扩展属性
			if (StringUtils.isNotBlank(p0x0B03.getAttribute())) {
				buf.put(ConvUtil.hex2Bytes(p0x0B03.getAttribute()));
			}
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;

		} catch (Exception e) {
			throw new P905Exception("P0x0B03编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 66 > data.length) {
			throw new P905Exception("P0x0B03消息体解码失败，消息为空或者长度不足。");
		}
		try {
			int len = data.length;
			P0x0B03 p = new P0x0B03();
			int pos = 0;
			P0x0200PB pb = new P0x0200PB();
			byte[] position = new byte[25];
			System.arraycopy(data, 0, position, 0, 25);
			p.setPosition((P0x0200) pb.bodyFromBytes(position));
			pos += 25;
			byte[] company = new byte[16]; // 单位代码
			byte[] driver = new byte[19]; // 司机代码
			byte[] platenumber = new byte[9]; // 车牌号
			byte[] openMachineTime = new byte[6]; // 计价器开机时间
			System.arraycopy(data, pos, company, 0, 16);
			pos += 16;
			System.arraycopy(data, pos, driver, 0, 19);
			pos += 19;
			System.arraycopy(data, pos, platenumber, 0, 6);
			pos += 6;
			System.arraycopy(data, pos, openMachineTime, 0, 6);
			pos += 6;
			p.setCompanyCode(ConvUtil.bytes2String(company));
			p.setDriverCode(ConvUtil.bytes2String(driver));
			p.setPlateNumber(ConvUtil.bytes2String(platenumber));
			String dateStr = "20" + ConvUtil.bytes2Bcd(openMachineTime);
			p.setOpenMachineTime(DateUtil.parseToDate(dateStr, "yyyyMMddHHmmss"));

			if (len > pos) {
				byte[] attribute = new byte[len - pos]; // 扩展属性
				System.arraycopy(data, pos, attribute, 0, attribute.length);
				p.setAttribute(ConvUtil.bytes2Hex(attribute));
			}
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x0B03编码失败:", e);
		}
	}

}
