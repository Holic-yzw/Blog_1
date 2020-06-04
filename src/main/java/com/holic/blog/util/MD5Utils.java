package com.holic.blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MD5Utils {

    public static String[] code(String username, String password) {
        Random random = new Random(47);
        StringBuffer preSalt = new StringBuffer(16+username.length());
        preSalt.append(random.nextInt(99999999)).append(username).append(random.nextInt(99999999));

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            // get the value of salt
            byte[] bs = md5.digest(preSalt.toString().getBytes());
            String salt = bytesToHex(bs);

            // get password after encode
            String prePwd = password.substring(0,4);
            String netPwd = password.substring(4);
            String str = prePwd + salt + netPwd;
            md5.update(str.getBytes());
            byte[] digest = md5.digest();
            int i;
            StringBuffer stringBuffer = new StringBuffer("");
            for (int offset = 0; offset < digest.length; offset++) {
                i = digest[offset];
                if (i < 0) i += 256;
                if (i < 16) stringBuffer.append("0");
                stringBuffer.append(Integer.toHexString(i));
            }
            String[] saltAndPwd = new String[2];
            saltAndPwd[0] = salt;
            saltAndPwd[1] = stringBuffer.toString();
            return saltAndPwd;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String findPassWord(String salt, String password) {

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            String prePwd = password.substring(0,4);
            String netPwd = password.substring(4);
            String str = prePwd + salt + netPwd;
            md5.update(str.getBytes());
            byte[] digest = md5.digest();
            int i;
            StringBuffer stringBuffer = new StringBuffer("");
            for (int offset = 0; offset < digest.length; offset++) {
                i = digest[offset];
                if (i < 0) i += 256;
                if (i < 16) stringBuffer.append("0");
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        String password = "111111";
//        String username = "holic";
//        String[] code = code(username, password);
//    }

    /**
     * 字节数组转16进制
     * @param bytes 需要转换的byte数组
     * @return 转换后的Hex字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
