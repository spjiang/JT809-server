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
 * 报警附件目录请求消息,从链路,DOWN_WARN_MSG_FILELIST_REQ
 * <br>
 * 上级平台向下级平台发送报警附件目录请求业务
 *
 * @author liangyi
 * @date 2021/2/23 14:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x9404 extends PModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 车牌号码，21字节
     */
    private String vehicleNo;

    /**
     * 车牌颜色
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
     * 报警标识号, 16字节
     *
     * 0	终端ID	BYTE[7]	7个字节，由大写字母和数字组成
     * 7	时间	BCD[6]	YY-MM-DD-hh-mm-ss
     * 13	序号	BYTE	同一时间点报警的序号，从0循环累加
     * 14	附件数量	BYTE	表示该报警对应的附件数量
     * 15	预留	BYTE
     */
    private String alarmId;

    @Override
    public String getSubTypeHex() {
        return "9404";
    }

    @Override
    public String getSubType() {
        return "DOWN_WARN_MSG_FILELIST_REQ";
    }
}
