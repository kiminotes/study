/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.util;

import java.security.InvalidKeyException;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public enum HashAlgorithm {

    MD5 {
        @Override
        public byte[] hash(byte[] secret, byte[] seed, int outputLength) throws InvalidKeyException {
            return hashInternal(secret, seed, outputLength, this);
        }
    },

    SHA1 {
        @Override
        public byte[] hash(byte[] secret, byte[] seed, int outputLength) throws InvalidKeyException {
            return hashInternal(secret, seed, outputLength, this);
        }
    };

    public abstract byte[] hash(byte[] secret, byte[] seed, int outputLength) throws InvalidKeyException;

    private static byte[] hashInternal(
        byte[] secret, byte[] seed, int outputLength, HashAlgorithm alg)
        throws InvalidKeyException {

        Assert.notNull(secret, "secret = null");
        Assert.notNull(seed, "seed == null");
        if (outputLength <= 0) {
            throw new IndexOutOfBoundsException("outputLength must positive");
        }

        byte[] a = seed;
        byte[] item = EMPTY;
        byte[] result = new byte[outputLength];
        int length;
        int index = 0;
        while (index < outputLength) {
            switch (alg) {
                case MD5:
                    a = HMAC.hmacMD5(secret, a);
                    item = HMAC.hmacMD5(secret, a, seed);
                    break;
                case SHA1:
                    a = HMAC.hmacSHA1(secret, a);
                    item = HMAC.hmacSHA1(secret, a, seed);
                    break;
            }
            length = Math.min(item.length, outputLength - index);
            System.arraycopy(item, 0, result, index, length);
            index += length;
        }

        return result;
    }

    private static final byte[] EMPTY = new byte[0];
}
