/*
 * Copyright 2013 kiminotes.lv@gmail.com.
 * All rights reserved.
 */
package net.kiminotes.tls.v10.protocol.cert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:kiminotes.lv@gmail.com">kimi</a>
 */
public class ASN1 {

    private int    constructed;
    private int    tagClass;
    private int    tag;
    private int    length;
    private byte[] data;
    private ASN1   next;
    private final List<ASN1> children = new ArrayList<ASN1>();

}
