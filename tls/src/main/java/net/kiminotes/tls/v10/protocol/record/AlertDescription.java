/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol.record;

import net.kiminotes.tls.util.ProtocolUtil;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public enum AlertDescription {

    CLOSE_NOTIFY(0),
    UNEXPECTED_MESSAGE(10),
    BAD_RECORD_MAC(20),
    DECRYPTION_FAILED(21),
    RECORD_OVERFLOW(22),
    DECOMPRESSION_FAILURE(30),
    HANDSHAKE_FAILURE(40),
    BAD_CERTIFICATE(42),
    UNSUPPORTED_CERTIFICATE(43),
    CERTIFICATE_REVOKED(44),
    CERTIFICATE_EXPIRED(45),
    CERTIFICATE_UNKNOWN(46),
    ILLEGAL_PARAMETER(47),
    UNKNOWN_CA(48),
    ACCESS_DENIED(49),
    DECODE_ERROR(50),
    DECRYPT_ERROR(51),
    EXPORT_RESTRICTION(60),
    PROTOCOL_VERSION(70),
    INSUFFICIENT_SECURITY(71),
    INTERNAL_ERROR(80),
    USER_CANCELED(90),
    NO_RENEGOTIATION(100);

    private int value;

    public int value() {
        return value;
    }

    private AlertDescription(int value) {
        this.value = ProtocolUtil.toUint8(value);
    }

}
