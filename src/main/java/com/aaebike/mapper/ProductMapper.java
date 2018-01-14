package com.aaebike.mapper;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.model.Product;
import com.aaebike.model.ProductDetail;

import java.util.List;

public interface ProductMapper extends MyMapper<Product> {
    List<Product> getProductList(Product product);

    ProductDetail getProductDetailById(Product product);

    List<Product> search(Product product);
}
