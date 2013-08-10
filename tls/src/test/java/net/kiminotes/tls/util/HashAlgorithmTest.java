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
public class HashAlgorithmTest {

    @Test
    public void test_P_hash() throws Exception {
        String seed = "efghijkl";

        byte[] md5Hash = HashAlgorithm.MD5.hash("ab".getBytes(), seed.getBytes(), 48);
        String md5Expect = "1b6ca10d18faddfbeb92b2d95f55ce2607d6c81ebe4b96d1bec81813b9a0275725564781eda73ac521548d7d1f982c17";
        assertEquals(md5Expect, HMACTest.toHexString(md5Hash));

//        byte[] sha1Hash = HashAlgorithm.SHA1.hash("cd".getBytes(), seed.getBytes(), 40);
//        String sha1Expect = "cbb3de5db9295cdb68eb1ab18f88939cb3146849fe167cf8f9ec5f131790005d7f27b2515db6c590";
//        assertEquals(sha1Expect, HMACTest.toHexString(sha1Hash));
    }

    @Test
    public void test_prf() throws Exception {
        String secret = "secret";
        String label = "label";
        String seed = "seed";
        int len = 20;
        byte[] result = PRF.prf(secret.getBytes(), label.getBytes(), seed.getBytes(), len);
        String expect = "b5baf4722b91851a8816d22ebd8c1d8cc2e94d55";
        assertEquals(expect, HMACTest.toHexString(result));
    }

}
