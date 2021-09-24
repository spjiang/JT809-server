/*
 * @Copyright (c) 北京北大千方科技有限公司  L.P. All rights
 */
package com.ctfo.engine.access.protocol.P809.src.main.java.com.ctfo.protocol.P809.vo;

import com.ctfo.protocol.P809.engine.p2011.P0x1407PB;
import com.ctfo.protocol.support.exception.P809Exception;
import com.ctfo.protocol.support.utils.ConvUtil;
import lombok.Data;
import lombok.ToString;
import org.apache.mina.core.buffer.IoBuffer;

import java.io.Serializable;

/**
 * 报警附件信息数据体
 *
 * @author liangyi
 * @date 2021/2/23 14:37
 */
@Data
@ToString
public class AlarmFileInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 文件名长度，1字节
     */
    private byte fileNameLength;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型，1字节
     */
    private byte fileType;

    /**
     * 附件大小，4字节
     */
    private int fileSize;

    /**
     * 文件URL的长度，1字节
     */
    private int fileUrlLength;

    /**
     * 文件URL
     */
    private String fileUrl;

    /**
     * 消息封包
     *
     * @return byte[]
     * @author liangyi
     * @date 2021/2/23 16:00
     */
    public byte[] toBytes() {
        IoBuffer b = IoBuffer.allocate(7 + this.fileNameLength + this.fileUrlLength);
        try {
            b.put(this.fileNameLength);
            b.put(ConvUtil.string2Bytes(this.fileName));
            b.put(this.fileType);
            b.putInt(this.fileSize);
            b.put((byte)this.fileUrlLength);
            b.put(ConvUtil.string2Bytes(this.fileUrl));
            return b.array();
        } catch (Exception e) {
            throw new P809Exception("报警附件信息toBytes失败：", e);
        }
    }

    /**
     * 消息拆包
     *
     * @param data 待解码的字节数据
     * @author liangyi
     * @date 2021/2/23 16:00
     */
    public int formBytes(byte[] data, int pos) {
        if (null == data || 7 > data.length) {
            throw new P809Exception("报警附件信息formBytes失败,消息长度不够。");
        }
        try {
            this.fileNameLength = data[pos];
            pos++;
            byte[] fileNameByte = new byte[this.fileNameLength];
            System.arraycopy(data, pos, fileNameByte, 0, this.fileNameLength);
            this.fileName = ConvUtil.bytes2String(fileNameByte);
            pos += this.fileNameLength;
            this.fileType = data[pos];
            pos++;
            this.fileSize = ConvUtil.bytes2Int(data, pos);
            pos += 4;
            this.fileUrlLength = data[pos] & 0xff;
            pos ++;
            byte[] fileUrlByte = new byte[this.fileUrlLength];
            System.arraycopy(data, pos, fileUrlByte, 0, this.fileUrlLength);
            this.fileUrl = ConvUtil.bytes2String(fileUrlByte);
            pos += this.fileUrlLength;
            return pos;
        } catch (Exception e) {
            throw new P809Exception("报警附件信息formBytes失败：", e);
        }
    }
}
