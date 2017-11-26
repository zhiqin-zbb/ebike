package com.aaebike.controller;

import com.aaebike.model.Product;
import com.aaebike.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getProductList(Product product) {
        ModelAndView result = new ModelAndView("index");

        List<Product> productList = productService.getProductList(product);
        result.addObject("productList", new PageInfo<>(productList));
        result.addObject("queryParam", product);
        result.addObject("page", product.getPage());
        result.addObject("rows", product.getRows());
        return result;
    }
}
