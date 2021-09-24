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
 * 车辆应急接入监管平台应答消息,主链路，UP_CTRL_MSG_EMERGENCY_MONITORING_ACK
 * <br>
 * 下级平台应答上级平台下发的车辆应急接入监管平台请求消息应答
 *
 * @author liangyi
 * @date 2020/8/27 9:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1505 extends PModel implements Serializable {

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
     * 0x00：车载终端成功收到该命令；
     * 0x01：无该车辆；
     * 0x02：其它原因失败
     */
    private byte result;

    @Override
    public String getSubTypeHex() {
        return "1505";
    }

    @Override
    public String getSubType() {
        return "UP_CTRL_MSG_EMERGENCY_MONITORING_ACK";
    }
}
