package com.aaebike.model.form;

import lombok.Data;

@Data
public class UserRegisterVo {
    private String username;

    private String password;

    private String passwordConfirm;

    private String name;

    private String email;

    private String tel;

    private String kaptcha;
}
