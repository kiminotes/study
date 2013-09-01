/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.kiminotes.tls.util.Assert;
import net.kiminotes.tls.util.Bytes;
import net.kiminotes.tls.v10.protocol.handshake.ClientHello;
import net.kiminotes.tls.v10.protocol.handshake.CompressionMethod;
import net.kiminotes.tls.v10.protocol.handshake.Handshake;
import net.kiminotes.tls.v10.protocol.record.TLSPlainText;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public class Codec {

    public static final Codec INSTANCE = new Codec();

    public void encode(Object obj, OutputStream os) throws IOException {
        Assert.notNull(obj, "obj == null");
        Assert.notNull(os, "os == null");

        if (obj instanceof ClientHello) {
            encodeClientHello((ClientHello) obj, os);
        } else if (obj instanceof Handshake) {
            encodeHandshake((Handshake) obj, os);
        } else if (obj instanceof TLSPlainText) {
            encodeTLSPlainText((TLSPlainText) obj, os);
        }
    }

    void encodeTLSPlainText(TLSPlainText tlsPlainText, OutputStream os) throws IOException {
        Object fragment = tlsPlainText.getFragment();
        byte[] bytes;
        if (!(fragment instanceof byte[])) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(512);
            encode(fragment, bos);
            bos.flush();
            bytes = bos.toByteArray();
        } else {
            bytes = (byte[]) fragment;
        }
        int round = bytes.length / TLSPlainText.MAX_LENGTH + 1;
        for (int i = 0; i < round; i++) {
            // content type
            os.write(tlsPlainText.getType().value());

            // version
            os.write(tlsPlainText.getVersion().getMajor());
            os.write(tlsPlainText.getVersion().getMinor());

            // fragment
            int offset = TLSPlainText.MAX_LENGTH * i;
            int length = bytes.length - offset;
            length = Math.min(TLSPlainText.MAX_LENGTH, length);
            os.write(bytes, offset, length);
        }
    }

    void encodeHandshake(Handshake handshake, OutputStream os) throws IOException {
        // handshake type
        os.write(handshake.getType().value());

        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        encode(handshake.getBody(), bos);
        byte[] body = bos.toByteArray();

        // length
        os.write(Bytes.uint24ToBigEndianBytes(body.length));

        // body
        os.write(body);
    }

    void encodeClientHello(ClientHello ch, OutputStream os) throws IOException {
        // client version
        os.write(ch.getClientVersion().getMajor());
        os.write(ch.getClientVersion().getMinor());

        // random
        os.write(Bytes.uint32ToBigEndianBytes(ch.getRandom().getGmtUnixTime()));
        byte[] bytes = ch.getRandom().getRandomBytes();
        os.write(bytes);

        // session id
        bytes = ch.getSessionId();
        int length;
        if (bytes != null
            && bytes.length > 0) {
            length = Math.min(32, bytes.length);
            os.write(length);
            os.write(bytes, 0, length);
        }

        // cipher suite
        os.write(Bytes.uint16ToBigEndianBytes(ch.getCipherSuites().length));
        for (int cs : ch.getCipherSuites()) {
            os.write(Bytes.uint16ToBigEndianBytes(cs));
        }

        // compression method
        os.write(ch.getCompressionMethods().length);
        for (CompressionMethod cm : ch.getCompressionMethods()) {
            os.write(cm.value());
        }
    }

    public Object decode(InputStream is) throws IOException {
        return null;
    }

}
