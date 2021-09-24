/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.model.p2011;

import com.ctfo.protocol.P809.model.PModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 主链路断开通知消息，从链路，UP_DISCONNECT_INFORM
 * <br>
 * 消息方向：下级平台往上级平台
 *
 * @author liangyi
 * @date 2020/8/17 19:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1007 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 错误代码: 0x00 主链路断开；0x01：其他原因
     */
    private byte errorCode;

    @Override
    public String getSubTypeHex() {
        return "1007";
    }

    @Override
    public String getSubType() {
        return "UP_DISCONNECT_INFORM";
    }
}
