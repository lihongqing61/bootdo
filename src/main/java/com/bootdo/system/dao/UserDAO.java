package com.bootdo.system.dao;

import com.bootdo.system.domain.UserDO;

/**
 * Description:
 * Create by li_hongqing1
 * Date: 2018/4/24 16:43
 */
public interface UserDAO {

    UserDO queryByUsername(String username);

}
