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
 * 主动上报车辆电子运单信息消息，主链路，UP_EXG_MSG_REPORT_EWAYBILL_INFO
 * <br>
 * 下级平台主动上报车辆电子运单信息，向上级平台上报车辆当前电子运单
 *
 * @author liangyi
 * @date 2020/8/25 17:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x120D extends PModel implements Serializable {
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
     * 电子运单数据体长度
     */
    private int ewaybillLength;

    /**
     * 电子运单数据内容，
     */
    private String ewaybillInfo;

    @Override
    public String getSubTypeHex() {
        return "120D";
    }

    @Override
    public String getSubType() {
        return "UP_EXG_MSG_REPORT_EWAYBILL_INFO";
    }

}
