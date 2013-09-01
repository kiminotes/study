/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol.handshake;

import java.security.SecureRandom;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public class Random {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static Random create() {
        Random result = new Random();
        result.setGmtUnixTime(System.currentTimeMillis() / 1000L);
        byte[] bytes = new byte[28];
        SECURE_RANDOM.nextBytes(bytes);
        result.setRandomBytes(bytes);
        return result;
    }

    /**
     * uint32
     */
    private long   gmtUnixTime;
    // length = 28
    private byte[] randomBytes;

    public long getGmtUnixTime() {
        return gmtUnixTime;
    }

    public void setGmtUnixTime(long gmtUnixTime) {
        this.gmtUnixTime = gmtUnixTime;
    }

    public byte[] getRandomBytes() {
        return randomBytes;
    }

    public void setRandomBytes(byte[] randomBytes) {
        this.randomBytes = randomBytes;
    }
}
