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
 * 申请交换指定车辆定位信息请求消息，主链路，UP_EXG_MSG_APPLY_FOR_MONITOR_STARTUP
 * <br>
 * 当下级平台需要在特定时间段内监控特殊车辆时，可上传此命令到上级平台申请对该车
 * 辆定位数据交换到下级平台，申请成功后，此车辆定位数据将在指定时间内交换到该平台（即使该
 * 车没有进入该平台所属区域也会交换），
 *
 * @author liangyi
 * @date 2020/8/25 17:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1207 extends PModel implements Serializable {
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
     * 开始时间，8字节，用UTC时间表示
     */
    private long startTime;

    /**
     * 结束时间，8字节，用UTC时间表示
     */
    private long endTime;

    @Override
    public String getSubTypeHex() {
        return "1207";
    }

    @Override
    public String getSubType() {
        return "UP_EXG_MSG_APPLY_FOR_MONITOR_STARTUP";
    }
}
