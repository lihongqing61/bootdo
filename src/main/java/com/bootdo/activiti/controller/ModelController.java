package com.bootdo.activiti.controller;

import com.bootdo.common.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description:
 * Create by li_hongqing1
 * Date: 2018/5/10 17:21
 */

@RequestMapping("/activiti")
@RestController
public class ModelController extends BaseController {

    @GetMapping("/model")
    public ModelAndView model() {
        return new ModelAndView("act/model/model");
    }
}
