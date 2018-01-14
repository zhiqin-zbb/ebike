package com.aaebike.controller;

import com.aaebike.model.Brand;
import com.aaebike.model.Product;
import com.aaebike.model.ProductDetail;
import com.aaebike.model.result.ProductListResult;
import com.aaebike.service.BrandService;
import com.aaebike.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Product> data(Product product) {
        List<Product> productList = productService.getProductList(product);
        return new PageInfo<>(productList);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ProductListResult getProductList(Product product) {
        List<Brand> brandList = brandService.getActiveBrandList();
        brandList.add(0, Brand.initAllBrand());

        List<Product> productList = productService.getProductList(product);

        ProductListResult result = new ProductListResult(brandList, new PageInfo<>(productList));
        result.setBrandId(product.getBrandId() != null ? product.getBrandId() : 0);
        return result;
    }

    @RequestMapping(value = "/detail/{productId}")
    public ModelAndView getProductDetail(@PathVariable Integer productId) {
        ProductDetail productDetail = productService.getProductDetailById(productId);

        if (productDetail != null) {
            // 如果详情图片为空，则展示封面图片
            if (StringUtils.isNotEmpty(productDetail.getImgUrl())) {
                productDetail.setImgUrlList(Arrays.asList(productDetail.getImgUrl().split(";")));
            } else if (StringUtils.isNotEmpty(productDetail.getCover())) {
                productDetail.setImgUrlList(Collections.singletonList(productDetail.getCover()));
            }
        }

        ModelAndView result = new ModelAndView("product/detail");
        result.addObject("productDetail", productDetail);
        return result;
    }

    @RequestMapping(value = "/searchResult", method = RequestMethod.GET)
    public ModelAndView searchResult(String name) {
        ModelAndView result = new ModelAndView("product/searchResult");
        result.addObject("name", name);
        return result;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Product> search(Product product) {
        List<Product> productList = productService.search(product);
        return new PageInfo<>(productList);
    }
}
