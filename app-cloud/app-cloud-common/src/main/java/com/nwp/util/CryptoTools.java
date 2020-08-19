package com.nwp.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import org.apache.commons.lang.StringUtils;

/**
 * 加密解密工具类
 *
 * @author lgl
 */
public class CryptoTools {
    /**
     * 设置密钥
     */
    private static final byte[] KEY = SecureUtil.generateDESKey(SymmetricAlgorithm.DES.getValue(), "hxpl@126.com20170510".getBytes()).getEncoded();

    /**
     * 默认加密数据
     *
     * @param data
     * @return
     */
    public static String encode(String data) {
        DES des = SecureUtil.des(KEY);
        return des.encryptBase64(data);
    }

    /**
     * 加密数据，允许客户自己传入自定义的加密密钥
     *
     * @param data        需要加密的数据
     * @param customerKey 客户自定义加密密钥
     * @return
     */
    public static String encode(String data, String customerKey) {
        byte[] myKey = KEY;
        if (StringUtils.isNotBlank(customerKey)) {
            myKey = SecureUtil.generateDESKey(SymmetricAlgorithm.DES.getValue(), customerKey.getBytes()).getEncoded();
        }
        DES des = SecureUtil.des(myKey);
        return des.encryptBase64(data);
    }

    /**
     * 默认解密数据
     *
     * @param data
     * @return
     */
    public static String decode(String data) {
        DES des = SecureUtil.des(KEY);
        return des.decryptStr(data);
    }

    /**
     * 解密数据，允许客户自己传入自定义的加密密钥进行解密
     *
     * @param data        需要加密的数据
     * @param customerKey 客户自定义加密密钥
     * @return
     */
    public static String decode(String data, String customerKey) {
        byte[] myKey = KEY;
        if (StringUtils.isNotBlank(customerKey)) {
            myKey = SecureUtil.generateDESKey(SymmetricAlgorithm.DES.getValue(), customerKey.getBytes()).getEncoded();
        }
        DES des = SecureUtil.des(myKey);
        return des.decryptStr(data);
    }
}
