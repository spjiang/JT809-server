/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.common.cache;

import com.ctfo.protocol.P809.vo.PlatformAccessInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地缓存
 *
 * @author liangyi
 * @date 2021/2/24 14:54
 */
public class LocalCache {

    /** 下级平台信息配置 */
    public static final Map<String, PlatformAccessInfo> PLATFORM_CONFIG = new ConcurrentHashMap<>();

    /** 协议ID，主从链路对应缓存 */
    public static final Map<String, String> PROTOCOL_MAP = new ConcurrentHashMap<>();

    static {
        PROTOCOL_MAP.put("1001","UP");
        PROTOCOL_MAP.put("1002","UP");
        PROTOCOL_MAP.put("1003","UP");
        PROTOCOL_MAP.put("1004","UP");
        PROTOCOL_MAP.put("1005","UP");
        PROTOCOL_MAP.put("1006","UP");
        PROTOCOL_MAP.put("1007","DOWN");
        PROTOCOL_MAP.put("1008","DOWN");
        PROTOCOL_MAP.put("9001","DOWN");
        PROTOCOL_MAP.put("9002","DOWN");
        PROTOCOL_MAP.put("9003","DOWN");
        PROTOCOL_MAP.put("9004","DOWN");
        PROTOCOL_MAP.put("9005","DOWN");
        PROTOCOL_MAP.put("9006","DOWN");
        PROTOCOL_MAP.put("9007","UP");
        PROTOCOL_MAP.put("9008","UP");
        PROTOCOL_MAP.put("9101","DOWN");
        PROTOCOL_MAP.put("1200","UP");
        PROTOCOL_MAP.put("9200","DOWN");
        PROTOCOL_MAP.put("1300","UP");
        PROTOCOL_MAP.put("9300","DOWN");
        PROTOCOL_MAP.put("1400","UP");
        PROTOCOL_MAP.put("9400","DOWN");
        PROTOCOL_MAP.put("1500","UP");
        PROTOCOL_MAP.put("9500","DOWN");
        PROTOCOL_MAP.put("1600","UP");
        PROTOCOL_MAP.put("9600","DOWN");
        PROTOCOL_MAP.put("1201","UP");
        PROTOCOL_MAP.put("1202","UP");
        PROTOCOL_MAP.put("1203","UP");
        PROTOCOL_MAP.put("1205","UP");
        PROTOCOL_MAP.put("1206","UP");
        PROTOCOL_MAP.put("1207","UP");
        PROTOCOL_MAP.put("1208","UP");
        PROTOCOL_MAP.put("1209","UP");
        PROTOCOL_MAP.put("120A","UP");
        PROTOCOL_MAP.put("120B","UP");
        PROTOCOL_MAP.put("120C","UP");
        PROTOCOL_MAP.put("120D","UP");
        PROTOCOL_MAP.put("9202","DOWN");
        PROTOCOL_MAP.put("9203","DOWN");
        PROTOCOL_MAP.put("9204","DOWN");
        PROTOCOL_MAP.put("9205","DOWN");
        PROTOCOL_MAP.put("9206","DOWN");
        PROTOCOL_MAP.put("9207","DOWN");
        PROTOCOL_MAP.put("9208","DOWN");
        PROTOCOL_MAP.put("9209","DOWN");
        PROTOCOL_MAP.put("920A","DOWN");
        PROTOCOL_MAP.put("920B","DOWN");
        PROTOCOL_MAP.put("1301","UP");
        PROTOCOL_MAP.put("1302","UP");
        PROTOCOL_MAP.put("9301","DOWN");
        PROTOCOL_MAP.put("9302","DOWN");
        PROTOCOL_MAP.put("1401","UP");
        PROTOCOL_MAP.put("1402","UP");
        PROTOCOL_MAP.put("1403","UP");
        PROTOCOL_MAP.put("1404","UP");
        PROTOCOL_MAP.put("1407","UP");
        PROTOCOL_MAP.put("9401","DOWN");
        PROTOCOL_MAP.put("9402","DOWN");
        PROTOCOL_MAP.put("9403","DOWN");
        PROTOCOL_MAP.put("9404","DOWN");
        PROTOCOL_MAP.put("1501","UP");
        PROTOCOL_MAP.put("1502","UP");
        PROTOCOL_MAP.put("1503","UP");
        PROTOCOL_MAP.put("1504","UP");
        PROTOCOL_MAP.put("1505","UP");
        PROTOCOL_MAP.put("9501","DOWN");
        PROTOCOL_MAP.put("9502","DOWN");
        PROTOCOL_MAP.put("9503","DOWN");
        PROTOCOL_MAP.put("9504","DOWN");
        PROTOCOL_MAP.put("9505","DOWN");
        PROTOCOL_MAP.put("1601","UP");
        PROTOCOL_MAP.put("9601","DOWN");
    }
}
