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
 * 主链路登陆请求消息 UP_CONNECT_REQ
 * <br>
 * 消息方向：下级平台往上级平台
 *
 * @author liangyi
 * @date 2020/8/17 19:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1001 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名， 4字节
     */
    private long userId;

    /**
     * 密码，8字节
     */
    private String password;

    /**
     * 下级平台提供对应的从链路服务端IP地址， 32字节
     */
    private String downLinkIp;

    /**
     * 下级平台提供对应的从链路服务端口号，2字节
     */
    private int downLinkPort;

    @Override
    public String getSubTypeHex() {
        return "1001";
    }

    @Override
    public String getSubType() {
        return "UP_CONNECT_REQ";
    }

}
