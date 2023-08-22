package com.example.keypass;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.security.Key;

public class EncryptDecrypt {

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    static String key = "0UkdFM0HDDrOc1ICX5Gw7pVuPhPP6qpy";

    public static String encrypt(String plaintext) throws Exception {
        Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] ciphertext = cipher.doFinal(plaintext.getBytes());
        return Base64.encodeToString(ciphertext, Base64.DEFAULT);
    }

    public static String decrypt(String ciphertext) throws Exception {
        Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plaintextBytes = Base64.decode(ciphertext, Base64.DEFAULT);
        String plaintext = new String(cipher.doFinal(plaintextBytes));
        return plaintext;
    }
}