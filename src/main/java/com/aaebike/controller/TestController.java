package com.aaebike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangbinbin
 * @version 2017/11/23
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/1")
    public String test1() {
        return "test";
    }

    @RequestMapping("/2")
    public String test2() {
        return "test1";
    }
}
