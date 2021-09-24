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
 * 上报报警信息消息，主链路，UP_WARN_MSG_ADPT_INFO
 * <br>
 * 下级平台向上级平台上报某车辆的报警信息
 * 本条消息上级平台无需应答。
 *
 * @author liangyi
 * @date 2020/8/26 20:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x1402 extends PModel implements Serializable {

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
     * 驾驶员姓名长度，1字节
     */
    private byte driverLength;

    /**
     * 驾驶员姓名
     */
    private String driver;

    /**
     * 驾驶员驾照号码长度， 1字节
     */
    private byte driverNoLength;

    /**
     * 驾驶员驾照号码
     */
    private String driverNo;

    /**
     * 报警级别
     */
    private byte level;

    /**
     * 经度，4字节
     */
    private int lon;

    /**
     * 维度，4字节
     */
    private int lat;

    /**
     * 海拔高度,单位为米(m) 2字节
     */
    private short altitude;

    /**
     * 行车速度，单位为千米每小时(km/h) 2字节
     */
    private short vec1;

    /**
     * 行驶记录速度,单位为千米每小时(km/h)  2字节
     */
    private short vec2;

    /**
     * 报警状态<br>
     * 0:不可用
     * 1:报警开始;
     * 2:报警结束
     */
    private byte status;

    /**
     * 方向,0-359,正北为 0,顺时针,  2字节
     */
    private short direction;


    // 主动安全报警数据部分-结束

    /**
     * 信息ID，4字节
     */
    private long infoId;

    /**
     * 报警数据长度，最长1024字节，4字节
     */
    private int infoLength;

    /**
     * 上报报警信息内容
     */
    private String infoContent;

    @Override
    public String getSubTypeHex() {
        return "1402";
    }

    @Override
    public String getSubType() {
        return "UP_WARN_MSG_ADPT_INFO";
    }
}
