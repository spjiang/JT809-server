package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;


import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x9102;
import com.ctfo.protocol.support.exception.P905Exception;

/**
 *
 * 音视频传输控制 解码处理类
 *
 * <p>类详细说明
 * @author zx 2018年9月27日 下午5:16:27
 * @version 1.0
 */
public class P0x9102PB implements IPassBody {

	@Override
	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x9102消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x9102 p = (P0x9102) model;
			byte[] b = new byte[4];
			b[0] = p.getCameraChannelNumber();//摄像头通道号
			b[1] = p.getControlInstruction();//控制指令
			b[2] = p.getCloseAudioVideoType();//关闭音视频类型
			b[3] = p.getSwitchingBitstreamType();//切换码流类型
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x9102编码失败:", e);
		}
	}

	@Override
	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if(null == data || 4 > data.length) {
			throw new P905Exception("P0x9102解析失败，消息长度不足。");
		}
		try {
			P0x9102 p = new P0x9102();
			//摄像头通道号
			p.setCameraChannelNumber(data[0]);
			//控制指令
			p.setControlInstruction(data[1]);
			//关闭音视频类型
			p.setCloseAudioVideoType(data[2]);
			//切换码流类型
			p.setSwitchingBitstreamType(data[3]);
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x9102解码失败:"+e);
		}
	}

}
