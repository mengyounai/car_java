package com.gasaiyuno.car.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class AesUtil {

    private static String key = "rings511115sgnir"; // 16位
    private static String iv = "511ringssgnir115"; // 16位

    //加密
    public static String encryptAES(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NOPadding"); // 参数分别代表
        // 算法名称/加密模式/数据填充方式
        int blockSize = cipher.getBlockSize();
        byte[] dataBytes = data.getBytes();
        int plaintextLength = dataBytes.length;
        if (plaintextLength % blockSize != 0) {
            plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
        }
        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
        SecretKeySpec aes = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec spec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, aes, spec);
        byte[] encrypted = cipher.doFinal(plaintext);
        Encoder encoder = Base64.getEncoder();
        return new String(encoder.encode(encrypted));
    }

    //解密
    public static String decryptAES(String data) throws Exception {
        Decoder decoder = Base64.getDecoder();
        byte[] encrypted1 = decoder.decode(data);
        Cipher cipher = Cipher.getInstance("AES/CBC/NOPadding");
        SecretKeySpec aes = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec spec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, aes, spec);
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original);
        return originalString.trim();
    }

    //加密
    public static String encryptAES(String data, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        Encoder encoder = Base64.getEncoder();
        byte[] datas = encoder.encode(encrypted);
        return new String(datas);
    }

    //解密
    public static String decryptAES(String data, String key, String iv) throws Exception {
        Decoder decoder = Base64.getDecoder();
        byte[] encrypted1 = decoder.decode(data);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original);
    }

}
