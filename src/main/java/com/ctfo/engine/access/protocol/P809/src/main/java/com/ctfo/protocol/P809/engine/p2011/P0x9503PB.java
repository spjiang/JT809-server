/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x9503;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 下发车辆报文请求消息，DOWN_CTRL_MSG_TEXT_INFO
 * <br>
 * 用于上级平台向下级平台下发报文到某指定车辆
 *
 * @author liangyi
 * @date 2021/2/23 10:13
 */
public class P0x9503PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x9503消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x9503 p = (P0x9503) model;
            IoBuffer buf = IoBuffer.allocate(37 + p.getMsgLength());
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.putInt((int) p.getMsgSequence());
            buf.put(p.getMsgPriority());
            buf.putInt(p.getMsgLength());
            buf.put(ConvUtil.string2Bytes(p.getMsgContent()));
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x9503编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 30 > data.length) {
            throw new P809Exception("P0x9503消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x9503 p = new P0x9503();
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
            p.setMsgSequence(ConvUtil.bigBytes2Unsigned32Long(data, pos));
            pos += 4;
            p.setMsgPriority(data[pos]);
            pos++;
            p.setMsgLength(ConvUtil.bytes2Int(data, pos));
            pos += 4;
            byte[] msgContent = new byte[p.getMsgLength()];
            System.arraycopy(data, pos, msgContent, 0, p.getMsgLength());
            p.setMsgContent(ConvUtil.bytes2String(msgContent));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x9503解码失败:", e);
        }
    }
}
