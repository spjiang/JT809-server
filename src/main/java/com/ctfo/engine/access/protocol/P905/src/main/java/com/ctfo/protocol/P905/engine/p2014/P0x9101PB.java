package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x9101;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 *
 * 实时音视频传输请求 解码处理类
 *
 * <p>类详细说明
 * @author zx 2018年9月27日 下午4:18:54
 * @see
 * @version 1.0
 */
public class P0x9101PB implements IPassBody {

	@Override
	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if(null == model) {
			throw new P905Exception("P0x9101消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x9101 p = (P0x9101) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			//服务器IP地址长度
			buf.put(p.getServerIpLength());
			//服务器IP地址
			buf.put(ConvUtil.string2Bytes(p.getServerIp()));
			//服务器视频通道监听端口号（TCP）
			buf.putShort(p.getServerTcpPort());
			//服务器视频通道监听端口号（UDP）
			buf.putShort(p.getServerUdpPort());
			//逻辑通道号
			buf.put(p.getLogicalChannel());
			//数据类型
			buf.put(p.getDateType());
			//码流类型
			buf.put(p.getCodeStreamType());
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x9101编码失败:", e);
		}
	}

	@Override
	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if(null == data || 0 > data.length) {
			throw new P905Exception("P0x9101解析失败，消息长度不足。");
		}
		try {
			P0x9101 p = new P0x9101();
			int pos = 0;
			//服务器IP地址长度
			p.setServerIpLength(data[pos]);
			pos += 1;
			//ip地址
			byte[] serverIp = new byte[p.getServerIpLength()];
			System.arraycopy(data, pos, serverIp, 0, p.getServerIpLength());
			p.setServerIp(ConvUtil.bytes2String(serverIp));
			pos += p.getServerIpLength();
			//服务器视频通道监听端口号（TCP）
			byte[] serverTcpPort = new byte[2];
			System.arraycopy(data, pos, serverTcpPort, 0, 2);
			p.setServerTcpPort(ConvUtil.bytes2Short(serverTcpPort));
			pos += 2;
			//服务器视频通道监听端口号（UDP）
			byte[] serverUdpPort = new byte[2];
			System.arraycopy(data, pos, serverUdpPort, 0, 2);
			p.setServerUdpPort(ConvUtil.bytes2Short(serverUdpPort));
			pos += 2;
			//逻辑通道号
			p.setLogicalChannel(data[pos]);
			pos ++;
			//数据类型
			p.setDateType(data[pos]);
			pos ++;
			//码流类型
			p.setCodeStreamType(data[pos]);
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x9101解码失败："+e);
		}
	}

}
