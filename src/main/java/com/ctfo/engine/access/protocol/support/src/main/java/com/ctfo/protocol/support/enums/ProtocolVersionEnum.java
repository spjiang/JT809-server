/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.support.src.main.java.com.ctfo.protocol.support.enums;

/**
 * 协议版本定义
 *
 * @author liangyi
 * @date 2020/8/17 17:52
 */
public enum ProtocolVersionEnum {

    P809_2011("P2011"),
    P809_2019("P2019");

    private String version;

    ProtocolVersionEnum(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
