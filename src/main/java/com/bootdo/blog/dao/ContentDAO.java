package com.bootdo.blog.dao;

import com.bootdo.blog.domain.ContentDO;
import com.bootdo.common.utils.Query;
import java.util.List;

/**
 * Description:
 * Create by li_hongqing1
 * Date: 2018/4/23 18:25
 */

public interface ContentDAO {
    List<ContentDO> list(Query query);

    int count(Query query);
}
