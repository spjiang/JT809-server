/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1502;
import com.ctfo.protocol.P809.vo.GnnsData;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 车辆拍照应答消息,主链路，UP_CTRL_MSG_TAKE_PHOTO_ACK
 * <br>
 * 下级平台应答上级平台发送的车辆拍照请求消息，上传图片信息到上级平台
 *
 * @author liangyi
 * @date 2021/2/22 15:07
 */
public class P0x1502PB implements IPassBody {

    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x1502消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x1502 p = (P0x1502) model;
            IoBuffer buf = IoBuffer.allocate(72 + p.getPhotoLen());
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.put(p.getPhotoRspFlag());
            buf.put(p.getGnnsData().toBytes());
            buf.put(p.getLensId());
            buf.putInt(p.getPhotoLen());
            buf.put(p.getSizeType());
            buf.put(p.getType());
            buf.put(ConvUtil.hex2Bytes(p.getPhoto()));
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x1502编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 72 > data.length) {
            throw new P809Exception("P0x1502消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x1502 p = new P0x1502();
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
            p.setPhotoRspFlag(data[pos]);
            pos++;
            byte[] gnnsDataByte = new byte[36];
            System.arraycopy(data, pos, gnnsDataByte, 0, 36);
            GnnsData gnnsData = new GnnsData();
            gnnsData.formBytes(gnnsDataByte);
            p.setGnnsData(gnnsData);
            pos += 36;
            p.setLensId(data[pos]);
            pos++;
            p.setSizeType(data[pos]);
            pos++;
            p.setType(data[pos]);
            pos++;
            byte[] photoByte = new byte[p.getPhotoLen()];
            System.arraycopy(data, pos, photoByte, 0, p.getPhotoLen());
            p.setPhoto(ConvUtil.bytes2Hex(photoByte));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x1502解码失败:", e);
        }
    }
}
