/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x9203;
import com.ctfo.protocol.P809.vo.GnnsData;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆定位信息自动补报请求消息，从链路，DOWN_EXG_MSG_HISTORY_ARCOSSAREA
 * <br>
 * 本业务在DOWN_EXG_MSG_APPLY_HISGNSSDATA_ACK 应答成功后，立即开始交换。
 * 如果申请失败，则不进行数据转发
 * <br>
 * 本条消息下级平台无需应答。
 *
 * @author liangyi
 * @date 2021/2/22 17:13
 */
public class P0x9203PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x9203消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x9203 p = (P0x9203) model;
            IoBuffer buf = IoBuffer.allocate(64);
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.put(p.getGnnsCnt());
            List<GnnsData> gnnsDataList = p.getGnnsDatas();
            for (GnnsData gnns : gnnsDataList) {
                buf.put(gnns.toBytes());
            }
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x9203编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 64 > data.length) {
            throw new P809Exception("P0x9203消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x9203 p = new P0x9203();
            int pos = 0;
            byte[] vehicleNoByte = new byte[21];
            System.arraycopy(data, pos, vehicleNoByte, 0, 21);
            p.setVehicleNo(ConvUtil.bytes2String(vehicleNoByte));
            pos += 21;
            p.setVehicleColor(data[pos]);
            pos++;
            byte[] dataTypeByte = new byte[2];
            System.arraycopy(data, pos, dataTypeByte, 0, 2);
            p.setDataType(ConvUtil.bytes2Short(dataTypeByte));
            pos += 2;
            byte[] dataLengthByte = new byte[4];
            System.arraycopy(data, pos, dataLengthByte, 0, 4);
            p.setDataLength(ConvUtil.bytes2Int(dataLengthByte));
            pos += 4;
            p.setGnnsCnt(data[pos]);
            pos++;
            List<GnnsData> gnnsDatas = new ArrayList<>();
            for (int i = 0; i < p.getGnnsCnt(); i++) {
                byte[] gnnsDataByte = new byte[36];
                System.arraycopy(data, pos, gnnsDataByte, 0, 36);
                GnnsData gnnsData = new GnnsData();
                gnnsData.formBytes(gnnsDataByte);
                gnnsDatas.add(gnnsData);
                pos += 36;
            }
            p.setGnnsDatas(gnnsDatas);
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x9203解码失败:", e);
        }
    }
}
