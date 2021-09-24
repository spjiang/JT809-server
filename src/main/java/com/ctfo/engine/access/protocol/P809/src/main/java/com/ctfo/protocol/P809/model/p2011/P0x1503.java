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
 * 下发车辆报文应答消息,主链路，UP_CTRL_MSG_TEXT_INFO_ACK
 * <br>
 * 下级平台应答上级平台下发的报文是否成功到达指定车辆
 *
 * @author liangyi
 * @date 2020/8/27 9:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1503 extends PModel implements Serializable {

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
     * 对应“ 下发车辆报文请求消息” 中的MSG_ID
     * <br>
     * 4字节
     */
    private long msgId;

    /**
     * 下发车辆报文应答结果，定义如下：
     * 0x00：下发成功；
     * 0x01：下发失败
     */
    private byte result;

    @Override
    public String getSubTypeHex() {
        return "1503";
    }

    @Override
    public String getSubType() {
        return "UP_CTRL_MSG_TEXT_INFO_ACK";
    }
}
