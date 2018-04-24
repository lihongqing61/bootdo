package com.bootdo.system.controller;

import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import groovy.util.logging.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录控制器
 */
@Controller
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String welcome(Model model) {
        return "redirect:/blog";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public R ajaxLogin(String username, String password) {
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return R.ok();
    }
}
