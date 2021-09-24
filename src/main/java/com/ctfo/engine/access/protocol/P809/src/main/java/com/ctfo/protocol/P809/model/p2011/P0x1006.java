/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.model.p2011;

import com.ctfo.protocol.P809.model.PModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 主链路连接保持应答消息(无消息体) UP_LINKTEST_RSP
 * <br>
 * 消息方向：上级平台往下级平台
 *
 * @author liangyi
 * @date 2020/8/17 19:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class P0x1006 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String getSubTypeHex() {
        return "1006";
    }

    @Override
    public String getSubType() {
        return "UP_LINKTEST_RSP";
    }
}
