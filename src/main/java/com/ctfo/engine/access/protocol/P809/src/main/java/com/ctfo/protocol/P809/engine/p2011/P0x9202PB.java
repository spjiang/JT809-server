/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x9202;
import com.ctfo.protocol.P809.vo.GnnsData;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 交换车辆定位信息消息,从链路，DOWN_EXG_MSG_CAR_LOCATION
 * <br>
 * 描述：上级平台在以下四种情况下通过该消息不间断向下级平台发送车辆定位信息。<br>
 * (1) 车辆跨域时，上级平台通过该消息不间断地向车辆进入区域所属的下级平台发送车辆定位信息，直到该车辆离开该区域；<br>
 * (2) 人工指定车辆定位信息交换时，上级平台通过该消息不间断地向指定交换对象下级平台发送车辆定位信息，直到人工指定交换车辆定位信息结束；<br>
 * (3) 下级平台向上级平台申请交换指定车辆定位信息成功后，上级平台通过该消息不间断地向交换对象下级平台发送车辆定位信息，直到申请交换指定车辆定位信息结束；<br>
 * (4) 应急状态监控车辆时，上级平台向车辆归属下级平台通过该消息不间断地发送车辆定位信息，实现车辆定位信息回传。<br>
 *
 * @author liangyi
 * @date 2021/2/22 17:13
 */
public class P0x9202PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x9202消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x9202 p = (P0x9202) model;
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
            throw new P809Exception("P0x9202编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 64 > data.length) {
            throw new P809Exception("P0x9202消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x9202 p = new P0x9202();
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
            byte[] gnnsDataByte = new byte[36];
            System.arraycopy(data, pos, gnnsDataByte, 0, 36);
            GnnsData gnnsData = new GnnsData();
            gnnsData.formBytes(gnnsDataByte);
            p.setGnnsData(gnnsData);
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x9202解码失败:", e);
        }
    }
}
