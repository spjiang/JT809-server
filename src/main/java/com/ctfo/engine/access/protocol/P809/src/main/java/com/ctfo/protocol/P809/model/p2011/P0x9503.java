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
 * 下发车辆报文请求消息，DOWN_CTRL_MSG_TEXT_INFO
 * <br>
 * 用于上级平台向下级平台下发报文到某指定车辆
 *
 * @author liangyi
 * @date 2021/1/11 16:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9503 extends PModel implements Serializable {

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
     * 消息 ID 序号，本序号作为标识本消息的唯一标识， 4字节
     */
    private long msgSequence;

    /**
     * 报文优先级，定义如下：
     * 0x00：紧急；
     * 0x01：一般
     */
    private byte msgPriority;

    /**
     * 报文信息长度,最长 1024 字节，  4字节
     */
    private int msgLength;

    /**
     * 报文信息内容
     */
    private String msgContent;

    @Override
    public String getSubTypeHex() {
        return "9503";
    }

    @Override
    public String getSubType() {
        return "DOWN_CTRL_MSG_TEXT_INFO";
    }
}
