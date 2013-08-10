/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public class HMACTest {

    static String toHexString(byte[] result) {
        int value;
        StringBuilder buf = new StringBuilder(40);
        for (byte b : result) {
            value = b & 0XFF;
            if (value < 0XF) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(value));
        }
        return buf.toString();
    }

    @Test
    public void test_HMAC_MD5() throws Exception {
        String secret = "ab";
        String seed = "efghijkl";
        byte[] result = HMAC.hmacMD5(secret.getBytes(), seed.getBytes());
        String expect = "efe3a7027ddbdb424cabd0935bfb3898";
        String actual = toHexString(result);
        assertEquals(expect, actual);
    }

    @Test
    public void test_And() throws Exception {
        byte b = -1;
        System.out.println(b & 0XFF);
    }

}
