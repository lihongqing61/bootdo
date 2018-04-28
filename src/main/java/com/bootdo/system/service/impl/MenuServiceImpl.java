package com.bootdo.system.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.system.dao.MenuDAO;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: 菜单业务层实现类
 * Create by li_hongqing1
 * Date: 2018/4/28 16:32
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDAO menuDAO;

    @Override
    public List<Tree<MenuDO>> listMenuTree(Long userId) {
        List<MenuDO> menus = menuDAO.listMenuByUserId(userId);
        return null;
    }
}
