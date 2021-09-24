/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x9505;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 上报车辆行驶记录请求消息， DOWN_CTRL_MSG_TAKE_TRAVEL_REQ
 * <br>
 *  上级平台向下级平台下发上报车辆行驶记录请求消息
 *
 * @author liangyi
 * @date 2021/2/23 10:13
 */
public class P0x9505PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x9505消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x9505 p = (P0x9505) model;
            IoBuffer buf = IoBuffer.allocate(173);
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            byte[] authenticationCode = ConvUtil.string2Bytes(p.getAuthenticationCode());
            buf.put(authenticationCode);
            int size = 10 - authenticationCode.length;
            for (int i = 0; i < size; i++) {
                buf.put((byte)0);
            }
            byte[] accessPointName = ConvUtil.string2Bytes(p.getAccessPointName());
            buf.put(accessPointName);
            size = 20 - accessPointName.length;
            for (int i = 0; i < size; i++) {
                buf.put((byte)0);
            }
            byte[] username = ConvUtil.string2Bytes(p.getUsername());
            buf.put(username);
            size = 49 - username.length;
            for (int i = 0; i < size; i++) {
                buf.put((byte)0);
            }
            byte[] password = ConvUtil.string2Bytes(p.getPassword());
            buf.put(password);
            size = 22 - password.length;
            for (int i = 0; i < size; i++) {
                buf.put((byte)0);
            }
            byte[] serverIp = ConvUtil.string2Bytes(p.getServerIp());
            buf.put(serverIp);
            size = 32 - serverIp.length;
            for (int i = 0; i < size; i++) {
                buf.put((byte)0);
            }
            buf.putShort((short) p.getTcpPort());
            buf.putShort((short) p.getUdpPort());
            buf.putLong(p.getEndTime());
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x9505编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 173 > data.length) {
            throw new P809Exception("P0x9505消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x9505 p = new P0x9505();
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
            byte[] authenticationCode = new byte[10];
            System.arraycopy(data, pos, authenticationCode, 0, 10);
            p.setAuthenticationCode(ConvUtil.bytes2String(authenticationCode));
            pos += 10;
            byte[] accessPointName = new byte[20];
            System.arraycopy(data, pos, accessPointName, 0, 20);
            p.setAccessPointName(ConvUtil.bytes2String(accessPointName));
            pos += 20;
            byte[] username = new byte[49];
            System.arraycopy(data, pos, username, 0, 49);
            p.setUsername(ConvUtil.bytes2String(username));
            pos += 49;
            byte[] password = new byte[22];
            System.arraycopy(data, pos, password, 0, 22);
            p.setPassword(ConvUtil.bytes2String(password));
            pos += 22;
            byte[] serverIp = new byte[32];
            System.arraycopy(data, pos, serverIp, 0, 32);
            p.setServerIp(ConvUtil.bytes2String(serverIp));
            pos += 32;
            p.setTcpPort(ConvUtil.bigBytes2Unsigned16Int(data, pos));
            pos += 2;
            p.setUdpPort(ConvUtil.bigBytes2Unsigned16Int(data, pos));
            pos += 2;
            byte[] endTime = new byte[8];
            System.arraycopy(data, pos, endTime, 0, 8);
            p.setEndTime(ConvUtil.bytes2Long(endTime));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x9505解码失败:", e);
        }
    }
}
