package com.aaebike.entity.table;

import javax.persistence.Column;
import javax.persistence.Table;

import com.aaebike.entity.base.TableBaseEntity;

import lombok.Data;

@Data
@Table(name = "user")
public class User extends TableBaseEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;
}
