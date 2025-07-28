package com.hc.common.utils;

import com.hc.common.exceptions.BaseException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DesUtil {

    public static final String DES_KEY = "95279527";

    /**
     * DES加密
     * @param content 待加密数据
     * @return
     * @throws Exception
     */
    public static String desEncrypt(String content) {
        try {
            //指定加密算法、加密模式、填充模式
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            //创建加密规则：指定key和加密类型
            SecretKeySpec secretKeySpec = new SecretKeySpec(DES_KEY.getBytes(), "DES");
            //指定加密模式为加密，指定加密规则
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            //调用加密方法
            byte[] result = cipher.doFinal(content.getBytes());
            //用Base64编码
            return new String(Base64.getEncoder().encode(result));
        }catch (Exception e) {
            throw new BaseException("加解密失败", e);
        }

    }

    /**
     * DES解密
     * @param content 待解密数据
     * @return
     * @throws Exception
     */
    public static String desDecrypt(String content) {
        try {
            //Base64解码
            byte[] result = Base64.getDecoder().decode(content);
            //指定加密算法、加密模式、填充模式
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            //创建加密规则：指定key和加密类型
            SecretKeySpec secretKeySpec = new SecretKeySpec(DES_KEY.getBytes(), "DES");
            //指定加密模式为解密，指定加密规则
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return new String(cipher.doFinal(result));
        }catch (Exception e) {
            throw new BaseException("加解密失败", e);
        }
    }

}

