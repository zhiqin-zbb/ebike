package com.aaebike.entity.request;

import lombok.Data;

@Data
public class UserRegisterVo {
    private String username;

    private String password;

    private String passwordConfirm;

    private String name;

    private String nickname;

    private String email;

    private String tel;

    private String kaptcha;
}
