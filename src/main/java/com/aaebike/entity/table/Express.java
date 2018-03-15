package com.aaebike.entity.table;

import javax.persistence.Table;

import com.aaebike.entity.base.TableBaseEntity;

import lombok.Data;

@Data
@Table(name = "express")
public class Express extends TableBaseEntity {
    private Integer userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String tel;

    /**
     * 地址
     */
    private String address;

    /**
     * 是否默认地址，0-否，1-是
     */
    private Integer defaultFlag;
}
