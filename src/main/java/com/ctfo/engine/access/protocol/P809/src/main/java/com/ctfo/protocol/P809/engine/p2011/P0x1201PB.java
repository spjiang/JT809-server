/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1201;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 上传车辆注册信息消息，主链路，UP_EXG_MSG_REGISTER
 *
 * @author liangyi
 * @date 2021/1/11 19:56
 */
public class P0x1201PB implements IPassBody {

    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x1201消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x1201 p = (P0x1201) model;
            IoBuffer buf = IoBuffer.allocate(89);
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            byte[] platformId = p.getPlatformId().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] platformIdB = new byte[11];
            System.arraycopy(platformId, 0, platformIdB, 0, platformId.length);
            buf.put(platformIdB);
            byte[] producerId = p.getProducerId().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] producerIdB = new byte[11];
            System.arraycopy(producerId, 0, producerIdB, 0, producerId.length);
            buf.put(producerIdB);
            byte[] terminalModelType = p.getTerminalModelType().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] terminalModelTypeB = new byte[20];
            System.arraycopy(terminalModelType, 0, terminalModelTypeB, 0, terminalModelType.length);
            buf.put(terminalModelType);
            byte[] terminalId = p.getTerminalId().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] terminalIdB = new byte[7];
            System.arraycopy(terminalId, 0, terminalIdB, 0, terminalId.length);
            buf.put(terminalIdB);
            byte[] terminalSimCode = p.getTerminalSimCode().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] terminalSimCodeB = new byte[12];
            System.arraycopy(terminalSimCode, 12 - terminalSimCode.length, terminalSimCodeB, 0, terminalSimCode.length);
            buf.put(terminalSimCodeB);
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x1201编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 89 > data.length) {
            throw new P809Exception("P0x1201消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x1201 p = new P0x1201();
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
            byte[] platformIdByte = new byte[11];
            System.arraycopy(data, pos, platformIdByte, 0, 11);
            p.setPlatformId(ConvUtil.bytes2String(platformIdByte));
            pos += 11;
            byte[] producerIdByte = new byte[11];
            System.arraycopy(data, pos, producerIdByte, 0, 11);
            p.setProducerId(ConvUtil.bytes2String(producerIdByte));
            pos += 11;
            byte[] terminalModelTypeByte = new byte[20];
            System.arraycopy(data, pos, terminalModelTypeByte, 0, 20);
            p.setTerminalModelType(ConvUtil.bytes2String(terminalModelTypeByte));
            pos += 20;
            byte[] terminalIdByte = new byte[7];
            System.arraycopy(data, pos, terminalIdByte, 0, 7);
            p.setTerminalId(ConvUtil.bytes2String(terminalIdByte));
            pos += 7;
            byte[] terminalSimCodeByte = new byte[12];
            System.arraycopy(data, pos, terminalSimCodeByte, 0, 12);
            p.setTerminalSimCode(ConvUtil.bytes2String(terminalSimCodeByte));
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x1201解码失败:", e);
        }
    }
}
