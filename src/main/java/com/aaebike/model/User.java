package com.aaebike.model;

import lombok.Data;

import java.util.Date;

@Data
public class User extends BaseEntity {
    private String username;

    private String password;

    private String name;

    private String email;

    private String tel;

    private Date createTime;

    private Date updateTime;

    private Integer delFlag;
}
