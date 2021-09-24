package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 位置跟踪控制消息数据格式Model定义<br>
 * 应答为终端通用应答，智能终端收到0x8202指令后，以终端通用应答回复后，按0x8202中定义的方式，以0x0202命令进行应答。
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0 属性 UINT8
 * 标示后面2字段是按时间还是距离，0X00:按时间间隔、持续时间；0X11:按距离间隔、持续距离；0X01:按时间间隔、持续距离；0X10:按距离间隔、持续时间；0XFF：停止当前跟踪(终端忽略后面字段)<br>
 * 14+1 时间/距离间隔 UINT16 时间单位为秒，距离单位为米<br>
 * 14+3 持续时间/距离 UINT32 时间单位为秒，距离单位为米<br>
 *
 * @author 凉意 2018年5月2日 下午2:10:19
 * @see
 * @version 1.0
 */
public class P0x8202 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 属性
	 * <p>
	 *
	 * 标示后面2字段是按时间还是距离， <br>
	 * 0X00:按时间间隔、持续时间；<br>
	 * 0X11:按距离间隔、持续距离；<br>
	 * 0X01:按时间间隔、持续距离；<br>
	 * 0X10:按距离间隔、持续时间；<br>
	 * 0XFF：停止当前跟踪(终端忽略后面字段)
	 */
	private byte attr;

	/**
	 * 时间/距离间隔,时间单位为秒，距离单位为米
	 */
	private short interval;

	/**
	 * 持续时间/距离，时间单位为秒，距离单位为米
	 */
	private int continued;

	/**
	 * @return the attr
	 */
	public byte getAttr() {
		return attr;
	}

	/**
	 * @param attr the attr to set
	 */
	public void setAttr(byte attr) {
		this.attr = attr;
	}

	/**
	 * @return the interval
	 */
	public short getInterval() {
		return interval;
	}

	/**
	 * @param interval the interval to set
	 */
	public void setInterval(short interval) {
		this.interval = interval;
	}

	/**
	 * @return the continued
	 */
	public int getContinued() {
		return continued;
	}

	/**
	 * @param continued the continued to set
	 */
	public void setContinued(int continued) {
		this.continued = continued;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8202 [");
		sb.append("attr=").append(attr).append(",");
		sb.append("interval=").append(interval).append(",");
		sb.append("continued=").append(continued).append("]");
		return sb.toString();
	}
}
