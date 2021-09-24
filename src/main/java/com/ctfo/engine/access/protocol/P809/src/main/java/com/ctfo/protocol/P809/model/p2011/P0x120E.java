/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.model.p2011;

import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.vo.TBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 实时上传电动汽车信息消息,UP_EXG_MSG_REAL_ELECTRIC_INFO
 * <br>
 * 下级平台主动向上级平台上报车辆上传的相关信息
 *
 * @author liangyi
 * @date 2021/3/22 10:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x120E extends PModel implements Serializable {

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
     * 数据采集时间，6字节，年-月-日-小时-分钟-秒
     */
    private Date collectTime;

    /**
     * 数据内容
     */
    private List<TBase> infoContent = new ArrayList<>();

    @Override
    public String getSubTypeHex() {
        return "120E";
    }

    @Override
    public String getSubType() {
        return "UP_EXG_MSG_REAL_ELECTRIC_INFO";
    }
}
