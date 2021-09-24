package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1002;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 主链路登陆应答消息，UP_CONNECT_RSP
 *
 * @author liangyi
 * @date 2020/11/3 14:41
 */
public class P0x1002PB implements IPassBody {

    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x1002消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x1002 p = (P0x1002) model;
            IoBuffer buf = IoBuffer.allocate(5);
            buf.put(p.getResult());
            buf.putInt((int) p.getVerifyCode());
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x1002编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 5 > data.length) {
            throw new P809Exception("P0x1002消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x1002 p = new P0x1002();
            int pos = 0;
            p.setResult(data[pos]);
            pos++;
            p.setVerifyCode(ConvUtil.bigBytes2Unsigned32Long(data, pos));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x1002解码失败:", e);
        }
    }
}
