package com.didispace.web.M001_userManage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/userManage")
public class UserManageController {

    @RequestMapping(value = "/insertUser")
    public ModelAndView insertUser() {
        ModelAndView mv = new ModelAndView();




        mv.addObject("userManage_ret","success");

        mv.setViewName("/thymeleaf/P001_mainPage/main::subMenu");
        return mv;
    }
}