package com.bootdo.blog.controller;

import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.service.ContentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 博客控制器
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private ContentService contentService;

    @GetMapping()
    String blog() {
        return "blog/index/main";
    }

    @GetMapping("/open/list")
    @ResponseBody
    public PageUtils openList(@RequestParam Map<String, Object> params) {

        Query query = new Query(params);
        List<ContentDO> contentList = contentService.list(query);
        int count = contentService.count(query);
        PageUtils page = new PageUtils(contentList, count);
        return page;
    }

}
