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
 * 上报车辆电子运单请求消息，从链路，DOWN_EXG_MSG_TAKE_EWAYBILL_REQ
 * <br>
 * 上级平台向下级平台下发上报车辆当前电子运单的请求消息
 *
 * @author liangyi
 * @date 2020/8/26 19:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x920B extends PModel implements Serializable {

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
     * 后续数据长度，4字节， 值为0
     */
    private int dataLength;

    @Override
    public String getSubTypeHex() {
        return "920B";
    }

    @Override
    public String getSubType() {
        return "DOWN_EXG_MSG_TAKE_EWAYBILL_REQ";
    }
}
