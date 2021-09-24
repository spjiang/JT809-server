package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;


import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 车辆控制消息体数据格式Model定义
 *
 * <p>
 * 14+0 控制项 UINT8 控制指令标志位数据格式见表 41 控制指令标志位数据格式 14+1 控制命令 UINT8
 *
 * @author xiahancheng 2018年5月3日 下午3:07:37
 * @see
 * @version 1.0
 */
public class P0x8500 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 控制项
	 */
	private byte controlItem;

	/**
	 * 控制命令
	 * <p>
	 * 控制项 标志 <br>
	 * 0 0：恢复车辆油路；1：断开车辆油路<br>
	 * 1 0：恢复车辆电路；1：断开车辆电路<br>
	 * 2 0：车门解锁；1：车门加锁<br>
	 * 3 0：车辆解除锁定；1：车辆锁定<br>
	 *
	 */
	private byte controlCommand;

	/**
	 * @return the controlItem
	 */
	public byte getControlItem() {
		return controlItem;
	}

	/**
	 * @param controlItem
	 *            the controlItem to set
	 */
	public void setControlItem(byte controlItem) {
		this.controlItem = controlItem;
	}

	/**
	 * @return the controlCommand
	 */
	public byte getControlCommand() {
		return controlCommand;
	}

	/**
	 * @param controlCommand
	 *            the controlCommand to set
	 */
	public void setControlCommand(byte controlCommand) {
		this.controlCommand = controlCommand;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8500 [");
		sb.append("controlItem=").append(this.controlItem).append(",");
		sb.append("controlCommand=").append(this.controlCommand).append("]");
		return sb.toString();
	}

}
