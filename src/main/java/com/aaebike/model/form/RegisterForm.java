package com.aaebike.model.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import lombok.Data;

/**
 * @author zhangbinbin
 * @version 2017/12/4
 */
@Data
public class RegisterForm {
    @Pattern(regexp = "^[A-Za-z0-9]{6,20}$", message = "请输入6-20位字母或数字的用户名!")
    private String username;

    @Pattern(regexp = "^[A-Za-z0-9]{8,20}$", message = "请输入8-20位字母或数字的密码!")
    private String password;

    private String passwordConfirm;

    @Pattern(regexp = "^[\u4e00-\u9fa5]{2,}$", message = "请输入真实的中文姓名!")
    private String name;

    @Email
    private String email;

    private String tel;

    private String kaptcha;
}
