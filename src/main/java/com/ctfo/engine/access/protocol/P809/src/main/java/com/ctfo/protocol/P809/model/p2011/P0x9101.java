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
 * 接收车辆定位信息数量通知消息，从链路，DOWN_TOTAL_RECV_BACK_MSG
 * <br>
 * 消息方向：上级平台往下级平台
 * <br>
 * 上级平台向下级平台定量通知已经收到下级平台上传的车辆定位信息数量，本条消息不需要下级平台应答
 *
 * @author liangyi
 * @date 2020/8/25 15:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9101 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * START_TIME~END_TIME 共收到的车辆定位信息数据
     * <br>
     * 4字节
     */
    private long dynamicInfoTotal;

    /**
     * 开始时间，用UTC时间表示，8字节
     */
    private long startTime;

    /**
     * 结束时间，用UTC时间表示，8字节
     */
    private long endTime;

    @Override
    public String getSubTypeHex() {
        return "9101";
    }

    @Override
    public String getSubType() {
        return "DOWN_TOTAL_RECV_BACK_MSG";
    }
}
