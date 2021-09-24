package com.ctfo.engine.access.protocol.support.src.main.java.com.ctfo.protocol.support.utils;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ctfo.protocol.support.constants.CharsetConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 字节码转换工具类 <br>
 * <p>
 * 各种数据类型之间的转换
 *
 * @author 凉意 2018年4月22日 上午10:50:13
 * @version 1.0
 */
@Slf4j
public class ConvUtil {

    /**
     * 消息体解码转义
     *
     * <pre>
     * 0x7d后紧跟一个0x02 <————> 0x7e <br>
     * 0x7d后紧跟一个0x01 <————> 0x7d
     *
     * @author 凉意 2018年4月22日 上午10:58:07
     * @param body
     *            待转义的消息体数据
     * @return 转义后的字节码
     */
    public static byte[] transDecodeBody(byte[] body) {
        byte[] result = null;
        // 消息体长度
        int bodyLen = body.length;
        // 消息长度小于等于2时不需要进行转义，直接返回
        if (2 >= bodyLen) {
            log.warn("参数长度不合法，要求长度大于2。不需要进行转义");
            return result;
        }

        int len = 0;
        int count = 0;
        // 先計算消息转义后的总长度
        for (int i = 1; i < bodyLen - 1; i++) {
            len++;
            if (body[i] == 0x7d && body[i + 1] == 0x01) {
                len--;
            }
            if (body[i] == 0x7d && body[i + 1] == 0x02) {
                len--;
            }
        }
        result = new byte[len + 2];
        // 转义
        for (int i = 0; i < bodyLen; i++) {
            if (body[i] == 0x7d && body[i + 1] == 0x01) {
                result[count] = body[i];
                i++;
            } else if (body[i] == 0x7d && body[i + 1] == 0x02) {
                result[count] = 0x7e;
                i++;
            } else {
                result[count] = body[i];
            }
            count++;
        }

        return result;
    }

    /**
     * 消息体编码转义
     *
     * <pre>
     * 0x7d后紧跟一个0x02 <————> 0x7e <br>
     * 0x7d后紧跟一个0x01 <————> 0x7d
     *
     * @author 凉意 2018年4月22日 上午11:13:12
     * @param body 待转义的消息体数据
     * @return 转义后的字节码
     */
    public static byte[] transEncodeBody(byte[] body) {
        byte[] result = null;
        // 消息体长度
        int bodyLen = body.length;
        // 消息长度小于等于2时不需要进行转义，直接返回
        if (2 >= bodyLen) {
            log.warn("参数长度不合法，要求长度大于2。不需要进行转义");
            return result;
        }

        int count = 0;
        // 计算需要转义的字节数
        for (int i = 1; i < bodyLen - 1; i++) {
            if (body[i] == 0x7d || body[i] == 0x7e) {
                count++;
            }
        }
        result = new byte[bodyLen + count];
        count = 0;
        // 转义
        for (int i = 0; i < bodyLen; i++) {
            if (i == 0 || i == bodyLen - 1) {
                result[i + count] = body[i];
            } else {
                if (body[i] == 0x7d) {
                    result[i + count] = body[i];
                    result[i + count + 1] = 0x01;
                    count++;
                } else if (body[i] == 0x7e) {
                    result[i + count] = 0x7d;
                    result[i + count + 1] = 0x02;
                    count++;
                } else {
                    result[i + count] = body[i];
                }
            }
        }
        return result;
    }

