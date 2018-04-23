package com.bootdo.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 博客控制器
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @GetMapping()
    String blog() {
        return "blog/index/main";
    }
}
