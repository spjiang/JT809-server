/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.model.p2011;

import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.vo.AlarmFileInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 报警附件目录请求应答消息,主链路,UP_WARN_MSG_FILELIST_ACK
 * <br>
 * 下级平台向上级平台发送报警附件目录请求应答业务，上级平台可通过报警附件文件 URL 以 HTTP 协议直接访问或自行下载报警附件文件
 *
 * @author liangyi
 * @date 2021/2/23 14:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1404 extends PModel implements Serializable {
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

    /**
     * 附件数量，1字节
     */
    private byte fileCount;


    /**
     * 报警附件
     */
    private List<AlarmFileInfo> fileList;

    @Override
    public String getSubTypeHex() {
        return "1404";
    }

    @Override
    public String getSubType() {
        return "UP_WARN_MSG_FILELIST_ACK";
    }
}
