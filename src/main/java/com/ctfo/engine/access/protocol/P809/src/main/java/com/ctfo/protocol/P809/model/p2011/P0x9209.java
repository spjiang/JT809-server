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
 * 补发车辆定位信息应答消息，从链路，DOWN_EXG_MSG_APPLY_HISGNSSDATA_ACK
 * <br>
 * 本条消息是上级平台应答下级平台发送的补发车辆定位信息请求消息，即
 * UP_EXG_MSG_APPLY_HISGNSSDATA_REQ，
 *
 * @author liangyi
 * @date 2020/8/26 19:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9209 extends PModel implements Serializable {

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
     * 补发车辆定位信息应答结果，定义如下：
     * 0x00：成功，上级平台即刻补发；
     * 0x01：成功，上级平台择机补发；
     * 0x02：失败，上级平台无对应申请的定位数据；
     * 0x03：失败，申请内容不正确；
     * 0x04：其它原因
     */
    private byte result;

    @Override
    public String getSubTypeHex() {
        return "9209";
    }

    @Override
    public String getSubType() {
        return "DOWN_EXG_MSG_APPLY_HISGNSSDATA_ACK";
    }
}
