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
 * 上报驾驶员身份识别信息应答消息，主链路，UP_EXG_MSG_REPORT_DRIVER_INFO_ACK
 * <br>
 * 下级平台应答上级平台发送的上报驾驶员身份识别信息请求消息，上传指定车辆的驾驶员身份识别信息数据
 *
 * @author liangyi
 * @date 2020/8/25 17:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x120A extends PModel implements Serializable {
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
     * 驾驶员姓名，16字节
     */
    private String driverName;

    /**
     * 身份证编号，20字节
     */
    private String driverId;

    /**
     * 从业资格证号(备用)，40字节
     */
    private String licence;

    /**
     * 发证机构名称(备用)，200字节
     */
    private String orgName;

    @Override
    public String getSubTypeHex() {
        return "120A";
    }

    @Override
    public String getSubType() {
        return "UP_EXG_MSG_REPORT_DRIVER_INFO_ACK";
    }
}
