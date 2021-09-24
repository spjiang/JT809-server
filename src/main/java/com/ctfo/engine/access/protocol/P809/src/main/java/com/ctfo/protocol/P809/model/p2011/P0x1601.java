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
 * 补报车辆静态信息应答消息,UP_BASE_MSG_VEHICLE_ADDED_ACK
 * <br>
 * 下级平台应答上级平台发送的补报车辆静态信息请求消息
 *
 * @author liangyi
 * @date 2021/1/11 16:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1601 extends PModel implements Serializable {

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
     * 车辆信息
     */
    private String carInfo;

    @Override
    public String getSubTypeHex() {
        return "1601";
    }

    @Override
    public String getSubType() {
        return "UP_BASE_MSG_VEHICLE_ADDED_ACK";
    }
}
