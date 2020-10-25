package com.didispace.thymeleaf.refresh;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {
 
    @RequestMapping("/fresh")
    public ModelAndView globalRefresh() {
        List<Map<String,String>> lists = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("author", "曹雪芹");
        map.put("title", "《红楼梦》");
        map.put("url", "www.baidu.com");
        lists.add(map);
 
        ModelAndView mv = new ModelAndView();
        mv.addObject("refresh", "测试局部刷新");
        mv.addObject("title", "我的书单");
        mv.addObject("books", lists);

        mv.setViewName("/test");
        return mv;
    }

    /**
     * 局部刷新测试
     * @return
     */
    @RequestMapping("/local")
    public ModelAndView localRefresh() {
        List<Map<String,String>> lists = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("author", "罗贯中");
        map.put("title", "《三国演义》");
        map.put("url", "www.baidu.com");
        lists.add(map);

        ModelAndView mv = new ModelAndView();
        mv.addObject("refresh", "刷新了");
        mv.addObject("title", "我的书单");
        mv.addObject("books", lists);

        // "test"是test.html的名，
        // "table_refresh"是test.html中需要刷新的部分标志,
        // 在标签里加入：th:fragment="table_refresh"
        mv.setViewName("/test::table_refresh");

        return mv;
    }
}