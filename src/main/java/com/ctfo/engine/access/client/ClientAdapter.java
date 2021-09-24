/*
 *  @Copyright (c) 重庆交凯信息技术有限公司 L.P. All rights
 */
package com.ctfo.engine.access.client;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

/**
 * 数据共享客户端适配器
 *
 * @author 凉意 2018年11月12日 下午11:25:27
 * @version 1.0
 */
@Slf4j
@Data
public abstract class ClientAdapter extends Thread {

    private long gnnsCenterId;
    private String downLinkIp;
    private Integer downLinkPort;
    private long verifyCode;

    private boolean isRun = true;

    /**
     * NioSocketConnector
     */
    protected NioSocketConnector connector = null;
    /**
     * ConnectFuture
     */
    protected ConnectFuture future = null;
    /**
     * 编码
     */
    protected String charset = "GBK";
    /**
     * 客户端是否初始化标识，避免重复初始化客户端
     */
    protected boolean isInit = false;
    /**
     * 记录连接次数
     */
    protected long connectNum = 0;

    /**
     * 初始化客户端
     *
     * @throws Exception
     * @author 凉意 2018年6月13日 上午11:30:55
     */
    public abstract void init() throws Exception;

    /**
     * 数据发送
     *
     * @author 凉意 2018年11月13日 下午3:53:10
     */
    public abstract void send(Object obj);

    /**
     * 执行建立连接
     *
     * @throws Exception
     * @author 凉意 2018年11月12日 下午11:40:06
     */
    protected boolean doConnect() throws Exception {
        connectNum++;
        // 创建连接
        future = connector.connect(new InetSocketAddress(this.downLinkIp, this.downLinkPort));
        // 设置为同步等待连接成功。
        future.awaitUninterruptibly();
        // 验证是否建立连接成功。
        if (future.isConnected()) {
            log.info("connected 【" + gnnsCenterId + "】 successed, (connect count:" + connectNum + "):" + this.downLinkIp + ":" + this.downLinkPort);
            return true;
        }
        log.info("connected【" + gnnsCenterId + "】 failed, (connect count:" + connectNum + "):" + this.downLinkIp + ":" + this.downLinkPort);
        return false;
    }

    /**
     * 执行操作
     *
     * @throws Exception
     * @author 凉意 2018年11月13日 上午12:06:27
     */
    public abstract void execute() throws Exception;

    /**
     * @see Runnable#run()
     */
    @Override
    public void run() {
        try {
            execute();
        } catch (Exception e) {
            log.error(getName() + "执行异常：", e);
        }
    }

    /**
     * 检查连接是否有效
     *
     * @return true连接正常 false连接异常
     * @author 凉意 2018年11月12日 下午11:44:37
     */
    protected boolean isConnecting() {
        if (null == connector || null == future) {
            return false;
        }
        if (connector.isActive()) {
            return true;
        }
        return false;
    }


}
