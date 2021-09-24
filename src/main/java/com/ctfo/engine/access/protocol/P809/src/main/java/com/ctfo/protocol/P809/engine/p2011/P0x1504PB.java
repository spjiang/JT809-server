/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1504;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 上报车辆行驶记录应答消息,主链路，UP_CTRL_MSG_TAKE_TRAVEL_ACK
 * <br>
 * 下级平台应答上级平台下发的上报车辆行驶记录请求消息，将车辆行驶记录数据上传至上级平台
 *
 * @author liangyi
 * @date 2021/2/22 15:27
 */
public class P0x1504PB implements IPassBody {


    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x1504消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x1504 p = (P0x1504) model;
            IoBuffer buf = IoBuffer.allocate(33 + p.getTraveldataLength());
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.put(p.getCommandType());
            buf.putInt(p.getTraveldataLength());
            buf.put(ConvUtil.string2Bytes(p.getTraveldataInfo()));
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x1504编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 33 > data.length) {
            throw new P809Exception("P0x1504消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x1504 p = new P0x1504();
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
            p.setCommandType(data[pos]);
            pos++;
            byte[] traveldataLength = new byte[4];
            System.arraycopy(data, pos, traveldataLength, 0, 4);
            p.setTraveldataLength(ConvUtil.bytes2Int(traveldataLength));
            pos += 4;
            byte[] traveldataInfo = new byte[p.getTraveldataLength()];
            System.arraycopy(data, pos, traveldataInfo, 0, p.getTraveldataLength());
            p.setTraveldataInfo(ConvUtil.bytes2String(traveldataInfo));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x1504解码失败:", e);
        }
    }
}
