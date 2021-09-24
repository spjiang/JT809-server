/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x120E;
import com.ctfo.protocol.P809.vo.T0x01;
import com.ctfo.protocol.P809.vo.T0x07;
import com.ctfo.protocol.P809.vo.TBase;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 实时上传电动汽车信息消息,UP_EXG_MSG_REAL_ELECTRIC_INFO
 * <br>
 * 下级平台主动向上级平台上报车辆上传的相关信息
 *
 * @author liangyi
 * @date 2021/3/22 13:45
 */
public class P0x120EPB implements IPassBody {

    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x120E消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x120E p = (P0x120E) model;
            IoBuffer buf = IoBuffer.allocate(34).setAutoExpand(true);
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            Calendar c = Calendar.getInstance();
            c.setTime(p.getCollectTime());
            buf.put((byte) (c.get(Calendar.YEAR) - 2000));
            buf.put((byte) (c.get(Calendar.MONTH) + 1));
            buf.put((byte) c.get(Calendar.DAY_OF_MONTH));
            buf.put((byte) c.get(Calendar.HOUR_OF_DAY));
            buf.put((byte) c.get(Calendar.MINUTE));
            buf.put((byte) c.get(Calendar.SECOND));
            List<TBase> infoContent = p.getInfoContent();
            for (TBase t : infoContent) {
                byte infoType = t.getInfoType();
                buf.put(infoType);
                if (1 == infoType) {// 整车数据
                    T0x01 t0x01 = (T0x01) t;
                    buf.put(t0x01.getVehicleState());
                    buf.put(t0x01.getChargeState());
                    buf.put(t0x01.getRunModel());
                    buf.putShort(t0x01.getSpeed());
                    buf.putInt(t0x01.getAccumulatedMileage());
                    buf.putShort(t0x01.getTotalElectricCurrent());
                    buf.put(t0x01.getSoc());
                    buf.put(t0x01.getDcdcState());
                    // 挡位字段
                    String gearBit = "00" + (t0x01.isImpetus() ? 1 : 0) + (t0x01.isBrakingForce() ? 1 : 0) + t0x01.getGear();
                    buf.put(Byte.parseByte(gearBit, 2));
                    buf.putShort(t0x01.getInsulationResistance());
                } else if (7 == infoType) {// 报警数据
                    T0x07 t0x07 = (T0x07) t;
                    buf.put(t0x07.getAlarmLevel());
                    buf.putInt(t0x07.getAlarmFlag());
                    buf.put(t0x07.getChargeDeviceFaultCount());
                    if (0 < t0x07.getChargeDeviceFaultCount()) {
                        for (Integer code : t0x07.getChargeDeviceFaultCodes()) {
                            buf.putInt(code);
                        }
                    }
                    buf.put(t0x07.getImpetusFaultCount());
                    if (0 < t0x07.getImpetusFaultCount()) {
                        for (Integer code : t0x07.getImpetusFaultCodes()) {
                            buf.putInt(code);
                        }
                    }
                    buf.put(t0x07.getEngineFaultCount());
                    if (0 < t0x07.getEngineFaultCount()) {
                        for (Integer code : t0x07.getEngineFaultCodes()) {
                            buf.putInt(code);
                        }
                    }
                    buf.put(t0x07.getOtherFaultCount());
                    if (0 < t0x07.getOtherFaultCount()) {
                        for (Integer code : t0x07.getOtherFaultCodes()) {
                            buf.putInt(code);
                        }
                    }
                }
            }
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x120E编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 34 > data.length) {
            throw new P809Exception("P0x120E消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x120E p = new P0x120E();
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
            byte[] dataLengthbyte = new byte[4];
            System.arraycopy(data, pos, dataLengthbyte, 0, 4);
            p.setDataLength(ConvUtil.bytes2Int(dataLengthbyte));
            pos += 4;
            byte[] collectTime = new byte[6]; // 数据收集时间
            System.arraycopy(data, pos, collectTime, 0, 6);
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, 2000 + collectTime[0]);
            c.set(Calendar.MONTH, collectTime[1]);
            c.set(Calendar.DAY_OF_MONTH, collectTime[2]);
            c.set(Calendar.HOUR_OF_DAY, collectTime[3]);
            c.set(Calendar.MINUTE, collectTime[4]);
            c.set(Calendar.SECOND, collectTime[5]);
            p.setCollectTime(c.getTime());
            pos += 6;
            // 处理消息中的具体数据项
            int len = data.length;
            while (pos < len) {
                byte infoType = data[pos];
                pos++;
                if (1 == infoType) {// 整车数据
                    T0x01 t0x01 = new T0x01();
                    t0x01.setInfoType(infoType);
                    t0x01.setVehicleState(data[pos]);
                    pos++;
                    t0x01.setChargeState(data[pos]);
                    pos++;
                    t0x01.setRunModel(data[pos]);
                    pos++;
                    byte[] speed = new byte[2];
                    System.arraycopy(data, pos, speed, 0, 2);
                    t0x01.setSpeed(ConvUtil.bytes2Short(speed));
                    pos += 2;
                    byte[] accumulatedMileage = new byte[4];
                    System.arraycopy(data, pos, accumulatedMileage, 0, 4);
                    t0x01.setAccumulatedMileage(ConvUtil.bytes2Int(accumulatedMileage));
                    pos += 4;
                    byte[] totalVoltage = new byte[2];
                    System.arraycopy(data, pos, totalVoltage, 0, 2);
                    t0x01.setTotalVoltage(ConvUtil.bytes2Short(totalVoltage));
                    pos += 2;
                    byte[] totalElectricCurrent = new byte[2];
                    System.arraycopy(data, pos, totalElectricCurrent, 0, 2);
                    t0x01.setTotalElectricCurrent(ConvUtil.bytes2Short(totalElectricCurrent));
                    pos += 2;
                    t0x01.setSoc(data[pos]);
                    pos++;
                    t0x01.setDcdcState(data[pos]);
                    pos++;
                    t0x01.setGearByte(data[pos]);
                    pos++;
                    // 处理挡位字段
                    String gearBit = ConvUtil.byteToBits(t0x01.getGearByte());
                    t0x01.setImpetus(Byte.parseByte(gearBit.substring(2, 3)) == 1);
                    t0x01.setBrakingForce(Byte.parseByte(gearBit.substring(3, 4)) == 1);
                    t0x01.setGear(gearBit.substring(4, 8));
                    byte[] insulationResistance = new byte[2];
                    System.arraycopy(data, pos, insulationResistance, 0, 2);
                    t0x01.setInsulationResistance(ConvUtil.bytes2Short(insulationResistance));
                    pos += 2;
                    p.getInfoContent().add(t0x01);
                } else if (7 == infoType) {// 报警数据
                    T0x07 t0x07 = new T0x07();
                    t0x07.setInfoType(infoType);
                    t0x07.setAlarmLevel(data[pos]);
                    pos++;
                    byte[] alarmFlag = new byte[4];
                    System.arraycopy(data, pos, alarmFlag, 0, 4);
                    t0x07.setAlarmFlag(ConvUtil.bytes2Int(alarmFlag));
                    pos += 4;
                    t0x07.setChargeDeviceFaultCount(data[pos]);
                    pos++;
                    if (0 < t0x07.getChargeDeviceFaultCount()) {
                        List<Integer> chargeDeviceFaultCodes = new ArrayList<>();
                        for (int i = 0; i < t0x07.getChargeDeviceFaultCount(); i++) {
                            byte[] chargeDeviceFaultCode = new byte[4];
                            System.arraycopy(data, pos, chargeDeviceFaultCode, 0, 4);
                            chargeDeviceFaultCodes.add(ConvUtil.bytes2Int(chargeDeviceFaultCode));
                            pos += 4;
                        }
                        t0x07.setChargeDeviceFaultCodes(chargeDeviceFaultCodes);
                    }
                    t0x07.setImpetusFaultCount(data[pos]);
                    pos++;
                    if (0 < t0x07.getImpetusFaultCount()) {
                        List<Integer> impetusFaultCodes = new ArrayList<>();
                        for (int i = 0; i < t0x07.getImpetusFaultCount(); i++) {
                            byte[] impetusFaultCode = new byte[4];
                            System.arraycopy(data, pos, impetusFaultCode, 0, 4);
                            impetusFaultCodes.add(ConvUtil.bytes2Int(impetusFaultCode));
                            pos += 4;
                        }
                        t0x07.setImpetusFaultCodes(impetusFaultCodes);
                    }
                    t0x07.setEngineFaultCount(data[pos]);
                    pos++;
                    if (0 < t0x07.getEngineFaultCount()) {
                        List<Integer> engineFaultCodes = new ArrayList<>();
                        for (int i = 0; i < t0x07.getEngineFaultCount(); i++) {
                            byte[] engineFaultCode = new byte[4];
                            System.arraycopy(data, pos, engineFaultCode, 0, 4);
                            engineFaultCodes.add(ConvUtil.bytes2Int(engineFaultCode));
                            pos += 4;
                        }
                        t0x07.setEngineFaultCodes(engineFaultCodes);
                    }
                    t0x07.setOtherFaultCount(data[pos]);
                    pos++;
                    if (0 < t0x07.getOtherFaultCount()) {
                        List<Integer> otherFaultCodes = new ArrayList<>();
                        for (int i = 0; i < t0x07.getOtherFaultCount(); i++) {
                            byte[] otherFaultCode = new byte[4];
                            System.arraycopy(data, pos, otherFaultCode, 0, 4);
                            otherFaultCodes.add(ConvUtil.bytes2Int(otherFaultCode));
                            pos += 4;
                        }
                        t0x07.setOtherFaultCodes(otherFaultCodes);
                    }
                    p.getInfoContent().add(t0x07);
                }
            }
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x120E解码失败:", e);
        }
    }
}
