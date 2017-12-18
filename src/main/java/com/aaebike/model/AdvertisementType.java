package com.aaebike.model;

import lombok.Data;

import java.util.Date;

@Data
public class AdvertisementType extends BaseEntity {
    private String name;

    private Date createTime;

    private Date updateTime;

    private int delFlag;
}
