package com.aaebike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aaebike.model.Product;

/**
 * @author zhangbinbin
 * @version 2017/12/13
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getProductList(Product product) {
        return "newindex";
    }

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test(Product product) {
        return "test";
    }
}
