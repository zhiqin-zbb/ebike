package com.aaebike.model;

import java.util.List;

import lombok.Data;

/**
 * @author zhangbinbin
 * @version 2017/12/7
 */
@Data
public class ProductDetail extends Product {
    /**
     * 品牌名称
     */
    String brandName;

    List<String> imgUrlList;
}
