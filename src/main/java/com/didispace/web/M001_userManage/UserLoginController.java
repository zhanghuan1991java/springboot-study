package com.didispace.web.M001_userManage;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
public class UserLoginController {

    @RequestMapping(value = "/login")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/thymeleaf/a_logon_manage/userLogin");
        return mv;
    }

    @RequestMapping(value = "/loginIn")
    public ModelAndView loginIn(@RequestParam("name") String userName, String passwd) {
        log.info("userName :" + userName + " ,passwd :" + passwd);

        ModelAndView mv = new ModelAndView();

        /**
         * 使用Shiro认证
         */
        //1、获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2、封装数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName,passwd);

        //3、执行登录方法
        try{
            subject.login(token);
            mv.setViewName("/thymeleaf/M001_mainPage/main");
        } catch (UnknownAccountException e) {
            log.info("用户不存在");
            mv.addObject("retMsg","用户不存在");
            mv.setViewName("/thymeleaf/a_logon_manage/userLogin");
        } catch (IncorrectCredentialsException e){
            log.info("密码错误");
            mv.addObject("retMsg","密码错误");
            mv.setViewName("/thymeleaf/a_logon_manage/userLogin");
        } catch (Exception e){
            log.info("未知错误");
            mv.addObject("retMsg","未知错误");
            mv.setViewName("/thymeleaf/a_logon_manage/userLogin");
        }

        return mv;
    }
}
