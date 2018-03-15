package com.aaebike.entity.request;

import lombok.Data;

@Data
public class UserLoginVo {
    private String username;

    private String password;

    private String kaptcha;
}
