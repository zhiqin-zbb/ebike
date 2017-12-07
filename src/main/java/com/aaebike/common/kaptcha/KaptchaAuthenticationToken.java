package com.aaebike.common.kaptcha;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;

/**
 * @author zhangbinbin
 * @version 2017/12/7
 */
@Data
public class KaptchaAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private String kaptcha;

    public KaptchaAuthenticationToken(String principal, String credentials, String kaptcha) {
        super(principal, credentials);
        this.kaptcha = kaptcha;
    }
}