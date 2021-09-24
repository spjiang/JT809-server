package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0200;
import com.ctfo.protocol.P905.model.p2014.P0x0203;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 位置信息补传消息编解码处理类。数据格式定义<code>P0x0203</code>
 *
 * <p>
 * 消息ID：0x0203
 *
 * @author 凉意 2018年5月2日 下午1:40:34
 * @see
 * @version 1.0
 */
public class P0x0203PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0203编码失败,传入消息对象为空。");
		}
		try {
			P0x0203 p = (P0x0203) model;
			List<P0x0200> positions = p.getPositions();
			IoBuffer buf = IoBuffer.allocate(25).setAutoExpand(true);
			for (P0x0200 p0x0200 : positions) {
				buf.putInt(p0x0200.getArmFlag());
				buf.putInt(p0x0200.getStateFlag());
				buf.putInt(p0x0200.getLat());
				buf.putInt(p0x0200.getLng());
				buf.putShort(p0x0200.getSpeed());
				buf.put(p0x0200.getDirection());
				String date = DateUtil.formatToString(p0x0200.getUpTime(), "yyMMddHHmmss");
				buf.put(ConvUtil.bcd2Bytes(date));
			}
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x0203编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 25 > data.length) {
			throw new P905Exception("P0x0203解析失败，消息长度不足25字节。");
		}
		P0x0203 p = new P0x0203();
		List<P0x0200> positions = new ArrayList<P0x0200>();
		try {
			int len = data.length;
			int pos = 0;
			while(pos < len) {
				P0x0200 p0x0200 = new P0x0200();
				byte[] armFlg = new byte[4]; // 报警标志位
				byte[] stateFlag = new byte[4]; // 状态标志位
				byte[] lat = new byte[4]; // 纬度
				byte[] lng = new byte[4]; // 经度
				byte[] speed = new byte[2]; // 速度
				byte[] upTime = new byte[6]; // 上报时间
				System.arraycopy(data, pos, armFlg, 0, 4);
				pos += 4;
				System.arraycopy(data, pos, stateFlag, 0, 4);
				pos += 4;
				System.arraycopy(data, pos, lat, 0, 4);
				pos += 4;
				System.arraycopy(data, pos, lng, 0, 4);
				pos += 4;
				System.arraycopy(data, pos, speed, 0, 2);
				pos += 2;
				byte direction = data[pos]; // 方向
				pos ++;
				System.arraycopy(data, pos, upTime, 0, 6);
				pos += 6;
				p0x0200.setArmFlag(ConvUtil.bytes2Int(armFlg));
				p0x0200.setStateFlag(ConvUtil.bytes2Int(stateFlag));
				p0x0200.setLat(ConvUtil.bytes2Int(lat));
				p0x0200.setLng(ConvUtil.bytes2Int(lng));
				p0x0200.setSpeed(ConvUtil.bytes2Short(speed));
				p0x0200.setDirection(direction);
				String dateStr = "20" + ConvUtil.bytes2Bcd(upTime);
				p0x0200.setUpTime(DateUtil.parseToDate(dateStr, "yyyyMMddHHmmss"));
				positions.add(p0x0200);
			}
			p.setPositions(positions);
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x0203解码失败:", e);
		}
	}

}
