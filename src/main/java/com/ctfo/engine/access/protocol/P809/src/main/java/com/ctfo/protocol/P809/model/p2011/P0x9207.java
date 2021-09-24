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
 * 申请交换指定车辆定位信息应答消息，从链路，DOWN_EXG_MSG_APPLY_FOR_MONITOR_STARTUP_ACK
 * <br>
 * 应答下级平台申请交换指定车辆定位信息请求消息，即
 * UP_EXG_MSG_APPLY_FOR_MONITOR_STARTUP
 *
 * @author liangyi
 * @date 2020/8/26 19:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9207 extends PModel implements Serializable {

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
     * 申请交换指定车辆定位信息结果，定义如下：
     * 0x00：申请成功；
     * 0x01：上级平台没有该车数据；
     * 0x02：申请时间段错误；
     * 0x03：其它
     */
    private byte result;

    @Override
    public String getSubTypeHex() {
        return "9207";
    }

    @Override
    public String getSubType() {
        return "DOWN_EXG_MSG_APPLY_FOR_MONITOR_STARTUP_ACK";
    }
}
