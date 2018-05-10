package com.bootdo.common.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * Create by li_hongqing1
 * Date: 2018/5/3 12:13
 */
public interface DictService {

    List<DictDO> type();

    PageUtils list(Map<String, Object> map);
}
