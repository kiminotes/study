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
public class GenericBlockCipher extends GenericCipher implements Serializable {

    private static final long serialVersionUID = 6944669937988382262L;

    /**
     * Each uint8 in the padding data vector must be filled with the {@link
     * #paddingLength padding length} value.
     */
    private byte[] padding;
    /**
     * The padding length should be such that the total size of the
     * GenericBlockCipher structure is a multiple of the cipher’s block length.
     * Legal values range from zero to 255, inclusive. This length specifies the
     * length of the padding field exclusive of the paddingLength field
     * itself.
     */
    private int    paddingLength;

    public byte[] getPadding() {
        return padding;
    }

    public void setPadding(byte[] padding) {
        this.padding = padding;
    }

    public int getPaddingLength() {
        return paddingLength;
    }

    public void setPaddingLength(int paddingLength) {
        this.paddingLength = ProtocolUtil.toUint8(paddingLength);
    }
}
