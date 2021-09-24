/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x120A;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 上报驾驶员身份识别信息应答消息，主链路，UP_EXG_MSG_REPORT_DRIVER_INFO_ACK
 * <br>
 * 下级平台应答上级平台发送的上报驾驶员身份识别信息请求消息，上传指定车辆的驾驶员身份识别信息数据
 *
 * @author liangyi
 * @date 2021/1/11 17:12
 */
public class P0x120APB implements IPassBody {

    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x120A消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x120A p = (P0x120A) model;
            IoBuffer buf = IoBuffer.allocate(304).setAutoExpand(true);
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            byte[] driverName = p.getDriverName().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] driverNameB = new byte[16];
            System.arraycopy(driverName, 0, driverNameB, 0, driverName.length);
            buf.put(driverNameB);
            byte[] driverId = p.getDriverId().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] driverIdB = new byte[20];
            System.arraycopy(driverId, 0, driverIdB, 0, driverId.length);
            buf.put(driverIdB);
            if (null != p.getLicence() && !"".equals(p.getLicence())) {
                byte[] licence = p.getLicence().getBytes(CharsetConst.CHARTSET_GBK);
                byte[] licenceB = new byte[40];
                System.arraycopy(licence, 0, licenceB, 0, licence.length);
                buf.put(licenceB);
            }
            if (null != p.getOrgName() && !"".equals(p.getOrgName())) {
                byte[] orgName = p.getOrgName().getBytes(CharsetConst.CHARTSET_GBK);
                byte[] orgNameB = new byte[200];
                System.arraycopy(orgName, 0, orgNameB, 0, orgName.length);
                buf.put(orgNameB);
            }
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x120A编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 64 > data.length) {
            throw new P809Exception("P0x120A消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x120A p = new P0x120A();
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
            byte[] driverNameByte = new byte[16];
            System.arraycopy(data, pos, driverNameByte, 0, 16);
            p.setDriverName(ConvUtil.bytes2String(driverNameByte));
            pos += 16;
            byte[] driverIdByte = new byte[20];
            System.arraycopy(data, pos, driverIdByte, 0, 20);
            p.setDriverId(ConvUtil.bytes2String(driverIdByte));
            pos += 20;
            if (data.length > pos) {
                byte[] licenceByte = new byte[40];
                System.arraycopy(data, pos, licenceByte, 0, 40);
                p.setLicence(ConvUtil.bytes2String(licenceByte));
                pos += 40;
            }
            if (data.length > pos) {
                byte[] orgNameByte = new byte[200];
                System.arraycopy(data, pos, orgNameByte, 0, 200);
                p.setOrgName(ConvUtil.bytes2String(orgNameByte));
            }
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x120A解码失败:", e);
        }
    }
}
