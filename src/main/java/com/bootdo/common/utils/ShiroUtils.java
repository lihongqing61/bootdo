package com.bootdo.common.utils;

import com.bootdo.system.domain.UserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Description:
 * Create by li_hongqing1
 * Date: 2018/4/28 16:25
 */
public class ShiroUtils {

    /**
     * 获取主体
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前用户
     * @return
     */
    public static UserDO getUser() {
        return (UserDO) getSubject().getPrincipal();
    }

    /**
     * 获取当前用户id
     * @return
     */
    public static Long getUserId() {
        return getUser().getUserId();
    }
}
