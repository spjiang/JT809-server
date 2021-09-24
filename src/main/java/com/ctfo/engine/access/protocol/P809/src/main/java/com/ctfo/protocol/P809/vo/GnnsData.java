/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.vo;

import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import lombok.Data;
import lombok.ToString;
import org.apache.mina.core.buffer.IoBuffer;

import java.io.Serializable;
import java.util.Date;

/**
 * 车辆定位信息数据体
 *
 * @author liangyi
 * @date 2020/8/25 15:33
 */
@Data
@ToString
public class GnnsData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 加密标识，1：已加密  0：未加密
     */
    private byte encrypt;

    /**
     * 时间，7字节，dmyy hms
     */
    private Date datetime;

    /**
     * 经度，4字节
     */
    private int lng;

    /**
     * 纬度，4字节
     */
    private int lat;

    /**
     * 速度，2字节，单位km/h
     */
    private short speed;

    /**
     * 行驶记录仪速度，2字节，单位km/h
     */
    private short tachographSpeed;

    /**
     * 车辆当前总里程数，4字节，单位km
     */
    private int totalMileage;

    /**
     * 方向，2字节，0-359，单位°，正北为0，顺时针
     */
    private short direction;

    /**
     * 海拔高度，2字节，单位为米
     */
    private short altitude;

    /**
     * 车辆状态，4字节，参考808-2011中表17的规定
     */
    private int state;

    /**
     * 报警状态，4字节，参考808-2011中表18的规定。
     */
    private int alarm;

    /**
     * 消息封包
     *
     * @return byte[]
     * @author liangyi
     * @date 2020/8/25 16:00
     */
    public byte[] toBytes() {
        IoBuffer b = IoBuffer.allocate(36);
        try {
            b.put(this.encrypt);
            String datetimeStr = DateUtil.formatToString(this.datetime,"yyyyMMddHHmmss");
            short year = Short.parseShort(datetimeStr.substring(0, 4));
            byte month = Byte.parseByte(datetimeStr.substring(4, 6));
            byte day = Byte.parseByte(datetimeStr.substring(6, 8));
            b.put(day).put(month).putShort(year);
            byte h = Byte.parseByte(datetimeStr.substring(8, 10));
            byte m = Byte.parseByte(datetimeStr.substring(10, 12));
            byte s = Byte.parseByte(datetimeStr.substring(12, 14));
            b.put(h).put(m).put(s);
            b.putInt(this.lng);
            b.putInt(this.lat);
            b.putShort(this.speed);
            b.putShort(this.tachographSpeed);
            b.putInt(this.totalMileage);
            b.putShort(this.direction);
            b.putShort(this.altitude);
            b.putInt(this.state);
            b.putInt(this.alarm);
            return b.array();
        } catch (Exception e) {
            throw new P809Exception("车辆位置信息toBytes失败：", e);
        }
    }

    /**
     * 消息拆包
     *
     * @param data 待解码的字节数据
     * @author liangyi
     * @date 2020/8/25 16:00
     */
    public void formBytes(byte[] data) {
        if (null == data || 36 > data.length) {
            throw new P809Exception("车辆位置信息formBytes失败,消息长度不够。");
        }
        try {
            int pos = 0;
            this.encrypt = data[pos];
            pos++;
            byte day = data[pos];
            pos++;
            byte month = data[pos];
            pos++;
            byte[] year = new byte[2];
            System.arraycopy(data, pos, year, 0, 2);
            pos += 2;
            byte h = data[pos];
            pos++;
            byte m = data[pos];
            pos++;
            byte s = data[pos];
            pos++;
            String datetimeStr = ConvUtil.bytes2Short(year) + "" + (month < 10 ? "0" + month : month) + (day < 10 ? "0" + day : day) + (h < 10 ? "0" + h : h) + "" + (m < 10 ? "0" + m : m) + (s < 10 ? "0" + s : s);
            this.datetime = DateUtil.parseToDate(datetimeStr,"yyyyMMddHHmmss");
            byte[] lngByte = new byte[4];
            System.arraycopy(data, pos, lngByte, 0, 4);
            this.lng = ConvUtil.bytes2Int(lngByte);
            pos += 4;
            byte[] latByte = new byte[4];
            System.arraycopy(data, pos, latByte, 0, 4);
            this.lat = ConvUtil.bytes2Int(latByte);
            pos += 4;
            byte[] speedByte = new byte[2];
            System.arraycopy(data, pos, speedByte, 0, 2);
            this.speed = ConvUtil.bytes2Short(speedByte);
            pos += 2;
            byte[] tachographSpeedByte = new byte[2];
            System.arraycopy(data, pos, tachographSpeedByte, 0, 2);
            this.tachographSpeed = ConvUtil.bytes2Short(tachographSpeedByte);
            pos += 2;
            byte[] totalMileageByte = new byte[4];
            System.arraycopy(data, pos, totalMileageByte, 0, 4);
            this.totalMileage = ConvUtil.bytes2Int(totalMileageByte);
            pos += 4;
            byte[] directionByte = new byte[2];
            System.arraycopy(data, pos, directionByte, 0, 2);
            this.direction = ConvUtil.bytes2Short(directionByte);
            pos += 2;
            byte[] altitudeByte = new byte[2];
            System.arraycopy(data, pos, altitudeByte, 0, 2);
            this.altitude = ConvUtil.bytes2Short(altitudeByte);
            pos += 2;
            byte[] stateByte = new byte[4];
            System.arraycopy(data, pos, stateByte, 0, 4);
            this.state = ConvUtil.bytes2Int(stateByte);
            pos += 4;
            byte[] alarmByte = new byte[4];
            System.arraycopy(data, pos, alarmByte, 0, 4);
            this.alarm = ConvUtil.bytes2Int(alarmByte);
        } catch (Exception e) {
            throw new P809Exception("车辆位置信息formBytes失败：", e);
        }
    }
}
