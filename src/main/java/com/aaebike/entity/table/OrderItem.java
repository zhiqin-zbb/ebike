package com.aaebike.entity.table;

import javax.persistence.Table;

import com.aaebike.entity.base.TableBaseEntity;

import lombok.Data;

@Data
@Table(name = "order_item")
public class OrderItem extends TableBaseEntity {
    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 数量
     */
    private Integer count;
}
