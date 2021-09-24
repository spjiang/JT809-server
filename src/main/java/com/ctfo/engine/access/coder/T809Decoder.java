package com.ctfo.engine.access.coder;

import com.ctfo.engine.access.common.cache.LocalCache;
import com.ctfo.protocol.P809.P809Message;
import com.ctfo.protocol.P809.vo.PlatformAccessInfo;
import com.ctfo.protocol.support.utils.ConvUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;

/**
 * 809协议消息解码处理类
 *
 * @author 凉意 2018年5月14日 下午4:14:12
 * @version 1.0
 */
@Slf4j
public class T809Decoder extends CumulativeProtocolDecoder {
    /**
     * 编码字符
     */
    private Charset charset;

    public T809Decoder(String charset) {
        this.charset = Charset.forName(charset);
    }

    /**
     * Head flag 头标识
     * Message Header 数据头
     * Message Body 数具体
     * CRC Code CRC 校验码
     * End Flag 尾标识
     *
     *
     * @see CumulativeProtocolDecoder#doDecode(IoSession,
     * IoBuffer,
     * ProtocolDecoderOutput)
     */
    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        if (in.remaining() < 22) {
            return false;
        }
        byte[] data = {};
        try {
            in.mark();// 设置标记位，方便指针回滚定位
            // 同步头 Head flag
            byte synHead = in.get();
            // 0x5b 开始标识
            if (synHead != 0x5b) {
                return true;
            }
            // 开始读取数据(数据头开始读取)
            byte[] _data = new byte[in.limit()];
            byte temp = 0;
            // 获取字节数计数器
            int count = 0;
            // 0x5d 结束标识（End Flag）
            while (temp != 0x5d) {
                if (in.remaining() == 0) {
                    in.reset();
                    return false;
                }
                temp = in.get();
                // 消息解码转义
                // 若数据内容中有出现字符 0x5a 的，需替换为字符 0x5a 紧跟字符 0x02;
                if (temp == 0x5a) {
                    if (in.remaining() == 0) {
                        in.reset();
                        return false;
                    }
                    temp = in.get();
                    if (temp == 0x01) {
                        _data[count] = 0x5b;
                    } else if (temp == 0x02) {
                        _data[count] = 0x5a;
                    }
                } else if (temp == 0x5e) {
                    if (in.remaining() == 0) {
                        in.reset();
                        return false;
                    }
                    temp = in.get();
                    if (temp == 0x01) {
                        _data[count] = 0x5d;
                    } else if (temp == 0x02) {
                        _data[count] = 0x5e;
                    }
                } else {
                    _data[count] = temp;
                }
                count++;
            }
            data = new byte[count - 1];
            System.arraycopy(_data, 0, data, 0, data.length);
            if (data.length - 22 < 0) {
                log.error("消息长度不足,无法解析:" + ConvUtil.bytes2Hex(data));
                return true;
            }

            // 数据头: 消息ID(MSG_ID)
            byte[] msgId = new byte[2];
            System.arraycopy(data, 8, msgId, 0, 2);
            String msgIdHex = ConvUtil.bytes2Hex(msgId);

            // 接入码，下级平台接入码，上级平台给下级平台分配唯一标识码。
            Long gnnsCenterId = ConvUtil.bigBytes2Unsigned32Long(data, 10);
            // 根据接入码获取下级平台信息
            PlatformAccessInfo platform = LocalCache.PLATFORM_CONFIG.get(gnnsCenterId.toString());
            if (null == platform) {
                log.error("非法接入码:{}.", gnnsCenterId);
                // 非法接入平台，直接关闭链接
                session.close(true);
                return true;
            }
            P809Message message = new P809Message();
            message.formBytes(data, msgIdHex, platform.getM1(), platform.getIA1(), platform.getIC1());
            out.write(message);
            return true;
        } catch (Exception e) {
            log.error("消息解码失败,报文：{},异常：{}", "5B" + ConvUtil.bytes2Hex(data) + "5D", e);
            return true;
        }
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
