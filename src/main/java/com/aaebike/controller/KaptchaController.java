package com.aaebike.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aaebike.common.kaptcha.ValidateCodeHandle;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * @author zhangbinbin
 * @version 2017/12/5
 */
@Controller
@RequestMapping("/kaptcha")
public class KaptchaController {
    @Autowired
    private Producer captchaProducer;

    @GetMapping("/image")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        ValidateCodeHandle.save(request.getSession().getId(), capText);

        BufferedImage bi = captchaProducer.createImage(capText);
        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(bi, "jpg", out);
            out.flush();
        }
    }
}
