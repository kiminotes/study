package net.kiminotes.study;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.crypto.Cipher;

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 */
public class RSA {

    static String generatePlain() {
        StringBuilder buf = new StringBuilder(1024);
        for (int i = 0; i < 36; i++) {
            buf.append(UUID.randomUUID().toString());
        }
        return buf.toString();
    }

    public static void main(String[] args) throws Exception {
        String plain = generatePlain();
        byte[] plainBytes = plain.getBytes("UTF-8");

        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        PublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        PrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        System.out.println(plain.equals(new String(expand(decrypt(encrypt(plainBytes, publicKey), privateKey)), "UTF-8")));
    }

    static byte[] expand(List<byte[]> list) {
        int len = 0;
        for(int i = 0; i < list.size(); i++) {
            len += list.get(i).length;
        }
        byte[] result = new byte[len];
        int index = 0;
        for(int i = 0; i < list.size(); i++) {
            byte[] item = list.get(i);
            System.arraycopy(item, 0, result, index, item.length);
            index += item.length;
        }
        return result;
    }

    static List<byte[]> decrypt(List<byte[]> bytes, PrivateKey key) throws Exception {
        List<byte[]> result = new ArrayList<byte[]>();
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key);
        for(int i = 0; i < bytes.size(); i++) {
            result.add(cipher.doFinal(bytes.get(i)));
        }
        return result;
    }

    static List<byte[]> encrypt(byte[] bytes, PublicKey key) throws Exception {
        List<byte[]> result = new ArrayList<byte[]>();
        int count = bytes.length / 32 + 1;
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        for(int i = 0; i < count; i++) {
            int startIndex = 32 * i;
            int len = bytes.length - startIndex;
            if (len > 32) {
                len = 32;
            }
            result.add(cipher.doFinal(bytes, startIndex, len));
        }
        return result;
    }

}
