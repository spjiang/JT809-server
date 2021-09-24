package com.ctfo.engine.access.server.handler;

import com.alibaba.fastjson.JSON;
import com.ctfo.engine.access.client.T809Client;
import com.ctfo.engine.access.common.ConfigLoader;
import com.ctfo.engine.access.common.cache.LocalCache;
import com.ctfo.protocol.P809.P809Message;
import com.ctfo.protocol.P809.model.p2011.P0x1001;
import com.ctfo.protocol.P809.model.p2011.P0x1002;
import com.ctfo.protocol.P809.model.p2011.P0x1006;
import com.ctfo.protocol.P809.vo.PlatformAccessInfo;
import com.ctfo.vsme.engine.common.Tools;
import com.ctfo.vsme.engine.common.kafka.KFProductor;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Random;


/**
 * 809协议收发处理适配器
 *
 * @author 凉意 2018年11月12日 下午9:57:38
 * @version 1.0
 */
@Slf4j
public class T809Handler extends IoHandlerAdapter {

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
        log.info("open connecting ip address {}.", session.getRemoteAddress());
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
        log.error(cause.getLocalizedMessage(), cause);
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
        String msgIdHex = obj.getMsgIdHex();
        if (log.isDebugEnabled()) {
            log.debug("JT809 UP {} RECV {} Packet: {}", obj.getMsgGnnsCenterid(), obj.getDataType(), obj.toString());
        }
        String dataTypeId = obj.getDataTypeId();
        if ("1202".equals(dataTypeId) || "1203".equals(dataTypeId)) {
            /**
             * 主业务 1200
             * 子业务
             *      1202 事实上传车辆定位信息
             *      1203 车辆定位信息自动补报
             */
            System.out.println("++++msgIdHex:" + msgIdHex + "+++++++" + "++++dataTypeId:" + dataTypeId + "+++++++");
            System.out.println("++++put-obj+++++++" + JSON.toJSONString(obj));
            System.out.println("++++put-CONFIG_KF_TOPIC+++++++" + ConfigLoader.CONFIG_KF_TOPIC.get("jt809PositionTopic"));
            KFProductor.put(ConfigLoader.CONFIG_KF_TOPIC.get("jt809PositionTopic"), JSON.toJSONString(obj));
        } else if ("1201".equals(dataTypeId)) {
            // 单独使用TOPIC
            /**
             * 主业务 1200
             * 子业务
             *      1201 上传车辆注册信息
             */
            KFProductor.put(ConfigLoader.CONFIG_KF_TOPIC.get("jt809RegisterTopic"), JSON.toJSONString(obj));
        } else if ("1402".equals(dataTypeId) || "1404".equals(dataTypeId) || "1407".equals(dataTypeId)) {
            // 主链路报警信息交互消息 UP_WARN_MSG: 1402 上报报警信息,
            KFProductor.put(ConfigLoader.CONFIG_KF_TOPIC.get("jt809AlarmTopic"), JSON.toJSONString(obj));
        } else if ("120E".equals(dataTypeId) || "120F".equals(dataTypeId)) {
            // 定制：大运会项目自己扩展，不是标准JTT809
            KFProductor.put(ConfigLoader.CONFIG_KF_TOPIC.get("jt809CanTopic"), JSON.toJSONString(obj));
        } else if ("1001".equals(msgIdHex)) { // 登陆请求
            handleP0x1001(obj, session);
        } else if ("1005".equals(msgIdHex)) { // 主链路连接保持请求消息
            // 应答1006
            P0x1006 p = new P0x1006();
            P809Message pmsg = new P809Message();
            pmsg.setMsgIdHex("1006");
            pmsg.setMsgGnnsCenterid(obj.getMsgGnnsCenterid());
            pmsg.setModel(p);
            pmsg.setMsgSn(Tools.getJT809MsgSN());
            session.write(pmsg);
        } else { // 其他数据交换业务放入kafka主题
            KFProductor.put(ConfigLoader.CONFIG_KF_TOPIC.get("jt809OtherTopic"), JSON.toJSONString(obj));
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
            log.debug("JT809 UP {} SEND {} Packet: {}", obj.getMsgGnnsCenterid(), obj.getDataType(), obj.toString());
        }
    }

    /**
     * 下级平台登录处理
     *
     * @param obj
     */
    private void handleP0x1001(P809Message obj, IoSession session) throws Exception {
        // 登陆信息校验，应答1002消息
        P0x1002 p = new P0x1002();
        P0x1001 up = (P0x1001) obj.getModel();
        String ac = String.valueOf(obj.getMsgGnnsCenterid()); // 接入码
        // 获取下级平台配置信息
        PlatformAccessInfo platform = LocalCache.PLATFORM_CONFIG.get(ac);
        if (null == platform) {
            p.setResult((byte) 2);
            log.error("gnnscenterid {} error", ac);
        } else {
            if (platform.getUserId() != up.getUserId()) {
                p.setResult((byte) 3);
                log.error("gnnscenterid {},user error {}", ac, up.getUserId());
            } else if (!platform.getPassword().equals(up.getPassword())) {
                p.setResult((byte) 4);
                log.error("gnnscenterid {},password error {}", ac, up.getPassword());
            } else {
                p.setResult((byte) 0);
            }
        }
        Random random = new Random();
        p.setVerifyCode(random.nextInt(Integer.MAX_VALUE));
        P809Message pmsg = new P809Message();
        pmsg.setMsgIdHex("1002");
        pmsg.setMsgGnnsCenterid(obj.getMsgGnnsCenterid());
        pmsg.setModel(p);
        pmsg.setMsgSn(Tools.getJT809MsgSN());
        session.write(pmsg);
        // 如果登录成功，记录相关信息
        if (0 == p.getResult()) {
            platform.setDownLinkIp(up.getDownLinkIp());
            platform.setDownLinkPort(up.getDownLinkPort());
            platform.setVerifyCode(p.getVerifyCode());
            platform.setUpSession(session);
            platform.setUpConnect(true);
            if (!platform.isCreateClient()) {
                // 建立从链路链接请求
                T809Client client = new T809Client();
                client.setGnnsCenterId(obj.getMsgGnnsCenterid());
                client.setDownLinkIp(up.getDownLinkIp());
                client.setDownLinkPort(up.getDownLinkPort());
                client.setVerifyCode(platform.getVerifyCode());
                client.init();
                client.start();
                platform.setCreateClient(true);
            }
        }
    }
}
