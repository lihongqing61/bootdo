package com.bootdo.common.controller;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * Create by li_hongqing1
 * Date: 2018/5/2 10:28
 */

@Controller
@RequestMapping("/common/dict")
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

    /**
     * 返回数据字典页面
     * @return
     */
    @GetMapping()
    public String dict() {
        return "common/dict/dict";
    }

    /**
     * 查询字典所以类型
     * @return
     */
    @GetMapping("/type")
    @ResponseBody
    public List<DictDO> type() {
        return dictService.type();
    }

    /**
     * 查询字典列表
     * @param params
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public PageUtils list(@RequestParam Map<String, Object> params) {
        return dictService.list(params);
    }

    /**
     * 返回新增页面
     * @return
     */
    @GetMapping("/add")
    public String add() {
        return "common/dict/add";
    }

    /**
     * 新增字典
     * @param dict
     * @return
     */
    @PostMapping("save")
    public R save(DictDO dict) {
        return null;
    }
}
