package com.aaebike.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aaebike.common.constants.ErrorConstants;
import com.aaebike.common.constants.SystemConstants;
import com.aaebike.common.kaptcha.ValidateCodeHandle;
import com.aaebike.common.utils.RandomUtils;
import com.aaebike.entity.base.ResponseVo;
import com.aaebike.entity.detail.OrderDetail;
import com.aaebike.entity.detail.OrderItemDetail;
import com.aaebike.entity.request.BuyRequest;
import com.aaebike.entity.request.UserInfoUpdateVo;
import com.aaebike.entity.request.UserLoginVo;
import com.aaebike.entity.request.UserPasswordUpdateVo;
import com.aaebike.entity.request.UserRegisterVo;
import com.aaebike.entity.table.Order;
import com.aaebike.entity.table.OrderItem;
import com.aaebike.entity.table.Product;
import com.aaebike.entity.table.User;
import com.aaebike.service.OrderService;
import com.aaebike.service.ProductService;
import com.aaebike.service.UserService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api")
public class UserApiController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseVo login(HttpServletRequest request, UserLoginVo userLoginVo) {
        if (userLoginVo == null || StringUtils.isEmpty(userLoginVo.getUsername()) || StringUtils.isEmpty(userLoginVo.getPassword()) || StringUtils.isEmpty(request.getParameter("kaptcha"))) {
            return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
        }

        String kaptcha = request.getParameter("kaptcha");
        if (!ValidateCodeHandle.matchCode(request.getSession().getId(), kaptcha)) {
            return ResponseVo.valueOf(false, null, ErrorConstants.KAPTCHA_ERROR);
        }

        User user = userService.findByUsername(userLoginVo.getUsername());
        if (user == null) {
            user = userService.findByNickname(userLoginVo.getUsername());
            if (user == null) {
                logger.error("[用户信息]username:{}用户不存在", userLoginVo.getUsername());
                return ResponseVo.valueOf(false, null, ErrorConstants.USER_NOT_FOUND);
            }
        }

