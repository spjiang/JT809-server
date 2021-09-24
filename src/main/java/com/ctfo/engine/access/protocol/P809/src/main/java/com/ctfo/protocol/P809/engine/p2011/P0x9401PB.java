/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x9401;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 报警督办请求消息，从链路，DOWN_WARN_MSG_URGE_TODO_REQ
 * <br>
 * 上级平台向车辆归属下级平台下发本消息，催促其及时处理相关车辆的报警信息
 *
 * @author liangyi
 * @date 2021/2/23 9:24
 */
public class P0x9401PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x9401消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x9401 p = (P0x9401) model;
            IoBuffer buf = IoBuffer.allocate(120);
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.put(p.getWranSrc());
            buf.putShort(p.getWranType());
            buf.putLong(p.getWranTime());
            buf.putInt((int) p.getSupervisionId());
            buf.putLong(p.getSupervisionEndtime());
            buf.put(p.getSupervisionLevel());
            byte[] supervisor = ConvUtil.string2Bytes(p.getSupervisor());
            buf.put(supervisor);
            int size = 16 - supervisor.length;
            for (int i = 0; i < size; i++) {
                buf.put((byte) 0);
            }
            byte[] supervisorTel = ConvUtil.string2Bytes(p.getSupervisorTel());
            buf.put(supervisorTel);
            size = 20 - supervisorTel.length;
            for (int i = 0; i < size; i++) {
                buf.put((byte) 0);
            }
            byte[] supervisorEmail = ConvUtil.string2Bytes(p.getSupervisorEmail());
            buf.put(supervisorEmail);
            size = 32 - supervisorEmail.length;
            for (int i = 0; i < size; i++) {
                buf.put((byte) 0);
            }
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x9401编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 120 > data.length) {
            throw new P809Exception("P0x9401消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x9401 p = new P0x9401();
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
            p.setWranSrc(data[pos]);
            pos++;
            byte[] wranType = new byte[2];
            System.arraycopy(data, pos, wranType, 0, 2);
            p.setWranType(ConvUtil.bytes2Short(wranType));
            pos += 2;
            byte[] wranTime = new byte[8];
            System.arraycopy(data, pos, wranTime, 0, 8);
            p.setWranTime(ConvUtil.bytes2Long(wranTime));
            pos += 8;
            p.setSupervisionId(ConvUtil.bigBytes2Unsigned32Long(data, pos));
            pos += 4;
            byte[] supervisionEndtime = new byte[8];
            System.arraycopy(data, pos, supervisionEndtime, 0, 8);
            p.setSupervisionEndtime(ConvUtil.bytes2Long(supervisionEndtime));
            pos += 8;
            p.setSupervisionLevel(data[pos]);
            pos ++;
            byte[] supervisor = new byte[16];
            System.arraycopy(data, pos, supervisor, 0, 16);
            p.setSupervisor(ConvUtil.bytes2String(supervisor));
            pos += 16;
            byte[] supervisorTel = new byte[20];
            System.arraycopy(data, pos, supervisor, 0, 20);
            p.setSupervisorTel(ConvUtil.bytes2String(supervisorTel));
            pos += 20;
            byte[] supervisorEmail = new byte[32];
            System.arraycopy(data, pos, supervisorEmail, 0, 32);
            p.setSupervisorEmail(ConvUtil.bytes2String(supervisorEmail));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x9401解码失败:", e);
        }
    }
}
