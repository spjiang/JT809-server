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
 * 上报车辆行驶记录请求消息， DOWN_CTRL_MSG_TAKE_TRAVEL_REQ
 * <br>
 *  上级平台向下级平台下发上报车辆行驶记录请求消息
 *
 *
 * @author liangyi
 * @date 2021/1/11 15:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9504 extends PModel implements Serializable {

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
     * 后续数据长度，4字节
     */
    private int dataLength;

    /**
     * 命令字，按照GB/T19056中相关要求，1字节
     */
    private byte commandType;

    @Override
    public String getSubTypeHex() {
        return "9504";
    }

    @Override
    public String getSubType() {
        return "DOWN_CTRL_MSG_TAKE_TRAVEL_REQ";
    }
}
