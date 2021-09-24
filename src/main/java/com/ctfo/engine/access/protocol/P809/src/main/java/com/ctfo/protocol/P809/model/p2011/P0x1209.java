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
 * 补发车辆定位信息请求消息，主链路，UP_EXG_MSG_APPLY_HISGNSSDATA_REQ
 * <br>
 * 在平台间传输链路中断并重新建立连接后，下级平台向上级平台请求中断期间内上级平
 * 台需交换至下级平台的车辆定位信息时，向上级平台发出补发车辆定位信息请求，上级平台对请求
 * 应答后进行“补发车辆定位信息”，
 *
 * @author liangyi
 * @date 2020/8/25 17:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1209 extends PModel implements Serializable {
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
        return "1209";
    }

    @Override
    public String getSubType() {
        return "UP_EXG_MSG_APPLY_HISGNSSDATA_REQ";
    }
}
