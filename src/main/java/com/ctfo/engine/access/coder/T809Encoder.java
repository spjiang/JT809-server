package com.ctfo.engine.access.coder;

import com.ctfo.engine.access.common.cache.LocalCache;
import com.ctfo.protocol.P809.P809Message;
import com.ctfo.protocol.P809.vo.PlatformAccessInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;


/**
 * 809协议消息编码处理类
 *
 * @author 凉意 2018年5月14日 下午4:13:58
 * @version 1.0
 */
@Slf4j
public class T809Encoder implements ProtocolEncoder {

    /**
     * 编码字符
     */
    private Charset charset;

    public T809Encoder(String charset) {
        this.charset = Charset.forName(charset);
    }

    /**
     * @see ProtocolEncoder#encode(IoSession,
     * Object,
     * ProtocolEncoderOutput)
     */
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        P809Message msg = (P809Message) message;
        try {
            // 根据接入码获取下级平台信息
            PlatformAccessInfo platform = LocalCache.PLATFORM_CONFIG.get(String.valueOf(msg.getMsgGnnsCenterid()));
            if (null == platform) return;
            byte[] bytes = msg.toBytes(platform.getM1(), platform.getIA1(), platform.getIC1());
            IoBuffer buf = IoBuffer.allocate(bytes.length, false);
            buf.put(bytes);
            buf.flip();
            out.write(buf);
        } catch (Exception e) {
            log.error("JT809消息编码失败:", e);
        }

    }

    /**
     * @see ProtocolEncoder#dispose(IoSession)
     */
    public void dispose(IoSession session) throws Exception {

    }

    /**
     * @return the charset
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * @param charset the charset to set
     */
    public void setCharset(Charset charset) {
        this.charset = charset;
    }
}
