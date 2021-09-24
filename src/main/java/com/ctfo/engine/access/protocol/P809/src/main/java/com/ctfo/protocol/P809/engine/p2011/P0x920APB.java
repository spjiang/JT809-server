/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x920A;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 上报驾驶员身份识别信息请求消息，从链路，DOWN_EXG_MSG_REPORT_DRIVER_INFO
 * <br>
 * 上级平台向下级平台下发上报车辆驾驶员身份识别信息的请求消息
 *
 * @author liangyi
 * @date 2021/1/11 19:03
 */
public class P0x920APB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x920A消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x920A p = (P0x920A) model;
            IoBuffer buf = IoBuffer.allocate(28);
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x920A编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 28 > data.length) {
            throw new P809Exception("P0x920A消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x920A p = new P0x920A();
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
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x920A解码失败:", e);
        }
    }
}
