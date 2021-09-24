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
 * 上报车辆行驶记录应答消息,主链路，UP_CTRL_MSG_TAKE_TRAVEL_ACK
 * <br>
 * 下级平台应答上级平台下发的上报车辆行驶记录请求消息，将车辆行驶记录数据上传至上级平台
 *
 * @author liangyi
 * @date 2020/8/27 9:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1504 extends PModel implements Serializable {

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
     * 命令字，按照GB/T19056中相关要求，1字节
     */
    private byte commandType;

    /**
     * 车辆行驶记录数据体长度
     * <br>
     * 4字节
     */
    private int traveldataLength;

    /**
     * 车辆行驶记录信息，按照GB/T 19056 的规定，
     */
    private String traveldataInfo;

    @Override
    public String getSubTypeHex() {
        return "1504";
    }

    @Override
    public String getSubType() {
        return "UP_CTRL_MSG_TAKE_TRAVEL_ACK";
    }
}
