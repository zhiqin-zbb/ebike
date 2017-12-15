package com.aaebike.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aaebike.model.Product;
import com.aaebike.model.User;

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
    public String test(ModelMap modelMap) {
        modelMap.put("msg", "SpringBoot Ajax 示例");
        return "test";
    }

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public List<User> data() {
        List<User> list = new ArrayList<User>();

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i + 1);
            user.setUsername("username" + i);
            user.setName("name" + i);

            list.add(user);
        }

        return list;
    }
}
