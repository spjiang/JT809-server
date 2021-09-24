/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.cst;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author liangyi
 * @date 2021/1/12 11:00
 */
public class P809Cst {

    public static boolean PARSER_ADAS = true;

    public static final Map<String, String> P809_2011_DATA_TYPE = new HashMap<>();
    public static final Map<String, String> P809_2019_DATA_TYPE = new HashMap<>();
    static {
        P809_2011_DATA_TYPE.put("1001","UP_CONNECT_REQ");
        P809_2011_DATA_TYPE.put("1002","UP_CONNECT_RSP");
        P809_2011_DATA_TYPE.put("1003","UP_DISCONNECT_REQ");
        P809_2011_DATA_TYPE.put("1004","UP_DISCONNECT_RSP");
        P809_2011_DATA_TYPE.put("1005","UP_LINKTEST_REQ");
        P809_2011_DATA_TYPE.put("1006","UP_LINKTEST_RSP");
        P809_2011_DATA_TYPE.put("1007","UP_DISCONNECT_INFORM");
        P809_2011_DATA_TYPE.put("1008","UP_CLOSELINK_INFORM");
        P809_2011_DATA_TYPE.put("9001","DOWN_CONNECT_REQ");
        P809_2011_DATA_TYPE.put("9002","DOWN_CONNECT_RSP");
        P809_2011_DATA_TYPE.put("9003","DOWN_DISCONNECT_REQ");
        P809_2011_DATA_TYPE.put("9004","DOWN_DISCONNECT_RSP");
        P809_2011_DATA_TYPE.put("9005","DOWN_LINKTEST_REQ");
        P809_2011_DATA_TYPE.put("9006","DOWN_LINKTEST_RSP");
        P809_2011_DATA_TYPE.put("9007","DOWN_DISCONNECT_INFORM");
        P809_2011_DATA_TYPE.put("9008","DOWN_CLOSELINK_INFORM");
        P809_2011_DATA_TYPE.put("9101","DOWN_TOTAL_RECV_BACK_MSG");
        P809_2011_DATA_TYPE.put("1200","UP_EXG_MSG");
        P809_2011_DATA_TYPE.put("9200","DOWN_EXG_MSG");
        P809_2011_DATA_TYPE.put("1300","UP_PLATFORM_MSG");
        P809_2011_DATA_TYPE.put("9300","DOWN_PLATFORM_MSG");
        P809_2011_DATA_TYPE.put("1400","UP_WARN_MSG");
        P809_2011_DATA_TYPE.put("9400","DOWN_WARN_MSG");
        P809_2011_DATA_TYPE.put("1500","UP_CTRL_MSG");
        P809_2011_DATA_TYPE.put("9500","DOWN_CTRL_MSG");
        P809_2011_DATA_TYPE.put("1600","UP_BASE_MSG");
        P809_2011_DATA_TYPE.put("9600","DOWN_BASE_MSG");
    }
}
