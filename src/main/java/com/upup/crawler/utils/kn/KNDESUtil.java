package com.upup.crawler.utils.kn;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

public class KNDESUtil {

    public static void main(String[] args) throws Exception {
        String str = "D+QcrFA6A0SWzremVcYybtJyXHbeNWdJEEzDx1+abtNC1JZpYwXJhOjMsPuY7YvbLwKsa6lPmA8sIv5lyUQfRQ==";
        String aa = KNDESUtil.decryptor(str, "DYP4yHW8fK4=");
        System.err.println(aa);
    }
    public KNDESUtil() {

    }

    /**
     *
     * @Method: encrypt
     * @Description: 加密数据
     * @param data
     * @return
     * @throws Exception
     * @date 2016年7月26日
     */
    public static String encrypt(String data,String password) {  //对string进行BASE64Encoder转换
        byte[] bt = encryptByKey(data.getBytes(), password);
        BASE64Encoder base64en = new BASE64Encoder();
        String strs = new String(base64en.encode(bt));
        return strs;
    }
    /**
     *
     * @Method: encrypt
     * @Description: 解密数据
     * @param data
     * @return
     * @throws Exception
     * @date 2016年7月26日
     */
    public static String decryptor(String data,String password) throws Exception {  //对string进行BASE64Encoder转换
        sun.misc.BASE64Decoder base64en = new sun.misc.BASE64Decoder();
        byte[] bt = decrypt(base64en.decodeBuffer(data), password);
        String strs = new String(bt);
        return strs;
    }
    /**
     * 加密
     * @param datasource byte[]
     * @return byte[]
     */
    private static byte[] encryptByKey(byte[] datasource, String key) {
        try{
            SecureRandom random = new SecureRandom();

            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource);
        }catch(Throwable e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 解密
     * @param src byte[]
     * @return byte[]
     * @throws Exception
     */
    private static byte[] decrypt(byte[] src, String key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key.getBytes("UTF-8"));
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }
}
