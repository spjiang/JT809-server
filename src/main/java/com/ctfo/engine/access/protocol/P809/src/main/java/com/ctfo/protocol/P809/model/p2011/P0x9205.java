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
 * 启动车辆定位信息交换请求消息，从链路，DOWN_EXG_MSG_RETURN_STARTUP
 * <br>
 * 在有车辆进入非归属地区地理区域、人工指定车辆定位信息交换和应急状态监控车辆时，
 * 上级平台向下级平台发出启动车辆定位信息交换请求消息。下级平台收到此命令后需要回复启动车
 * 辆定位信息交换应答消息给上级平台，即UP_EXG_MSG_RETURN_STARTUP_ACK
 *
 * @author liangyi
 * @date 2020/8/26 19:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9205 extends PModel implements Serializable {

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
     * 启动车辆定位信息交换的原因，定义如下：
     * 0x00:车辆进入指定区域；
     * 0x01:人工指定交换；
     * 0x02：应急状态下车辆定位信息回传；
     * 0x03：其他原因
     */
    private byte reasonCode;

    @Override
    public String getSubTypeHex() {
        return "9205";
    }

    @Override
    public String getSubType() {
        return "DOWN_EXG_MSG_RETURN_STARTUP";
    }
}
