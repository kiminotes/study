/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.util;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public final class ProtocolUtil {

    public static final int UINT8_MAX  = 0XFF;
    public static final int UINT16_MAX = 0XFFFF;
    public static final int UINT24_MAX = 0XFFFFFF;

    public static int toUint8(int value) {
        if (0 <= value && value <= UINT8_MAX) {
            return UINT8_MAX & value;
        }
        throw new IllegalArgumentException(value + " out of range 0 ~ " + UINT8_MAX);
    }

    public static int toUint16(int value) {
        if (0 <= value && value <= UINT16_MAX) {
            return UINT16_MAX & value;
        }
        throw new IllegalArgumentException(value + " out of range 0 ~ " + UINT16_MAX);
    }

    public static int toUint24(int value) {
        if (0 <= value && value <= UINT24_MAX) {
            return UINT24_MAX & value;
        }
        throw new IllegalArgumentException(value + " out of range 0 ~ " + UINT24_MAX);
    }

    private ProtocolUtil() {
    }

}
