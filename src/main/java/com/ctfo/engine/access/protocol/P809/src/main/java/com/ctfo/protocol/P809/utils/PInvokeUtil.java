/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.utils;

import com.ctfo.protocol.P809.engine.IPassBody;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liangyi
 * @date 2021/2/24 10:17
 */
@Slf4j
public class PInvokeUtil {

    /**
     * 根据消息ID获取消息体编解码处理引擎（809）
     *
     * @param msgId                消息ID
     * @param protocolInnerVersion 协议版本
     * @return IPassBody
     * @author 凉意 2018年5月15日 下午4:46:15
     */
    public static IPassBody invoke809PB(String msgId, String protocolInnerVersion) {
        try {
            String className = "com.ctfo.protocol.P809.engine.p" +protocolInnerVersion+ ".P0x" + msgId + "PB";
            Class<?> classz = Class.forName(className);
            return (IPassBody) classz.newInstance();
        } catch (Exception e) {
            log.error("JT809协议获取编解码引擎失败,消息ID：" + msgId, e.getMessage());
        }
        return null;
    }

    /**
     * 根据消息ID获取消息体模型（809）
     *
     * @param msgId                消息ID
     * @param protocolInnerVersion 协议版本
     * @return IPassBody
     * @author 凉意 2018年5月15日 下午4:46:15
     */
    public static IPassBody invoke809(String msgId, String protocolInnerVersion) {
        try {
            String className = "com.ctfo.protocol.P809.engine.p" +protocolInnerVersion+ ".P0x" + msgId;
            Class<?> classz = Class.forName(className);
            return (IPassBody) classz.newInstance();
        } catch (Exception e) {
            log.error("JT809协议获取消息模型失败,消息ID：" + msgId, e.getMessage());
        }
        return null;
    }


}
