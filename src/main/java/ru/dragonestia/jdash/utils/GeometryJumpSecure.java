package ru.dragonestia.jdash.utils;

import org.apache.tomcat.util.codec.binary.Base64;

// todo: Это можно переименовать во что-нибудь получше
public class GeometryJumpSecure {

    private static final String PASSWORD_SEED = "37526";

    private GeometryJumpSecure() {

    }

    public static String xorCipher(String pText, String pKey) {
        byte[] txt = pText.getBytes();
        byte[] key = pKey.getBytes();
        byte[] res = new byte[pText.length()];

        for (int i = 0; i < txt.length; i++) {
            res[i] = (byte) (txt[i] ^ key[i % key.length]);
        }

        return new String(res);
    }

    public static String gjpEncode(String password) {
        return Base64.encodeBase64String(xorCipher(password, PASSWORD_SEED).getBytes());
    }

    public static String gjpDecode(String value) {
        return xorCipher(new String(Base64.decodeBase64(value)), PASSWORD_SEED);
    }
}
