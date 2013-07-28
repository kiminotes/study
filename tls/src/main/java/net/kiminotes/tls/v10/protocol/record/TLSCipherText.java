/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol.record;

import java.io.Serializable;

import net.kiminotes.tls.util.ProtocolUtil;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public class TLSCipherText implements Serializable {

    private static final long serialVersionUID = -8900781232580981563L;

    /**
     * 2 ^ 14 + 2048
     */
    public static final int MAX_LENGTH = TLSPlainText.MAX_LENGTH + 2048;

    private ContentType     type;
    private ProtocolVersion version;
    private int             length;
    private GenericCipher   fragment;

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }

    public ProtocolVersion getVersion() {
        return version;
    }

    public void setVersion(ProtocolVersion version) {
        this.version = version;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = ProtocolUtil.toUint16(length);
    }

    public GenericCipher getFragment() {
        return fragment;
    }

    public void setFragment(GenericCipher fragment) {
        this.fragment = fragment;
    }
}
