/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol.record;

import net.kiminotes.tls.util.ProtocolUtil;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public enum ContentType {

    CHANGE_CIPHER_SPEC(20),
    ALERT(21),
    HANDSHAKE(22),
    APPLICATION_DATA(23);

    /**
     * uint8
     */
    private int value;

    private ContentType(int value) {
        this.value = ProtocolUtil.toUint8(value);
    }

    public int value() {
        return this.value;
    }

}
