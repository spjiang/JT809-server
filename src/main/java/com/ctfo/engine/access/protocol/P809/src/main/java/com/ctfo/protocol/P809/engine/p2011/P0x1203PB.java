package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1203;
import com.ctfo.protocol.P809.vo.GnnsData;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆定位信息自动补报请求消息，主链路，UP_EXG_MSG_HISTORY_LOCATION
 * <br>
 * 如果平台间传输链路中断，下级平台重新登录并与上级平台建立通信链路后，下级平台
 * 应将中断期间内车载终端上传的车辆定位信息自动补报到上级平台。如果系统断线期间，该车需发
 * 送的数据包条数大于5，则以每包五条进行补发，直到补发完毕。多条数据以卫星定位时间先后顺
 * 序排列。本条消息上级平台采用定量回复，即收到一定数量的数据后，即通过从链路应答数据量。
 *
 * @author liangyi
 * @date 2020/11/3 15:15
 */
public class P0x1203PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x1203消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x1203 p = (P0x1203) model;
            IoBuffer buf = IoBuffer.allocate(64).setAutoExpand(true);
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.put(p.getGnnsCnt());
            List<GnnsData> gnnsDataList = p.getGnnsDatas();
            if (null != gnnsDataList && 0 < gnnsDataList.size()) {
                for (GnnsData gd : gnnsDataList) {
                    buf.put(gd.toBytes());
                }
            }
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x1203编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 64 > data.length) {
            throw new P809Exception("P0x1203消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x1203 p = new P0x1203();
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
            byte[] dataLengthbyte = new byte[4];
            System.arraycopy(data, pos, dataLengthbyte, 0, 4);
            p.setDataLength(ConvUtil.bytes2Int(dataLengthbyte));
            pos += 4;
            p.setGnnsCnt(data[pos]);
            pos++;
            List<GnnsData> gnnsDataList = new ArrayList<>();
            for (int i = 0; i < p.getGnnsCnt(); i++) {
                byte[] gnnsByte = new byte[36];
                System.arraycopy(data, pos, gnnsByte, 0, 36);
                GnnsData gnnsData = new GnnsData();
                gnnsData.formBytes(gnnsByte);
                pos += 36;
                gnnsDataList.add(gnnsData);
            }
            p.setGnnsDatas(gnnsDataList);
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x1203解码失败:", e);
        }
    }
}
