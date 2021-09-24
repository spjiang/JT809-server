package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;


import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;

/**
 * 外围设备指令下行透传消息体数据格式model定义
 *
 * <p>
 * 起始字节 字段 数据类型 说明<br>
 * 14+0 TypeID UINT8 参见10.2编号编码规则，设备类型代码定义<br>
 * 14+1 DataType UINT16 参见表 59 DataType属性定义<br>
 * 14+3 数据包 UINT8[] 如果采用加密模式时，建议不超过384个字节，非加密模式建议不超过512字节。
 * 数据内容为通讯协议体(命令字2字节+数据区)的明文或密文，终端负责协议的组包.
 *
 * @author xiahancheng 2018年5月7日 上午8:55:02
 * @see
 * @version 1.0
 */
public class P0x8B10 extends PModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * TypeID
	 */
	private byte TypeID;

	/**
	 * DataType<br>
	 * 0-2 压缩算法描述，000：数据无压缩；001：gz压缩；其它：RFU <br>
	 * 3 1-密文，0-明文，<br>
	 * 4-15 预留
	 */
	private short DataType;

	/**
	 * 数据包,如果采用加密模式时，建议不超过384个字节，非加密模式建议不超过512字节。
	 * 数据内容为通讯协议体(命令字2字节+数据区)的明文或密文，终端负责协议的组包。
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
	 * @return the dataType
	 */
	public short getDataType() {
		return DataType;
	}

	/**
	 * @param dataType
	 *            the dataType to set
	 */
	public void setDataType(short dataType) {
		DataType = dataType;
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
		StringBuilder sb = new StringBuilder("P0x8B10 [");
		sb.append("TypeID=").append(this.TypeID).append(",");
		sb.append("DataType=").append(this.DataType).append(",");
		sb.append("dataPacket=").append(this.dataPacket).append("]");
		return sb.toString();
	}

}
