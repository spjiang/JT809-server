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
 * 主动上报报警处理结果信息消息, UP_WARN_MSG_ADPT_TODO_INFO
 * <br>
 *下级平台向主动向上级平台上报报警处理结果。本条消息上级平台无需应答
 *
 * @author liangyi
 * @date 2021/1/11 14:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1403 extends PModel implements Serializable {
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
     * 报警信息ID，4字节
     */
    private long infoId;

    /**
     * 报警处理结果，定义如下<br>
     * 0x00：处理中；
     * 0x01：已处理完毕；
     * 0x02：不作处理；
     * 0x03：将来处理
     */
    private byte result;

    // 主动安全报警数据部分-开始
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
     * 报警处理方式， 1字节
     * <br>
     * 0x00：快速拍照
     * 0x01：语音下发
     * 0x02：不做处理
     * 0x03：其他
     */
    private byte method;

    /**
     * 报警处理人姓名长度，1字节
     */
    private byte operatorLength;

    /**
     * 报警处理人姓名
     */
    private String operator;

    /**
     * 报警处理人所属公司名称长度，1字节
     */
    private byte companyLength;

    /**
     * 报警处理人所属公司名称
     */
    private  String company;

    // 主动安全报警数据部分-结束

    @Override
    public String getSubTypeHex() {
        return "1403";
    }

    @Override
    public String getSubType() {
        return "UP_WARN_MSG_ADPT_TODO_INFO";
    }

}
