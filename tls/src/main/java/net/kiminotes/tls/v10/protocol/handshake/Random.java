/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol.handshake;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public class Random {

    /**
     * uint32
     */
    private int    gmtUnixTime;
    // length = 28
    private byte[] randomBytes;

    public int getGmtUnixTime() {
        return gmtUnixTime;
    }

    public void setGmtUnixTime(int gmtUnixTime) {
        this.gmtUnixTime = gmtUnixTime;
    }

    public byte[] getRandomBytes() {
        return randomBytes;
    }

    public void setRandomBytes(byte[] randomBytes) {
        this.randomBytes = randomBytes;
    }
}
