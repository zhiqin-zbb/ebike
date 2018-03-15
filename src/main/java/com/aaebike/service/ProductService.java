package com.aaebike.service;

import com.aaebike.common.constants.StringConstants;
import com.aaebike.entity.detail.ProductDetail;
import com.aaebike.entity.table.Product;
import com.aaebike.mapper.ProductMapper;
import com.github.pagehelper.PageHelper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public Product getProductById(Integer productId) {
        Product product = new Product();
        product.setId(productId);
        return productMapper.selectByPrimaryKey(product);
    }

    public ProductDetail getProductDetailById(Integer productId) {
        Product product = new Product();
        product.setId(productId);
        ProductDetail productDetail = productMapper.getProductDetailById(product);

        if (productDetail == null) {
            return null;
        }

        if (StringUtils.isNotEmpty(productDetail.getImgUrl())) {
            productDetail.setImgUrlList(Arrays.asList(productDetail.getImgUrl().split(StringConstants.SEMICOLON)));
        }

        return productDetail;
    }

    public List<Product> getProductList(Product product) {
        if (product.getPage() != null && product.getRows() != null) {
            PageHelper.startPage(product.getPage(), product.getRows());
        }
        return productMapper.getProductList(product);
    }

    public List<Product> search(Product product) {
        if (product.getPage() != null && product.getRows() != null) {
            PageHelper.startPage(product.getPage(), product.getRows());
        }
        return productMapper.search(product);
    }
}
