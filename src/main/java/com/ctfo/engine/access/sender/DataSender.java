/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.sender;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctfo.engine.access.common.cache.LocalCache;
import com.ctfo.protocol.P809.P809Message;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.vo.PlatformAccessInfo;
import com.ctfo.vsme.engine.common.Queue;
import com.ctfo.vsme.engine.common.thread.AbstractThread;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 下发数据业务处理类
 *
 * @author liangyi
 * @date 2021/3/4 16:45
 */
@Slf4j
public class DataSender extends AbstractThread {

    public static final Queue<String> DATA = new Queue<>("data-sender-queue");

    public DataSender(String name) {
        super(name);
    }

    @Override
    public void doHandle() {
        while (isRun) {
            if (DataSender.DATA.isEmpty()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.error(e.getLocalizedMessage(), e);
                }
                continue;
            }
            String data = DataSender.DATA.poll();
            if (StringUtils.isBlank(data)) continue;
            JSONObject obj = JSON.parseObject(data);
            String dataTypeId = obj.getString("dataTypeId");
            P809Message message = obj.toJavaObject(P809Message.class);
            try {
                message.setModel((PModel) JSON.parseObject(obj.getString("model"), Class.forName("com.ctfo.protocol.P809.model.p" + message.getProtocolInnerVersion() + ".P0x" + dataTypeId)));
                // 下级平台接入码
                String ac = String.valueOf(message.getMsgGnnsCenterid());
                // 获取下级平台链接管理对象
                PlatformAccessInfo platform = LocalCache.PLATFORM_CONFIG.get(ac);
                if (null == platform) continue;
                // 获取链路类型，判断是主链路 还是 从链路
                String link = LocalCache.PROTOCOL_MAP.get(dataTypeId);
                platform.send(message, link);
            } catch (Exception e) {
                log.error("sending data error. data={}", message.toString(), e);
            }
        }
    }
}
