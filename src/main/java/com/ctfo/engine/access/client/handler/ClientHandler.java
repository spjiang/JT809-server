/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.client.handler;

import com.ctfo.engine.access.common.cache.LocalCache;
import com.ctfo.protocol.P809.P809Message;
import com.ctfo.protocol.P809.model.p2011.P0x9002;
import com.ctfo.protocol.P809.vo.PlatformAccessInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * TODO
 *
 * @author liangyi
 * @date 2020/12/28 11:45
 */
@Slf4j
public class ClientHandler extends IoHandlerAdapter {

    private long gnnsCenterId;

    public ClientHandler(long gnnsCenterId) {
        this.gnnsCenterId = gnnsCenterId;
    }

    /**
     * @see IoHandlerAdapter#sessionCreated(IoSession)
     */
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }

    /**
     * @see IoHandlerAdapter#sessionOpened(IoSession)
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
    }

    /**
     * @see IoHandlerAdapter#sessionClosed(IoSession)
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {

    }

    /**
     * @see IoHandlerAdapter#sessionIdle(IoSession,
     * IdleStatus)
     */
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        session.close(true);
    }

    /**
     * @see IoHandlerAdapter#exceptionCaught(IoSession,
     * Throwable)
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        session.close(true);
    }

    /**
     * @see IoHandlerAdapter#messageReceived(IoSession,
     * Object)
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        if (null == message) {
            return;
        }
        P809Message obj = (P809Message) message;
        if (log.isDebugEnabled()) {
            log.debug("JT809 DOWN {} RECV {} Packet: {}", obj.getMsgGnnsCenterid(), obj.getDataType(), obj.toString());
        }
        String msgIdHex = obj.getMsgIdHex();
        if ("9002".equals(msgIdHex)) {// 从链路连接应答消息
            handleP0x9002(obj, session);
        } else if ("9006".equals(msgIdHex)) { // 从链路连接保持请求应答，不处理

        }
    }

    /**
     * @see IoHandlerAdapter#messageSent(IoSession,
     * Object)
     */
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        if (null == message) {
            return;
        }
        P809Message obj = (P809Message) message;
        if (log.isDebugEnabled()) {
            log.debug("JT809 DOWN {} SEND {} Packet: {}", obj.getMsgGnnsCenterid(), obj.getDataType(), obj.toString());
        }
    }

    /**
     * 处理从链路连接应答消息
     *
     * @param obj     消息对象
     * @param session 从链路链接会话
     */
    private void handleP0x9002(P809Message obj, IoSession session) {
        P0x9002 up = (P0x9002) obj.getModel();
        byte result = up.getResult();
        // 获取下级平台配置信息
        PlatformAccessInfo platform = LocalCache.PLATFORM_CONFIG.get(String.valueOf(obj.getMsgGnnsCenterid()));
        if (0 == result) {
            // 链接成功，保存会话
            platform.setDownSession(session);
            platform.setDownConnect(true);
            log.info("gnnscenterid {},down link connected success", obj.getMsgGnnsCenterid());
        } else {
            log.error("gnnscenterid {},down link connected fail,reason： {}", obj.getMsgGnnsCenterid(), result);
            platform.setDownSession(null);
            platform.setDownConnect(false);
            session.close(true);
        }
    }
}
