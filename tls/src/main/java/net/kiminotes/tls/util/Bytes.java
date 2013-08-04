/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.util;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public final class Bytes {

    public static byte[] uint16ToBigEndianBytes(int value) {
        return longToBigEndianBytes(value, new byte[2]);
    }

    static byte[] longToBigEndianBytes(long value, byte[] bytes) {
        int length = Math.min(8, bytes.length);
        for(int i = length - 1, offset = 0; i >=0; i--, offset +=8) {
            bytes[i] = (byte)((value >>> offset) & 0XFF);
        }
        return bytes;
    }

    static long bigEndianBytesToLong(byte[] bytes) {
        long result = 0L;
        for(int i = 0, offset = bytes.length - 1; i < bytes.length; i++, offset--) {
            result |= (bytes[i] & 0XFF) << (offset  * 8);
        }
        return result;
    }

    public static int bigEndianBytesToUint16(byte[] bytes) {
        return (int)bigEndianBytesToLong(bytes);
    }

    public static byte[] uint24ToBigEndianBytes(int value) {
        return longToBigEndianBytes(value, new byte[3]);
    }

    public static int bigEndianBytesToUint24(byte[] bytes) {
        return (int)bigEndianBytesToLong(bytes);
    }

    public static byte[] uint32ToBigEndianBytes(long value) {
        return longToBigEndianBytes(value, new byte[4]);
    }

    public static long bigEndianBytesToUint32(byte[] bytes) {
        return bigEndianBytesToLong(bytes);
    }

    private Bytes() {
    }

}
