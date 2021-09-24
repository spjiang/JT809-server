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
 * 主动上报驾驶员身份信息消息，主链路，UP_EXG_MSG_REPORT_DRIVER_INFO
 * <br>
 * 下级平台在接收到车载终端上传的驾驶员身份信息后，主动向上级平台上报该信
 * 息，本条消息客户端无需应答。
 *
 * @author liangyi
 * @date 2021/1/6 19:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x120C extends PModel implements Serializable {
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
     * 驾驶员姓名，16字节
     */
    private String driverName;

    /**
     * 身份证编号，20字节
     */
    private String driverId;

    /**
     * 从业资格证号(备用)，40字节
     */
    private String licence;

    /**
     * 发证机构名称(备用)，200字节
     */
    private String orgName;

    @Override
    public String getSubTypeHex() {
        return "120C";
    }

    @Override
    public String getSubType() {
        return "UP_EXG_MSG_REPORT_DRIVER_INFO";
    }
}
