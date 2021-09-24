package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.vo;

import java.io.Serializable;

/**
 * 终端参数设置VO对象
 * <p>
 * 本对象定义参数项数据格式
 *
 * @author 凉意 2018年4月25日 下午5:33:00
 * @see
 * @version 1.0
 */
public class P0x8103VO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 参数ID,UINT16
	 * <p>
	 * 参数ID 数据类型 说明<br>
	 * 0x0001 UINT32 终端心跳发送间隔，秒（默认30秒）<br>
	 * 0x0002 UINT32 TCP消息应答超时时间，秒（默认30秒）<br>
	 * 0x0003 UINT32 TCP消息重传次数（默认3次）<br>
	 * 0x0004 UINT32 SMS消息应答超时时间，秒（默认30秒）<br>
	 * 0x0005 UINT32 SMS消息重传次数（默认3次）<br>
	 * 0X0006～0X000F 预留<br>
	 * 0X0010 STRING 主服务器APN，无线通信拨号访问点<br>
	 * 0X0011 STRING 主服务器无线通信拨号用户名<br>
	 * 0x0012 STRING 主服务器无线通信拨号密码<br>
	 * 0x0013 STRING 主服务器地址,IP或域名<br>
	 * 0x0014 STRING 备份服务器APN，无线通信拨号访问点<br>
	 * 0x0015 STRING 备份服务器无线通信拨号用户名<br>
	 * 0x0016 STRING 备份服务器无线通信拨号密码<br>
	 * 0x0017 STRING 备份服务器地址,IP或域名<br>
	 * 0x0018 UINT32 主服务器TCP端口<br>
	 * 0x0019 UINT32 备份服务器TCP端口<br>
	 * 0x001A～0x001F 保留<br>
	 * 0X0020 UINT32 位置汇报策略，0：定时汇报；1：定距汇报；定时 + 定距汇报<br>
	 * 0X0021 UINT32位置汇报方案，0：根据ACC状态；1：根据空重车状态；2：根据登录状态ACC状态，先判断登录状态，若登录再根据ACC状态；3：根据登录状态空重车状态，先判断登录状态，若登录再根据空重车状态<br>
	 * 0X0022 UINT32 未登录汇报时间间隔，秒，>0<br>
	 * 0X0023 UINT32 ACC OFF汇报时间间隔，秒，>0<br>
	 * 0X0024 UINT32 ACC ON汇报时间间隔，秒，>0<br>
	 * 0X0025 UINT32 空车汇报时间间隔，秒，>0<br>
	 * 0X0026 UINT32 重车汇报时间间隔，秒，>0<br>
	 * 0X0027 UINT32 休眠时汇报时间间隔，秒，>0<br>
	 * 0X0028 UINT32 紧急报警时汇报时间间隔，秒，>0<br>
	 * 0X0029 UINT32 未登录汇报距离间隔，米，>0<br>
	 * 0X002A UINT32 ACC OFF汇报距离间隔，米，>0<br>
	 * 0X002B UINT32 ACC ON汇报距离间隔，米，>0<br>
	 * 0X002C UINT32 空车汇报距离间隔，米，>0<br>
	 * 0X002D UINT32 重车汇报距离间隔，米，>0<br>
	 * 0X002E UINT32 休眠时汇报距离间隔，米，>0<br>
	 * 0X002F UINT32 紧急报警时汇报距离间隔，米，>0<br>
	 * 0X0030 UINT32 拐点补传角度，<180<br>
	 * 0X00032～0X003F 预留 0X0040 STRING 监控指挥中心电话号码<br>
	 * 0X0041 STRING 复位电话号码<br>
	 * 0X0042 STRING 恢复出厂设置电话号码<br>
	 * 0X0043 STRING 监控指挥中心SMS电话号码<br>
	 * 0X0044 STRING 接收终端SMS文本报警号码<br>
	 * 0X0045 UINT32 终端电话接听策略，0：自动接听；1：ACC ON时自动接听，OFF时手动接听<br>
	 * 0X0046 UINT32 每次最长通话时间，秒<br>
	 * 0X0047 UINT32 当月最长通话时间，秒<br>
	 * 0X0048 UINT32 电话短号长度<br>
	 * 0X0049 STRING 监听电话号码<br>
	 * 0X004A STRING 终端设备维护密码<br>
	 * 0X004B UINT 终端的语音播报音量控制0-9 0静音 9为最高<br>
	 * 0X004C～0X004F 预留<br>
	 * 0X0050 UINT32 报警屏蔽字，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警被屏蔽<br>
	 * 0X0051 UINT32 报警发送文本SMS开关，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警时发送文本SMS<br>
	 * 0X0052 UINT32 报警拍摄开关，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警时摄像头拍摄<br>
	 * 0X0053 UINT32 报警拍摄存储标志，与位置信息汇报消息中的报警标志相对应，相应位为1则对相应报警时拍的照片进行存储，否则实时上传<br>
	 * 0X0055 UINT32 最高速度，KM/H<br>
	 * 0X0056 UINT32 超速持续时间，秒<br>
	 * 0X0057 UINT32 连续驾驶时间门限，秒<br>
	 * 0X0058 UINT32 最小休息时间，秒<br>
	 * 0X0059 UINT32 最长停车时间，秒<br>
	 * 0X005A UINT32 当天累计驾驶时间门限，秒<br>
	 * 0X005B～0X006F 预留<br>
	 * 0X0070 UINT32 图像/视频质量，1～10，1最好<br>
	 * 0X0071 UINT32 亮度，0～255<br>
	 * 0X0072 UINT32 对比度，0～127<br>
	 * 0X0073 UINT32 饱和度，0～127<br>
	 * 0X0074 UINT32 色度，0～255<br>
	 * 0X0075～0X007F 预留<br>
	 * 0X0080 UINT32 车辆里程表读数，1/10KM<br>
	 * 0X0081 UINT32 车辆所在的省域ID，1～255<br>
	 * 0X0082 UINT32 车辆所在的市域ID，1～255<br>
	 * 0X0090 BCD[2] 计价器营运次数限制，0-9999；0表示不做限制<br>
	 * 0X0091 BCD[5] 计价器营运时间限制，YYYY-MM-DD-hh，0000000000表示不做限制<br>
	 * 0X0092-0X009F 预留<br>
	 * 0X00A0 UINT8 智能终端录音模式（0X01：全程录音；0X02：翻牌录音），默认02<br>
	 * 0X00A1 UINT8 智能终端录音文件最大时长（1-255）分钟<br>
	 * 0X00A2 UINT8 液晶（LCD）心跳时间间隔(单位S 1-60)<br>
	 * 0X00A3 UINT8 LED心跳时间间隔(单位S 1-60)<br>
	 * 0X00AF UINT16 ACC OFF后进入休眠模式的时间，单位S<br>
	 * 0X00B0 UINT8 视频服务器协议模式 0X00：TCP；0X01：UDP<br>
	 * 0X00B1 STRING 视频服务器APN，无线通信拨号访问点<br>
	 * 0X00B2 STRING 视频服务器无线通信拨号用户名<br>
	 * 0X00B3 STRING 视频服务器无线通信拨号密码<br>
	 * 0X00B4 STRING 视频服务器地址,IP或域名<br>
	 * 0X00B5 UINT32 视频服务器端口<br>
	 * 0x001A STRING 一卡通主服务器地址,IP 或域名<br>
	 * 0x001B UINT32 一卡通主服务器TCP 端口<br>
	 * 0x001C STRING 一卡通备份服务器地址IP 或域名<br>
	 * 0x001D UINT32 一卡通备份服务器TCP 端口<br>
	 * 0X0092 STRING 经营许可证号<br>
	 * 0X0093 STRING 企业简称<br>
	 * 0X0094 ASCII[6] 车牌号码(不包括汉字)<br>
	 * 0x0100 STRING 空转重后语音提示的附加信息，最多100字节。默认内容为：“请对我们的服务进行监督”。该参数用于定义在播放完“欢迎乘坐XXX公司出租汽车”后播放的内容。<br>
	 * 0x0200 UINT16 超速报警提醒次数，默认值：xx 0-不提醒；0xFFFF：一直提醒；其他：提醒次数<br>
	 * 0x0201 UINT16 超速报警提醒间隔：单位：秒，默认值：xx<br>
	 * 0x0202 UINT16 连续疲劳驾驶报警提醒次数，默认值：xx 0-不提醒；0xFFFF：一直提醒；其他：提醒次数<br>
	 * 0x0203 UINT16 连续疲劳驾驶提醒间隔：单位：秒，默认值：xx<br>
	 * 0x0204 UINT16 累计疲劳驾驶报警提醒次数，默认值：xx 0-不提醒；0xFFFF：一直提醒；其他：提醒次数<br>
	 * 0x0205 UINT16 累计疲劳驾驶提醒间隔：单位：秒，默认值：xx<br>
	 * 0x0230 STRING 电召中心电话号码（该号码不做呼入呼出限制）<br>
	 * 0x0250 BCD[2] 附加费（为0 标识不启用），格式为XXX.X 元<br>
	 * 0x0251 BCD[4] 附加费启用日期，格式为YYYYMMDD（全0 标识不启用）<br>
	 * 0x0252 BCD[2] 电召费（全0 标识不启用），格式为XXX.X 元<br>
	 * 0x0253 BCD[4] 电召费启用日期，格式为YYYYMMDD（全0 标识不启用）<br>
	 */
	private short paramId;

	/**
	 * 参数长度
	 */
	private byte paramLen;

	/**
	 * 参数值，这里统一定义为字符串。需要使用到本属性时，再进行数据类型转换
	 */
	private String paramVal;

	/**
	 * @return the paramId
	 */
	public short getParamId() {
		return paramId;
	}

	/**
	 * @param paramId
	 *            the paramId to set
	 */
	public void setParamId(short paramId) {
		this.paramId = paramId;
	}

	/**
	 * @return the paramLen
	 */
	public byte getParamLen() {
		return paramLen;
	}

	/**
	 * @param paramLen
	 *            the paramLen to set
	 */
	public void setParamLen(byte paramLen) {
		this.paramLen = paramLen;
	}

	/**
	 * @return the paramVal
	 */
	public String getParamVal() {
		return paramVal;
	}

	/**
	 * @param paramVal
	 *            the paramVal to set
	 */
	public void setParamVal(String paramVal) {
		this.paramVal = paramVal;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("P0x8103VO [");
		sb.append("paramId=").append(paramId).append(",");
		sb.append("paramLen=").append(paramLen).append(",");
		sb.append("paramVal=").append(paramVal).append("]");
		return sb.toString();
	}
}
