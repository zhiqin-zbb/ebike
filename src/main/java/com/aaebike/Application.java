package com.aaebike;

import com.alipay.demo.trade.config.Configs;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;

/**
 * 支付主控
 * 启动   java -jar ebike.jar --server.port=8886
 * linux 下 后台启动  nohup java -jar ebike.jar --server.port=8886 &
 */
@SpringBootApplication
@ImportResource({"classpath:spring-context-dubbo.xml"})
@Controller
public class Application extends WebMvcConfigurerAdapter {
    private static final Logger logger = Logger.getLogger(Application.class);

    @RequestMapping("/")
    public String greeting() {
        return "index";
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/cert/**").addResourceLocations(
                "classpath:/cert/");
        super.addResourceHandlers(registry);
    }

    public static void main(String[] args) throws InterruptedException,
            IOException {
        SpringApplication.run(Application.class, args);
        // 初始化 支付宝 微信 银联 参数 涉及机密 此文件不提交 请自行配置加载
        Configs.init("zfbinfo.properties");
        //ConfigUtil.init("wxinfo.properties");
        //SDKConfig.getConfig().loadPropertiesFromSrc();
    }
}