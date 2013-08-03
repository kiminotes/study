/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol.handshake;

import net.kiminotes.tls.util.ProtocolUtil;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public enum  CompressionMethod {

    NULL(0);

    private int value;

    public int value() {
        return value;
    }

    private CompressionMethod(int value) {
        this.value = ProtocolUtil.toUint8(value);
    }

}
