package com.bootdo.common.service.impl;

import com.bootdo.common.dao.DictDAO;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * Create by li_hongqing1
 * Date: 2018/5/3 12:15
 */

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictDAO dictDAO;

    @Override
    public List<DictDO> type() {
        return dictDAO.type();
    }

    @Override
    public PageUtils list(Map<String, Object> params) {
        Query query = new Query(params);
        List<DictDO> list = dictDAO.list(query);
        int count = dictDAO.count(query);

        PageUtils page = new PageUtils(list, count);
        return page;
    }
}
