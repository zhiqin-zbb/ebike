package com.aaebike.model.form;

import lombok.Data;

@Data
public class UserInfoUpdateVo {
    private Integer userId;

    private String name;

    private String email;

    private String tel;
}
