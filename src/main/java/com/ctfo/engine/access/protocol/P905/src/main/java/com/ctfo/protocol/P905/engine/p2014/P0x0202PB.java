package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0200;
import com.ctfo.protocol.P905.model.p2014.P0x0202;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.Map;
import java.util.Map.Entry;

/**
 * 位置跟踪信息汇报消息编解码处理类，数据格式定义<code>P0x0202</code>
 *
 * <p>
 * 类详细说明
 *
 * @author 凉意 2018年5月2日 下午3:04:37
 * @see
 * @version 1.0
 */
public class P0x0202PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0202消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x0202 p0x0202 = (P0x0202) model;
			P0x0200 p = p0x0202.getPosition();
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.putInt(p.getArmFlag());
			buf.putInt(p.getStateFlag());
			buf.putInt(p.getLat());
			buf.putInt(p.getLng());
			buf.putShort(p.getSpeed());
			buf.put(p.getDirection());
			String date = DateUtil.formatToString(p.getUpTime(), "yyMMddHHmmss");
			buf.put(ConvUtil.bcd2Bytes(date));
			// 附加信息
			Map<String, Object> additionalMap = p.getAdditionalMap();
			if (null != additionalMap) { // 存在附加信息
				for (Entry<String, Object> e : additionalMap.entrySet()) {
					String[] key = e.getKey().split("_");
					String id = key[0];
					buf.put(ConvUtil.hex2Bytes(id));
					byte length = Byte.parseByte(key[1]);
					buf.put(length);
					Object value = e.getValue();
					if (4 == length) { // 类型为UINT32消息处理
						buf.putInt(Integer.parseInt(value.toString()));
					} else if (2 == length) {// 类型为UINT16或者INT16消息处理
						buf.putShort(Short.parseShort(value.toString()));
					} else { // 按字符串类型进行处理
						buf.put(ConvUtil.string2Bytes(value.toString()));
					}
				}
			}
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x0202编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 25 > data.length) {
			throw new P905Exception("P0x0202消息体解码失败，消息为空或者长度不足。");
		}
		try {
			P0x0200 p = new P0x0200();
			byte[] armFlg = new byte[4]; // 报警标志位
			byte[] stateFlag = new byte[4]; // 状态标志位
			byte[] lat = new byte[4]; // 纬度
			byte[] lng = new byte[4]; // 经度
			byte[] speed = new byte[2]; // 速度
			byte direction = data[18]; // 方向
			byte[] upTime = new byte[6]; // 上报时间
			System.arraycopy(data, 0, armFlg, 0, 4);
			System.arraycopy(data, 4, stateFlag, 0, 4);
			System.arraycopy(data, 8, lat, 0, 4);
			System.arraycopy(data, 12, lng, 0, 4);
			System.arraycopy(data, 16, speed, 0, 2);
			System.arraycopy(data, 19, upTime, 0, 6);

			p.setArmFlag(ConvUtil.bytes2Int(armFlg));
			p.setStateFlag(ConvUtil.bytes2Int(stateFlag));
			p.setLat(ConvUtil.bytes2Int(lat));
			p.setLng(ConvUtil.bytes2Int(lng));
			p.setSpeed(ConvUtil.bytes2Short(speed));
			p.setDirection(direction);
			String dateStr = "20" + ConvUtil.bytes2Bcd(upTime);
			p.setUpTime(DateUtil.parseToDate(dateStr, "yyyyMMddHHmmss"));

			// 附加信息，通过消息长度来判定是否存在附加信息
			int dataLen = data.length;
			if (25 < dataLen) {// 消息长度大于25，表示存在附加信息
				int pos = 25;
				while (pos < dataLen) {
					// 附加信息ID
					byte[] id = { data[pos] };
					String idHex = ConvUtil.bytes2Hex(id);
					pos++;
					// 附加信息长度
					byte length = data[pos];
					pos++;
					// 附加信息项内容
					byte[] content = new byte[length];
					System.arraycopy(data, pos, content, 0, length);
					if (4 == length) { // 类型为UINT32消息处理
						p.getAdditionalMap().put(idHex + "_" + length, ConvUtil.bytes2Int(content));
					} else if (2 == length) {// 类型为UINT16或者INT16消息处理
						p.getAdditionalMap().put(idHex + "_" + length, ConvUtil.bytes2Short(content));
					} else { // 按字符串类型进行处理
						p.getAdditionalMap().put(idHex + "_" + length, ConvUtil.bytes2String(content));
					}
					pos += length;
				}
			}
			P0x0202 p0x0202 = new P0x0202();
			p0x0202.setPosition(p);
			return p0x0202;
		} catch (Exception e) {
			throw new P905Exception("P0x0202解码失败:", e);
		}
	}

}
