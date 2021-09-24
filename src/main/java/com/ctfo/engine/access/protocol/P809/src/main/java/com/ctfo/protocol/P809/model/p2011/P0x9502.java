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
 * 车辆拍照请求消息， DOWN_CTRL_MSG_TAKE_PHOTO_REQ。
 * <br>
 * 上级平台向下级平台下发对某指定车辆的拍照请求消息
 *
 * @author liangyi
 * @date 2021/1/11 16:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9502 extends PModel implements Serializable {

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
     * 镜头ID
     */
    private byte lensId;

    /**
     * 照片大小，定义如下：
     * 0x01:320x240；
     * 0x02:640x480；
     * 0x03:800x600；
     * 0x04:1024x768；
     * 0x05:176x144[QCIF]；
     * 0x06:352*288[CIF]；
     * 0x07:704*288[HALF D1]；
     * 0x08:704*576[D1]
     */
    private byte size;

    @Override
    public String getSubTypeHex() {
        return "9502";
    }

    @Override
    public String getSubType() {
        return "DOWN_CTRL_MSG_TAKE_PHOTO_REQ";
    }
}
