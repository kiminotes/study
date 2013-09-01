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
public class ProtocolVersion implements Serializable {

    public static final ProtocolVersion TLS_V_1 = new ProtocolVersion(3, 1);

    public static ProtocolVersion create(int major, int minor) {
        if (major == TLS_V_1.getMajor()
            && minor == TLS_V_1.getMinor()) {
            return TLS_V_1;
        } else {
            return new ProtocolVersion(major, minor);
        }
    }

    private static final long serialVersionUID = 8917368945192906263L;

    /**
     * uint8
     */
    private int major;
    /**
     * uint8
     */
    private int minor;

    private ProtocolVersion(int major, int minor) {
        this.major = ProtocolUtil.toUint8(major);
        this.minor = ProtocolUtil.toUint8(minor);
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    @Override
    public String toString() {
        return "{major:" + major + ", minor:" + minor + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProtocolVersion that = (ProtocolVersion) o;

        if (major != that.major) return false;
        if (minor != that.minor) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = major;
        result = 31 * result + minor;
        return result;
    }

}
