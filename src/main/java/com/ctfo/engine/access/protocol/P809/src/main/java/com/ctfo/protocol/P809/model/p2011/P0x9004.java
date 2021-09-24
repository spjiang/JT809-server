/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.model.p2011;

import com.ctfo.protocol.P809.model.PModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 从链路注销应答消息(无消息体)，从链路，DOWN_DISCONNECT_RSP
 * <br>
 * 消息方向：下级平台往上级平台
 *
 * @author liangyi
 * @date 2020/8/25 14:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class P0x9004 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String getSubTypeHex() {
        return "9004";
    }

    @Override
    public String getSubType() {
        return "DOWN_DISCONNECT_RSP";
    }
}
