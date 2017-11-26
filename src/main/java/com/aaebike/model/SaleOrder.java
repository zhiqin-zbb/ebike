package com.aaebike.model;

import lombok.Data;

import java.util.Date;

@Data
public class SaleOrder extends BaseEntity {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 下单价格
     */
    private Double price;

    /**
     * 随机码
     */
    private String randomCode;

    private Date createTime;

    private Date updateTime;

    private int delFlag;
}
