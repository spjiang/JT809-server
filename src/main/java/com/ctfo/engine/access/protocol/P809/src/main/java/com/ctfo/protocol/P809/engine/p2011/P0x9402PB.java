/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x9402;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 报警预警消息，从链路，DOWN_WARN_MSG_INFORM_TIPS
 * <br>
 * 用于上级平台向车辆归属或车辆跨域下级平台下发相关车辆的报警预警或运行提示信息
 * 本条消息下级平台无需应答
 *
 * @author liangyi
 * @date 2021/2/23 9:56
 */
public class P0x9402PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x9402消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x9402 p = (P0x9402) model;
            IoBuffer buf = IoBuffer.allocate(43 + p.getWarnLength());
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.put(p.getWranSrc());
            buf.putShort(p.getWranType());
            buf.putLong(p.getWranTime());
            buf.putInt(p.getWarnLength());
            buf.put(ConvUtil.string2Bytes(p.getWarnContent()));
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x9402编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 43 > data.length) {
            throw new P809Exception("P0x9402消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x9402 p = new P0x9402();
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
            byte[] wranType = new byte[2];
            System.arraycopy(data, pos, wranType, 0, 2);
            p.setWranType(ConvUtil.bytes2Short(wranType));
            pos += 2;
            byte[] wranTime = new byte[8];
            System.arraycopy(data, pos, wranTime, 0, 8);
            p.setWranTime(ConvUtil.bytes2Long(wranTime));
            pos += 8;
            byte[] warnLength = new byte[4];
            System.arraycopy(data, pos, warnLength, 0, 4);
            p.setWarnLength(ConvUtil.bytes2Int(warnLength));
            pos += 4;
            byte[] warnContent = new byte[p.getWarnLength()];
            System.arraycopy(data, pos, warnContent, 0, p.getWarnLength());
            p.setWarnContent(ConvUtil.bytes2String(warnContent));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x9402解码失败:", e);
        }
    }
}
