package com.aaebike.model;

import lombok.Data;

/**
 * @author zhangbinbin
 * @version 2018/2/12
 */
@Data
public class OrderItemDetail {
    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 数量
     */
    private Integer count;

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

    /**
     * 商品封面
     */
    private String cover;
}
