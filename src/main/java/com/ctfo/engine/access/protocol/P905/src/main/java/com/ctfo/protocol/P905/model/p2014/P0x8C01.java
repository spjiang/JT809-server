package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.model.p2014;

import com.ctfo.protocol.P905.model.PModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 平台下发热点区域空车率，侯客情况
 */
public class P0x8C01 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区域ID，4字节
     */
    private Integer areaId;

    /**
     * 数据计算时间，BDC[6]
     */
    private Date updateTime;

    /**
     * 区域内空车率,byte 。 百分比
     */
    private byte emptyVehRate;

    /**
     * 区域空车数，byte
     */
    private byte emptyVehNum;

    /**
     * 业务描述
     */
    private String desc;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public byte getEmptyVehRate() {
        return emptyVehRate;
    }

    public void setEmptyVehRate(byte emptyVehRate) {
        this.emptyVehRate = emptyVehRate;
    }

    public byte getEmptyVehNum() {
        return emptyVehNum;
    }

    public void setEmptyVehNum(byte emptyVehNum) {
        this.emptyVehNum = emptyVehNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "P0x8C01{" +
                "areaId=" + areaId +
                ", updateTime=" + updateTime +
                ", emptyVehRate=" + emptyVehRate +
                ", emptyVehNum=" + emptyVehNum +
                ", desc='" + desc + '\'' +
                '}';
    }
}
