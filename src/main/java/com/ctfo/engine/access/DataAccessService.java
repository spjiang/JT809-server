/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access;

import com.ctfo.engine.access.common.ConfigLoader;
import com.ctfo.engine.access.sender.DataSender;
import com.ctfo.engine.access.server.T809Server;
import com.ctfo.vsme.engine.common.Tools;
import com.ctfo.vsme.engine.common.exception.ConfigLoadException;
import com.ctfo.vsme.engine.common.kafka.KFConsumer;
import com.ctfo.vsme.engine.common.kafka.KFProductor;
import com.ctfo.vsme.engine.common.redis.RedisCache;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 数据接入服务
 *
 * @author liangyi
 * @date 2021/3/3 19:16
 */
@Slf4j
public class DataAccessService {
    /** 可以换取当前系统的换行符 */
    private static final String N = System.getProperty("line.separator", "\n");
    private static boolean running = false;

    /** 系统字符串编码 */
    private static String charset = "GBK";

    /** 809协议开放端口 */
    private static int serverPort809 = 8810;

    /** 会话自动失效时间,单位:秒 */
    private static int sessionInvalid = 120;

    /** 系统版本 */
    private static String sysVersion = "DataAccessService-v1.0.0-20210303";

    /** 系统管理器监听绑定IP */
    private static String sysListenHost = "127.0.0.1";

    /** 系统管理器监听端口 */
    private static int sysLisenPort = 53011;

    // *********************************************** //
    /** Redis缓存对象 */
    private static RedisCache mcc;

    public static void main(String[] args) {
        DataAccessService service = new DataAccessService();
        service.loadConfigInfo(null);// 加载配置文件
        running = true;// 系统启动标识
        log.info("数据接入服务(" + sysVersion + ")启动...");
        service.start();
        System.out.println("===启动完成，详细信息请跟踪查看日志===");
        log.info("==============================启动完成===============================");
    }

    public void start() {
        try {
            // 创建缓存服务连接池管理器
            mcc = this.createMCCPool(ConfigLoader.CONFIG_REDISCONNECTION);
            // 创建数据生产者
            new KFProductor(ConfigLoader.getConfigKafkaProuctor());
            // 创建下行数据消费者
            new KFConsumer("dataSenderGroup", ConfigLoader.CONFIG_KF_TOPIC.get("jt809SendTopic"), DataSender.DATA, ConfigLoader.getConfigKafkaConsumer()).start();
            // 启动下行数据发送业务处理线程
            new DataSender("data-sender-thread").start();
            // 创建809服务端
            createT809Server();
        } catch (Exception e) {
            log.error("服务启动异常：\n" + Tools.getStackTrace(e));
            log.error("系统退出...");
            System.exit(0);
        }
    }


    /**
     * 加载系统配置信息
     *
     * @author 凉意 2018年5月14日 下午3:33:10
     * @param configFile
     */
    private void loadConfigInfo(String configFile) {
        try {
            ConfigLoader.loadLogback();
            ConfigLoader.load(configFile);
            // 加载系统参数
            Map<String, String> sycf = ConfigLoader.CONFIG_SYS_PARAM;
            charset = sycf.get("charset").toUpperCase();
            sysListenHost = sycf.get("managehost").toLowerCase();
            sysLisenPort = Integer.parseInt(sycf.get("manageport"));
            sysVersion = sycf.get("sysversion").toLowerCase();
            serverPort809 = Integer.parseInt(sycf.get("serverPort809"));
            sessionInvalid = Integer.parseInt(sycf.get("sessionInvalid"));
        } catch (ConfigLoadException e) {
            log.error("加载配置文件出错，程序退出:".concat(N).concat(Tools.getStackTrace(e)));
            System.exit(0);
        }
    }

    /**
     * 创建JT809服务
     *
     * @author 凉意 2018年5月16日 上午10:18:40
     * @throws Exception
     */
    private T809Server createT809Server() throws Exception {
        T809Server server = new T809Server();
        server.setCharset(charset);
        server.setSessionInvalid(sessionInvalid);
        server.setPort(serverPort809);
        server.bind();
        log.info("JT809服务创建完成！" + serverPort809);
        return server;
    }

    /**
     * 创建缓存服务客户端
     *
     * @author 凉意 2018年5月14日 下午3:29:56
     * @param config
     *            缓存服务客户端连接池配置信息
     * @return 缓存服务客户端连接池对象
     * @throws Exception
     *             创建缓存服务客户端连接池异常
     */
    private RedisCache createMCCPool(Map<String, String> config) throws Exception {
        RedisCache mcc = new RedisCache(config.get("masterhost"), Integer.valueOf(config.get("masterport")), config.get("auth"));
        mcc.setDatabaseid(Integer.parseInt(config.get("databaseid")));
        mcc.setSlaveof(config.get("slaveof"));// 从服务器地址，为空的话表示无从服务
        mcc.setMastername(config.get("mastername"));
        mcc.setMaxConnects(Integer.parseInt(config.get("maxconns")));// 最大连接数
        mcc.setMaxIdles(Integer.parseInt(config.get("maxidles")));// 最大空闲连接数
        mcc.setMinIdles(Integer.parseInt(config.get("minidles")));// 最小空闲连接数
        mcc.setWaitTime(Long.parseLong(config.get("waittime")));// 等待空闲连接时间
        mcc.setOptTimeout(Integer.parseInt(config.get("opratime")));// 最大操作时间
        mcc.setValidAble(Boolean.parseBoolean(config.get("validate")));// 获取连接时是否检查有效
        mcc.setReconnecOfErrCount(Integer.parseInt(config.get("reconncs")));// 异常重连次数
        mcc.setStatsLevel(config.get("statlevl"));
        mcc.build();
        log.info("内存缓存连接池创建完成！");
        return mcc;
    }
}
