package com.aaebike.entity.table;

import javax.persistence.Table;

import com.aaebike.entity.base.TableBaseEntity;

import lombok.Data;

@Data
@Table(name = "product")
public class Product extends TableBaseEntity {
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
}
