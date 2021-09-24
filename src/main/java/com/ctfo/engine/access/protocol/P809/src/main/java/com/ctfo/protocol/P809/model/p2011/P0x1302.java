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
 * 下发平台间报文应答消息，主链路，UP_PLATFORM_MSG_INFO_ACK
 * <br>
 * 下级平台收到上级平台发送的下发平台间报文请求消息后，根据相应的下发报文对象类型，进行转发，并向上级平台发送应答消息
 *
 * @author liangyi
 * @date 2020/8/26 19:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1302 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 子业务类型，2字节
     */
    private short dataType;

    /**
     * 后续数据长度，4字节
     */
    private int dataLength;

    /**
     * 收到信息的ID，4字节
     */
    private long infoId;

    @Override
    public String getSubTypeHex() {
        return "1302";
    }

    @Override
    public String getSubType() {
        return "UP_PLATFORM_MSG_INFO_ACK";
    }
}
