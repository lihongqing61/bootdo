package com.bootdo.blog.service;

import com.bootdo.blog.domain.ContentDO;
import com.bootdo.common.utils.Query;

import java.util.List;

/**
 * Description: 文章博客业务层
 * Create by li_hongqing1
 * Date: 2018/4/23 18:18
 */
public interface ContentService {
    List<ContentDO> list(Query query);

    int count(Query query);
}
