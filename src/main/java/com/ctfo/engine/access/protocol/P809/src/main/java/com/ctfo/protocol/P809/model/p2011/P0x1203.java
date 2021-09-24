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
import java.util.List;

/**
 * 车辆定位信息自动补报请求消息，主链路，UP_EXG_MSG_HISTORY_LOCATION
 * <br>
 * 如果平台间传输链路中断，下级平台重新登录并与上级平台建立通信链路后，下级平台
 * 应将中断期间内车载终端上传的车辆定位信息自动补报到上级平台。如果系统断线期间，该车需发
 * 送的数据包条数大于5，则以每包五条进行补发，直到补发完毕。多条数据以卫星定位时间先后顺
 * 序排列。本条消息上级平台采用定量回复，即收到一定数量的数据后，即通过从链路应答数据量。
 *
 * @author liangyi
 * @date 2020/8/25 16:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1203 extends PModel implements Serializable {

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
     * 该数据包的卫星定位数据个数
     * 1~5
     */
    private byte gnnsCnt;

    /**
     * 车辆位置数据列表，
     */
    private List<GnnsData> gnnsDatas;

    @Override
    public String getSubTypeHex() {
        return "1203";
    }

    @Override
    public String getSubType() {
        return "UP_EXG_MSG_HISTORY_LOCATION";
    }
}
