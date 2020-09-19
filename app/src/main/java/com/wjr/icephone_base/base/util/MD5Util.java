package com.wjr.icephone_base.base.util;

import java.security.MessageDigest;

public final class MD5Util {

    /**
     * MD5 32加密，大写结果
     *
     * @param encryptStr 需要加密的字符
     * @return String MD5加密后的大写形势
     * 异常：
     */
    public static String encodeMD532(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString().toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

    /**
     * MD5 16位加密
     *
     * @param encryptStr
     * @return String
     */
    public static String enccodeMD516(String encryptStr) {
        return encodeMD532(encryptStr).substring(8, 24);
    }


}
