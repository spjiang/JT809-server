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
 * 车辆单向监听请求消息,从链路，DOWN_CTRL_MSG_MONITOR_VEHICLE_REQ
 * <br>
 * 上级平台向下级平台下发车辆单向监听请求消息，
 *
 * @author liangyi
 * @date 2020/8/27 9:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9501 extends PModel implements Serializable {

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
     * 回拨电话号码，参数为电话号码，如有
     * 分机号码，中间用“-”分隔，
     * 20字节
     */
    private String monitorTel;

    @Override
    public String getSubTypeHex() {
        return "9501";
    }

    @Override
    public String getSubType() {
        return "DOWN_CTRL_MSG_MONITOR_VEHICLE_REQ";
    }
}
