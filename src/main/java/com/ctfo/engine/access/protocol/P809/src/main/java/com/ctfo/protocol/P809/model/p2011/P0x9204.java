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
 * 交换车辆静态信息消息，从链路，DOWN_EXG_MSG_CAR_INFO
 * <br>
 * 在首次启动跨域车辆定位信息交换，或者以后交换过程中车辆静态信息有更新时，由上
 * 级平台向下级平台下发一次车辆静态信息。下级平台接收后自行更新该车辆静态信息，其数据体规
 * 定见表32。本条消息客户端无需应答。
 *
 * @author liangyi
 * @date 2020/8/26 19:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9204 extends PModel implements Serializable {

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
     * 车辆静态信息
     */
    private String carInfo;

    @Override
    public String getSubTypeHex() {
        return "9204";
    }

    @Override
    public String getSubType() {
        return "DOWN_EXG_MSG_CAR_INFO";
    }
}
