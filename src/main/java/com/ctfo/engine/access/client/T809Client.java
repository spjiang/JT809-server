/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.client;

import com.ctfo.engine.access.client.handler.ClientHandler;
import com.ctfo.engine.access.coder.T809CodecFactory;
import com.ctfo.engine.access.common.cache.LocalCache;
import com.ctfo.protocol.P809.P809Message;
import com.ctfo.protocol.P809.model.p2011.P0x9001;
import com.ctfo.protocol.P809.model.p2011.P0x9005;
import com.ctfo.protocol.P809.vo.PlatformAccessInfo;
import com.ctfo.vsme.engine.common.Tools;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * 从链路客户端
 *
 * @author liangyi
 * @date 2021/2/24 15:34
 */
@Slf4j
public class T809Client extends ClientAdapter {
    @Override
    public void init() throws Exception {
        connector = new NioSocketConnector();
        // 设置连接超时时间
        connector.setConnectTimeoutMillis(30000);
        // 创建接收数据的过滤器
        DefaultIoFilterChainBuilder chain = connector.getFilterChain();
        chain.addLast("codec", new ProtocolCodecFilter(new T809CodecFactory(charset)));
        // 设定服务器端的消息处理器
        connector.setHandler(new ClientHandler(this.getGnnsCenterId()));
        isInit = true;
        // 创建心跳线程
        new HeartbeatThread().start();
    }

    @Override
    public void send(Object obj) {
        if (isConnecting()) {
            IoSession session = future.getSession();
            session.write(obj);
        }
    }

    @Override
    public void execute() throws Exception {
        while (isRun()) {
            if (!isConnecting()) {
                // 获取下级平台配置信息
                PlatformAccessInfo platform = LocalCache.PLATFORM_CONFIG.get(String.valueOf(getGnnsCenterId()));
                platform.setDownSession(null);
                platform.setDownConnect(false);
                // 重新链接
                if (doConnect()) {
                    // 连接成功，发送登陆信息
                    P809Message msg = getLoginMsg(platform);
                    future.getSession().write(msg);
                }
            }
            try {
                // 5秒检查连接是否正常
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
            }
        }
    }

    private P809Message getLoginMsg(PlatformAccessInfo platform) {
        P0x9001 p = new P0x9001();
        p.setVerifyCode(platform.getVerifyCode());
        P809Message msg = new P809Message();
        msg.setMsgIdHex("9001");
        msg.setMsgGnnsCenterid(getGnnsCenterId());
        msg.setMsgSn(Tools.getJT809MsgSN());
        msg.setModel(p);
        return msg;
    }

    class HeartbeatThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(60 * 1000);
                    // 获取下级平台配置信息
                    PlatformAccessInfo platform = LocalCache.PLATFORM_CONFIG.get(String.valueOf(getGnnsCenterId()));
                    if (isConnecting() && platform.isDownConnect()) {
                        P809Message msg = getHeartbeat();
                        future.getSession().write(msg);
                    }
                } catch (Exception e) {
                    log.error("down link sending heartbeat error ", e);
                }
            }
        }

        private P809Message getHeartbeat() {
            P0x9005 p = new P0x9005();
            P809Message msg = new P809Message();
            msg.setMsgIdHex("9005");
            msg.setMsgGnnsCenterid(getGnnsCenterId());
            msg.setMsgSn(Tools.getJT809MsgSN());
            msg.setModel(p);
            return msg;
        }
    }
}
