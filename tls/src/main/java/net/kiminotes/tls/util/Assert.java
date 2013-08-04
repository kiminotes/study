/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.util;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public final class Assert {

    public static void notNull(Object obj, String msg) {
        if (obj == null) {
            throw new NullPointerException(msg);
        }
    }

    private Assert() {
    }
}
