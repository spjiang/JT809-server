package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8105;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 终端控制消息编解码处理类，数据格式参照<code>P0x8105</code>
 *
 * <p>
 * 消息ID：0x8105
 *
 * @author 凉意 2018年4月26日 下午4:59:08
 * @see
 * @version 1.0
 */
public class P0x8105PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x8105消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x8105 p = (P0x8105) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			if ((byte) 1 == p.getCommandWord()) { // 无线升级
				buf.put(p.getCommandWord()); // 命令字
				buf.put(p.getDeviceType());
				buf.put(p.getFirmFlag());
				buf.put(ConvUtil.bcd2Bytes(p.getHardwareVesion()));
				buf.put(ConvUtil.bcd2Bytes(p.getSoftwareVesion()));
				buf.put(ConvUtil.string2Bytes(p.getApn())).put((byte) 0);
				buf.put(ConvUtil.string2Bytes(p.getUserName())).put((byte) 0);
				buf.put(ConvUtil.string2Bytes(p.getPassword())).put((byte) 0);
				buf.put(ConvUtil.string2Bytes(p.getUpgradeServerIp())).put((byte) 0);
				buf.putShort(p.getUpgradeServerPort());
			} else { // 其他，暂时不用
				buf.put(p.getCommandWord());
			}
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x8105编码失败:", e);
		}
	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 1 > data.length) {
			throw new P905Exception("P0x8105消息体解码失败，消息为空或者长度不足。");
		}
		try {
			P0x8105 p = new P0x8105();
			int pos = 0;
			byte commandWord = data[pos]; // 命令字
			if ((byte) 1 == commandWord) { // 无线升级
				p.setCommandWord(commandWord);
				pos++;
				p.setDeviceType(data[pos]);
				pos++;
				p.setFirmFlag(data[pos]);
				pos++;
				p.setHardwareVesion(ConvUtil.byte2Bcd(data[pos]));
				pos++;
				byte[] softwareVesion = new byte[2];
				System.arraycopy(data, pos, softwareVesion, 0, 2);
				p.setSoftwareVesion(ConvUtil.bytes2Bcd(softwareVesion));
				pos += 2;

				int dataStrLen = data.length;
				IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
				// APN
				while (pos <= dataStrLen) {
					if (data[pos] == (byte) 0) { // 每一项数据以0x00结尾
						buf.flip();
						byte[] b = new byte[buf.limit()];
						buf.get(b);
						p.setApn(ConvUtil.bytes2String(b));
						pos++;
						break;
					} else {
						buf.put(data[pos]);
					}
					pos++;
				}
				// 拨号用户名
				buf = IoBuffer.allocate(1).setAutoExpand(true);
				while (pos <= dataStrLen) {
					if (data[pos] == (byte) 0) { // 每一项数据以0x00结尾
						buf.flip();
						byte[] b = new byte[buf.limit()];
						buf.get(b);
						p.setUserName(ConvUtil.bytes2String(b));
						pos++;
						break;
					} else {
						buf.put(data[pos]);
					}
					pos++;
				}
				// 拨号密码
				buf = IoBuffer.allocate(1).setAutoExpand(true);
				while (pos <= dataStrLen) {
					if (data[pos] == (byte) 0) { // 每一项数据以0x00结尾
						buf.flip();
						byte[] b = new byte[buf.limit()];
						buf.get(b);
						p.setPassword(ConvUtil.bytes2String(b));
						pos++;
						break;
					} else {
						buf.put(data[pos]);
					}
					pos++;
				}
				// 升级服务器地址
				buf = IoBuffer.allocate(1).setAutoExpand(true);
				while (pos <= dataStrLen) {
					if (data[pos] == (byte) 0) { // 每一项数据以0x00结尾
						buf.flip();
						byte[] b = new byte[buf.limit()];
						buf.get(b);
						p.setUpgradeServerIp(ConvUtil.bytes2String(b));
						pos++;
						break;
					} else {
						buf.put(data[pos]);
					}
					pos++;
				}
				// 升级服务器开放端口,UINT16
				byte[] upgradeServerPort = new byte[2];
				System.arraycopy(data, pos, upgradeServerPort, 0, 2);
				p.setUpgradeServerPort(ConvUtil.bytes2Short(upgradeServerPort));
			} else {
				p.setCommandWord(commandWord);
			}
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x8105解码失败:", e);
		}

	}

}
