package com.aaebike.entity.request;

import lombok.Data;

@Data
public class UserPasswordUpdateVo {
    private Integer userId;

    private String originalPassword;

    private String newPassword;

    private String kaptcha;
}
