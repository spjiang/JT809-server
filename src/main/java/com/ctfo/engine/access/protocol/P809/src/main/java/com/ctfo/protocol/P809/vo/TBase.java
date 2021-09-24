/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.vo;

import lombok.Data;

/**
 * TODO
 *
 * @author liangyi
 * @date 2021/3/22 10:49
 */
@Data
public class TBase {

    /**
     * 信息类型，0x01:整车数据  0x07:报警数据
     */
    private byte infoType;
}
