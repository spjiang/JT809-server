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
 * 结束车辆定位信息交换请求消息，从链路，DOWN_EXG_MSG_RETURN_END
 * <br>
 * 在进入非归属地区地理区域的车辆离开该地理区域、人工取消指定车辆定位信息交换和
 * 应急状态结束时，上级平台向下级平台发出结束车辆定位信息交换请求消息。下级平台收到该命令
 * 后应回复结束车辆定位信息交换应答消息，即UP_EXG_MSG_RETURN_END_ACK
 *
 * @author liangyi
 * @date 2020/8/26 19:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9206 extends PModel implements Serializable {

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
     * 结束车辆定位信息交换的原因，定义如下：
     * 0x00：车辆离开指定区域；
     * 0x01：人工停止交换；
     * 0x02：紧急监控完成；
     * 0x03：车辆离线；
     * 0x04：其它原因
     */
    private byte reasonCode;

    @Override
    public String getSubTypeHex() {
        return "9206";
    }

    @Override
    public String getSubType() {
        return "DOWN_EXG_MSG_RETURN_END";
    }
}
