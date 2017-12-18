package com.aaebike.model;

import lombok.Data;

import java.util.Date;

@Data
public class Product extends BaseEntity {
    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品详情
     */
    private String description;

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 市场价
     */
    private Double marketPrice;

    /**
     * 销售价
     */
    private Double salePrice;

    /**
     * 封面图片地址
     */
    private String cover;

    /**
     * 图片地址
     */
    private String imgUrl;

    private Date createTime;

    private Date updateTime;

    private int delFlag;
}
