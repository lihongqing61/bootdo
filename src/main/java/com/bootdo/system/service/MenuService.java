package com.bootdo.system.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.system.domain.MenuDO;

import java.util.List;

/**
 * Description: 菜单业务层
 * Create by li_hongqing1
 * Date: 2018/4/28 16:31
 */
public interface MenuService {
    List<Tree<MenuDO>> listMenuTree(Long userId);
}
