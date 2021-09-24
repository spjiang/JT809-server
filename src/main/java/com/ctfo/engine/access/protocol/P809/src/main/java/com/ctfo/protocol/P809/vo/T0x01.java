/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 电动汽车整车数据格式定义，信息类型:0x01
 *
 * @author liangyi
 * @date 2021/3/22 10:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class T0x01 extends TBase implements Serializable {

    /**
     * 车辆状态，1字节
     * <br>
     * 0x01:车辆启动状态;0x02:熄火;0x03:其他状态;“0xFE”表示异常,“0xFF”表示无效
     */
    private byte vehicleState;

    /**
     * 充电状态，1字节
     * <br>
     * 0x01:停车充电;0x02:行驶充电;0x03:未充电状态;0x04:充电完成;“0xFE”表示异常,“0xFF”表示无效
     */
    private byte chargeState;

    /**
     * 运行模式，1字节
     * <br>
     * 0x01:纯电;0x02:混动;0x03:燃油;0xFE表示异常;0xFF表示无效
     */
    private byte runModel;

    /**
     * 车速，2字节
     * <br>
     * 有效值范围:0~2200(表示0km/h~220km/h),最小计量单元:0.1km/h,“0xFF,0xFE”表示异常,“0xFF,0xFF”表示无效
     */
    private short speed;

    /**
     * 累计里程，4字节
     * <br>
     * 有效值范围:0~9999999(表示0km~999999.9km),最小计量单元:0.1km。“0xFF,0xFF,0xFF,0xFE”表示异常,“0xFF,0xFF,0xFF,0xFF”表示无效
     */
    private int accumulatedMileage;

    /**
     * 总电压，2字节
     * <br>
     * 有效值范围:0~10000(表示0V~1000V),最小计量单元:0.1V,“0xFF,0xFE”表示异常,“0xFF,0xFF”表示无效
     */
    private short totalVoltage;

    /**
     * 总电流
     * <br>
     * 有效值范围:0~20000(偏移量1000A,表示-1000A~+1000A),最小计量单元:0.1A,“0xFF,0xFE”表示异常,“0xFF,0xFF”表示无效
     */
    private short totalElectricCurrent;

    /**
     * SOC,1字节
     * <br>
     * 有效值范围:0~100(表示0%~100%),最小计量单元:1%,“0xFE”表示异常,“0xFF”表示无效
     */
    private byte soc;

    /**
     * DC-DC状态，1字节
     * <br>
     * 0x01:工作;0x02:断开,“0xFE”表示异常,“0xFF”表示无效
     */
    private byte dcdcState;

    /**
     * 挡位，1字节, 参考标准附录A。1
     * <br>
     * 0-3位表示挡位<br>
     * 4位：1有制动力 0无制动力
     * 5位：1有驱动力 0无驱动力
     * 6、7位：预留
     */
    private byte gearByte;
    // 挡位
    private String gear;
    // 是否有制动力
    private boolean isBrakingForce;
    // 是否有驱动力
    private boolean isImpetus;

    /**
     * 绝缘电阻，2字节
     * <br>
     * 有效范围0~60000(表示0kΩ~60000kΩ),最小计量单元:1kΩ
     */
    private short insulationResistance;

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
