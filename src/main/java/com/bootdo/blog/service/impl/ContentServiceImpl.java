package com.bootdo.blog.service.impl;

import com.bootdo.blog.dao.ContentDAO;
import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.service.ContentService;
import com.bootdo.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: 文章博客业务层实现类
 * Create by li_hongqing1
 * Date: 2018/4/23 18:24
 */

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDAO contentDAO;


    @Override
    public List<ContentDO> list(Query query) {
        return contentDAO.list(query);
    }

    @Override
    public int count(Query query) {
        return contentDAO.count(query);
    }
}
