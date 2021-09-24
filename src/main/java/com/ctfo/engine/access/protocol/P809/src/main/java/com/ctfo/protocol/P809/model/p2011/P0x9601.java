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
 * 补报车辆静态信息请求消息, DOWN_BASE_MSG_VEHICLE_ADDED
 * <br>
 * 上级平台在接收到车辆定位信息后，发现该车辆静态信息在上级平台不存在，上级平台向下级平台下发补报该车辆静态信息的请求消息
 *
 * @author liangyi
 * @date 2021/1/11 16:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9601 extends PModel implements Serializable {

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
     * 后续数据长度，4字节, 0x00000000
     */
    private int dataLength;

    @Override
    public String getSubTypeHex() {
        return "9601";
    }

    @Override
    public String getSubType() {
        return "DOWN_BASE_MSG_VEHICLE_ADDED";
    }
}
