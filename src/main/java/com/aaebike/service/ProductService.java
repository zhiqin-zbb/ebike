package com.aaebike.service;

import com.aaebike.common.constants.StringConstants;
import com.aaebike.mapper.ProductMapper;
import com.aaebike.model.Product;
import com.aaebike.model.ProductDetail;
import com.github.pagehelper.PageHelper;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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

        if (StringUtils.isNotEmpty(productDetail.getImgUrl())) {
            productDetail.setImgUrlList(Arrays.asList(productDetail.getImgUrl().split(StringConstants.SEMICOLON)));
        }

        return productDetail;
    }

    public List<Product> getProductList(Product product) {
        if (product.getPage() != null && product.getRows() != null) {
            PageHelper.startPage(product.getPage(), product.getRows());
        }
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", 0);

        if (product.getBrandId() != null && product.getBrandId() > 0) {
            criteria.andEqualTo("brandId", product.getBrandId());
        }

        example.orderBy("updateTime").desc();
        return productMapper.selectByExample(example);
    }
}
