package com.didispace.web.M000_mainPage;

import com.didispace.mybatisMuti.mapper_oracle.M000_menuMapper;
import com.didispace.web.M000_beans.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class MainPageController {

    @Autowired
    private M000_menuMapper mapper;

    /**
     * 加载主页面
     * 加载root 一级菜单  ， sql控制菜单顺序
     * @return
     */
    @RequestMapping(value = "/main")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/thymeleaf/M001_mainPage/main");
        return mv;
    }


    /**
     * 加载root 一级菜单  ， sql控制菜单顺序
     * @return
     */
    @RequestMapping(value = "/rootMenu")
    public ModelAndView rootMenu() {
        ModelAndView mv = new ModelAndView();

        //1 先从缓存中取， 若取不到，再查数据库 ，代码待完善

        //2 从数据库查询root菜单
        List<Menu> rootMenu = mapper.selectRootMenu();

        //日志打印
        rootMenu.stream()
                .map(Menu::toStringPretty)
                .forEach(log::info);

        mv.addObject("rootMenu",rootMenu);
        mv.setViewName("/thymeleaf/M001_mainPage/main::rootMenu");
        return mv;
    }

    /**
     * 加载 二级菜单  ， java代码控制菜单顺序
     * @param parentMenuId
     * @return
     */
    @RequestMapping(value = "/subMenu/{id}")
    public ModelAndView subMenu(@PathVariable("id") String parentMenuId) {
        ModelAndView mv = new ModelAndView();

        List<Menu> subMenu = mapper.selectSubMenu(parentMenuId)
                .stream()
                .filter(t->{return  t.getIs_enable().equals("YES");})
                .sorted((o1, o2) -> o1.getMenu_order()-o2.getMenu_order())
                .collect(Collectors.toList());

        subMenu.stream().map(Menu::toStringPretty).forEach(log::info);

        mv.addObject("subMenu",subMenu);
        mv.addObject("userManage_ret","success");

        mv.setViewName("/thymeleaf/M001_mainPage/main::subMenu");
        return mv;
    }
}
