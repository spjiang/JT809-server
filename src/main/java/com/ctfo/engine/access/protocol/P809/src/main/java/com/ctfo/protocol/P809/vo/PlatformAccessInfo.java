/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.vo;

import com.ctfo.protocol.P809.P809Message;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.session.IoSession;

/**
 * 下级平台接入信息
 *
 * @author liangyi
 * @date 2021/2/24 11:38
 */
@Data
@ToString
@Slf4j
public class PlatformAccessInfo {

    /**
     * 用户名
     */
    private long userId;

    /**
     * 密码
     */
    private String password;

    /**
     * 接入码
     */
    private long gnnsCenterId;

    // 加密参数
    private long M1;
    private long IA1;
    private long IC1;

    // 从链路地址及端口
    private String downLinkIp;
    private int downLinkPort;

    // 从链路校验码, 主链路登录成功后，上级平台生成并应答给下级平台。 从链路建立链接时需要带上这个校验码
    private long verifyCode;

    // 主链路会话
    private IoSession upSession;
    private boolean upConnect = false;
    // 从链路会话
    private IoSession downSession;
    private boolean downConnect = false;
    private boolean isCreateClient = false;

    /**
     * 数据发送
     *
     * @param message 消息
     * @param link    链路类型
     * @throws Exception 运行时异常
     */
    public void send(P809Message message, String link) throws Exception {
        if ("UP".equals(link)) {// 主链路
            if (null == this.upSession) {
                log.warn("UP Link is not exist");
                return;
            }
            if (!this.upSession.isConnected()) {
                log.warn("UP Link is not connected");
                return;
            }
            this.upSession.write(message);
        } else if ("DOWN".equals(link)) {// 从链路
            if (null == this.downSession) {
                log.warn("DOWN Link is not exist");
                return;
            }
            if (!this.downSession.isConnected()) {
                log.warn("DOWN Link is not connected");
                return;
            }
            this.downSession.write(message);
        }
    }
}
