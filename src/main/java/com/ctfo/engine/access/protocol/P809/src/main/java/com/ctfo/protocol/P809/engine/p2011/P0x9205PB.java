/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x9205;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 启动车辆定位信息交换请求消息，从链路，DOWN_EXG_MSG_RETURN_STARTUP
 * <br>
 * 在有车辆进入非归属地区地理区域、人工指定车辆定位信息交换和应急状态监控车辆时，
 * 上级平台向下级平台发出启动车辆定位信息交换请求消息。下级平台收到此命令后需要回复启动车
 * 辆定位信息交换应答消息给上级平台，即UP_EXG_MSG_RETURN_STARTUP_ACK
 *
 * @author liangyi
 * @date 2021/2/22 17:13
 */
public class P0x9205PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x9205消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x9205 p = (P0x9205) model;
            IoBuffer buf = IoBuffer.allocate(29);
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.put(p.getReasonCode());
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x9205编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 29 > data.length) {
            throw new P809Exception("P0x9205消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x9205 p = new P0x9205();
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
            p.setReasonCode(data[pos]);
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x9205解码失败:", e);
        }
    }
}
