package com.aaebike.controller.user;

import java.util.List;

import com.aaebike.common.kaptcha.ValidateCodeHandle;
import com.aaebike.common.utils.RandomUtils;
import com.aaebike.model.*;
import com.aaebike.model.form.InfoUpdateForm;
import com.aaebike.service.OrderService;
import com.aaebike.service.ProductService;
import com.aaebike.service.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final int CODE_LENGTH = 6;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/info")
    public ModelAndView info() {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getById(userInfo.getUserId());

        InfoUpdateForm infoUpdateForm = new InfoUpdateForm();
        infoUpdateForm.setUsername(user.getUsername());
        infoUpdateForm.setName(user.getName());
        infoUpdateForm.setEmail(user.getEmail());
        infoUpdateForm.setTel(user.getTel());

        ModelAndView result = new ModelAndView("/user/info");
        result.addObject("infoUpdateForm", infoUpdateForm);
        return result;
    }

    @RequestMapping("/order")
    public String order() {
        return "user/order";
    }

    @RequestMapping("/orderList")
    @ResponseBody
    public PageInfo<OrderDetail> orderList(SaleOrder saleOrder) {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getById(userInfo.getUserId());

        saleOrder.setUserId(user.getId());
        List<OrderDetail> orderList = orderService.getOrderList(saleOrder);

        return new PageInfo<>(orderList);
    }

    @RequestMapping("/buy/{productId}")
    public String buy(@PathVariable Integer productId) {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = userInfo.getUserId();

        if (productId == null || productId == 0) {
            logger.error("[下单]userId:{}下单入参productId为空或0", userId);
            return "index";
        }

        Product product = productService.getProductById(productId);
        if (product == null) {
            logger.error("[下单]userId:{}下单入参productId:{}，该产品不存在", userId, productId);
            return "index";
        }

        String randomCode = RandomUtils.getRandomCode(CODE_LENGTH);
        logger.info("[下单][入参]userId:{},productId:{},price:{},randomCode:{}", userId, productId, product.getSalePrice(), randomCode);

        Boolean success = orderService.createOrder(userId, productId, product.getSalePrice(), randomCode);
        if (success) {
            logger.info("[下单][结果]userId:{},productId:{}下单成功", userId, productId);
        } else {
            logger.error("[下单][结果]userId:{},productId:{}下单失败", userId, productId);
        }

        return "redirect:/user/order";
    }

    @RequestMapping("/info/update")
    public String update(HttpServletRequest request, @Valid InfoUpdateForm infoUpdateForm, BindingResult bindingResult) {// 校验验证码
        String kaptcha = request.getParameter("kaptcha");
        if (!ValidateCodeHandle.matchCode(request.getSession().getId(), kaptcha)) {
            bindingResult.rejectValue("kaptcha","misFormat", "验证码错误!");
        }

        if (bindingResult.hasErrors()) {
            return "user/info";
        }

        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        user.setId(userInfo.getUserId());
        user.setUsername(infoUpdateForm.getUsername());
        user.setName(infoUpdateForm.getName());
        user.setEmail(infoUpdateForm.getEmail());
        user.setTel(infoUpdateForm.getTel());

        userService.updateUserInfo(user);
        return "redirect:/user/info";
    }
}
