package com.aaebike.mapper;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.model.Product;
import com.aaebike.model.ProductDetail;

public interface ProductMapper extends MyMapper<Product> {
    ProductDetail getProductDetailById(Product product);
}