    /**
     * Byte数组转ASII码
     *
     * @param p 待转换的数据
     * @return ASII码值
     * @author 凉意 2018年4月25日 下午5:43:40
     */
    public static String bytes2ASII(byte[] p) {
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < p.length; i++) {
            char c = (char) p[i];
            str.append(c);
        }
        return str.toString();
    }

    /**
     * Byte转换二进制位
     *
     * @param b 待转换的数字
     * @return 转换后的位数据bit
     * @author 凉意 2018年4月22日 上午11:27:11
     */
    public static String byteToBits(byte b) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < 8; i++)
            buf.append((int) (b >> (8 - (i + 1)) & 0x0001));
        return buf.toString();
    }

    /**
     * Byte转换二进制位,取自定位值
     *
     * @param b   待转换的数据
     * @param bit 需要取的位值，取值范围0-7
     * @return
     * @author 凉意 2018年5月8日 下午4:02:57
     * @version 1.0
     * @see
     */
    public static String byteToBits(byte b, int bit) {
        return String.valueOf((b >> (8 - (bit + 1)) & 0x0001));
    }

    /**
     * Byte数组转换二进制位
     *
     * @param byteArr
     * @return
     * @author 凉意 2018年4月22日 上午11:28:46
     */
    public static String bytesToBits(byte[] byteArr) {
        StringBuilder result = new StringBuilder();
        for (byte b : byteArr) {
            result.append(byteToBits(b));
        }
        return result.toString();
    }

    /**
     * Byte数组转换二进制位,取自定位值
     *
     * @param byteArr 待转换的数据
     * @param bit     需要取的位值，取值范围0-n
     * @return
     * @author 凉意 2018年5月8日 下午4:05:12
     * @version 1.0
     * @see
     */
    public static String bytesToBits(byte[] byteArr, int bit) {
        StringBuilder result = new StringBuilder();
        for (byte b : byteArr) {
            result.append(byteToBits(b));
        }
        int bitLen = byteArr.length * 8;
        return result.toString().substring(bitLen - bit - 1, bitLen - bit);
    }

    /**
     * Byte数组转换为十六进制数据
     *
     * @param data 待转换的Byte数据
     * @return 十六进制字符串（大写）
     * @author 凉意 2018年4月22日 上午11:25:05
     * @version 1.0
     * @see
     */
    public static String bytes2Hex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        int dataLen = data.length;
        for (int i = 0; i < dataLen; i++) {
            int num = Integer.valueOf(byteToBits(data[i]), 2);
            if (num < 16) {
                sb.append("0" + Integer.toHexString(num));
            } else {
                sb.append(Integer.toHexString(num));
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 十六进制转换Byte数组
     *
     * @param hex 待转换的十六进制字符串
     * @return
     * @author 凉意 2018年4月22日 上午11:35:51
     * @version 1.0
     * @see
     */
    public static byte[] hex2Bytes(String hex) {
        if (StringUtils.isBlank(hex)) {
            log.warn("参数不合法，为空");
            return null;
        } else if (hex.length() < 2) {
            log.warn("参数长度不够，要求长度大于等于2");
            return null;
        }
        int len = hex.length() / 2;
        byte[] buffer = new byte[len];
        byte[] src = hex.getBytes();
        for (int i = 0; i < len; i++) {
            buffer[i] = (byte) (0xff & ((getHexValue(src[i << 1]) << 4) | getHexValue(src[(i << 1) + 1])));
        }

        for (int i = 0; i < len; i++) {
            buffer[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }

        return buffer;
    }

    private static int getHexValue(byte b) {
        if (b >= 0x30 && b < 0x39) {
            return b - 0x30;
        }
        if (b >= 'a' && b <= 'f') {
            return b - 'a';
        }
        if (b >= 'A' && b <= 'F') {
            return b - 'A';
        }
        return 0;
    }

    /**
     * Byte数组转Short
     *
     * @param data 待转换的数据
     * @return short类型的数值
     * @author 凉意 2018年4月22日 上午11:38:52
     */
    public static short bytes2Short(byte[] data) {
        return (short) (data[1] & 0xff | (data[0] & 0xff) << 8);
    }

    /**
     * Byte数组转Short，选择起始地址
     *
     * @param data
     * @param offset
     * @return
     * @author 凉意 2018年4月22日 上午11:40:12
     */
    public static int bytes2Short(byte[] data, int offset) {
        return data[offset + 1] & 0xff | (data[offset + 0] & 0xff) << 8;
    }

    /**
     * Short转换为Byte数组
     *
     * @param number
     * @return
     * @author 凉意 2018年4月22日 上午11:42:34
     * @version 1.0
     * @see
     */
    public static byte[] short2Bytes(short number) {
        byte[] bytesRet = new byte[2];
        bytesRet[0] = (byte) ((number >> 8) & 0xFF);
        bytesRet[1] = (byte) (number & 0xFF);
        return bytesRet;
    }

    /**
     * Byte数组转Int
     *
     * @param data
     * @return
     * @author 凉意 2018年4月22日 上午11:45:09
     * @version 1.0
     * @see
     */
    public static int bytes2Int(byte[] data) {
        return data[3] & 0xff | (data[2] & 0xff) << 8 | (data[1] & 0xff) << 16 | (data[0] & 0xff) << 24;
    }

    /**
     * Byte数组转Int，选择起始位置
     *
     * @param data
     * @param offset
     * @return
     * @author 凉意 2018年4月22日 上午11:45:05
     * @version 1.0
     * @see
     */
    public static int bytes2Int(byte[] data, int offset) {
        return data[offset + 3] & 0xff | (data[offset + 2] & 0xff) << 8 | (data[offset + 1] & 0xff) << 16 | (data[offset] & 0xff) << 24;
    }

    /**
     * Int转换为Byte数组
     *
     * @param number
     * @return
     * @author 凉意 2018年4月22日 上午11:47:36
     * @version 1.0
     * @see
     */
    public static byte[] int2Bytes(int number) {
        byte b[] = new byte[4];
        b[0] = (byte) ((number >> 24) & 0xFF);
        b[1] = (byte) ((number >> 16) & 0xFF);
        b[2] = (byte) ((number >> 8) & 0xFF);
        b[3] = (byte) (number & 0xFF);
        return b;
    }

    /**
     * Byte数组转换为Long
     *
     * @param b
     * @return
     * @author 凉意 2018年4月22日 上午11:48:40
     * @version 1.0
     * @see
     */
    public static long bytes2Long(byte[] b) {
        return ((b[0] & 0xff) << 56) | ((b[1] & 0xff) << 48) | ((b[2] & 0xff) << 40) | ((b[3] & 0xff) << 32) | ((b[4] & 0xff) << 24) | ((b[5] & 0xff) << 16)
                | ((b[6] & 0xff) << 8) | ((b[7] & 0xff) << 0);
    }

    /**
     * Long转换为Byte数组
     *
     * @param number
     * @return
     * @author 凉意 2018年4月22日 上午11:50:18
     * @version 1.0
     * @see
     */
    public static byte[] long2Bytes(long number) {
        byte b[] = new byte[8];
        b[0] = (byte) ((number >> 56) & 0xFF);
        b[1] = (byte) ((number >> 48) & 0xFF);
        b[2] = (byte) ((number >> 40) & 0xFF);
        b[3] = (byte) ((number >> 32) & 0xFF);
        b[4] = (byte) ((number >> 24) & 0xFF);
        b[5] = (byte) ((number >> 16) & 0xFF);
        b[6] = (byte) ((number >> 8) & 0xFF);
        b[7] = (byte) (number & 0xFF);
        return b;
    }

    /**
     * Byte数组转换为Float
     *
     * @param data
     * @return
     * @author 凉意 2018年4月22日 上午11:54:36
     * @version 1.0
     * @see
     */
    public static float bytes2Float(byte[] data) {
        int i = 0;
        i = ((((data[3] & 0xff) << 8 | (data[2] & 0xff)) << 8) | (data[1] & 0xff)) << 8 | (data[0] & 0xff);
        return Float.intBitsToFloat(i);
    }

    /**
     * Float转换为Byte数组
     *
     * @param number
     * @return
     * @author 凉意 2018年4月22日 上午11:55:42
     * @version 1.0
     * @see
     */
    public static byte[] float2Bytes(float number) {
        int l = Float.floatToIntBits(number);
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = new Integer(l).byteValue();
            l = l >> 8;
        }
        return b;
    }

    /**
     * Byte数组转换为Double
     *
     * @param data
     * @return
     * @author 凉意 2018年4月22日 上午11:57:04
     * @version 1.0
     * @see
     */
    public static double bytes2Double(byte[] data) {
        long l;
        l = data[0];
        l &= 0xff;
        l |= ((long) data[1] << 8);
        l &= 0xffff;
        l |= ((long) data[2] << 16);
        l &= 0xffffff;
        l |= ((long) data[3] << 24);
        l &= 0xffffffffl;
        l |= ((long) data[4] << 32);
        l &= 0xffffffffffl;
        l |= ((long) data[5] << 40);
        l &= 0xffffffffffffl;
        l |= ((long) data[6] << 48);
        l &= 0xffffffffffffffl;
        l |= ((long) data[7] << 56);
        return Double.longBitsToDouble(l);
    }

    /**
     * Double转换为Byte数组
     *
     * @param number
     * @return
     * @author 凉意 2018年4月22日 上午11:57:57
     * @version 1.0
     * @see
     */
    public static byte[] double2Bytes(double number) {
        long l = Double.doubleToLongBits(number);
        byte[] b = new byte[8];
        for (int i = 0; i < 8; i++) {
            b[i] = new Long(l).byteValue();
            l = l >> 8;
        }
        return b;
    }

    /**
     * Byte转换为BCD码
     *
     * @param b
     * @return
     * @author 凉意 2018年4月22日 下午12:04:32
     * @version 1.0
     * @see
     */
    public static String byte2Bcd(byte b) {
        StringBuilder sb = new StringBuilder();
        String tmp;
        tmp = Integer.toHexString(0xFF & b);
        if (tmp.length() < 2)
            sb.append(0);
        sb.append(tmp.toUpperCase());
        return sb.toString();
    }

    /**
     * Byte数组转换为BCD码
     *
     * @param data
     * @return
     * @author 凉意 2018年4月22日 下午12:03:13
     * @version 1.0
     * @see
     */
    public static String bytes2Bcd(byte[] data) {
        int dataLen = data.length;
        StringBuilder sb = new StringBuilder(dataLen);
        String tmp;
        for (int i = 0; i < dataLen; i++) {
            tmp = Integer.toHexString(0xFF & data[i]);
            if (tmp.length() < 2)
                sb.append(0);
            sb.append(tmp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * BCD码转换为Byte数组
     *
     * @param bcd
     * @return
     * @author 凉意 2018年4月22日 下午12:06:49
     * @version 1.0
     * @see
     */
    public static byte[] bcd2Bytes(String bcd) {
        int len = (bcd.length() / 2);
        byte[] result = new byte[len];
        char[] achar = bcd.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (char2Byte(achar[pos]) << 4 | char2Byte(achar[pos + 1]));
        }
        return result;
    }

    /**
     * Char转换为Byte
     *
     * @param c
     * @return
     * @author 凉意 2018年4月22日 下午12:06:31
     * @version 1.0
     * @see
     */
    private static byte char2Byte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * Byte数组转换为字符串
     *
     * @param _data
     * @return
     * @author 凉意 2018年4月22日 下午12:14:25
     * @version 1.0
     * @see
     */
    public static String bytes2String(byte[] _data) {
        byte[] data = null;
        if (_data.length % 2 == 0)
            data = new byte[_data.length];
        else {
            if (_data[_data.length - 1] == 0)
                data = new byte[_data.length - 1];
            else
                data = new byte[_data.length];
        }
        System.arraycopy(_data, 0, data, 0, data.length);
        IoBuffer buf = IoBuffer.allocate(data.length).setAutoExpand(true);
        Charset charset = Charset.forName(CharsetConst.CHARTSET_GBK);
        buf.put(data);
        buf.flip();
        CharsetDecoder cDecoder = charset.newDecoder();
        String str = null;
        try {
            str = buf.getString(cDecoder);
        } catch (CharacterCodingException e) {
            log.error("bytes2String异常:" + ConvUtil.bytes2Hex(_data) + ",Error Message:" + e.getLocalizedMessage());
        }
        return str;
    }

    /**
     * String字符串转换为Byte数组
     *
     * @param str
     * @return
     * @author 凉意 2018年4月22日 下午12:25:19
     * @version 1.0
     * @see
     */
    public static byte[] string2Bytes(String str) {
        IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
        Charset charset = Charset.forName(CharsetConst.CHARTSET_GBK);
        CharsetEncoder cEncoder = charset.newEncoder();
        byte[] b = null;
        try {
            buf.putString(str, cEncoder);
            buf.flip();
            b = new byte[buf.remaining()];
            buf.get(b);
        } catch (CharacterCodingException e) {
            log.error("string2Bytes异常:", e);
        }
        return b;
    }

    /**
     * int数字转换为BCD码，然后转换为Bytes数据。位数不够高位补0
     *
     * @param num
     * @param len
     * @return
     * @author 凉意 2018年5月16日 下午4:58:28
     * @version 1.0
     * @see
     */
    public static byte[] int2Bcd2Bytes(int num, int len) {
        String hex = String.valueOf(num);
        hex = beforeAddZero(hex, len);
        return bcd2Bytes(hex);
    }

    /**
     * 方法简要描述
     * <p>
     * 方法详细描述
     *
     * @param b
     * @param len
     * @return
     * @author 凉意 2018年5月16日 下午5:25:05
     * @version 1.0
     * @see
     */
    public static int bytes2Bcd2Int(byte[] b, int len) {
        StringBuffer sb = new StringBuffer(b.length);
        String sTemp = "0";
        int forLen = len / 2;
        for (int i = 0; i < forLen; i++) {
            sTemp = Integer.toHexString(0xFF & b[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * 位数不足，高位补0
     *
     * @param hex
     * @param len
     * @return
     * @author 凉意 2018年5月16日 下午5:01:06
     * @version 1.0
     * @see
     */
    private static String beforeAddZero(String hex, int len) {
        StringBuilder sb = new StringBuilder();
        if (hex.length() < len) {
            int c = len - hex.length();
            for (int i = 0; i < c; i++) {
                sb.append("0");
            }
        }
        sb.append(hex);
        return sb.toString();
    }

    /**
     * 稽查码处理，获取稽查码点阵数据。根据新标准点阵数据为48*16像素点
     * <p>
     * 车辆状态显示屏显示的点阵取模方式为横向取模，先取左侧的8列，产生16字节的点阵数据，作为数据包的前16字节的内容，再取接着的8列，产生16字节的点阵数据，…，最后，取最右侧的8列数据产生16字节的点阵数据放在点阵数据包的最后16个数据的位置
     *
     * @param str 稽查码
     * @return
     * @author 凉意 2018年12月18日 上午11:06:42
     * @version 1.0
     * @see
     */
    public static byte[] getLattice(String str) {
        BufferedImage image = new BufferedImage(48, 16, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        Font font = new Font("宋体", Font.PLAIN, 16);
        g.setFont(font);
        g.clearRect(0, 0, 48, 16);
        // 计算文字长度，计算居中的x点坐标
        FontMetrics fm = g.getFontMetrics(font);
        int textWidth = fm.stringWidth(str);
        int widthX = (48 - textWidth) / 2;
        // 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
        g.drawString(str, widthX, 14);
        int[] p = image.getRGB(0, 0, image.getWidth(), image.getHeight(), new int[image.getWidth() * image.getHeight()], 0, image.getWidth());
        List<Byte> blist = new ArrayList<>();
        String bits = "";
        Map<String, String> temp = new HashMap<>();
        int index = 1;
        for (int i = 1; i <= p.length; i++) {
            if (i % 48 == 0) {
                bits += Math.abs((byte) p[i - 1]) + "";
                temp.put("index_" + index, bits);
                bits = "";
                index++;
            } else {
                bits += Math.abs((byte) p[i - 1]) + "";
            }
        }
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 16; j++) {
                String d = temp.get("index_" + j);
                blist.add((byte) Integer.parseInt(d.substring(i * 8 - 8, i * 8), 2));
            }
        }

        byte[] b = new byte[blist.size()];
        int size = blist.size();
        for (int i = 0; i < size; i++) {
            b[i] = blist.get(i);
        }
        return b;
    }

    public static byte[] unSigned32LongToBigBytes(long val) {
        byte[] b = new byte[4];
        b[3] = ((byte) (int) (val >> 0));
        b[2] = ((byte) (int) (val >> 8));
        b[1] = ((byte) (int) (val >> 16));
        b[0] = ((byte) (int) (val >> 24));
        return b;
    }

    public static long bigBytes2Unsigned32Long(byte[] b, int pos) {
        int firstByte = 0;
        int secondByte = 0;
        int thirdByte = 0;
        int fourthByte = 0;
        int index = pos;
        firstByte = 0xFF & b[(index + 3)];
        secondByte = 0xFF & b[(index + 2)];
        thirdByte = 0xFF & b[(index + 1)];
        fourthByte = 0xFF & b[(index + 0)];
        index += 4;
        return (fourthByte << 24 | thirdByte << 16 | secondByte << 8 | firstByte) & 0xFFFFFFFF;
    }

    public static byte[] unSigned16IntToBigBytes(int val) {
        byte[] b = new byte[2];
        b[1] = ((byte) (val >> 0));
        b[0] = ((byte) (val >> 8));
        return b;
    }

    public static int bigBytes2Unsigned16Int(byte[] b, int pos) {
        int firstByte = 0;
        int secondByte = 0;
        int index = pos;
        firstByte = 0xFF & b[(index + 1)];
        secondByte = 0xFF & b[(index + 0)];
        index += 2;
        return (secondByte << 8 | firstByte) & 0xFFFF;
    }
}
