package com.aaebike.entity.result;

import java.util.List;

import com.aaebike.entity.table.Brand;
import com.aaebike.entity.table.Product;
import com.github.pagehelper.PageInfo;

import lombok.Data;

@Data
public class ProductListResult {
    private Integer brandId;

    private List<Brand> brandList;

    private PageInfo<Product> productPageInfo;

    public ProductListResult() {
    }

    public ProductListResult(List<Brand> brandList, PageInfo<Product> productPageInfo) {
        this.brandList = brandList;
        this.productPageInfo = productPageInfo;
    }
}
