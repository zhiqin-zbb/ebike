package com.aaebike.entity.request;

import lombok.Data;

@Data
public class UserInfoUpdateVo {
    private Integer userId;

    private String name;

    private String nickname;

    private String email;

    private String tel;

    private String kaptcha;
}
