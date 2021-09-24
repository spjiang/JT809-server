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
 * 上传车辆注册信息消息，主链路，UP_EXG_MSG_REGISTER
 *
 * @author liangyi
 * @date 2020/8/25 15:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1201 extends PModel implements Serializable {

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
     * 平台唯一编码，由平台所在地行政区划代码和平台编号组成。
     * 11字节，bytes类型
     */
    private String platformId;

    /**
     * 车辆终端厂商唯一编码，由车载终端厂商所在地行政区划代码和制造商 ID 组成
     *
     * 11字节，bytes类型
     */
    private String producerId;

    /**
     * 车载终端型号，20字节，不足20位时以“\0”终结
     */
    private String terminalModelType;

    /**
     * 车载终端编号，7字节，大写字母和数字组成
     */
    private String terminalId;

    /**
     * 车载终端SIM卡电话号码，12字节，不足12位则再前补充数字0
     */
    private String terminalSimCode;

    @Override
    public String getSubTypeHex() {
        return "1201";
    }

    @Override
    public String getSubType() {
        return "UP_EXG_MSG_REGISTER";
    }
}
