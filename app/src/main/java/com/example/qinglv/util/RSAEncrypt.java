package com.example.qinglv.util;


import android.util.Base64;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

//  RSA加密方法
public class RSAEncrypt {

    //公钥加密,传入密码和公钥
    public static String publicEncrypt(String content, String publicKey) throws Exception{
        if(content == null || content.equals("")) {
            return null;
        }
        byte[] bytes = publicEncrypt(base642Byte(content), string2PublicKey(publicKey));
        return byte2Base64(bytes);
    }

    //将Base64编码后的公钥转换成PublicKey对象
    private static PublicKey string2PublicKey(String pubStr) throws Exception{
        byte[] keyBytes = base642Byte(pubStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }
    //公钥加密
    private static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher= Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    //字节数组转Base64编码
    public static String byte2Base64(byte[] bytes){
        String strBase64 = Base64.encodeToString(bytes, Base64.NO_WRAP);
        return strBase64;
    }

    //Base64编码转字节数组
    public static byte[] base642Byte(String base64Key) throws IOException {
        assert base64Key != null;
        byte[] contentByte = Base64.decode(base64Key, Base64.NO_WRAP);
//        byte[] contentByte = Base64.decode(base64Key, Base64.NO_WRAP);
        return contentByte;
    }
}

