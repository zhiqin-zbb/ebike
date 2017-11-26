package com.aaebike.model;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Data
public class UserInfo extends User {
    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String name;

    public UserInfo(Integer userId, String username, String password, String name, List<SimpleGrantedAuthority> authorities)
            throws IllegalArgumentException {
        super(username, password, authorities);
        this.userId = userId;
        this.name = name;
    }
}
