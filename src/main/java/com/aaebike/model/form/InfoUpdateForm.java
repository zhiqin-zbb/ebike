package com.aaebike.model.form;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;

@Data
public class InfoUpdateForm {
    @Pattern(regexp = "^[A-Za-z0-9]{6,20}$", message = "请输入6-20位字母或数字的用户名!")
    private String username;

    @Pattern(regexp = "^[\u4e00-\u9fa5]{2,}$", message = "请输入真实的中文姓名!")
    private String name;

    @Email
    private String email;

    private String tel;

    private String kaptcha;
}
