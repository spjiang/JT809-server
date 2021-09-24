/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x9101;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 接收车辆定位信息数量通知消息，从链路，DOWN_TOTAL_RECV_BACK_MSG
 * <br>
 * 消息方向：上级平台往下级平台
 * <br>
 * 上级平台向下级平台定量通知已经收到下级平台上传的车辆定位信息数量，本条消息不需要下级平台应答
 *
 * @author liangyi
 * @date 2021/2/22 17:06
 */
public class P0x9101PB implements IPassBody {

    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x9101消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x9101 p = (P0x9101) model;
            IoBuffer buf = IoBuffer.allocate(20);
            buf.putInt((int) p.getDynamicInfoTotal());
            buf.putLong(p.getStartTime());
            buf.putLong(p.getEndTime());
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x9101编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 20 > data.length) {
            throw new P809Exception("P0x9101消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x9101 p = new P0x9101();
            int pos = 0;
            p.setDynamicInfoTotal(ConvUtil.bigBytes2Unsigned32Long(data, pos));
            pos += 4;
            byte[] startTime = new byte[8];
            System.arraycopy(data, pos, startTime, 0, 8);
            p.setStartTime(ConvUtil.bytes2Long(startTime));
            pos += 8;
            byte[] endTime = new byte[8];
            System.arraycopy(data, pos, endTime, 0, 8);
            p.setEndTime(ConvUtil.bytes2Long(endTime));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x9101解码失败:", e);
        }
    }
}
