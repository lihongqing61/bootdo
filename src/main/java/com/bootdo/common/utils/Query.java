package com.bootdo.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: 查询参数封装
 * Create by li_hongqing1
 * Date: 2018/4/23 18:06
 */
public class Query extends LinkedHashMap<String, Object> {

    /**
     * 开始记录数
     */
    private int offset;

    /**
     * 每页记录数
     */
    private int limit;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        // 分页参数
        this.offset = Integer.parseInt(params.get("offset").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put("offset", offset);
        this.put("page", offset / limit + 1);
        this.put("limit", limit);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.put("offset", offset);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
