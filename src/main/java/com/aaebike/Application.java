package com.aaebike;

import com.aaebike.model.Product;
import com.aaebike.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.List;

@Controller
@EnableWebMvc
@SpringBootApplication
@MapperScan(basePackages = "com.aaebike.mapper")
public class Application extends WebMvcConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public ModelAndView greeting(Model model) {
        Product product = new Product();
        product.setRows(12);
        List<Product> productList = productService.getProductList(product);

        ModelAndView result = new ModelAndView("index");
        result.addObject("productList", new PageInfo<>(productList));
        result.addObject("queryParam", product);
        result.addObject("page", product.getPage());
        result.addObject("rows", product.getRows());
        return result;
    }

    public static void main(String[] args) throws InterruptedException,
            IOException {
        SpringApplication.run(Application.class, args);
        // 初始化 支付宝 微信 银联 参数 涉及机密 此文件不提交 请自行配置加载
        // Configs.init("zfbinfo.properties");
        // ConfigUtil.init("wxinfo.properties");
        // SDKConfig.getConfig().loadPropertiesFromSrc();
    }
}