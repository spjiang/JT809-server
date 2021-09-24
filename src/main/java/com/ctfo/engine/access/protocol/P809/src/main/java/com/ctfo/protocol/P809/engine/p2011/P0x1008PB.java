/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1007;
import com.ctfo.protocol.P809.model.p2011.P0x1008;
import com.ctfo.protocol.support.exception.P809Exception;

/**
 * 下级平台主动关闭主从链路通知消息，从链路，UP_CLOSELINK_INFORM
 * <br>
 * 消息方向：下级平台往上级平台
 *
 * @author liangyi
 * @date 2021/1/11 19:38
 */
public class P0x1008PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x1008消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x1008 p = (P0x1008) model;
            return new byte[]{p.getReasonCode()};
        } catch (Exception e) {
            throw new P809Exception("P0x1008编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 1 != data.length) {
            throw new P809Exception("P0x1008消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x1008 p = new P0x1008();
            p.setReasonCode(data[0]);
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x1008解码失败:", e);
        }
    }
}
