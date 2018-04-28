package com.bootdo.system.dao;

import com.bootdo.system.domain.MenuDO;

import java.util.List;

/**
 * Description: 菜单持久层
 * Create by li_hongqing1
 * Date: 2018/4/28 16:49
 */
public interface MenuDAO {

    List<MenuDO> listMenuByUserId(Long userId);
}
