/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1001;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.Arrays;

/**
 * 主链路登陆请求消息 UP_CONNECT_REQ
 * <br>
 * 消息方向：下级平台往上级平台
 *
 * @author liangyi
 * @date 2021/1/11 17:08
 */
public class P0x1001PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x1001消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x1001 p = (P0x1001) model;
            IoBuffer buf = IoBuffer.allocate(46);
            buf.putInt((int) p.getUserId());
            buf.put(p.getPassword().getBytes(CharsetConst.CHARTSET_GBK));
            byte[] vb = p.getDownLinkIp().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] b1 = new byte[32];
            System.arraycopy(vb, 0, b1, 0, vb.length);
            buf.put(b1);
            buf.putShort((short) p.getDownLinkPort());
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x1001编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 46 > data.length) {
            throw new P809Exception("P0x1001消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x1001 p = new P0x1001();
            int pos = 0;
            p.setUserId(ConvUtil.bigBytes2Unsigned32Long(data, pos));
            pos += 4;
            p.setPassword(ConvUtil.bytes2String(Arrays.copyOfRange(data, pos, pos + 8)));
            pos += 8;
            p.setDownLinkIp(ConvUtil.bytes2String(Arrays.copyOfRange(data, pos, pos + 32)));
            pos += 32;
            p.setDownLinkPort(ConvUtil.bigBytes2Unsigned16Int(data, pos));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x1001解码失败:", e);
        }
    }
}
