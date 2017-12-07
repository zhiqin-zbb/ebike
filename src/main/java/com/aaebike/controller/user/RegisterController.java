package com.aaebike.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aaebike.common.kaptcha.ValidateCodeHandle;
import com.aaebike.model.RegisterForm;
import com.aaebike.model.User;
import com.aaebike.service.UserService;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author zhangbinbin
 * @version 2017/11/17
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String showRegisterPage(Model model) {
        RegisterForm registerForm = new RegisterForm();
        model.addAttribute("registerForm", registerForm);
        return "user/register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(HttpServletRequest request, @Valid RegisterForm registerForm, BindingResult bindingResult) {
        // 校验验证码
        String kaptcha = request.getParameter("kaptcha");
        if (!ValidateCodeHandle.matchCode(request.getSession().getId(), kaptcha)) {
            bindingResult.rejectValue("kaptcha","misFormat", "验证码错误!");
        }

        if (!registerForm.getPasswordRepeat().equals(registerForm.getPassword())) {
            bindingResult.rejectValue("passwordRepeat","misFormat", "两次输入的密码不一致!");
        }

        User byUsername = userService.findByUsername(registerForm.getUsername());
        if (byUsername != null) {
            bindingResult.rejectValue("username","misFormat", "该用户名已存在!");
        }

        if (bindingResult.hasErrors()) {
            return "user/register";
        }

        User user = new User();
        user.setUsername(registerForm.getUsername());
        user.setPassword(registerForm.getPassword());
        user.setName(registerForm.getName());
        user.setEmail(registerForm.getEmail());
        user.setTel(registerForm.getTel());
        user.setCreateTime(new Date());
        userService.save(user);

        return "redirect:/";
    }
}
