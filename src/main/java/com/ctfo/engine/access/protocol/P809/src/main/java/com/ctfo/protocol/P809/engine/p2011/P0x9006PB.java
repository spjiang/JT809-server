/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x9006;
import com.ctfo.protocol.support.exception.P809Exception;

/**
 * 从链路连接保持应答消息(无消息体),从链路，DOWN_LINKTEST_RSP
 * <br>
 * 消息方向：上级平台往下级平台
 *
 * @author liangyi
 * @date 2021/2/22 16:22
 */
public class P0x9006PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        return new byte[0];
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        return new P0x9006();
    }
}
