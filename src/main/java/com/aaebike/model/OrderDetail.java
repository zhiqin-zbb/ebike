package com.aaebike.model;

import lombok.Data;

@Data
public class OrderDetail extends SaleOrder {
    /**
     * 品牌
     */
    private String brandName;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 市场价
     */
    private Double marketPrice;
}
