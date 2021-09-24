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
 * 报警督办请求消息，从链路，DOWN_WARN_MSG_URGE_TODO_REQ
 * <br>
 * 上级平台向车辆归属下级平台下发本消息，催促其及时处理相关车辆的报警信息
 *
 * @author liangyi
 * @date 2020/8/26 20:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9401 extends PModel implements Serializable {

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
     * 报警信息来源，定义如下：
     * 0x01：车载终端；
     * 0x02：企业监控平台；
     * 0x03：政府监管平台；
     * 0x09：其它
     */
    private byte wranSrc;

    /**
     * 报警类型，2字节，详见表75
     */
    private short wranType;

    /**
     * 报警时间，UTC 时间格式，8字节
     */
    private long wranTime;

    /**
     * 报警督办ID， 4字节
     */
    private long supervisionId;

    /**
     * 督办截止时间，UTC 时间格式， 8字节
     */
    private long supervisionEndtime;

    /**
     * 督办级别，定义如下：
     * 0x00：紧急；
     * 0x01：一般
     */
    private byte supervisionLevel;

    /**
     * 督办人，16字节
     */
    private String supervisor;

    /**
     * 督办人联系电话，20字节
     */
    private String supervisorTel;

    /**
     * 督办联系电子邮箱，32字节
     */
    private String supervisorEmail;

    @Override
    public String getSubTypeHex() {
        return "9401";
    }

    @Override
    public String getSubType() {
        return "DOWN_WARN_MSG_URGE_TODO_REQ";
    }
}
