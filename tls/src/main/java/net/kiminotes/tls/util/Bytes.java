/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public final class Bytes {

    public static int bigEndianBytesToUint8(byte[] bytes) {
        return bigEndianBytesToUint8(bytes, 0);
    }

    public static int bigEndianBytesToUint8(byte[] bytes, int offset) {
        assertOffsetAndLength(bytes, offset, 1);
        return bytes[offset] & 0XFF;
    }

    public static int bigEndianBytesToUint16(byte[] bytes, int offset) {
        assertOffsetAndLength(bytes, offset, 2);
        int result = 0;
        result |= (bytes[offset + 1] & 0XFF) << 8;
        result |= (bytes[offset + 0] & 0XFF) << 0;
        return result;
    }

    public static int bigEndianBytestoUint24(byte[] bytes, int offset) {
        assertOffsetAndLength(bytes, offset, 3);
        int result = 0;
        result |= (bytes[offset + 2] & 0XFF) << 16;
        result |= (bytes[offset + 1] & 0XFF) << 8;
        result |= (bytes[offset + 0] & 0XFF) << 0;
        return result;
    }

    public static byte[] uint16ToBigEndianBytes(int value) {
        return longToBigEndianBytes(value, new byte[2]);
    }

    static byte[] longToBigEndianBytes(long value, byte[] bytes) {
        int length = Math.min(8, bytes.length);
        for (int i = length - 1, offset = 0; i >= 0; i--, offset += 8) {
            bytes[i] = (byte) ((value >>> offset) & 0XFF);
        }
        return bytes;
    }

    static long bigEndianBytesToLong(byte[] bytes) {
        long result = 0L;
        for (int i = 0, offset = bytes.length - 1; i < bytes.length; i++, offset--) {
            result |= (bytes[i] & 0XFF) << (offset * 8);
        }
        return result;
    }

    public static int bigEndianBytesToUint16(byte[] bytes) {
        return bigEndianBytesToUint16(bytes, 0);
    }

    public static byte[] uint24ToBigEndianBytes(int value) {
        return longToBigEndianBytes(value, new byte[3]);
    }

    public static int bigEndianBytesToUint24(byte[] bytes) {
        return bigEndianBytestoUint24(bytes, 0);
    }

    public static byte[] uint32ToBigEndianBytes(long value) {
        return longToBigEndianBytes(value, new byte[4]);
    }

    public static long bigEndianBytesToUint32(byte[] bytes) {
        return bigEndianBytesToLong(bytes);
    }

    public static byte[] merge(byte[] bytes, byte[]... others) {
        Assert.notNull(bytes, "bytes = null");
        if (CollectionUtil.isEmpty(others)) {
            return bytes;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes.length + 128);
        try {
            bos.write(bytes);
            for (byte[] other : others) {
                bos.write(other);
            }
        } catch (IOException e) {
            // ignore
        }
        return bos.toByteArray();
    }

    static void assertOffsetAndLength(byte[] bytes, int offset, int length) {
        Assert.notNull(bytes, "bytes == null");

        if (offset < 0
            || offset >= bytes.length
            || (offset + length) > bytes.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private Bytes() {
    }

}
