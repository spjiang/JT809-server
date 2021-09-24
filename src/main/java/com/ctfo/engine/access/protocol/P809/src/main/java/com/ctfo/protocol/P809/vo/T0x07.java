/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 电动汽车报警数据格式定义，信息类型:0x07
 *
 * @author liangyi
 * @date 2021/3/22 11:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class T0x07 extends TBase implements Serializable {

    /**
     * 最高报警等级，1字节
     * <br>
     * 为当前发生的故障中的最高等级值,有效值范围:0~3,“0”表示无故障;“1”表示1级故障,指代不影响车辆正常行驶的故障;“2”表示2级故障,指代影响车辆性能,需驾驶员限制行驶的故障;“3”表示3级故障,为最高级别故障,指代驾驶员应立即停车处理或请求救援的故障;具体等级对应的故障内容由厂商自行定义;“0xFE”表示异常,“0xFF”表示无效
     */
    private byte alarmLevel;

    /**
     * 通用报警标志,4字节，通用报警标志位定义见表18
     * <br>
     *
     */
    private int alarmFlag;

    /**
     * 充电储能装置故障数，1字节
     */
    private byte chargeDeviceFaultCount;

    /**
     * 可充电储能装置故障代码列表，4*N字节
     */
    private List<Integer> chargeDeviceFaultCodes;

    /**
     * 驱动电机故障总数，1字节
     */
    private byte impetusFaultCount;

    /**
     * 驱动电机故障代码列表， 4*N字节
     */
    private List<Integer> impetusFaultCodes;

    /**
     * 发动机故障总数，1字节
     */
    private byte engineFaultCount;

    /**
     * 发动机故障列表， 4*N字节
     */
    private List<Integer> engineFaultCodes;

    /**
     * 其他故障总数，1字节
     */
    private byte otherFaultCount;

    /**
     * 其他故障代码列表，4*N字节
     */
    private List<Integer> otherFaultCodes;

    /**
     * 消息封包
     * @return 封包后的字节数组
     */
    public byte[] toBytes() {
        return null;
    }

    /**
     * 消息拆包
     *
     * @param data 待拆包的数据报文
     */
    public void formBytes(byte[] data) {

    }
}
