package com.ctfo.engine.access.protocol.support.src.main.java.com.ctfo.protocol.support.utils;

public class CRC16_CUI {

	public static byte[] getBigCRC16(byte[] bytes) {
		int crc = 65535;
		int polynomial = 40961;
		for (int j = 0; j < bytes.length; j++) {
			crc ^= bytes[j];
			for (int i = 0; i < 8; i++) {
				boolean c15 = (crc & 0x1) == 1;
				crc >>= 1;
				if (c15)
					crc ^= polynomial;
			}
		}
		byte[] b = new byte[2];
		b[0] = ((byte) (crc & 0xFF));
		b[1] = ((byte) (crc >> 8));
		return b;
	}

	public static byte[] getCRCCRC16_CCITT(byte[] bytes) {
		int crc = 65535;
		int polynomial = 4129;
		for (byte b : bytes) {
			for (int i = 0; i < 8; i++) {
				boolean bit = (b >> 7 - i & 0x1) == 1;
				boolean c15 = (crc >> 15 & 0x1) == 1;
				crc <<= 1;
				if ((c15 ^ bit))
					crc ^= polynomial;
			}
		}
		crc &= 65535;
		return ConvUtil.unSigned16IntToBigBytes(crc);
	}

}
