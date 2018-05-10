package com.bootdo.common.dao;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.Query;
import java.util.List;

/**
 * Description:
 * Create by li_hongqing1
 * Date: 2018/5/3 12:16
 */
public interface DictDAO {

    List<DictDO> type();

    List<DictDO> list(Query query);

    int count(Query query);
}
