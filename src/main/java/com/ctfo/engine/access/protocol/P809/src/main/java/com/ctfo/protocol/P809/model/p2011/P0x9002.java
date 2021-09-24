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
 * 从链路连接应答信息，从链路，DOWN_CONNECT_RSP
 * <br>
 * 消息方向：下级平台往上级平台
 *
 * @author liangyi
 * @date 2020/8/17 19:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9002 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 验证结果，1字节，0x00:成功；0x01:验证码错误；0x02:资源紧张，稍后再连接(已经占用)；0x03:其他
     */
    private byte result;

    @Override
    public String getSubTypeHex() {
        return "9002";
    }

    @Override
    public String getSubType() {
        return "DOWN_CONNECT_RSP";
    }
}
