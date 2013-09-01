/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol.cert;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public interface Constants {

    int ASN1_CLASS_UNIVERSAL   = 0;
    int ASN1_CLASS_APPLICATION = 1;
    int ASN1_CONTEXT_SPECIFIC  = 2;
    int ASN1_PRIVATE           = 3;

    int ASN1_BER                  = 0;
    int ASN1_BOOLEAN              = 2;
    int ASN1_INTEGER              = 2;
    int ASN1_BIT_STRING           = 3;
    int ASN1_OCTET_STRING         = 4;
    int ASN1_NULL                 = 5;
    int ASN1_OBJECT_IDENTIFIER    = 6;
    int ASN1_OBJECT_DESCRIPTOR    = 7;
    int ASN1_INSTANCE_OF_EXTERNAL = 8;
    int ASN1_REAL                 = 9;
    int ASN1_ENUMERATED           = 10;
    int ASN1_EMBEDDED_PRV         = 11;
    int ASN1_UTF8_STRING          = 12;
    int ASN1_RELATIVE_OID         = 13;

    int ASN1_SEQUENCE         = 16;
    int ASN1_SET              = 17;
    int ASN1_NUMERIC_STRING   = 18;
    int ASN1_PRINTABLE_STRING = 19;
    int ASN1_TELETEX_STRING   = 20;
    int ASN1_T61_STRING       = 20;
    int ASN1_VIDEOTEX_STRING  = 21;
    int ASN1_IA5_STRING       = 22;
    int ASN1_UTC_TIME         = 23;
    int ASN1_GENERALIZED_TIME = 24;
    int ASN1_GRAPHIC_STRING   = 25;
    int ASN1_VISIABLE_STRING  = 26;
    int ASN1_ISO64_STRING     = 27;
    int ASN1_UNIVERSAL_STRING = 28;
    int ASN1_CHARACTER_STRING = 29;
    int ASN1_BMP_STRING       = 30;
}
