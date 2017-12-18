package com.aaebike.model.result;

import com.aaebike.model.Brand;
import com.aaebike.model.Product;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

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
