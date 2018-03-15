package com.aaebike.common.kaptcha;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class ValidateCodeHandle {
    private static ConcurrentHashMap<String, String> kaptchaMap = new ConcurrentHashMap<>();

    public static void save(String sessionId, String code) {
        kaptchaMap.put(sessionId, code);
    }

    public static boolean matchCode(String sessionId, String inputCode) {
        Object obj = kaptchaMap.get(sessionId);
        if (obj != null) {
            String saveCode = String.valueOf(obj);
            return StringUtils.isNotEmpty(saveCode) && saveCode.equals(inputCode);
        }
        return false;
    }
}
