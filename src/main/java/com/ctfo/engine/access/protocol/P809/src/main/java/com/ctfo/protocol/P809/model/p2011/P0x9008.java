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
 * 上级平台主动管理主从链路通知消息，主链路，DOWN_CLOSELINK_INFORM
 * <br>
 * 消息方向：上级平台往下级平台。
 *
 * @author liangyi
 * @date 2020/8/25 14:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9008 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 链路关闭原因，
     * 0x00:网关重启；
     * 0x01:其他原因
     */
    private byte reasonCode;

    @Override
    public String getSubTypeHex() {
        return "9008";
    }

    @Override
    public String getSubType() {
        return "DOWN_CLOSELINK_INFORM";
    }
}
