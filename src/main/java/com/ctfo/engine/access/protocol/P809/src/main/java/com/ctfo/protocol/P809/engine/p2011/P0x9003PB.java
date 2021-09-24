/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x9001;
import com.ctfo.protocol.P809.model.p2011.P0x9003;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;

/**
 * 从链路注销请求消息，从链路，DOWN_DISCONNECT_REQ
 * <br>
 * 消息方向：上级平台往下级平台
 *
 * @author liangyi
 * @date 2021/2/22 16:13
 */
public class P0x9003PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x9003消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x9003 p = (P0x9003) model;
            return ConvUtil.unSigned32LongToBigBytes(p.getVerifyCode());
        } catch (Exception e) {
            throw new P809Exception("P0x9003编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 4 > data.length) {
            throw new P809Exception("P0x9003消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x9003 p = new P0x9003();
            p.setVerifyCode(ConvUtil.bigBytes2Unsigned32Long(data, 0));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x9003解码失败:", e);
        }
    }
}
