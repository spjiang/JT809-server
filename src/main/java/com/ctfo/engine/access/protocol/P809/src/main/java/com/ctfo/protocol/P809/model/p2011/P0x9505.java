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
 * 车辆应急接入监管平台请求消息,DOWN_CTRL_MSG_EMERGENCY_MONITORING_REQ
 * <br>
 * 在应急情况下，政府监管平台需要及时监控该车辆时，应向该车辆归属的下级平台发送该命令
 *
 * @author liangyi
 * @date 2021/1/11 15:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9505 extends PModel implements Serializable {

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
     * 监管平台下发的鉴权码，用于车载终端链接到监管平台鉴权时使用，10字节
     */
    private String authenticationCode;

    /**
     * 拨号点名称，一般为服务器的APN，无线通信拨号访问点，若网络制式为CDMA，则该值为PPP链接拨号号码
     * <br>
     * 20字节
     */
    private String accessPointName;

    /**
     * 拨号用户名，49字节
     */
    private String username;

    /**
     * 拨号密码，22字节
     */
    private String password;

    /**
     * 地址，服务器IP地址或域名，32字节
     */
    private String serverIp;

    /**
     * 服务器TCP端口，无符号 2字节
     */
    private int tcpPort;

    /**
     * 服务器UDP端口，无符号 2字节
     */
    private int udpPort;

    /**
     * 结束时间，用UTC时间标识，0表示一直链接指定的服务器
     */
    private long endTime;

    @Override
    public String getSubTypeHex() {
        return "9505";
    }

    @Override
    public String getSubType() {
        return "DOWN_CTRL_MSG_EMERGENCY_MONITORING_REQ";
    }
}
