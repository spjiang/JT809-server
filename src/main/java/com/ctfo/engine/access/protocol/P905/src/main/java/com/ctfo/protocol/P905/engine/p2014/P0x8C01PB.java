
package com.ctfo.engine.access.protocol.P905.src.main.java.com.ctfo.protocol.P905.engine.p2014;

import com.ctfo.protocol.P905.engine.IPassBody;
import com.ctfo.protocol.P905.model.PModel;
import com.ctfo.protocol.P905.model.p2014.P0x8C01;
import com.ctfo.protocol.support.exception.P905Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 平台下发热点区域空车率，侯客情况
 */
public class P0x8C01PB implements IPassBody {

    @Override
    public byte[] bodyToBytes(PModel model) throws P905Exception {
        if (null == model) {
            throw new P905Exception("P0x8C01消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x8C01 p = (P0x8C01) model;
            IoBuffer buf = IoBuffer.allocate(12).setAutoExpand(true);
            buf.putInt(p.getAreaId());
            String date = DateUtil.formatToString(p.getUpdateTime(), "yyMMddHHmmss");
            buf.put(ConvUtil.bcd2Bytes(date));
            buf.put(p.getEmptyVehRate());
            buf.put(p.getEmptyVehNum());
            if (StringUtils.isNotBlank(p.getDesc())) {
                buf.put(ConvUtil.string2Bytes(p.getDesc()));
            }
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P905Exception("P0x8C01编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P905Exception {
        if (null == data || 12 > data.length) {
            throw new P905Exception("P0x8C01消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x8C01 p = new P0x8C01();
            int pos = 0;
            byte[] areaId = new byte[4];
            System.arraycopy(data, pos, areaId, 0, 4);
            p.setAreaId(ConvUtil.bytes2Int(areaId));
            pos += 4;
            byte[] updateTime = new byte[6];
            System.arraycopy(data, pos, updateTime, 0, 6);
            String dateStr = "20" + ConvUtil.bytes2Bcd(updateTime);
            p.setUpdateTime(DateUtil.parseToDate(dateStr, "yyyyMMddHHmmss"));
            pos += 6;
            p.setEmptyVehRate(data[pos]);
            pos++;
            p.setEmptyVehNum(data[pos]);
            pos++;
            byte[] desc = new byte[data.length - pos];
            if (0 < desc.length) {
                System.arraycopy(data, pos, desc, 0, desc.length);
                p.setDesc(ConvUtil.bytes2String(desc));
            }
            return p;
        } catch (Exception e) {
            throw new P905Exception("P0x8C01解码失败:", e);
        }
    }
}
