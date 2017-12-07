package com.aaebike.common.kaptcha;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author zhangbinbin
 * @version 2017/12/5
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class AuthenticationInitializer extends AbstractSecurityWebApplicationInitializer {
    @Autowired
    UserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "kaptchaAuthenticationProvider")
    public KaptchaAuthenticationProvider kaptchaAuthenticationProvider() {
        KaptchaAuthenticationProvider kaptchaAuthenticationProvider = new KaptchaAuthenticationProvider();
        kaptchaAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        kaptchaAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return kaptchaAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> list = new ArrayList<>();
        list.add(kaptchaAuthenticationProvider());
        return new ProviderManager(list);
    }

    @Bean
    public DefaultAuthenticationFilter loginFilter(AuthenticationManager authenticationManager) {
        DefaultAuthenticationFilter filter = new DefaultAuthenticationFilter(new AntPathRequestMatcher("/login", "POST"));
        filter.setAuthenticationManager(authenticationManager);
        filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/"));
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error=true"));
        return filter;
    }
}
