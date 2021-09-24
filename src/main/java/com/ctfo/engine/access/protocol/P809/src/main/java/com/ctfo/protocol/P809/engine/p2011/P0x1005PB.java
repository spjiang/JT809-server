/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1005;
import com.ctfo.protocol.support.exception.P809Exception;

/**
 * 主链路连接保持请求消息(无消息体) UP_LINKTEST_REQ
 * <br>
 * 消息方向：下级平台往上级平台
 *
 * @author liangyi
 * @date 2021/1/11 19:27
 */
public class P0x1005PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        return new byte[]{};
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        return new P0x1005();
    }
}
