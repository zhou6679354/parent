package com.tianlong.common.encryptutils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class AesEncryptUtils {
    private static final String KEY = "d7585fde114abcda";
    private static final String ALGORITHMSTR = "AES/CBC/PKCS5Padding";
    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }
    public static byte[] base64Decode(String base64Code) throws Exception {
        return Base64.decodeBase64(base64Code);
    }
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
            return cipher.doFinal(content.getBytes("utf8"));
    }
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
           KeyGenerator kgen = KeyGenerator.getInstance("AES");
           kgen.init(128);
           Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
           cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
           byte[] decryptBytes = cipher.doFinal(encryptBytes);
           return new String(decryptBytes);
    }
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }
    /**
     * 将二进制转换成16进制
     * @method parseByte2HexStr
     * @param buf
     * @return
     * @throws
     * @since v1.0
     */
    public static String parseByte2HexStr(byte buf[]){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < buf.length; i++){
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     * @method parseHexStr2Byte
     * @param hexStr
     * @return
     * @throws
     * @since v1.0
     */
    public static byte[] parseHexStr2Byte(String hexStr){
        if(hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
        String content = "{name:\"lynn\",id:1}";
        System.out.println("加密前：" + content);
        String encrypt = aesEncrypt(content, KEY);
        System.out.println(encrypt.length() + ":加密后：" + encrypt);
        String decrypt = aesDecrypt(encrypt, KEY);
        System.out.println("解密后：" + decrypt);
    }
}