        if (passwordEncoder.matches(userLoginVo.getPassword(), user.getPassword())) {
            user.setPassword(null);
            return ResponseVo.valueOf(true, user, null);
        } else {
            return ResponseVo.valueOf(false, null, ErrorConstants.USER_PASSWORD_ERROR);
        }
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseVo register(HttpServletRequest request, UserRegisterVo userRegisterVo) {
        if (userRegisterVo == null || StringUtils.isEmpty(userRegisterVo.getUsername()) || StringUtils.isEmpty(userRegisterVo.getPassword()) ||
                StringUtils.isEmpty(userRegisterVo.getPasswordConfirm()) || StringUtils.isEmpty(userRegisterVo.getName()) ||
                StringUtils.isEmpty(userRegisterVo.getNickname()) || StringUtils.isEmpty(userRegisterVo.getEmail()) || StringUtils.isEmpty(userRegisterVo.getTel()) ||
                !userRegisterVo.getPassword().equals(userRegisterVo.getPasswordConfirm()) || StringUtils.isEmpty(request.getParameter("kaptcha"))) {
            return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
        }

        String kaptcha = request.getParameter("kaptcha");
        if (!ValidateCodeHandle.matchCode(request.getSession().getId(), kaptcha)) {
            return ResponseVo.valueOf(false, null, ErrorConstants.KAPTCHA_ERROR);
        }

        // 判断username是否已存在
        User find = userService.findByUsername(userRegisterVo.getUsername());
        if (find != null) {
            logger.error("[用户注册]username:{}已存在", userRegisterVo.getUsername());
            return ResponseVo.valueOf(false, null, ErrorConstants.USERNAME_ALREADY_EXIST);
        }

        // 判断nickname是否已存在
        find = userService.findByNickname(userRegisterVo.getNickname());
        if (find != null) {
            logger.error("[用户注册]nickname:{}已存在", userRegisterVo.getNickname());
            return ResponseVo.valueOf(false, null, ErrorConstants.NICKNAME_ALREADY_EXIST);
        }

        // 判断nickname是否与username重合
        find = userService.findByUsername(userRegisterVo.getNickname());
        if (find != null) {
            logger.error("[用户注册]nickname:{}用户名、昵称冲突", userRegisterVo.getNickname());
            return ResponseVo.valueOf(false, null, ErrorConstants.USERNAME_NICKNAME_CONFLICT);
        }


        User user = new User();
        user.setUsername(userRegisterVo.getUsername());
        user.setPassword(userRegisterVo.getPassword());
        user.setName(userRegisterVo.getName());
        user.setNickname(userRegisterVo.getNickname());
        user.setEmail(userRegisterVo.getEmail());
        user.setTel(userRegisterVo.getTel());
        user.setCreateTime(new Date());

        return ResponseVo.valueOf(userService.save(user) != 0, null, ErrorConstants.USER_REGISTER_ERROR);
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public ResponseVo info(Integer userId) {
        if (userId == null || userId == 0) {
            logger.error("[用户信息]入参userId为空或0");
            return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
        }

        User user = userService.getById(userId);
        if (user == null) {
            logger.error("[用户信息]userId:{}用户不存在", userId);
            return ResponseVo.valueOf(false, null, ErrorConstants.USER_NOT_FOUND);
        }

        user.setPassword(null);
        return ResponseVo.valueOf(true, user, null);
    }

    @RequestMapping(value = "/user/info/update", method = RequestMethod.POST)
    public ResponseVo updateUserInfo(HttpServletRequest request, UserInfoUpdateVo userInfoUpdateVo) {
        if (userInfoUpdateVo == null || userInfoUpdateVo.getUserId() == null || userInfoUpdateVo.getUserId() == 0
                || StringUtils.isEmpty(userInfoUpdateVo.getName()) || StringUtils.isEmpty(userInfoUpdateVo.getEmail()) ||
                StringUtils.isEmpty(userInfoUpdateVo.getNickname()) || StringUtils.isEmpty(userInfoUpdateVo.getTel()) || StringUtils.isEmpty(request.getParameter("kaptcha"))) {
            return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
        }

        String kaptcha = request.getParameter("kaptcha");
        if (!ValidateCodeHandle.matchCode(request.getSession().getId(), kaptcha)) {
            return ResponseVo.valueOf(false, null, ErrorConstants.KAPTCHA_ERROR);
        }

        User user = userService.getById(userInfoUpdateVo.getUserId());
        if (user == null) {
            logger.error("[更新用户信息]userId:{}不存在", userInfoUpdateVo.getUserId());
            return ResponseVo.valueOf(false, null, ErrorConstants.USER_NOT_FOUND);
        }

        User findByNickname = userService.findByNickname(userInfoUpdateVo.getNickname());
        if (findByNickname != null && !findByNickname.getId().equals(userInfoUpdateVo.getUserId())) {
            logger.error("[更新用户信息]nickname:{}已存在", userInfoUpdateVo.getNickname());
            return ResponseVo.valueOf(false, null, ErrorConstants.NICKNAME_ALREADY_EXIST);
        }

        // 判断nickname是否与username重合
        User findByUsername = userService.findByUsername(userInfoUpdateVo.getNickname());
        if (findByUsername != null && !findByUsername.getId().equals(userInfoUpdateVo.getUserId())) {
            logger.error("[用户注册]nickname:{}用户名、昵称冲突", userInfoUpdateVo.getNickname());
            return ResponseVo.valueOf(false, null, ErrorConstants.USERNAME_NICKNAME_CONFLICT);
        }

        user.setUsername(null);
        user.setPassword(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);

        user.setName(userInfoUpdateVo.getName());
        user.setNickname(userInfoUpdateVo.getNickname());
        user.setEmail(userInfoUpdateVo.getEmail());
        user.setTel(userInfoUpdateVo.getTel());
        return ResponseVo.valueOf(userService.updateUserInfo(user) != 0, null, ErrorConstants.UPDATE_USER_INFO_FAILED);
    }

    @RequestMapping(value = "/user/password/update", method = RequestMethod.POST)
    public ResponseVo updateUserPassword(HttpServletRequest request, UserPasswordUpdateVo userPasswordUpdateVo) {
        if (userPasswordUpdateVo == null || userPasswordUpdateVo.getUserId() == null || userPasswordUpdateVo.getUserId() == 0
                || StringUtils.isEmpty(userPasswordUpdateVo.getOriginalPassword()) || StringUtils.isEmpty(userPasswordUpdateVo.getNewPassword()) ||
                StringUtils.isEmpty(request.getParameter("kaptcha"))) {
            return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
        }

        String kaptcha = request.getParameter("kaptcha");
        if (!ValidateCodeHandle.matchCode(request.getSession().getId(), kaptcha)) {
            return ResponseVo.valueOf(false, null, ErrorConstants.KAPTCHA_ERROR);
        }

        User user = userService.getById(userPasswordUpdateVo.getUserId());
        if (user == null) {
            logger.error("[更新用户密码]userId:{}用户不存在", userPasswordUpdateVo.getUserId());
            return ResponseVo.valueOf(false, null, ErrorConstants.USER_NOT_FOUND);
        } else if (!passwordEncoder.matches(userPasswordUpdateVo.getOriginalPassword(), user.getPassword())) {
            logger.error("[更新用户密码]userId:{}用户原密码错误", userPasswordUpdateVo.getUserId());
            return ResponseVo.valueOf(false, null, ErrorConstants.ORIGINAL_PASSWORD_ERROR);
        }

        user.setUsername(null);
        user.setName(null);
        user.setEmail(null);
        user.setTel(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);

        user.setPassword(passwordEncoder.encode(userPasswordUpdateVo.getNewPassword()));
        return ResponseVo.valueOf(userService.updateUserInfo(user) != 0, null, ErrorConstants.UPDATE_USER_PASSWORD_FAILED);
    }

    @RequestMapping(value = "/user/orderList", method = RequestMethod.GET)
    public ResponseVo orderList(Integer userId, Integer page, Integer rows) {
        if (userId == null || userId == 0) {
            logger.error("[用户订单列表]入参userId为空或0");
            return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
        }

        User user = userService.getById(userId);
        if (user == null) {
            logger.error("[用户订单列表]userId:{}用户不存在", userId);
            return ResponseVo.valueOf(false, null, ErrorConstants.USER_NOT_FOUND);
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setPage(page);
        order.setRows(rows);

        List<OrderDetail> orderList = orderService.getOrderList(order);
        if (CollectionUtils.isNotEmpty(orderList)) {
            for (OrderDetail orderDetail : orderList) {
                List<OrderItemDetail> orderItemDetailList = orderService.getOrderItemList(orderDetail.getId());
                orderDetail.setProductList(orderItemDetailList);
            }
        }
        return ResponseVo.valueOf(CollectionUtils.isNotEmpty(orderList), new PageInfo<>(orderList), ErrorConstants.ORDER_NOT_FOUND);
    }

    @RequestMapping(value = "/user/buy", method = RequestMethod.POST)
    public ResponseVo buy(@RequestBody BuyRequest request) {
        if (request == null || request.getUserId() == null || request.getUserId() == 0 || request.getExpressId() == null || request.getExpressId() == 0 || CollectionUtils.isEmpty(request.getProductList())) {
            logger.error("[用户下单]入参userId、expresstId或productList为空或0");
            return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
        }

        User user = userService.getById(request.getUserId());
        if (user == null) {
            logger.error("[下单]userId:{}下单，该用户不存在", request.getUserId());
            return ResponseVo.valueOf(false, null, ErrorConstants.USER_NOT_FOUND);
        }

        Double totalAmount = 0D;
        for (OrderItem product : request.getProductList()) {
            if (product.getProductId() == null || product.getProductId() == 0 || product.getCount() == null || product.getCount() == 0) {
                logger.error("[用户下单]userId:{}下单，入参productList中productId、count为空或0:{}", request.getUserId(), product);
                return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
            }

            Product productById = productService.getProductById(product.getProductId());
            if (productById == null) {
                logger.error("[下单]userId:{}下单入参productId:{}，该产品不存在", request.getUserId(), product.getProductId());
                return ResponseVo.valueOf(false, null, ErrorConstants.PRODUCT_NOT_FOUND);
            }

            totalAmount += (productById.getSalePrice() * product.getCount());
        }

        String randomCode = RandomUtils.getRandomCode(SystemConstants.CODE_LENGTH);
        logger.info("[下单][入参]userId:{},price:{},randomCode:{}", request.getUserId(), totalAmount, randomCode);


        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setExpressId(request.getExpressId());
        order.setPrice(totalAmount);
        order.setRandomCode(randomCode);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        Boolean successFlag = orderService.createOrder(order);

        if (!successFlag) {
            logger.error("[下单]下单失败，入参:{}", request);
            return ResponseVo.valueOf(false, null, ErrorConstants.CREATE_ORDER_FAILED);
        } else {
            for (OrderItem product : request.getProductList()) {
                product.setOrderId(order.getId());
                if (!orderService.saveOrderDetail(product)) {
                    logger.error("[下单]下单失败，入参:{}", request);
                    return ResponseVo.valueOf(false, null, ErrorConstants.CREATE_ORDER_FAILED);
                }
            }
        }
        return ResponseVo.valueOf(true, randomCode, ErrorConstants.CREATE_ORDER_FAILED);
    }
}
