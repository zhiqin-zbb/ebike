package com.aaebike.common.kaptcha;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author zhangbinbin
 * @version 2017/12/5
 */
public class DefaultAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public DefaultAuthenticationFilter(AntPathRequestMatcher antPathRequestMatcher) {
        super(antPathRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String kaptcha = request.getParameter("kaptcha");

        // 校验验证码
        if (kaptcha == null) {
            throw new BadCredentialsException("验证码为空!");
        }
        if (!ValidateCodeHandle.matchCode(request.getSession().getId(), kaptcha)) {
            throw new BadCredentialsException("验证码错误!");
        }

        KaptchaAuthenticationToken token = new KaptchaAuthenticationToken(username, password, kaptcha);
        return this.getAuthenticationManager().authenticate(token);
    }
}
