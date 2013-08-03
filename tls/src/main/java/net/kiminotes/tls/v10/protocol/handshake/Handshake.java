/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol.handshake;

import net.kiminotes.tls.util.ProtocolUtil;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public class Handshake {

    private HandshakeType type;
    /**
     * uint24
     */
    private int           length;
    private HandshakeBody body;

    public HandshakeType getType() {
        return type;
    }

    public void setType(HandshakeType type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = ProtocolUtil.toUint24(length);
    }

    public HandshakeBody getBody() {
        return body;
    }

    public void setBody(HandshakeBody body) {
        this.body = body;
    }
}
