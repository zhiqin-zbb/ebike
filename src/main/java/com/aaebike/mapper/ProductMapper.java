package com.aaebike.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.entity.detail.ProductDetail;
import com.aaebike.entity.table.Product;

@Repository
public interface ProductMapper extends MyMapper<Product> {
    List<Product> getProductList(Product product);

    ProductDetail getProductDetailById(Product product);

    List<Product> search(Product product);
}
