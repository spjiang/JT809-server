package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1003;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.Arrays;

/**
 * 主链路注销请求消息 UP_DISCONNECT_REQ
 *
 * @author liangyi
 * @date 2020/11/3 14:46
 */
public class P0x1003PB implements IPassBody {

    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x1003消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x1003 p = (P0x1003) model;
            IoBuffer buf = IoBuffer.allocate(12);
            buf.putInt((int) p.getUserId());
            buf.put(p.getPassword().getBytes(CharsetConst.CHARTSET_GBK));
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x1003编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 12 > data.length) {
            throw new P809Exception("P0x1003消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x1003 p = new P0x1003();
            int pos = 0;
            p.setUserId(ConvUtil.bigBytes2Unsigned32Long(data, pos));
            pos += 4;
            p.setPassword(new String(Arrays.copyOfRange(data, pos, pos + 8), CharsetConst.CHARTSET_GBK));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x1003解码失败:", e);
        }
    }
}
