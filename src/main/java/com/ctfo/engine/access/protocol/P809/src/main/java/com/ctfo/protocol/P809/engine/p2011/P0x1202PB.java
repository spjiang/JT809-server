/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1202;
import com.ctfo.protocol.P809.vo.GnnsData;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 实时上传车辆定位信息消息，主链路，UP_EXG_MSG_REAL_LOCATION
 * <br>
 * 本条消息服务端无需应答
 *
 * @author liangyi
 * @date 2021/1/11 17:42
 */
public class P0x1202PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x1202消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x1202 p = (P0x1202) model;
            IoBuffer buf = IoBuffer.allocate(64);
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.put(p.getGnnsData().toBytes());
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x1202编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 64 > data.length) {
            throw new P809Exception("P0x1202消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x1202 p = new P0x1202();
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
            byte[] gnnsByte = new byte[data.length - pos];
            System.arraycopy(data, pos, gnnsByte, 0, gnnsByte.length);
            GnnsData gnnsData = new GnnsData();
            gnnsData.formBytes(gnnsByte);
            p.setGnnsData(gnnsData);
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x1202解码失败:", e);
        }
    }
}
