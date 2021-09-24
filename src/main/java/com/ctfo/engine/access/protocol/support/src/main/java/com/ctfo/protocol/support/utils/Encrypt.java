package com.ctfo.engine.access.protocol.support.src.main.java.com.ctfo.protocol.support.utils;

import java.util.Random;

/**
 * 报文加密处理工具类类
 */
public class Encrypt {

    private static Random ran;

    private static final Long UINT32_MAX_VALUE = 4294967295L;

    public static byte[] encryptUtil(long key, byte[] buffer, long size,
                                     long M1, long IA1, long IC1) {
        int idx = 0;
        if (0 == key) {
            key = 1;
        }
        while (idx < size) {
            key = IA1 * (key % M1) + IC1;
            // 若key大于，取低位32位,java是有符号的所以unit32必须要用long类型类承接，而key为long类型时
            // key = IA1 * (key % M1) + IC1很可能大于unit32的最大值，java实现里面是正常的，但是对比c#
            // 加密后的数组字符串发现不一致，后来发现原始实现中用的uint32，当大于他的最大值是高位会被丢弃
            // 也就是&UINT32_MAX_VALUE后的值才是正确的值
//            if (key > UINT32_MAX_VALUE) {
//                key &= UINT32_MAX_VALUE;
//            }
            buffer[idx++] ^= (byte) ((key >> 20) * 0xFF);
        }
        return buffer;
    }

    public static long getRandom32long() {
        if (ran == null) {
            ran = new Random();
        }
        return ran.nextLong() & 0xFFFFFFFF;
    }

}
