package net.kiminotes.tls.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import org.junit.Test;

import org.junit.Assert;

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 */
public class BytesTest {

    @Test
    public void test_toBigEndianBytes() throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        LongBuffer longBuffer = byteBuffer.asLongBuffer();

        byte[] expect;
        byte[] actual;
        long value;

        value = 0XFFAA;
        longBuffer.put(value);
        expect = byteBuffer.array();
        actual = new byte[8];
        Bytes.longToBigEndianBytes(value, actual);
        assertEquals(expect, actual);
        Assert.assertEquals(value, Bytes.bigEndianBytesToLong(actual));
    }

    static void assertEquals(byte[] expect, byte[] actual) {
        int length = Math.min(expect.length, actual.length);
        for(int i = 0; i < length; i++) {
            Assert.assertEquals(expect[i], actual[i]);
        }
    }

    @Test
    public void test_convert_Between_Uint16_And_Bytes() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(ByteOrder.BIG_ENDIAN);
        ShortBuffer shortBuffer = buffer.asShortBuffer();

        short value = 0X1234;
        shortBuffer.put(value);

        byte[] actual = Bytes.uint16ToBigEndianBytes(value);
        Assert.assertArrayEquals(buffer.array(), actual);
        Assert.assertEquals(value, Bytes.bigEndianBytesToUint16(actual));
    }

    @Test
    public void test_convert_Between_Uint24_And_Int() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(ByteOrder.BIG_ENDIAN);
        IntBuffer intBuffer = buffer.asIntBuffer();

        int value = 0X123456;
        intBuffer.put(value);

        byte[] actual = Bytes.uint24ToBigEndianBytes(value);
        Assert.assertEquals(value, Bytes.bigEndianBytesToUint24(actual));
    }

    @Test
    public void test_assertOffsetAndLength() throws Exception {
        Bytes.assertOffsetAndLength(new byte[]{1}, 0, 1);
        Bytes.assertOffsetAndLength(new byte[]{1, 2, 3, 4}, 2, 2);
    }

}
