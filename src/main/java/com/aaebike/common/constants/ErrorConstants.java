package com.aaebike.common.constants;

import lombok.Getter;
import lombok.Setter;

public enum ErrorConstants {
    REQUEST_PARAM_INVALID(1, "请求参数错误"),

    USER_NOT_FOUND(2, "用户不存在"),

    USER_ALREADY_EXIST(3, "用户已存在"),

    USER_REGISTER_ERROR(4, "用户注册失败"),

    USER_PASSWORD_ERROR(5, "密码错误"),

    ORDER_NOT_FOUND(6, "订单不存在"),

    PRODUCT_NOT_FOUND(7, "产品不存在"),

    CREATE_ORDER_FAILED(8, "下单失败"),

    UPDATE_USER_INFO_FAILED(9, "更新用户信息失败"),

    ORIGINAL_PASSWORD_ERROR(10, "原密码错误"),

    UPDATE_USER_PASSWORD_FAILED(11, "更新用户密码失败"),

    BRAND_NOT_FOUND(12, "品牌不存在"),

    CAROUSEL_NOT_FOUND(13, "图片轮播不存在"),

    ADVERTISEMENT_NOT_FOUND(14, "广告不存在");

    @Getter
    @Setter
    private Integer errorCode;

    @Getter
    @Setter
    private String errorMsg;

    ErrorConstants(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
