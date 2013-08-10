package net.kiminotes.tls.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 */
public class HMACTest {

    @Test
    public void test_HMAC_MD5() throws Exception {
        String secret = "ab";
        String seed = "efghijkl";
        byte[] result = HMAC.hmacMD5(secret.getBytes(), seed.getBytes());
        StringBuilder buf = new StringBuilder(20);
        buf.append("0x");
        int value;
        for(byte b : result) {
            value = b & 0XFF;
            if (value < 0XF) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(value));
        }
        String expect = "0xefe3a7027ddbdb424cabd0935bfb3898";
        String actual = buf.toString();
        assertEquals(expect, actual);
    }

    @Test
    public void test_And() throws Exception {
        byte b = -1;
        System.out.println(b & 0XFF);
    }

}
