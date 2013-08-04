package net.kiminotes.tls.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.LongBuffer;

import org.junit.Test;

import junit.framework.Assert;

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

}
