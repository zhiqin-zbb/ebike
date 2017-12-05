package com.aaebike.model;

import java.util.Date;

import lombok.Data;

@Data
public class User extends BaseEntity {
    private String username;

    private String password;

    private String name;

    private String email;

    private String tel;

    private Date createTime;

    private Date updateTime;

    private int delFlag;
}
