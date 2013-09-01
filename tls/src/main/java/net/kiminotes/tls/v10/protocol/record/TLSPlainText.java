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
public class TLSPlainText implements Serializable {

    private static final long serialVersionUID = -2383950584419294919L;

    /**
     * 2 ^ 14
     */
    public static final int MAX_LENGTH = 1 << 13;

    private ContentType     type;
    private ProtocolVersion version;
    /**
     * uint16
     */
    private int             length;
    private Object          fragment;

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

    public Object getFragment() {
        return fragment;
    }

    public void setFragment(Object fragment) {
        this.fragment = fragment;
    }

}
