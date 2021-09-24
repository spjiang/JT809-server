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
 * 补报电动汽车信息消息,UP_EXG_MSG_HISTORY_ELECTRIC_INFO
 * <br>
 * 当数据通信链路异常时，下级平台应将车载终端上报的数据进行本地存储。在数据通信链路恢复正常后，在发送实时上报数据的空闲时间完成补发存储上的上报数据。补发的上报数据应为3日内通信链路异常期间存储的数据，数据格式与实时上传数据相同。本条消息上级平台无需应答。本条消息服务端无需应答。
 *
 * @author liangyi
 * @date 2021/3/22 10:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x120F extends PModel implements Serializable {

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
        return "120F";
    }

    @Override
    public String getSubType() {
        return "UP_EXG_MSG_HISTORY_ELECTRIC_INFO";
    }
}
