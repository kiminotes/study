package net.kiminotes.tls.v10.protocol.handshake;

import net.kiminotes.tls.util.ProtocolUtil;

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 */
public enum CipherSuiteIdentifier {

    // NULL
    TLS_NULL_WITH_NULL_NULL(0X0000),

    // RSA
    TLS_RSA_WITH_NULL_MD5(0x0001),

    TLS_RSA_WITH_NULL_SHA(0x0002),

    TLS_RSA_EXPORT_WITH_RC4_40_MD5(0x0003),

    TLS_RSA_WITH_RC4_128_MD5(0x0004),

    TLS_RSA_WITH_RC4_128_SHA(0x0005),

    TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5(0x0006),

    TLS_RSA_WITH_IDEA_CBC_SHA(0x0007),

    TLS_RSA_EXPORT_WITH_DES40_CBC_SHA(0x0008),

    TLS_RSA_WITH_DES_CBC_SHA(0x0009),

    TLS_RSA_WITH_3DES_EDE_CBC_SHA(0x000A),

    // DH
    TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA(0x000B),

    TLS_DH_DSS_WITH_DES_CBC_SHA(0x000C),

    TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA(0x000D),

    TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA(0x000E),

    TLS_DH_RSA_WITH_DES_CBC_SHA(0x000F),

    TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA(0x0010),

    TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA(0x0011),

    TLS_DHE_DSS_WITH_DES_CBC_SHA(0x0012),

    TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA(0x0013),

    TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA(0x0014),

    TLS_DHE_RSA_WITH_DES_CBC_SHA(0x0015),

    TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA(0x0016),

    TLS_DH_anon_EXPORT_WITH_RC4_40_MD5(0x0017),

    TLS_DH_anon_WITH_RC4_128_MD5(0x0018),

    TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA(0x0019),

    TLS_DH_anon_WITH_DES_CBC_SHA(0x001A),

    TLS_DH_anon_WITH_3DES_EDE_CBC_SHA(0x001B);

    /**
     * uint16
     */
    private int value;

    public int value() {
        return value;
    }

    private CipherSuiteIdentifier(int value) {
        this.value = ProtocolUtil.toUint16(value);
    }

    public static int[] getSupportedCipherSuite() {
        return new int[]{};
    }

}
