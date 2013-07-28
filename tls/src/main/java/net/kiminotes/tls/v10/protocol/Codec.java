/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol;

import java.nio.ByteBuffer;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public interface Codec {

    public void encode(ByteBuffer buffer);
    public void decode(ByteBuffer buffer);

}
