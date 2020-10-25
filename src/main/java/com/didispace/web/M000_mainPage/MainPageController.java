package com.didispace.web.M000_mainPage;

import com.didispace.mybatisMuti.mapper_oracle.M000_menuMapper;
import com.didispace.web.M000_beans.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class MainPageController {

    @Autowired
    private M000_menuMapper mapper;

    /**
     * 加载主页面
     * @return
     */
    @RequestMapping(value = "/main")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/thymeleaf/M001_mainPage/main");
        return mv;
    }

    @RequestMapping(value = "/subMenu/{id}")
    public ModelAndView subMenu(@PathVariable("id") String parentMenuId) {
        ModelAndView mv = new ModelAndView();

        //从数据库查询子菜单
        List<Menu> subMenu = mapper.selectSubMenu(parentMenuId)
                .stream()
                .sorted((o1, o2) -> o1.getMenu_order()-o2.getMenu_order())
                .collect(Collectors.toList());

        //打印日志
        subMenu.stream().map(Menu::toStringPretty).forEach(log::info);

        mv.addObject("subMenu",subMenu);
        mv.addObject("userManage_ret","success");

        mv.setViewName("/thymeleaf/M001_mainPage/main::subMenu");
        return mv;
    }
}
