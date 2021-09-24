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
 * 结束车辆定位信息交换应答消息，主链路，UP_EXG_MSG_RETURN_END_ACK
 * <br>
 * 本条消息是下级平台对上级平台服务器端下发的 DOWN_EXG_MSG_RETURN_END 消息的应答消息
 *
 * @author liangyi
 * @date 2020/8/25 16:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1206 extends PModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 车牌号，21字节
     */
    private String vehicleNo;

    /**
     * 车牌颜色，1字节
     */
    private byte vehicleColor;

    /**
     * 子业务类型，2字节
     */
    private short dataType;

    /**
     * 后续数据长度，4字节，值为 0x00000000
     */
    private int dataLength;

    @Override
    public String getSubTypeHex() {
        return "1206";
    }

    @Override
    public String getSubType() {
        return "UP_EXG_MSG_RETURN_END_ACK";
    }
}
