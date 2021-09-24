/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x9501;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 车辆单向监听请求消息,从链路，DOWN_CTRL_MSG_MONITOR_VEHICLE_REQ
 * <br>
 * 上级平台向下级平台下发车辆单向监听请求消息，
 *
 * @author liangyi
 * @date 2021/2/23 10:13
 */
public class P0x9501PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x9501消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x9501 p = (P0x9501) model;
            IoBuffer buf = IoBuffer.allocate(48);
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            byte[] monitorTel = ConvUtil.string2Bytes(p.getMonitorTel());
            buf.put(monitorTel);
            int size = 20 - monitorTel.length;
            for (int i = 0; i < size; i++) {
                buf.put((byte) 0);
            }
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x9501编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 48 > data.length) {
            throw new P809Exception("P0x9501消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x9501 p = new P0x9501();
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
            byte[] monitorTel = new byte[20];
            System.arraycopy(data, pos, monitorTel, 0, 20);
            p.setMonitorTel(ConvUtil.bytes2String(monitorTel));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x9501解码失败:", e);
        }
    }
}
