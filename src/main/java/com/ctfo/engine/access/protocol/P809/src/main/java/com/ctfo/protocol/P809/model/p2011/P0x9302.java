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
 * 下发平台间报文请求消息，从链路，DOWN_PLATFORM_MSG_INFO_REQ
 * <br>
 * 上级平台不定期向下级平台下发平台间报文
 *
 * @author liangyi
 * @date 2020/8/26 19:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9302 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 子业务类型，2字节
     */
    private short dataType;

    /**
     * 后续数据长度，4字节
     */
    private int dataLength;

    /**
     * 下发报文对象的类型， 1字节
     * <br>
     * 0x00   下级平台所属单一平台
     * 0x01   当前链接的下级平台
     * 0x02   下级平台所属单一业户
     * 0x03   下级平台所属所有业户
     * 0x04   下级平台所属所有平台
     * 0x05   下级平台所属所有平台和业户
     * 0x06   下级平台所属所有政府监管平台（含监控端）
     * 0x07   下级平台所属所有企业监控平台
     * 0x08   下下级平台所属所有经营性企业监控平台
     * 0x09   下级平台所属所有非经营性企业监控平台
     */
    private byte objectType;

    /**
     * 下发报文对象ID，长度不足时后补0x00，12字节
     * <br>
     * 对象类型<0X02 时，由平台行政区划代码和平台唯一编码组成；
     * 对象类型=0X02 时，为业户经营许可证号；
     * 对象类型>0X02 时，为0x000000000000000000000000。
     */
    private String objectId;

    /**
     * 信息ID，4字节
     */
    private long infoId;

    /**
     * 信息长度，4字节
     */
    private int infoLength;

    /**
     * 信息内容
     */
    private String infoContent;

    @Override
    public String getSubTypeHex() {
        return "9302";
    }

    @Override
    public String getSubType() {
        return "DOWN_PLATFORM_MSG_INFO_REQ";
    }
}
