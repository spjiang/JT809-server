package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808.model.p2011;

import com.ctfo.protocol.P808.model.PModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 终端注册消息体数据格式Model定义
 *
 * @author 凉意 2018年5月2日 下午5:29:13
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x0100 extends PModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 省域 ID
	 * <p>
	 * 标示终端安装车辆所在的省域，0 保留，由平台取默 认值。省域 ID 采用 GB/T 2260 中规定的行政区划代 码六位中前两位。
	 */
	private short provinceId;

	/**
	 * 市县域 ID
	 * <p>
	 * 标示终端安装车辆所在的市域和县域，0 保留，由平 台取默认值。市县域 ID 采用 GB/T 2260 中规定的行 政区划代码六位中后四位。
	 */
	private short cityId;

	/**
	 * 制造商 ID ,byte[5] ,5 个字节，终端制造商编码
	 */
	private String manufacturer;

	/**
	 * 终端型号 ，byte[20],20 个字节，此终端型号由制造商自行定义，位数不 足时，后补“0X00”。
	 */
	private String terminalModel;

	/**
	 * 终端 ID ,byte[7],7 个字节，由大写字母和数字组成，此终端 ID 由制 造商自行定义，位数不足时，后补“0X00”。
	 */
	private String terminaId;

	/**
	 * 车牌颜色 ,按照 JT/T415-2006 的 5.4.12。 未上牌时，取值为 0。
	 */
	private byte plateColor;

	/**
	 * 车辆标识，车牌颜色为 0 时，表示车辆 VIN； 否则，表示公安交通管理部门颁发的机动车号牌
	 */
	private String vehicleNo;
}
