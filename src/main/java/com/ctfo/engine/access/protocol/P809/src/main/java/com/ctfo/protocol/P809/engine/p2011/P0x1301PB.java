/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1301;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 平台查岗应答消息，主链路，UP_PLATFORM_MSG_POST_QUERY_ACK
 * <br>
 * 下级平台应答上级平台发送的不定期平台查岗消息
 *
 * @author liangyi
 * @date 2021/1/12 15:28
 */
public class P0x1301PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x1301消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x1301 p = (P0x1301) model;
            IoBuffer buf = IoBuffer.allocate(27 + p.getInfoLength());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.put(p.getObjectType());
            byte[] objectId = p.getObjectId().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] objectIdB = new byte[12];
            System.arraycopy(objectId, 0, objectIdB, 0, objectId.length);
            buf.put(objectIdB);
            buf.putInt((int)(p.getInfoId()));
            buf.put(p.getInfoContent().getBytes(CharsetConst.CHARTSET_GBK));
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x1301编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 27 > data.length) {
            throw new P809Exception("P0x1301消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x1301 p = new P0x1301();
            int pos = 0;
            byte[] dataTypeByte = new byte[2];
            System.arraycopy(data, pos, dataTypeByte, 0, 2);
            p.setDataType(ConvUtil.bytes2Short(dataTypeByte));
            pos += 2;
            byte[] dataLengthByte = new byte[4];
            System.arraycopy(data, pos, dataLengthByte, 0, 4);
            p.setDataLength(ConvUtil.bytes2Int(dataLengthByte));
            pos += 4;
            p.setObjectType(data[0]);
            pos++;
            byte[] objectIdByte = new byte[12];
            System.arraycopy(data, pos, objectIdByte, 0, 12);
            p.setObjectId(ConvUtil.bytes2String(objectIdByte));
            pos += 12;
            byte[] infoIdByte = new byte[4];
            System.arraycopy(data, pos, infoIdByte, 0, 4);
            p.setInfoId(ConvUtil.bigBytes2Unsigned32Long(infoIdByte, 0));
            pos += 4;
            byte[] infoLengthByte = new byte[4];
            System.arraycopy(data, pos, infoLengthByte, 0, 4);
            p.setInfoLength(ConvUtil.bytes2Int(infoLengthByte));
            pos += 4;
            byte[] infoContentByte = new byte[p.getInfoLength()];
            System.arraycopy(data, pos, infoContentByte, 0, p.getInfoLength());
            p.setInfoContent(ConvUtil.bytes2String(infoContentByte));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x1301解码失败:", e);
        }
    }
}
