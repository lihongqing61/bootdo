package com.bootdo.system.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.system.dao.MenuDAO;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
        Tree<MenuDO> tree = null;
        Map<String, Object> attributes = null;
        for(MenuDO menu : menus) {
            tree = new Tree<MenuDO>();
            tree.setId(String.valueOf(menu.getMenuId()));
            tree.setParentId(String.valueOf(menu.getParentId()));
            tree.setText(menu.getName());

            attributes = new HashMap<>(16);
            attributes.put("url", menu.getUrl());
            attributes.put("icon", menu.getIcon());
            tree.setAttributes(attributes);

            trees.add(tree);
        }
        List<Tree<MenuDO>> list = BuildTree.buildList(trees, "0");
        return list;
    }
}
