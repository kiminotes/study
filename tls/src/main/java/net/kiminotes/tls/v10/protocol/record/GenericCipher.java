/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol.record;

import java.io.Serializable;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public abstract class GenericCipher implements Serializable {

    private static final long serialVersionUID = 7118025353100328969L;

    private byte[] content;
    private byte[] mac;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getMac() {
        return mac;
    }

    public void setMac(byte[] mac) {
        this.mac = mac;
    }
}
