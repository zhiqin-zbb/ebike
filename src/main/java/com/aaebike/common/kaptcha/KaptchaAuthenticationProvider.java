package com.aaebike.common.kaptcha;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author zhangbinbin
 * @version 2017/12/7
 */
public class KaptchaAuthenticationProvider extends DaoAuthenticationProvider {
    @Override
    public boolean supports(Class<?> authentication) {
        return KaptchaAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Object salt = null;

        if (getSaltSource() != null) {
            salt = getSaltSource().getSalt(userDetails);
        }

        if (authentication.getCredentials() == null || StringUtils.isEmpty(authentication.getCredentials().toString())) {
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException("请输入密码!");
        }

        String presentedPassword = authentication.getCredentials().toString();

        if (!this.getPasswordEncoder().isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
            logger.debug("Authentication failed: password does not match stored value");
            throw new BadCredentialsException("密码错误!");
        }
    }
}