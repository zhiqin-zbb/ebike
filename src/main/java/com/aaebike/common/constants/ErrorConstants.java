package com.aaebike.common.constants;

import lombok.Getter;
import lombok.Setter;

public enum ErrorConstants {
    REQUEST_PARAM_INVALID(1, "请求参数错误"),

    USER_NOT_FOUND(2, "用户不存在"),

    USER_ALREADY_EXIST(3, "用户已存在"),

    USER_REGISTER_ERROR(4, "用户注册失败"),

    KAPTCHA_ERROR(5, "验证码错误"),

    USER_PASSWORD_ERROR(6, "密码错误"),

    ORDER_NOT_FOUND(7, "订单不存在"),

    PRODUCT_NOT_FOUND(8, "产品不存在"),

    CREATE_ORDER_FAILED(9, "下单失败"),

    UPDATE_USER_INFO_FAILED(10, "更新用户信息失败"),

    ORIGINAL_PASSWORD_ERROR(11, "原密码错误"),

    UPDATE_USER_PASSWORD_FAILED(12, "更新用户密码失败"),

    BRAND_NOT_FOUND(13, "品牌不存在"),

    CAROUSEL_NOT_FOUND(14, "图片轮播不存在"),

    ADVERTISEMENT_NOT_FOUND(15, "广告不存在"),

    NOT_IMAGE_FILE_ERROR(16, "上传的不是图片文件"),

    FILE_SIZE_EXCEEDED_EXCEPTION(17, "文件大小超出限制"),

    FILE_IO_EXCEPTION(18, "上传时发生错误"),

    CREDIT_ALREADY_SAVED(19, "该用户已经上传过信用卡信息"),

    CREDIT_NOT_FOUND(20, "该用户的信用卡信息不存在"),

    SAVE_CREDIT_ERROR(21, "保存信用卡失败"),

    SAVE_EXPRESS_ERROR(22, "保存快递失败"),

    EXPRESS_NOT_FOUND(23, "快递信息不存在");

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
