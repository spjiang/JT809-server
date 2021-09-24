/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.cst.P809Cst;
import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1402;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 上报报警信息消息，主链路，UP_WARN_MSG_ADPT_INFO
 * <br>
 * 下级平台向上级平台上报某车辆的报警信息
 * 本条消息上级平台无需应答。
 *
 * @author liangyi
 * @date 2021/2/22 11:37
 */
public class P0x1402PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x1402消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x1402 p = (P0x1402) model;
            IoBuffer buf;
            if (P809Cst.PARSER_ADAS) {
                buf = IoBuffer.allocate(77 + p.getDriverLength() + p.getDriverNoLength() + p.getInfoLength());
            } else {
                buf = IoBuffer.allocate(47 + p.getInfoLength());
            }
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.put(p.getWranSrc());
            buf.putShort(p.getWranType());
            buf.putLong(p.getWranTime());
            if (P809Cst.PARSER_ADAS) {
                buf.put(ConvUtil.hex2Bytes(p.getAlarmId()));
                buf.put(p.getDriverLength());
                buf.put(ConvUtil.string2Bytes(p.getDriver()));
                buf.put(p.getDriverNoLength());
                buf.put(ConvUtil.string2Bytes(p.getDriverNo()));
                buf.put(p.getLevel());
                buf.putInt(p.getLon());
                buf.putInt(p.getLat());
                buf.putShort(p.getAltitude());
                buf.putShort(p.getVec1());
                buf.putShort(p.getVec2());
                buf.put(p.getStatus());
                buf.putShort(p.getDirection());
                buf.putShort((short) p.getInfoLength());
            } else {
                buf.putInt((int) p.getInfoId());
                buf.putInt(p.getInfoLength());
            }
            byte[] infoContent = ConvUtil.string2Bytes(p.getInfoContent());
            buf.put(infoContent);
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x1402编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data) {
            throw new P809Exception("P0x1402消息体解码失败，消息为空或者长度不足。");
        }
        if (P809Cst.PARSER_ADAS) {
            if (75 > data.length) {
                throw new P809Exception("P0x1402消息体解码失败，消息为空或者长度不足。");
            }
        } else {
            if (47 > data.length) {
                throw new P809Exception("P0x1402消息体解码失败，消息为空或者长度不足。");
            }
        }
        try {
            P0x1402 p = new P0x1402();
            int pos = 0;
            byte[] vehicleNoByte = new byte[21];
            System.arraycopy(data, pos, vehicleNoByte, 0, 21);
            p.setVehicleNo(ConvUtil.bytes2String(vehicleNoByte));
            pos += 21;
            p.setVehicleColor(data[pos]);
            pos++;
            byte[] dataTypeByte = new byte[2];
            System.arraycopy(data, pos, dataTypeByte, 0, 2);
            p.setDataType(ConvUtil.bytes2Short(dataTypeByte));
            pos += 2;
            byte[] dataLengthByte = new byte[4];
            System.arraycopy(data, pos, dataLengthByte, 0, 4);
            p.setDataLength(ConvUtil.bytes2Int(dataLengthByte));
            pos += 4;
            p.setWranSrc(data[pos]);
            pos++;
            byte[] warnType = new byte[2];
            System.arraycopy(data, pos, warnType, 0, 2);
            p.setWranType(ConvUtil.bytes2Short(warnType));
            pos += 2;
            byte[] warnTime = new byte[8];
            System.arraycopy(data, pos, warnTime, 0, 8);
            p.setWranTime(ConvUtil.bytes2Long(warnTime));
            pos += 8;
            if (P809Cst.PARSER_ADAS) {
                byte[] alarmId = new byte[16];
                System.arraycopy(data, pos, alarmId, 0, 16);
                p.setAlarmId(ConvUtil.bytes2Hex(alarmId));
                pos += 16;
                p.setDriverLength(data[pos]);
                pos++;
                byte[] driver = new byte[p.getDriverLength()];
                System.arraycopy(data, pos, driver, 0, p.getDriverLength());
                p.setDriver(ConvUtil.bytes2String(driver));
                pos += p.getDriverLength();
                p.setDriverNoLength(data[pos]);
                pos++;
                byte[] driverNo = new byte[p.getDriverNoLength()];
                System.arraycopy(data, pos, driverNo, 0, p.getDriverNoLength());
                p.setDriverNo(ConvUtil.bytes2String(driverNo));
                pos += p.getDriverNoLength();
                p.setLevel(data[pos]);
                pos++;
                p.setLon(ConvUtil.bytes2Int(data, pos));
                pos += 4;
                p.setLat(ConvUtil.bytes2Int(data, pos));
                pos += 4;
                p.setAltitude((short) ConvUtil.bytes2Short(data, pos));
                pos += 2;
                p.setVec1((short) ConvUtil.bytes2Short(data, pos));
                pos += 2;
                p.setVec2((short) ConvUtil.bytes2Short(data, pos));
                pos += 2;
                p.setStatus(data[pos]);
                pos++;
                p.setDirection((short) ConvUtil.bytes2Short(data, pos));
                pos += 2;
                p.setInfoLength(ConvUtil.bytes2Short(data, pos));
                pos += 2;
            } else {
                p.setInfoId(ConvUtil.bigBytes2Unsigned32Long(data, pos));
                pos += 4;
                p.setInfoLength(ConvUtil.bytes2Int(data, pos));
                pos += 4;
            }
            byte[] infoContent = new byte[p.getInfoLength()];
            System.arraycopy(data, pos, infoContent, 0, p.getInfoLength());
            p.setInfoContent(ConvUtil.bytes2String(infoContent));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x1402解码失败:", e);
        }
    }
}
