/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x9002;
import com.ctfo.protocol.support.exception.P809Exception;

/**
 * 从链路连接应答信息，从链路，DOWN_CONNECT_RSP
 * <br>
 * 消息方向：下级平台往上级平台
 *
 * @author liangyi
 * @date 2021/2/22 16:18
 */
public class P0x9002PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x9002消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x9002 p = (P0x9002) model;
            return new byte[]{p.getResult()};
        } catch (Exception e) {
            throw new P809Exception("P0x9002编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 1 > data.length) {
            throw new P809Exception("P0x9002消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x9002 p = new P0x9002();
            p.setResult(data[0]);
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x9002解码失败:", e);
        }
    }
}
