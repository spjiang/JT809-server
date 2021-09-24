/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1302;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 下发平台间报文应答消息，主链路，UP_PLATFORM_MSG_INFO_ACK
 * <br>
 * 下级平台收到上级平台发送的下发平台间报文请求消息后，根据相应的下发报文对象类型，进行转发，并向上级平台发送应答消息
 *
 * @author liangyi
 * @date 2021/1/12 16:56
 */
public class P0x1302PB implements IPassBody {

    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x1302消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x1302 p = (P0x1302) model;
            IoBuffer buf = IoBuffer.allocate(10);
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.putInt((int) p.getInfoId());
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x1302编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 10 > data.length) {
            throw new P809Exception("P0x1302消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x1302 p = new P0x1302();
            int pos = 0;
            byte[] dataTypeByte = new byte[2];
            System.arraycopy(data, pos, dataTypeByte, 0, 2);
            p.setDataType(ConvUtil.bytes2Short(dataTypeByte));
            pos += 2;
            byte[] dataLengthByte = new byte[4];
            System.arraycopy(data, pos, dataLengthByte, 0, 4);
            p.setDataLength(ConvUtil.bytes2Int(dataLengthByte));
            pos += 4;
            byte[] infoIdByte = new byte[4];
            System.arraycopy(data, pos, infoIdByte, 0, 4);
            p.setInfoId(ConvUtil.bigBytes2Unsigned32Long(infoIdByte, 0));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x1302解码失败:", e);
        }
    }
}
