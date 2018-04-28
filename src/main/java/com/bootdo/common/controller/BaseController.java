package com.bootdo.common.controller;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;
import org.springframework.stereotype.Controller;

/**
 * Description:
 * Create by li_hongqing1
 * Date: 2018/4/28 16:24
 */
@Controller
public class BaseController {

    public UserDO getUser() {
        return ShiroUtils.getUser();
    }

    public Long getUserId() {
        return ShiroUtils.getUserId();
    }
}
