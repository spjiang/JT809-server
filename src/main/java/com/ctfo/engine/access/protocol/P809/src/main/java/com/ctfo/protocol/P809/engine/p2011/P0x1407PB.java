/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.engine.p2011;

import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.model.p2011.P0x1404;
import com.ctfo.protocol.P809.model.p2011.P0x1407;
import com.ctfo.protocol.P809.vo.AlarmFileInfo;
import com.ctfo.protocol.support.constants.CharsetConst;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 报警附件主动上报,主链路，UP_WARN_MSG_FILELIST_AUTO
 * <br>
 * 下级平台向上级平台主动发送报警附件目录，上级平台可通过报警附件文件 URL 以 HTTP 协议直接访问附件文件或者下载附件文件
 *
 * @author liangyi
 * @date 2021/2/23 16:13
 */
public class P0x1407PB implements IPassBody {
    @Override
    public byte[] bodyToBytes(PModel model) throws P809Exception {
        if (null == model) {
            throw new P809Exception("P0x1407消息体编码失败，传入消息体实体对象为空。");
        }
        try {
            P0x1407 p = (P0x1407) model;
            IoBuffer buf = IoBuffer.allocate(25).setAutoExpand(true);
            byte[] vehicleNo = p.getVehicleNo().getBytes(CharsetConst.CHARTSET_GBK);
            byte[] vehicleNoB = new byte[21];
            System.arraycopy(vehicleNo, 0, vehicleNoB, 0, vehicleNo.length);
            buf.put(vehicleNoB).put(p.getVehicleColor());
            buf.putShort(p.getDataType());
            buf.putInt(p.getDataLength());
            buf.put(ConvUtil.hex2Bytes(p.getAlarmId()));
            buf.put(p.getFileCount());
            List<AlarmFileInfo> fileList = p.getFileList();
            for (AlarmFileInfo alarmFileInfo : fileList) {
                buf.put(alarmFileInfo.toBytes());
            }
            buf.flip();
            byte[] b = new byte[buf.limit()];
            buf.get(b);
            return b;
        } catch (Exception e) {
            throw new P809Exception("P0x1407编码失败:", e);
        }
    }

    @Override
    public PModel bodyFromBytes(byte[] data) throws P809Exception {
        if (null == data || 25 > data.length) {
            throw new P809Exception("P0x1407消息体解码失败，消息为空或者长度不足。");
        }
        try {
            P0x1407 p = new P0x1407();
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
            byte[] alarmId = new byte[16];
            System.arraycopy(data, pos, alarmId, 0, 16);
            p.setAlarmId(ConvUtil.bytes2Hex(alarmId));
            pos += 16;
            p.setFileCount(data[pos]);
            pos++;
            List<AlarmFileInfo> fileList = new ArrayList<>();
            int size = p.getFileCount();
            for (int i = 0; i < size; i++) {
                AlarmFileInfo alarmFileInfo = new AlarmFileInfo();
                pos = alarmFileInfo.formBytes(data, pos);
                fileList.add(alarmFileInfo);
            }
            p.setFileList(fileList);
            return p;
        } catch (Exception e) {
            throw new P809Exception("P0x1407解码失败:", e);
        }
    }
}
