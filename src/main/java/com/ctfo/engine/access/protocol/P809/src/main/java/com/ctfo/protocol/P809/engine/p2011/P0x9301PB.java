/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x9301;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 平台查岗请求消息，从链路，DOWN_PLATFORM_MSG_POST_QUERY_REQ
 * <br>
 * 上级平台不定期向下级平台发送平台查岗信息
 *
 * @author liangyi
 * @date 2021/2/23 9:04
 */
public class P0x9301PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x9301消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x9301 p = (P0x9301) model;
            IoBuffer buf = IoBuffer.allocate(27 + p.getInfoLength());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.put(p.getObjectType());
            byte[] objectId = ConvUtil.string2Bytes(p.getObjectId());
            buf.put(objectId);
            int size = 12 - objectId.length;
            for (int i = 0; i < size; i++) {
                buf.put((byte) 0);
            }
            buf.putInt((int) p.getInfoId());
            buf.putInt(p.getInfoLength());
            buf.put(ConvUtil.string2Bytes(p.getInfoContent()));
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x9301编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 27 > data.length) {
            throw new P809Exception("P0x9301消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x9301 p = new P0x9301();
            int pos = 0;
            byte[] dataType = new byte[2];
            System.arraycopy(data, pos, dataType, 0, 2);
            p.setDataType(ConvUtil.bytes2Short(dataType));
            pos += 2;
            byte[] dataLength = new byte[4];
            System.arraycopy(data, pos, dataLength, 0, 4);
            p.setDataLength(ConvUtil.bytes2Int(dataLength));
            pos += 4;
            p.setObjectType(data[pos]);
            pos ++;
            byte[] objectId = new byte[12];
            System.arraycopy(data, pos, objectId, 0, 12);
            p.setObjectId(ConvUtil.bytes2String(objectId));
            pos += 12;
            p.setInfoId(ConvUtil.bigBytes2Unsigned32Long(data, pos));
            pos += 4;
            byte[] infoLength = new byte[4];
            System.arraycopy(data, pos, infoLength, 0, 4);
            p.setInfoLength(ConvUtil.bytes2Int(infoLength));
            pos += 4;
            byte[] infoContent = new byte[p.getInfoLength()];
            System.arraycopy(data, pos, infoContent, 0, p.getInfoLength());
            p.setInfoContent(ConvUtil.bytes2String(infoContent));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x9301解码失败:", e);
        }
    }
}
