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
 * 主链路登陆应答消息，UP_CONNECT_RSP
 * <br>
 * 消息方向：上级平台往下级平台
 *
 * @author liangyi
 * @date 2020/8/17 19:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1002 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 验证结果，1字节  定义如下:<br>
     * 0x00:成功
     * 0x01:IP地址不正确
     * 0x02:接入码不正确
     * 0x03:用户没有注册
     * 0x04:密码错误
     * 0x05:资源紧张，稍后再连接(已经占用)
     * 0x06:其他。
     */
    private byte result;

    /**
     * 校验码， 4字节
     */
    private long verifyCode;

    @Override
    public String getSubTypeHex() {
        return "1002";
    }

    @Override
    public String getSubType() {
        return "UP_CONNECT_RSP";
    }
}
