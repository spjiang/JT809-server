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
 * 实时交换报警信息消息，从链路，DOWN_WARN_MSG_EXG_INFORM
 * <br>
 * 用于上级平台向车辆跨域目的地下级平台下发相关车辆的当前报警情况
 * 本条消息下级平台无需应答
 *
 * @author liangyi
 * @date 2020/8/26 20:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9403 extends PModel implements Serializable {

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
     * 报警信息来源，定义如下：
     * 0x01：车载终端；
     * 0x02：企业监控平台；
     * 0x03：政府监管平台；
     * 0x09：其它
     */
    private byte wranSrc;

    /**
     * 报警类型，2字节，详见表75
     */
    private short wranType;

    /**
     * 报警时间，UTC 时间格式，8字节
     */
    private long wranTime;

    /**
     * 报警信息长度，4字节
     */
    private int warnLength;

    /**
     * 报警描述，报警信息提示
     */
    private String warnContent;

    @Override
    public String getSubTypeHex() {
        return "9403";
    }

    @Override
    public String getSubType() {
        return "DOWN_WARN_MSG_EXG_INFORM";
    }
}
