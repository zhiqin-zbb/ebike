package com.aaebike.model;

import lombok.Data;

import java.util.Date;

@Data
public class Product extends BaseEntity {
    private String name;

    private String description;

    private Integer brandId;

    private Double price;

    private String imgUrl;

    private Date createTime;

    private Date updateTime;

    private int delFlag;
}
