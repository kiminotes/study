/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol.handshake;

import net.kiminotes.tls.util.ProtocolUtil;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public enum HandshakeType {

    HELLO_REQUEST(0),
    CLIENT_HELLO(1),
    SERVER_HELLO(2),
    CERTIFICATE(11),
    SERVER_KEY_EXCHANGE(12),
    CERTIFICATE_REQUEST(13),
    SERVER_HELLO_DONE(14),
    CERTIFICATE_VERIFY(15),
    CLIENT_KEY_EXCHANGE(16),
    FINISHED(20);

    private int value;

    public int value() {
        return value;
    }

    private HandshakeType(int value) {
        this.value = ProtocolUtil.toUint8(value);
    }

    public static HandshakeType getHandshakeType(int value) {
        for (HandshakeType type : values()) {
            if (type.value() == value) {
                return type;
            }
        }

        throw new IllegalArgumentException("Unsupported handshake type " + value);
    }

}
