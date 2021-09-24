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
 * 报警督办应答消息，主链路，UP_WARN_MSG_URGE_TODO_ACK
 * <br>
 * 下级平台应答上级平台下发的报警督办请求消息，向上级平台上报车辆的报警处理结
 *
 * @author liangyi
 * @date 2020/8/26 20:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1401 extends PModel implements Serializable {

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
     * 报警督办ID，4字节
     */
    private long supervisionId;

    /**
     * 报警处理结果，定义如下：
     * 0x00：处理中；
     * 0x01：已处理完毕；
     * 0x02：不作处理；
     * 0x03：将来处理
     */
    private byte result;

    @Override
    public String getSubTypeHex() {
        return "1401";
    }

    @Override
    public String getSubType() {
        return "UP_WARN_MSG_URGE_TODO_ACK";
    }
}
