package com.bootdo.common.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 分页工具
 * Create by li_hongqing1
 * Date: 2018/4/23 18:00
 */
public class PageUtils implements Serializable {

    /**
     * 总记录数
     */
    private int total;

    /**
     * 对象
     */
    private List<?> rows;

    public PageUtils() {
    }

    public PageUtils(List<?> rows, int total) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

}
