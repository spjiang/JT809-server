package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0B11;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备巡检应答消息体编解码处理类，数据格式定义参考<code>P0x0B11</code>
 *
 * <p>
 * 消息ID：0x0B11
 *
 * @author 凉意 2018年5月16日 下午8:16:16
 * @see
 * @version 1.0
 */
public class P0x0B11PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0B11消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x0B11 p = (P0x0B11) model;
			List<P0x0B11> list = p.getList();
			if (null == list || 0 >= list.size()) {
				return null;
			}

			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			for (P0x0B11 item : list) {
				buf.put((byte) item.getEquipType()); // 设备类型
				IoBuffer v = IoBuffer.allocate(1).setAutoExpand(true);
				if (0 == item.getEquipType()) { // 0x00 智能服务终端
					v.put(ConvUtil.bcd2Bytes(item.getEquipSerId()));
					v.put(ConvUtil.bcd2Bytes(item.getHardwareVersion()));
					v.put(ConvUtil.bcd2Bytes(item.getSoftwareVersion()));
					v.putInt(item.getEquipStatus());
					v.putInt(item.getEquipAlarm());
					v.put(item.getLoginCacheLine());
					v.put(item.getLogOutCacheLine());
					v.put(item.getOpDataCacheLine());
					v.put(item.getCardOpDataCacheLine());
				}
				v.flip();
				byte[] b = new byte[v.limit()];
				buf.get(b);
				buf.put((byte) b.length);
				buf.put(b);
			}
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x0B11编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 0 > data.length) {
			throw new P905Exception("P0x0B11消息体解码失败，消息为空或者长度不足。");
		}
		try {
			// 该模式为TLV格式，注意解析
			// 定义一个指针，作用：指向每个TLV的类型
			int point = 0;
			// 数据长度
			int dataLen = data.length;
			List<P0x0B11> list = new ArrayList<P0x0B11>();
			while (point < dataLen) {
				P0x0B11 p = new P0x0B11();
				byte type = data[point]; // 巡检设备类型
				p.setEquipType(type);
				point++;
				byte length = data[point];// 具体数据长度
				point++;
				byte[] content = new byte[length]; // 具体数据
				System.arraycopy(data, point, content, 0, length);
				p.setGetValue(ConvUtil.bytes2Hex(content));
				point += length;
				// 定义内容数据每次取值位置变量
				int pos = 0;
				// 终端设备序列号 BCD[5]
				byte[] value = new byte[5];
				System.arraycopy(content, pos, value, 0, 5);
				p.setEquipSerId(ConvUtil.bytes2Bcd(value));
				pos += 5;
				p.setHardwareVersion(ConvUtil.byte2Bcd(content[pos]));
				pos++;
				// 目前实现，0x00、0x02、0x05类型，后面需要扩展可以在这里进行添加
				if (0 == type) { // 0x00 智能服务终端
					// 软件版本号 BCD[2]
					byte[] softwareVersion = new byte[2];
					System.arraycopy(content, pos, softwareVersion, 0, 2);
					p.setSoftwareVersion(ConvUtil.bytes2Bcd(softwareVersion));
					pos += 2;
					// 终端设备状态 UNIT32
					byte[] equipStatus = new byte[4];
					System.arraycopy(content, pos, equipStatus, 0, 4);
					p.setEquipStatus(ConvUtil.bytes2Int(equipStatus));
					pos += 4;
					// 终端报警标志 UNIT32
					byte[] equipAlarm = new byte[4];
					System.arraycopy(content, pos, equipAlarm, 0, 4);
					p.setEquipAlarm(ConvUtil.bytes2Int(equipAlarm));
					pos += 4;
					// 签到缓存数据条数 INT8
					p.setLoginCacheLine(content[pos]);
					pos++;
					// 签退缓存数据条数 INT8
					p.setLogOutCacheLine(content[pos]);
					pos++;
					// 营运记录缓存条数 INT8
					p.setOpDataCacheLine(content[pos]);
					pos++;
					// 一卡通交易缓存条数 INT8
					p.setCardOpDataCacheLine(content[pos]);
					pos++;
				}
				list.add(p);
			}
			P0x0B11 result = new P0x0B11();
			result.setList(list);
			return result;
		} catch (Exception e) {
			throw new P905Exception("P0x0B11解码失败:", e);
		}
	}

}
