/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.util;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public final class PRF {

    private static final Log LOG = LogFactory.getLog(PRF.class);

    public static final Charset ASCII               = Charset.forName("ascii");
    public static final byte[]  MASTER_SECRET_LABEL = "master secret".getBytes(ASCII);
    public static final byte[]  KEY_EXCHANGE_LABEL  = "key expansion".getBytes(ASCII);

    public static byte[] computeMasterSecret(byte[] preMasterSecret,
                                             byte[] clientRandom,
                                             byte[] serverRandom,
                                             int masterSecretLength)
        throws InvalidKeyException {

        return computeMasterSecret(preMasterSecret, MASTER_SECRET_LABEL,
                                   clientRandom, serverRandom, masterSecretLength);
    }

    public static byte[] computeMasterSecret(byte[] preMasterSecret,
                                             byte[] label,
                                             byte[] clientRandom,
                                             byte[] serverRandom,
                                             int masterSecretLength)
        throws InvalidKeyException {

        return prf(preMasterSecret, label, Bytes.merge(clientRandom, serverRandom),
                   masterSecretLength);
    }

    public static byte[] prf(byte[] secret, byte[] label, byte[] seed, int outputLength) throws InvalidKeyException {
        Assert.notNull(secret, "secret = null");
        Assert.notNull(label, "label = null");
        Assert.notNull(seed, "seed = null");
        if (outputLength <= 0) {
            throw new IndexOutOfBoundsException("outputLength must be positive");
        }

        int halfLength = secret.length / 2 + secret.length % 2;
        byte[] s1 = new byte[halfLength];
        System.arraycopy(secret, 0, s1, 0, halfLength);
        byte[] s2 = new byte[halfLength];
        System.arraycopy(secret, secret.length - halfLength, s2, 0, halfLength);
        byte[] hashSeed = new byte[label.length + seed.length];
        System.arraycopy(label, 0, hashSeed, 0, label.length);
        System.arraycopy(seed, 0, hashSeed, label.length, seed.length);

        byte[] s1Hash = HashAlgorithm.MD5.hash(s1, hashSeed, outputLength);
        byte[] s2Hash = HashAlgorithm.SHA1.hash(s2, hashSeed, outputLength);

        for (int i = 0; i < outputLength; i++) {
            s1Hash[i] ^= s2Hash[i];
        }

        return s1Hash;
    }

    private PRF() {
    }
}
