/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol.handshake;

import net.kiminotes.tls.v10.protocol.record.ProtocolVersion;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public class ClientHello extends HandshakeBody {

    private ProtocolVersion     clientVersion;
    private Random              random;
    /**
     * uint8
     */
    private int                 sessionIdLength;
    // length: 0 ~ 32
    private byte[]              sessionId;
    /**
     * uint16
     */
    private int                 cipherSuitesLength;
    // length: 2 ~ 2 ^ 16 -1, uint16
    private int[]               cipherSuites;
    /**
     * uint8
     */
    private int                 compressionMethodsLength;
    // length: 1 ~ 2 ^ 8 - 1
    private CompressionMethod[] compressionMethods;

    public ProtocolVersion getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(ProtocolVersion clientVersion) {
        this.clientVersion = clientVersion;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public byte[] getSessionId() {
        return sessionId;
    }

    public void setSessionId(byte[] sessionId) {
        this.sessionId = sessionId;
    }

    public int[] getCipherSuites() {
        return cipherSuites;
    }

    public void setCipherSuites(int[] cipherSuites) {
        this.cipherSuites = cipherSuites;
    }

    public CompressionMethod[] getCompressionMethods() {
        return compressionMethods;
    }

    public void setCompressionMethods(CompressionMethod[] compressionMethods) {
        this.compressionMethods = compressionMethods;
    }
}
