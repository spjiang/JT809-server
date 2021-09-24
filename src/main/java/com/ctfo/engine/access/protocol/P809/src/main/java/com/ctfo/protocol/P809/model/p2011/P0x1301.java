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
 * 平台查岗应答消息，主链路，UP_PLATFORM_MSG_POST_QUERY_ACK
 * <br>
 * 下级平台应答上级平台发送的不定期平台查岗消息
 *
 * @author liangyi
 * @date 2020/8/26 19:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1301 extends PModel implements Serializable {

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
     * 查岗对象的类型， 1字节
     * <br>
     * 0x01   当前链接的下级平台
     * 0x02   下级平台所属单一业户
     * 0x03   下级平台所属所有业户
     */
    private byte objectType;

    /**
     * 查岗对象ID，长度不足时后补0x00，12字节
     * <br>
     * 对象类型为平台时，又平台行政区划代码和平台唯一编码组成；
     * 对象类型为业户时，为业户经营许可证号
     */
    private String objectId;

    /**
     * 信息ID，本ID跟下发的ID相同，4字节
     */
    private long infoId;

    /**
     * 数据长度，4字节
     */
    private int infoLength;

    /**
     * 应答内容
     */
    private String infoContent;

    @Override
    public String getSubTypeHex() {
        return "1301";
    }

    @Override
    public String getSubType() {
        return "UP_PLATFORM_MSG_POST_QUERY_ACK";
    }
}
