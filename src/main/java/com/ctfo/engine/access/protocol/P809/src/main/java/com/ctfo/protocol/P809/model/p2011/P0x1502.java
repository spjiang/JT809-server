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
 * 车辆拍照应答消息,主链路，UP_CTRL_MSG_TAKE_PHOTO_ACK
 * <br>
 * 下级平台应答上级平台发送的车辆拍照请求消息，上传图片信息到上级平台
 *
 * @author liangyi
 * @date 2020/8/27 9:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1502 extends PModel implements Serializable {

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
     * 拍照应答标识，标识拍照后的结果或原因，
     * 定义如下：
     * 0x00：不支持拍照；
     * 0x01：完成拍照；
     * 0x02：完成拍照、照片数据稍后传送；
     * 0x03：未拍照（不在线）；
     * 0x04：未拍照（无法使用指定镜头）；
     * 0x05：未拍照（其他原因）；
     * 0x09：车牌号码错误
     */
    private byte photoRspFlag;

    /**
     * 拍照位置地点，36字节
     */
    private GnnsData gnnsData;

    /**
     * 镜头ID
     */
    private byte lensId;

    /**
     * 图片长度， 4字节
     */
    private int photoLen;

    /**
     * 图片大小，定义如下：
     * 0x01:320x240；
     * 0x02:640x48；
     * 0x03:800x600；
     * 0x04:1024x76；
     * 0x05:176x144[QCIF]；
     * 0x06:352*288[CIF]；
     * 0x07:704*288[HALF D1]；
     * 0x08:704*576[D1]
     */
    private byte sizeType;

    /**
     * 图像格式，定义如下：
     * 0x01:jpg；
     * 0x02:gif；
     * 0x03:tiff；
     * 0x04:png
     */
    private byte type;

    /**
     * 图片内容，HEX格式
     */
    private String photo;

    @Override
    public String getSubTypeHex() {
        return "1502";
    }

    @Override
    public String getSubType() {
        return "UP_CTRL_MSG_TAKE_PHOTO_ACK";
    }
}
