package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * * 中心巡检消息体数据格式Model定义
 * <p>
 * 14+0 巡检设备类型代码 UINT8[N]
 * N为根据巡检设备的数量而定，N=0时表示对所有设备进行巡检；N不为0时，表示对指定的一个或多个设备进行巡检。<br>
 *
 * @author fanya 2018年5月4日 下午2:58:34
 * @see
 * @version 1.0
 */
public class P0x8B11 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 巡检设备类型代码
	 * <p>
	 * 设备类型代码 设备名称 <br>
	 * 0x00 智能服务终端<br>
	 * 0x01 通讯模块(GPRS/CDMA)<br>
	 * 0x02 计价器<br>
	 * 0x03 出租汽车安全模块TSM<br>
	 * 0x04 LED显示屏<br>
	 * 0x05 智能顶灯<br>
	 * 0x06 服务评价器<br>
	 * 0x07 摄像头<br>
	 * 0x08 卫星定位设备<br>
	 * 0x09 液晶（LCD）多媒体屏<br>
	 * 0x0A-0xFE RFU
	 */
	private byte[] inspection;

	public byte[] getInspection() {
		if (null != inspection)
			return inspection.clone();
		return new byte[] {};
	}

	public void setInspection(byte[] inspection) {
		if (null != inspection)
			this.inspection = inspection.clone();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8B11 [");
		sb.append("inspection=").append(this.inspection).append("]");
		return sb.toString();
	}
}
