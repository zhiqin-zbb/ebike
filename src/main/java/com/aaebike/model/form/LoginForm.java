package com.aaebike.model.form;

import javax.validation.constraints.Pattern;

import lombok.Data;

/**
 * @author zhangbinbin
 * @version 2017/12/7
 */
@Data
public class LoginForm {
    @Pattern(regexp = "^[A-Za-z0-9]{6,20}$", message = "请输入6-20位字母或数字的用户名！")
    private String username;

    @Pattern(regexp = "^[A-Za-z0-9]{8,20}$", message = "请输入8位以上字母或数字的密码！")
    private String password;

    private String kaptcha;
}
