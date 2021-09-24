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
 * 从链路注销请求消息，从链路，DOWN_DISCONNECT_REQ
 * <br>
 * 消息方向：上级平台往下级平台
 *
 * @author liangyi
 * @date 2020/8/25 14:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9003 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 校验码，4字节
     */
    private long verifyCode;

    @Override
    public String getSubTypeHex() {
        return "9003";
    }

    @Override
    public String getSubType() {
        return "DOWN_DISCONNECT_REQ";
    }
}
