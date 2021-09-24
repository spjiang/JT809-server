/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1207;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 申请交换指定车辆定位信息请求消息，主链路，UP_EXG_MSG_APPLY_FOR_MONITOR_STARTUP
 * <br>
 * 当下级平台需要在特定时间段内监控特殊车辆时，可上传此命令到上级平台申请对该车
 * 辆定位数据交换到下级平台，申请成功后，此车辆定位数据将在指定时间内交换到该平台（即使该
 * 车没有进入该平台所属区域也会交换），
 *
 * @author liangyi
 * @date 2021/1/12 10:14
 */
public class P0x1207PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x1207消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x1207 p = (P0x1207) model;
            IoBuffer buf = IoBuffer.allocate(44);
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.putLong(p.getStartTime());
            buf.putLong(p.getEndTime());
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x1207编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 44 > data.length) {
            throw new P809Exception("P0x1207消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x1207 p = new P0x1207();
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
            byte[] startTimeByte = new byte[8];
            System.arraycopy(data, pos, startTimeByte, 0, 8);
            p.setStartTime(ConvUtil.bytes2Long(startTimeByte));
            pos += 8;
            byte[] endTimeByte = new byte[8];
            System.arraycopy(data, pos, endTimeByte, 0, 8);
            p.setEndTime(ConvUtil.bytes2Long(endTimeByte));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x1207解码失败:", e);
        }
    }
}
