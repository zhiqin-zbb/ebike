package com.aaebike.model;

import lombok.Data;

import java.util.Date;

@Data
public class Brand extends BaseEntity {
    private String name;

    private Date createTime;

    private Date updateTime;

    private int delFlag;

    public static Brand initAllBrand() {
        Brand allBrand = new Brand();
        allBrand.setId(0);
        allBrand.setName("所有品牌");
        return allBrand;
    }
}
