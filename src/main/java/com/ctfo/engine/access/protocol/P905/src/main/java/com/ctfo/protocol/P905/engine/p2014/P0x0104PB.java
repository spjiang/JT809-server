package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x0104;
import com.ctfo.protocol.P905.vo.P0x8103VO;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询终端参数应答消息编解码处理类<code>P0x0104</code>
 * <p>
 * 消息ID：0x0104
 *
 * @author 凉意 2018年4月26日 下午4:05:35
 * @see
 * @version 1.0
 */
public class P0x0104PB implements IPassBody {

	public byte[] bodyToBytes(PModel model) throws P905Exception {
		if (null == model) {
			throw new P905Exception("P0x0104消息体编码失败，传入消息体实体对象为空。");
		}
		try {
			P0x0104 p = (P0x0104) model;
			IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true);
			buf.putShort(p.getAnsFlowNo());
			List<P0x8103VO> paramList = p.getParams();
			for (P0x8103VO pvo : paramList) {
				short id = pvo.getParamId();
				// 十六进制ID
				String idHex = ConvUtil.bytes2Hex(ConvUtil.short2Bytes(id));
				buf.putShort(id);
				byte length = pvo.getParamLen();
				buf.put(length);
				String value = pvo.getParamVal();
				if (idHex.equals("0001")) {// UINT32 终端心跳发送间隔，秒（默认30秒）
					buf.putInt(Integer.parseInt(value));
				}  else if (idHex.equals("0002")) { // UINT32 TCP消息应答超时时间，秒（默认30秒）
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0003")) { // UINT32 TCP消息重传次数（默认3次）
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0004")) { // UINT32 SMS消息应答超时时间，秒（默认30秒）
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0005")) { // UINT32 SMS消息重传次数（默认3次）
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0010")) { // STRING 主服务器APN，无线通信拨号访问点
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0011")) { // STRING 主服务器无线通信拨号用户名
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0012")) { // STRING 主服务器无线通信拨号密码
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0013")) { // STRING 主服务器地址,IP或域名
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0014")) { // STRING 备份服务器APN，无线通信拨号访问点
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0015")) { // STRING 备份服务器无线通信拨号用户名
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0016")) { // STRING 备份服务器无线通信拨号密码
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0017")) { // STRING 备份服务器地址,IP或域名
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0018")) { // UINT32 主服务器TCP端口
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0019")) { // UINT32 备份服务器TCP端口
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("001A")) { // STRING 一卡通主服务器地址,IP 或域名
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("001B")) { // UINT32 一卡通主服务器TCP 端口
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("001C")) { // STRING 一卡通备份服务器地址IP 或域名
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("001D")) { // UINT32一卡通备份服务器TCP 端口
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0020")) { // UINT32 位置汇报策略，0：定时汇报；1：定距汇报；定时 + 定距汇报
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0021")) { // UINT32 位置汇报方案，0：根据ACC状态；1：根据空重车状态；2：根据登录状态 + ACC状态，先判断登录状态，若登录再根据ACC状态；3：根据登录状态 + 空重车状态，先判断登录状态，若登录再根据空重车状态
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0022")) { // UINT32 未登录汇报时间间隔，秒，>0
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0023")) { // UINT32 ACC OFF汇报时间间隔，秒，>0
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0024")) { // UINT32 ACC ON汇报时间间隔，秒，>0
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0025")) { // UINT32 空车汇报时间间隔，秒，>0
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0026")) { // UINT32 重车汇报时间间隔，秒，>0
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0027")) { // UINT32 休眠时汇报时间间隔，秒，>0
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0028")) { // UINT32 紧急报警时汇报时间间隔，秒，>0
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0029")) { // UINT32 未登录汇报距离间隔，米，>0
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("002A")) { // UINT32 ACC OFF汇报距离间隔，米，>0
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("002B")) { // UINT32 ACC ON汇报距离间隔，米，>0
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("002C")) { // UINT32 空车汇报距离间隔，米，>0
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("002D")) { // UINT32 重车汇报距离间隔，米，>0
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("002E")) { // UINT32 休眠时汇报距离间隔，米，>0
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("002F")) { // UINT32 紧急报警时汇报距离间隔，米，>0
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0030")) { // UINT32 拐点补传角度，<180
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0040")) { // STRING 监控指挥中心电话号码
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0041")) { // STRING 复位电话号码
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0042")) { // STRING 恢复出厂设置电话号码
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0043")) { // STRING 监控指挥中心SMS电话号码
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0044")) { // STRING 接收终端SMS文本报警号码
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0045")) { // UINT32  终端电话接听策略，0：自动接听；1：ACC ON时自动接听，OFF时手动接听
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0046")) { // UINT32  每次最长通话时间，秒
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0047")) { // UINT32  当月最长通话时间，秒
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0048")) { // UINT32  电话短号长度
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0049")) { // UINT32 监听电话号码
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("004A")) { // STRING 终端设备维护密码
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("004B")) { // UINT 终端的语音播报音量控制0-9 0静音 9为最高
					buf.put(Byte.parseByte(value));
				} else if (idHex.equals("0050")) { // UINT32 报警屏蔽字，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警被屏蔽
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0051")) { // UINT32 报警发送文本SMS开关，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警时发送文本SMS
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0052")) { // UINT32 报警拍摄开关，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警时摄像头拍摄
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0053")) { // UINT32 报警拍摄存储标志，与位置信息汇报消息中的报警标志相对应，相应位为1则对相应报警时拍的照片进行存储，否则实时上传
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0055")) { // UINT32 最高速度，KM/H
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0056")) { // UINT32 超速持续时间，秒
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0057")) { // UINT32 连续驾驶时间门限，秒
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0058")) { // UINT32 最小休息时间，秒
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0059")) { // UINT32 最长停车时间，秒
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("005A")) { // UINT32 当天累计驾驶时间门限，秒
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0070")) { // UINT32 图像/视频质量，1～10，1最好
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0071")) { // UINT32 亮度，0～255
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0072")) { // UINT32 对比度，0～127
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0073")) { // UINT32 饱和度，0～127
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0074")) { // UINT32 色度，0～255
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0080")) { // UINT32 车辆里程表读数，1/10KM
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0081")) { // UINT32 车辆所在的省域ID，1～255
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0082")) { // UINT32 车辆所在的市域ID，1～255
					buf.putInt(Integer.parseInt(value));
				} else if (idHex.equals("0090")) { // BCD[2] 计价器营运次数限制，0-9999；0表示不做限制
					buf.put(ConvUtil.bcd2Bytes(value));
				} else if (idHex.equals("0091")) { // BCD[5]	计价器营运时间限制，YYYY-MM-DD-hh，0000000000表示不做限制
					buf.put(ConvUtil.hex2Bytes(value));
				} else if (idHex.equals("0092")) { // STRING	企业编码
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0093")) { // STRING	企业简称
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0094")) { // ASCII[9]	出租车车牌号码（去掉省份简称的车牌号），不足长度右补0x00
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("0095")) { // STRING	交接班方向列表，以半角分号分隔，每个地点方向最长20字节
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("00A0")) { // UINT8 智能终端录音模式（0X01：全程录音；0X02：翻牌录音），默认02
					buf.put(Byte.parseByte(value));
				} else if (idHex.equals("00A1")) { // UINT8 智能终端录音文件最大时长（1-255）分钟
					buf.put(Byte.parseByte(value));
				} else if (idHex.equals("00A2")) { // UINT8 液晶（LCD）心跳时间间隔(单位S 1-60)
					buf.put(Byte.parseByte(value));
				} else if (idHex.equals("00A3")) { // UINT8 LED心跳时间间隔(单位S 1-60)
					buf.put(Byte.parseByte(value));
				} else if (idHex.equals("00AF")) { // UINT16	ACC OFF后进入休眠的时间，单位为秒（s）
					buf.putShort(Short.parseShort(value));
				} else if (idHex.equals("00B0")) { // UINT8	视频服务器协议模式 0x00：TCP；0x01：UDP
					buf.put(Byte.parseByte(value));
				} else if (idHex.equals("00B1")) { // STRING	视频服务器APN，无线通信拨号访问点
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("00B2")) { // STRING	视频服务器无线通信拨号用户名
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("00B3")) { // STRING	视频服务器无线通信拨号密码
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("00B4")) { // STRING	视频服务器地址,IP或域名
					buf.put(ConvUtil.string2Bytes(value));
				} else if (idHex.equals("00B5")) { // UINT32	视频服务器端口
					buf.putInt(Integer.parseInt(value));
				}
			}
			buf.flip();
			byte[] b = new byte[buf.limit()];
			buf.get(b);
			return b;
		} catch (Exception e) {
			throw new P905Exception("P0x0104编码失败:", e);
		}

	}

	public PModel bodyFromBytes(byte[] data) throws P905Exception {
		if (null == data || 0 >= data.length) {
			throw new P905Exception("P0x0104消息体解码失败，消息为空或者长度不足。");
		}
		try {
			P0x0104 p = new P0x0104();
			int dataLen = data.length;
			int pos = 0;
			// 应答流水号
			byte[] ansFlowNo = new byte[2];
			System.arraycopy(data, pos, ansFlowNo, 0, 2);
			pos += 2;
			p.setAnsFlowNo(ConvUtil.bytes2Short(ansFlowNo));
			// 具体参数列表
			List<P0x8103VO> paramList = new ArrayList<P0x8103VO>();
			while (pos < dataLen) {
				byte[] id = new byte[2]; // 参数ID
				System.arraycopy(data, pos, id, 0, 2);
				String idHex = ConvUtil.bytes2Hex(id); // 16进制ID
				pos += 2;
				byte len = data[pos]; // 参数值长度
				pos += 1;
				byte[] value = new byte[len]; // 参数值
				System.arraycopy(data, pos, value, 0, len);
				pos += len;
				P0x8103VO pvo = new P0x8103VO();
				pvo.setParamId(ConvUtil.bytes2Short(id));
				pvo.setParamLen(len);
				if (idHex.equals("0001")) {// UINT32 终端心跳发送间隔，秒（默认30秒）
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				}  else if (idHex.equals("0002")) { // UINT32 TCP消息应答超时时间，秒（默认30秒）
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0003")) { // UINT32 TCP消息重传次数（默认3次）
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0004")) { // UINT32 SMS消息应答超时时间，秒（默认30秒）
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0005")) { // UINT32 SMS消息重传次数（默认3次）
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0010")) { // STRING 主服务器APN，无线通信拨号访问点
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0011")) { // STRING 主服务器无线通信拨号用户名
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0012")) { // STRING 主服务器无线通信拨号密码
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0013")) { // STRING 主服务器地址,IP或域名
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0014")) { // STRING 备份服务器APN，无线通信拨号访问点
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0015")) { // STRING 备份服务器无线通信拨号用户名
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0016")) { // STRING 备份服务器无线通信拨号密码
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0017")) { // STRING 备份服务器地址,IP或域名
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0018")) { // UINT32 主服务器TCP端口
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0019")) { // UINT32 备份服务器TCP端口
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("001A")) { // STRING 一卡通主服务器地址,IP 或域名
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("001B")) { // UINT32 一卡通主服务器TCP 端口
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("001C")) { // STRING 一卡通备份服务器地址IP 或域名
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("001D")) { // UINT32一卡通备份服务器TCP 端口
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0020")) { // UINT32 位置汇报策略，0：定时汇报；1：定距汇报；定时 + 定距汇报
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0021")) { // UINT32 位置汇报方案，0：根据ACC状态；1：根据空重车状态；2：根据登录状态 + ACC状态，先判断登录状态，若登录再根据ACC状态；3：根据登录状态 + 空重车状态，先判断登录状态，若登录再根据空重车状态
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0022")) { // UINT32 未登录汇报时间间隔，秒，>0
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0023")) { // UINT32 ACC OFF汇报时间间隔，秒，>0
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0024")) { // UINT32 ACC ON汇报时间间隔，秒，>0
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0025")) { // UINT32 空车汇报时间间隔，秒，>0
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0026")) { // UINT32 重车汇报时间间隔，秒，>0
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0027")) { // UINT32 休眠时汇报时间间隔，秒，>0
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0028")) { // UINT32 紧急报警时汇报时间间隔，秒，>0
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0029")) { // UINT32 未登录汇报距离间隔，米，>0
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("002A")) { // UINT32 ACC OFF汇报距离间隔，米，>0
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("002B")) { // UINT32 ACC ON汇报距离间隔，米，>0
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("002C")) { // UINT32 空车汇报距离间隔，米，>0
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("002D")) { // UINT32 重车汇报距离间隔，米，>0
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("002E")) { // UINT32 休眠时汇报距离间隔，米，>0
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("002F")) { // UINT32 紧急报警时汇报距离间隔，米，>0
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0030")) { // UINT32 拐点补传角度，<180
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0040")) { // STRING 监控指挥中心电话号码
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0041")) { // STRING 复位电话号码
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0042")) { // STRING 恢复出厂设置电话号码
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0043")) { // STRING 监控指挥中心SMS电话号码
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0044")) { // STRING 接收终端SMS文本报警号码
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0045")) { // UINT32  终端电话接听策略，0：自动接听；1：ACC ON时自动接听，OFF时手动接听
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0046")) { // UINT32  每次最长通话时间，秒
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0047")) { // UINT32  当月最长通话时间，秒
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0048")) { // UINT32  电话短号长度
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0049")) { // UINT32 监听电话号码
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("004A")) { // STRING 终端设备维护密码
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("004B")) { // UINT 终端的语音播报音量控制0-9 0静音 9为最高
					pvo.setParamVal(String.valueOf(value[0]));
				} else if (idHex.equals("0050")) { // UINT32 报警屏蔽字，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警被屏蔽
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0051")) { // UINT32 报警发送文本SMS开关，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警时发送文本SMS
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0052")) { // UINT32 报警拍摄开关，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警时摄像头拍摄
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0053")) { // UINT32 报警拍摄存储标志，与位置信息汇报消息中的报警标志相对应，相应位为1则对相应报警时拍的照片进行存储，否则实时上传
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0055")) { // UINT32 最高速度，KM/H
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0056")) { // UINT32 超速持续时间，秒
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0057")) { // UINT32 连续驾驶时间门限，秒
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0058")) { // UINT32 最小休息时间，秒
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0059")) { // UINT32 最长停车时间，秒
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("005A")) { // UINT32 当天累计驾驶时间门限，秒
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0070")) { // UINT32 图像/视频质量，1～10，1最好
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0071")) { // UINT32 亮度，0～255
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0072")) { // UINT32 对比度，0～127
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0073")) { // UINT32 饱和度，0～127
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0074")) { // UINT32 色度，0～255
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0080")) { // UINT32 车辆里程表读数，1/10KM
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0081")) { // UINT32 车辆所在的省域ID，1～255
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0082")) { // UINT32 车辆所在的市域ID，1～255
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				} else if (idHex.equals("0090")) { // BCD[2] 计价器营运次数限制，0-9999；0表示不做限制
					pvo.setParamVal(ConvUtil.bytes2Bcd(value));
				} else if (idHex.equals("0091")) { // BCD[5]	计价器营运时间限制，YYYY-MM-DD-hh，0000000000表示不做限制
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0092")) { // STRING	企业编码
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0093")) { // STRING	企业简称
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0094")) { // ASCII[9]	出租车车牌号码（去掉省份简称的车牌号），不足长度右补0x00
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("0095")) { // STRING	交接班方向列表，以半角分号分隔，每个地点方向最长20字节
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("00A0")) { // UINT8 智能终端录音模式（0X01：全程录音；0X02：翻牌录音），默认02
					pvo.setParamVal(String.valueOf(value[0]));
				} else if (idHex.equals("00A1")) { // UINT8 智能终端录音文件最大时长（1-255）分钟
					pvo.setParamVal(String.valueOf(value[0]));
				} else if (idHex.equals("00A2")) { // UINT8 液晶（LCD）心跳时间间隔(单位S 1-60)
					pvo.setParamVal(String.valueOf(value[0]));
				} else if (idHex.equals("00A3")) { // UINT8 LED心跳时间间隔(单位S 1-60)
					pvo.setParamVal(String.valueOf(value[0]));
				} else if (idHex.equals("00AF")) { // UINT16	ACC OFF后进入休眠模式的时间，单位为秒（s）
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Short(value)));
				} else if (idHex.equals("00B0")) { // UINT8	视频服务器协议模式 0x00：TCP；0x01：UDP
					pvo.setParamVal(String.valueOf(value[0]));
				} else if (idHex.equals("00B1")) { // STRING	视频服务器APN，无线通信拨号访问点
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("00B2")) { // STRING	视频服务器无线通信拨号用户名
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("00B3")) { //STRING	视频服务器无线通信拨号密码
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("00B4")) { // STRING	视频服务器地址,IP或域名
					pvo.setParamVal(ConvUtil.bytes2String(value));
				} else if (idHex.equals("00B5")) { // UINT32	视频服务器端口
					pvo.setParamVal(String.valueOf(ConvUtil.bytes2Int(value)));
				}

				paramList.add(pvo);
			}
			p.setParams(paramList);
			return p;
		} catch (Exception e) {
			throw new P905Exception("P0x0104解码失败:", e);
		}

	}

}
