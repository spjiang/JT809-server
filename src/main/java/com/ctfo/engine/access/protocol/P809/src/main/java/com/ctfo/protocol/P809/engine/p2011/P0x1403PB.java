/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.cst.P809Cst;
import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1403;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 主动上报报警处理结果信息消息, UP_WARN_MSG_ ADPT _TODO_INFO
 * <br>
 *下级平台向主动向上级平台上报报警处理结果。本条消息上级平台无需应答
 *
 * @author liangyi
 * @date 2021/2/22 14:54
 */
public class P0x1403PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x1403消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x1403 p = (P0x1403) model;
            IoBuffer buf;
            if (P809Cst.PARSER_ADAS) {
                buf = IoBuffer.allocate(48 + p.getOperatorLength() + p.getCompanyLength());
            } else {
                buf = IoBuffer.allocate(33);
            }
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            if (P809Cst.PARSER_ADAS) {
                buf.put(ConvUtil.hex2Bytes(p.getAlarmId())); // 报警ID
            } else {
                buf.putInt((int)p.getInfoId());
            }
            buf.put(p.getResult());
            if (P809Cst.PARSER_ADAS) {
                buf.put(p.getMethod());
                buf.put(p.getOperatorLength());
                buf.put(ConvUtil.string2Bytes(p.getOperator()));
                buf.put(p.getCompanyLength());
                buf.put(ConvUtil.string2Bytes(p.getCompany()));
            }
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x1403编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data ) {
            throw new P809Exception("P0x1403消息体解码失败，消息为空或者长度不足。");
        }
        if (P809Cst.PARSER_ADAS) {
            if (48 > data.length) {
                throw new P809Exception("P0x1403消息体解码失败，消息为空或者长度不足。");
            }
        } else {
            if (33 > data.length) {
                throw new P809Exception("P0x1403消息体解码失败，消息为空或者长度不足。");
            }
        }
        try {
            P0x1403 p = new P0x1403();
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
            if (P809Cst.PARSER_ADAS) {
                byte[] alarmId = new byte[16];
                System.arraycopy(data, pos, alarmId, 0, 16);
                p.setAlarmId(ConvUtil.bytes2Hex(alarmId));
                pos += 16;
            } else {
                p.setInfoId(ConvUtil.bigBytes2Unsigned32Long(data, pos));
                pos += 4;
            }
            p.setResult(data[pos]);
            pos ++;
            if (P809Cst.PARSER_ADAS) {
                p.setMethod(data[pos]);
                pos++;
                p.setOperatorLength(data[pos]);
                pos++;
                byte[] operator = new byte[p.getOperatorLength()];
                System.arraycopy(data, pos, operator, 0, p.getOperatorLength());
                p.setOperator(ConvUtil.bytes2String(operator));
                pos += p.getOperatorLength();
                p.setCompanyLength(data[pos]);
                pos++;
                byte[] company = new byte[p.getCompanyLength()];
                System.arraycopy(data, pos, company, 0, p.getCompanyLength());
                p.setCompany(ConvUtil.bytes2String(company));
            }
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x1403解码失败:", e);
        }
    }
}
