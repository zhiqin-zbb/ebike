package com.aaebike.controller.api;

import com.aaebike.common.constants.ErrorConstants;
import com.aaebike.model.Brand;
import com.aaebike.model.Product;
import com.aaebike.model.ProductDetail;
import com.aaebike.model.ResponseVo;
import com.aaebike.service.BrandService;
import com.aaebike.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductApiController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/brand/list", method = RequestMethod.GET)
    public ResponseVo getBrandList(Integer page, Integer rows) {
        Brand brand = new Brand();
        brand.setPage(page);
        brand.setRows(rows);
        List<Brand> brandList = brandService.getBrandList(brand);
        return ResponseVo.valueOf(CollectionUtils.isNotEmpty(brandList), new PageInfo<>(brandList), ErrorConstants.BRAND_NOT_FOUND);
    }

    @RequestMapping(value = "/product/list", method = RequestMethod.GET)
    public ResponseVo getProductList(Product product) {
        List<Product> productList = productService.getProductList(product);
        return ResponseVo.valueOf(CollectionUtils.isNotEmpty(productList), new PageInfo<>(productList), ErrorConstants.PRODUCT_NOT_FOUND);
    }

    @RequestMapping(value = "/product/detail", method = RequestMethod.GET)
    public ResponseVo getProductDetail(Integer productId) {
        if (productId == null || productId == 0) {
            return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
        }

        ProductDetail productDetail = productService.getProductDetailById(productId);

        if (productDetail == null) {
            return ResponseVo.valueOf(false, null, ErrorConstants.PRODUCT_NOT_FOUND);
        }

        // 如果详情图片为空，则展示封面图片
        if (StringUtils.isNotEmpty(productDetail.getImgUrl())) {
            productDetail.setImgUrlList(Arrays.asList(productDetail.getImgUrl().split(";")));
        } else if (StringUtils.isNotEmpty(productDetail.getCover())) {
            productDetail.setImgUrlList(Collections.singletonList(productDetail.getCover()));
        }

        return ResponseVo.valueOf(true, productDetail, null);
    }

    @RequestMapping(value = "/product/search", method = RequestMethod.POST)
    public ResponseVo search(Product product) {
        List<Product> productList = productService.search(product);
        return ResponseVo.valueOf(CollectionUtils.isNotEmpty(productList), new PageInfo<>(productList), ErrorConstants.PRODUCT_NOT_FOUND);
    }
}
