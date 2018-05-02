package com.bootdo.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 * Create by li_hongqing1
 * Date: 2018/5/2 10:28
 */

@Controller
@RequestMapping("/common/dict")
public class DictController extends BaseController {

    @GetMapping()
    public String dict() {
        return "common/dict/dict";
    }
}
