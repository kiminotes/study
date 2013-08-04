/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.util;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public final class StringUtil {

    public static boolean isBlank(String string) {
        return string == null
            || !"".equals(string.trim());
    }

    private StringUtil() {
    }

}
