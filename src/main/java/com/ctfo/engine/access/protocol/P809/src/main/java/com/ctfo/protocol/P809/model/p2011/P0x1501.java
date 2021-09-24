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
 * 车辆单向监听应答消息,主链路，UP_CTRL_MSG_MONITOR_VEHICLE_ACK
 * <br>
 * 下级平台向上级平台上传车辆单向监听请求消息的应答
 *
 * @author liangyi
 * @date 2020/8/27 9:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1501 extends PModel implements Serializable {

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
     * 车辆单向监听应答结果，定义如下：
     * 0x00：监听成功；
     * 0x01：监听失败
     */
    private byte result;

    @Override
    public String getSubTypeHex() {
        return "1501";
    }

    @Override
    public String getSubType() {
        return "UP_CTRL_MSG_MONITOR_VEHICLE_ACK";
    }
}
