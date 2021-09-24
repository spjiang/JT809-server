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
 * 主链路注销请求消息 UP_DISCONNECT_REQ
 * <br>
 * 消息方向：下级平台往上级平台
 *
 * @author liangyi
 * @date 2020/8/17 19:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1003 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名，4字节
     */
    private long userId;

    /**
     * 密码， 8字节
     */
    private String password;

    @Override
    public String getSubTypeHex() {
        return "1003";
    }

    @Override
    public String getSubType() {
        return "UP_DISCONNECT_REQ";
    }
}
