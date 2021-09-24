/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 协议实体基础类，所有协议实体都集成此类。
 *
 * @author liangyi
 * @date 2020/8/17 18:23
 */
@Data
@ToString
public abstract class PModel implements Serializable {

    /**
     * 数据库唯一标识
     */
    private String uuid;

    /**
     * 获取子业务类型
     *
     * @return 子业务类型， HEX
     */
    public abstract String getSubTypeHex();

    /**
     * 获取子业务类型
     *
     * @return 子业务类型
     */
    public abstract String getSubType();
}
