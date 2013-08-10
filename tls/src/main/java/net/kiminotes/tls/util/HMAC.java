/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public final class HMAC {

    private static final Log LOG = LogFactory.getLog(HMAC.class);

    private static final ThreadLocal<Mac> HMAC_MD5 = new ThreadLocal<Mac>() {

        @Override
        protected Mac initialValue() {
            try {
                return Mac.getInstance("HmacMD5");
            } catch (NoSuchAlgorithmException e) {
                if (LOG.isErrorEnabled()) {
                    LOG.error(e.getMessage(), e);
                }
            }
            return null;
        }
    };

    private static final ThreadLocal<Mac> HMAC_SHA1 = new ThreadLocal<Mac>() {

        @Override
        protected Mac initialValue() {
            try {
                return Mac.getInstance("HmacSHA1");
            } catch (NoSuchAlgorithmException e) {
                if (LOG.isErrorEnabled()) {
                    LOG.error(e.getMessage(), e);
                }
            }
            return null;
        }
    };

    public static byte[] hmacMD5(byte[] secret, byte[] seed, byte[]... others) throws InvalidKeyException {
        Assert.notNull(secret, "secret = null");
        Assert.notNull(seed, "seed = null");

        Mac mac = HMAC_MD5.get();
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret, mac.getAlgorithm());
        mac.init(secretKeySpec);
        return mac(mac, seed, others);
    }

    public static byte[] hmacSHA1(byte[] secret, byte[] seed, byte[]... others) throws InvalidKeyException {
        Assert.notNull(secret, "secret = null");
        Assert.notNull(seed, "seed = null");

        Mac mac = HMAC_SHA1.get();
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret, mac.getAlgorithm());
        mac.init(secretKeySpec);
        return mac(mac, seed, others);
    }

    static byte[] mac(Mac mac, byte[] seed, byte[]... others) {
        mac.update(seed);
        for (byte[] other : others) {
            mac.update(other);
        }
        return mac.doFinal();
    }

    private HMAC() {
    }

}
