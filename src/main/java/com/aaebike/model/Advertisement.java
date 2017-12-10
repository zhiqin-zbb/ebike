package com.aaebike.model;

import lombok.Data;

import java.util.Date;

@Data
public class Advertisement extends BaseEntity {
    private String name;

    private String imgUrl;

    private Date createTime;

    private Date updateTime;

    private int delFlag;
}
