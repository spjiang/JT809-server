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
 * 从链路断开通知消息，从链路，DOWN_DISCONNECT_INFORM
 * <br>
 * 消息方向：上级平台往下级平台
 *
 * @author liangyi
 * @date 2020/8/25 14:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9007 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 错误代码，
     * 0x00:无法连接下级平台指定的服务IP与端口；
     * 0x01:上级平台客户端与下级平台服务端断开；
     * 0x02:其他原因。
     */
    private byte errorCode;

    @Override
    public String getSubTypeHex() {
        return "9007";
    }

    @Override
    public String getSubType() {
        return "DOWN_DISCONNECT_INFORM";
    }

}
