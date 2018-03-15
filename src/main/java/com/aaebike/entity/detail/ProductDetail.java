package com.aaebike.entity.detail;

import java.util.List;

import com.aaebike.entity.table.Product;

import lombok.Data;

@Data
public class ProductDetail extends Product {
    /**
     * 品牌名称
     */
    String brandName;

    List<String> imgUrlList;
}
