package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;


import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 外围设备指令下行透传消息体数据格式model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0 TypeID UINT8 参见外围设备指令下行透传的消息体<br>
 * 14+1 厂商标识 UINT8<br>
 * 14+2 命令类型 UINT16<br>
 * 14+4 数据包 UINT8[]<br>
 *
 * @author xiahancheng 2018年5月7日 上午9:21:12
 * @see
 * @version 1.0
 */
public class P0x0B10 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * TypeID
	 */
	private byte TypeID;

	/**
	 * 厂商标识
	 */
	private byte quotientIdentification;

	/**
	 * 命令类型
	 */
	private short commandType;

	/**
	 * 数据包
	 */

	private String dataPacket;

	/**
	 * @return the typeID
	 */
	public byte getTypeID() {
		return TypeID;
	}

	/**
	 * @param typeID
	 *            the typeID to set
	 */
	public void setTypeID(byte typeID) {
		TypeID = typeID;
	}

	/**
	 * @return the quotientIdentification
	 */
	public byte getQuotientIdentification() {
		return quotientIdentification;
	}

	/**
	 * @param quotientIdentification
	 *            the quotientIdentification to set
	 */
	public void setQuotientIdentification(byte quotientIdentification) {
		this.quotientIdentification = quotientIdentification;
	}

	/**
	 * @return the commandType
	 */
	public short getCommandType() {
		return commandType;
	}

	/**
	 * @param commandType
	 *            the commandType to set
	 */
	public void setCommandType(short commandType) {
		this.commandType = commandType;
	}

	/**
	 * @return the dataPacket
	 */
	public String getDataPacket() {
		return dataPacket;
	}

	/**
	 * @param dataPacket
	 *            the dataPacket to set
	 */
	public void setDataPacket(String dataPacket) {
		this.dataPacket = dataPacket;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x0B10 [");
		sb.append("TypeID=").append(this.TypeID).append(",");
		sb.append("quotientIdentification=").append(this.quotientIdentification).append(",");
		sb.append("commandType=").append(this.commandType).append(",");
		sb.append("dataPacket=").append(this.dataPacket).append("]");
		return sb.toString();
	}

}
