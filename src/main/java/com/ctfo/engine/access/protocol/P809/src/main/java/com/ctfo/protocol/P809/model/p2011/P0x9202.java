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
 * 交换车辆定位信息消息,从链路，DOWN_EXG_MSG_CAR_LOCATION
 * <br>
 * 描述：上级平台在以下四种情况下通过该消息不间断向下级平台发送车辆定位信息。<br>
 * (1) 车辆跨域时，上级平台通过该消息不间断地向车辆进入区域所属的下级平台发送车辆定位信息，直到该车辆离开该区域；<br>
 * (2) 人工指定车辆定位信息交换时，上级平台通过该消息不间断地向指定交换对象下级平台发送车辆定位信息，直到人工指定交换车辆定位信息结束；<br>
 * (3) 下级平台向上级平台申请交换指定车辆定位信息成功后，上级平台通过该消息不间断地向交换对象下级平台发送车辆定位信息，直到申请交换指定车辆定位信息结束；<br>
 * (4) 应急状态监控车辆时，上级平台向车辆归属下级平台通过该消息不间断地发送车辆定位信息，实现车辆定位信息回传。<br>
 *
 *
 * @author liangyi
 * @date 2020/8/25 17:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9202 extends PModel implements Serializable {

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
        return "9202";
    }

    @Override
    public String getSubType() {
        return "DOWN_EXG_MSG_CAR_LOCATION";
    }
}
