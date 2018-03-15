package com.aaebike.entity.table;

import javax.persistence.Table;

import com.aaebike.entity.base.TableBaseEntity;

import lombok.Data;

@Data
@Table(name = "sale_order")
public class Order extends TableBaseEntity {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 快递地址ID
     */
    private Integer expressId;

    /**
     * 下单价格
     */
    private Double price;

    /**
     * 随机码
     */
    private String randomCode;
}
