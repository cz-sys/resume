package cn.edu.haust.resumebackend.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 * @author chengzhen
 * created_at 2025/5/1
 */
public final class AESUtils {
    private static String AES_KEY;

    private AESUtils() {
    }

    public static void setAesKey(String key) {
        AES_KEY = key;
    }

    public static String encrypt(String content) {
        if (AES_KEY == null) {
            throw new IllegalStateException("AES_KEY 未初始化，请检查配置");
        }
        AES aes = SecureUtil.aes(AES_KEY.getBytes());
        return aes.encryptBase64(content);
    }
}
