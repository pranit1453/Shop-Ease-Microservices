package com.java.auth.service.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtil {

    private static final String ALGO = "AES/GCM/NoPadding";
    private static final int IV_LENGTH = 12;
    private static final int TAG_LENGTH = 128;

    private static final String KEY_PATH = "/key";

    private static SecretKey getKey(){
        try(InputStream is = AESUtil.class){

        }
    }

    public static String encrypt(final String data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGO);

            byte[] iv = new byte[IV_LENGTH];
            new SecureRandom().nextBytes(iv);

            GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, iv);

            cipher.init(Cipher.ENCRYPT_MODE, getKey(), spec);

            byte[] encryptedData = cipher.doFinal(data.getBytes());

            byte[] combined = new byte[IV_LENGTH + encryptedData.length];
            System.arraycopy(iv, 0, combined, 0, IV_LENGTH);
            System.arraycopy(encryptedData, 0, combined, IV_LENGTH, encryptedData.length);

            return Base64.getEncoder().encodeToString(combined);

        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting", e);
        }
    }

    public static String decrypt(final String encryptedData) {
        try {
            byte[] decoded = Base64.getDecoder().decode(encryptedData);

            byte[] iv = new byte[IV_LENGTH];
            byte[] cipherText = new byte[decoded.length - IV_LENGTH];

            System.arraycopy(decoded, 0, iv, 0, IV_LENGTH);
            System.arraycopy(decoded, IV_LENGTH, cipherText, 0, cipherText.length);

            Cipher cipher = Cipher.getInstance(ALGO);
            GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, iv);

            cipher.init(Cipher.DECRYPT_MODE, getKey(), spec);

            byte[] decrypted = cipher.doFinal(cipherText);

            return new String(decrypted);

        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting", e);
        }
    }
}