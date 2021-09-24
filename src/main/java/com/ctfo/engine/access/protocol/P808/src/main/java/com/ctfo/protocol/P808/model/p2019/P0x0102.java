/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P808.src.main.java.com.ctfo.protocol.P808.model.p2019;

import com.ctfo.protocol.P808.model.PModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 终端鉴权消息体数据格式
 *
 * @author liangyi
 * @date 2020/8/17 15:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class P0x0102 extends PModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 鉴权码长度
     */
    private byte authCodeLen;

    /**
     * 鉴权码
     */
    private String authCode;

    /**
     * 终端IMEI， 15字节
     */
    private String imei;

    /**
     * 软件版本号，20字节
     */
    private String softwareVersion;
}
