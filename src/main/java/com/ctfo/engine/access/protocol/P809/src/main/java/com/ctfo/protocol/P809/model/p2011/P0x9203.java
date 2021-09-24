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
 * 车辆定位信息自动补报请求消息，从链路，DOWN_EXG_MSG_HISTORY_ARCOSSAREA
 * <br>
 * 本业务在DOWN_EXG_MSG_APPLY_HISGNSSDATA_ACK 应答成功后，立即开始交换。
 * 如果申请失败，则不进行数据转发
 * <br>
 * 本条消息下级平台无需应答。
 *
 * @author liangyi
 * @date 2020/8/25 17:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9203 extends PModel implements Serializable {

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
        return "9203";
    }

    @Override
    public String getSubType() {
        return "DOWN_EXG_MSG_HISTORY_ARCOSSAREA";
    }
}
