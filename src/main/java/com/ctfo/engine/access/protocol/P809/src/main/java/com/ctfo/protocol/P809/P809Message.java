/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809;

import com.ctfo.protocol.P809.cst.P809Cst;
import com.ctfo.protocol.P809.engine.IPassBody;
import com.ctfo.protocol.P809.model.PModel;
import com.ctfo.protocol.P809.utils.PInvokeUtil;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.CRC16_CUI;
import com.ctfo.protocol.support.utils.ConvUtil;
import com.ctfo.protocol.support.utils.Encrypt;
import lombok.Data;
import lombok.ToString;
import org.apache.mina.core.buffer.IoBuffer;

import java.io.Serializable;

/**
 * 809协议消息结构定义
 *
 * @author liangyi
 * @date 2020/8/17 17:34
 */
@Data
@ToString
public class P809Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 协议内部版本，用于区分消息头不同编解码处理
     */
    private String protocolInnerVersion = "2011";

    /**
     * 头标识
     */
    private byte headFlag = 0x5b;

    /**
     * 数据长度(包括头标识、数据头、数据体和尾标识)  4字节
     */
    private int msgLength;

    /**
     * 报文序列号  4字节
     */
    private long msgSn;

    /**
     * 业务类型 2字节
     */
    private short msgId;

    /**
     * 业务类型 16进制
     */
    private String msgIdHex;

    /**
     * 子业务类型 16进制
     */
    private String dataTypeId;

    /**
     * 下级平台接入码，上级平台给下级平台分配唯一标识码。 4字节
     */
    private long msgGnnsCenterid;

    /**
     * 协议版本号标识，上下级平台采用的标准协议版本编号；长度为3字节
     */
    private byte[] versionFlag = {1, 2, 15};

    /**
     * 报文加密标识位：0表示报文不加密，1表示报文加密
     */
    private byte encryptFlag = 0;

    /**
     * 数据加密的密钥，长度为4个字节
     */
    private long encryptKey = 1;

    /**
     * 数据体
     */
    private PModel model;

    /**
     * CRC校验码 2字节
     */
    private short crc;

    /**
     * 结尾标识
     */
    private byte endFlag = 0x5d;

    /**
     * 获取809所定义的业务类型代码
     *
     * @return 业务类型代码，例如：UP_CONNECT_REQ
     */
    public String getDataType() {
        String dataType = "";
        if ("2011".equals(this.protocolInnerVersion)) {
            dataType = P809Cst.P809_2011_DATA_TYPE.get(this.msgIdHex);
        } else if ("2019".equals(this.protocolInnerVersion)) {
            dataType = P809Cst.P809_2019_DATA_TYPE.get(this.msgIdHex);
        }
        return dataType;
    }

    public void formBytes(byte[] data, String msgIdHex, long M1, long IA1, long IC1) throws P809Exception {
        IoBuffer bf = IoBuffer.wrap(data);
        this.msgLength = bf.getInt();
        this.msgSn = bf.getInt();
        // 消息ID
        bf.getShort();
        this.msgIdHex = msgIdHex;
        this.msgGnnsCenterid = Integer.toUnsignedLong(bf.getInt());
        byte[] versionFlag = new byte[3];
        bf.get(versionFlag);
        this.versionFlag = versionFlag;
        this.encryptFlag = bf.get();
        this.encryptKey = Integer.toUnsignedLong(bf.getInt());
        byte[] body = new byte[data.length - 22 - 2];
        bf.get(body);
        if (this.encryptFlag == 1) {// 报文解密
            body = Encrypt.encryptUtil(this.encryptKey, body, body.length, M1, IA1, IC1);
        }
        dataTypeId = msgIdHex;
        if ("1200".equals(msgIdHex) || "9200".equals(msgIdHex) || "1300".equals(msgIdHex) || "9300".equals(msgIdHex)
                || "1400".equals(msgIdHex) || "9400".equals(msgIdHex) || "1500".equals(msgIdHex) || "9500".equals(msgIdHex)
                || "1600".equals(msgIdHex) || "9600".equals(msgIdHex)) {
            // 子业务类型ID
            byte[] dataTypeIdByte = new byte[2];
            System.arraycopy(body, 22, dataTypeIdByte, 0, 2);
            dataTypeId = ConvUtil.bytes2Hex(dataTypeIdByte);
        }

        IPassBody pb = PInvokeUtil.invoke809PB(dataTypeId, this.protocolInnerVersion);
        this.model = pb.bodyFromBytes(body);
    }

    public byte[] toBytes(long M1, long IA1, long IC1) throws P809Exception {
        // 子业务类型ID
        String dataTypeId = this.model.getSubTypeHex();
        IPassBody pb = PInvokeUtil.invoke809PB(dataTypeId, this.protocolInnerVersion);
        byte[] body = pb.bodyToBytes(model);
        if (this.encryptFlag == 1) { // 报文加密
            this.encryptKey = Encrypt.getRandom32long();
            body = Encrypt.encryptUtil(this.encryptKey, body, body.length, M1, IA1, IC1);
        }
        this.msgLength = 1 + 22 + body.length + 2 + 1;
        // 消息头 + 消息体
        IoBuffer buf = IoBuffer.allocate(this.msgLength - 4);
        buf.putInt(this.msgLength);
        buf.putInt((int) this.msgSn);
        buf.put(ConvUtil.hex2Bytes(this.msgIdHex));
        buf.putInt((int) this.msgGnnsCenterid);
        buf.put(this.versionFlag);
        buf.put(this.encryptFlag);
        buf.putInt((int) this.encryptKey);
        buf.put(body);
        buf.flip();
        byte[] head_body = new byte[buf.limit()];
        buf.get(head_body);
        // CRC 2字节
        byte[] crc16 = CRC16_CUI.getCRCCRC16_CCITT(head_body);
        // 转义
        head_body = encode(head_body);
        crc16 = encode(crc16);
        IoBuffer buffer = IoBuffer.allocate(head_body.length + crc16.length + 2);
        buffer.put(this.headFlag);
        buffer.put(head_body);
        buffer.put(crc16);
        buffer.put(this.endFlag);
        buffer.flip();
        byte[] b = new byte[buffer.limit()];
        buffer.get(b);
        return b;
    }


    /**
     * 消息转义
     *
     * <pre>
     * 0x5b >> 0x5a0x01 <br>
     * 0x5a >> 0x5a0x02 <br>
     * 0x5d >> 0x5e0x01 <br>
     * 0x5e >> 0x5e0x02 <br>
     *
     * @author liangyi 2017-7-3 下午01:17:12
     */
    private byte[] encode(byte[] bytes) {
        IoBuffer buf = IoBuffer.allocate(2).setAutoExpand(true);
        for (byte b : bytes) {
            if (b == 91) {
                buf.put((byte) 90);
                buf.put((byte) 1);
            } else if (b == 90) {
                buf.put((byte) 90);
                buf.put((byte) 2);
            } else if (b == 93) {
                buf.put((byte) 94);
                buf.put((byte) 1);
            } else if (b == 94) {
                buf.put((byte) 94);
                buf.put((byte) 2);
            } else {
                buf.put(b);
            }
        }
        buf.flip();
        byte[] result = new byte[buf.limit()];
        buf.get(result);
        return result;
    }

}
