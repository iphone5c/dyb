package com.dyb.platforms.fixfunds.services.utils;

import java.security.MessageDigest;
import java.util.Random;

public class MD5 {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String md5Hex(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {

		}
		return resultString;
	}

    //加密函数
    public static String MD5Encode(String origin) {
        String resultString = null;
        StringBuffer salt=new StringBuffer(getRandom(8)+getRandom(8));
        try {
            resultString = md5Hex(origin+salt);
            char[] cs = new char[48];
            for (int i=0;i<48;i+=3){
                cs[i] = resultString.charAt(i / 3 * 2);
                cs[i + 1] = resultString.charAt(i / 3 * 2 + 1);
                char c = salt.charAt(i / 3);
                cs[i + 2] = c;
            }
            resultString=String.valueOf(cs);
        } catch (Exception ex) {

        }
        return resultString;
    }

    public static String getRandom(int flag){
        StringBuffer sb=new StringBuffer();
        if (flag>0&&flag<15){
            int info=1;
            for (int i=0;i<flag;i++){
                info=info*10;
            }
            Random r = new Random();
            int i= r.nextInt(info);
            String str=String.valueOf(i);
            if (str.length()<flag){
                for (int j=0;j<flag-str.length();j++){
                    sb.append("0");
                }
            }
            sb.append(str);
        }
        return sb.toString();
    }

    //验证密码
    public static boolean verify(String password, String md5) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 1);
            cs2[i / 3] = md5.charAt(i + 2);
        }
        String salt = new String(cs2);
        return md5Hex(password + salt).equals(new String(cs1));
    }
}