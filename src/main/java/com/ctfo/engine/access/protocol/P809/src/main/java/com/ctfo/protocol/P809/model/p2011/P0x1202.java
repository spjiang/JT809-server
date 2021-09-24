/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.model.p2011;

import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.vo.GnnsData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 实时上传车辆定位信息消息，主链路，UP_EXG_MSG_REAL_LOCATION
 * <br>
 * 本条消息服务端无需应答
 *
 * @author liangyi
 * @date 2020/8/25 15:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1202 extends PModel implements Serializable {

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
     * 车辆定位信息数据体
     */
    private GnnsData gnnsData;

    @Override
    public String getSubTypeHex() {
        return "1202";
    }

    @Override
    public String getSubType() {
        return "UP_EXG_MSG_REAL_LOCATION";
    }
}
