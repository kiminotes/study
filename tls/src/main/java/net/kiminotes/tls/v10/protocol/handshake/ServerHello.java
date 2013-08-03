/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol.handshake;

import net.kiminotes.tls.v10.protocol.record.ProtocolVersion;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public class ServerHello extends HandshakeBody {

    private ProtocolVersion   serverVersion;
    private Random            random;
    // length: 0 ~ 32
    private byte[]            sessionId;
    private int               cipherSuite;
    private CompressionMethod compressionMethod;

    public ProtocolVersion getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(ProtocolVersion serverVersion) {
        this.serverVersion = serverVersion;
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

    public int getCipherSuite() {
        return cipherSuite;
    }

    public void setCipherSuite(int cipherSuite) {
        this.cipherSuite = cipherSuite;
    }

    public CompressionMethod getCompressionMethod() {
        return compressionMethod;
    }

    public void setCompressionMethod(CompressionMethod compressionMethod) {
        this.compressionMethod = compressionMethod;
    }
}
