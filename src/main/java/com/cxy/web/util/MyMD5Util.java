package com.cxy.web.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class MyMD5Util {

	public static String getEncryptedStr(String s) {  
        char hexDigits[]={'2','0','1','5','V','T','H','A','P','P','0','6','A','T','0','1'};     
  
        try {  
            byte[] btInput = s.getBytes();  
            // 获得MD5摘要算法的 MessageDigest 对象  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
            // 使用指定的字节更新摘要  
            mdInst.update(btInput);  
            // 获得密文  
            byte[] md = mdInst.digest();  
            // 把密文转换成十六进制的字符串形式  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {  
                byte byte0 = md[i];  
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
                str[k++] = hexDigits[byte0 & 0xf];  
            }  
            return new String(str);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println(MyMD5Util.getEncryptedStr("1"));
	}
}
