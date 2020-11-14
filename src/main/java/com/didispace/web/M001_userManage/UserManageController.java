package com.didispace.web.M001_userManage;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.didispace.mybatis.a_user.A_USER;
import com.didispace.mybatis.a_user.A_USER_Mapper;
import com.didispace.mybatis.pageInfo.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 */
@RestController
@Slf4j
public class UserManageController {

    @Autowired
    private A_USER_Mapper mapper;

    /**
     * 用户管理   修改页面
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/thymeleaf/a_logon_manage/userManage");
        return mv;
    }

    /**
     * 用户管理主页面
     * @return
     */
    @RequestMapping(value = "/userPage")
    public ModelAndView userPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/thymeleaf/a_logon_manage/userManage");
        return mv;
    }
    /**
     * 用户注册页面
     * @return
     */
    @RequestMapping(value = "/user_register_html")
    public ModelAndView user_register_html() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("/thymeleaf/a_logon_manage/userRegister");

        return mv;
    }

    /**
     * 使用对象  接收  form表单的post 请求
     * 注册1按钮
     * @param user
     * @return
     */
    @RequestMapping(value = "/registerUserByForm",method = RequestMethod.POST)
    public ModelAndView registerUserByForm(@ModelAttribute(value = "user") A_USER user) {
        user.setId(IdUtil.fastUUID());
        int ret = mapper.insertUser(user);
        log.info("registerUserByForm -------newUser...新增条数:" +ret);

        //查询新增的用户信息到界面 start
        ModelAndView mv = queryUser("",new Page());
        mv.addObject("retMsg", "用户["+user.getName()+"]注册成功!");
        //查询新增的用户信息到界面 end;

        mv.setViewName("/thymeleaf/a_logon_manage/userManage");
        return mv;
    }

    /**
     *  页面未使用 ，测试类使用
     *  注册2按钮
     * 使用Json串接受  from 表单的 post请求
     * @param userStr
     * @return
     */
    @RequestMapping(value = "/registerUserByJson",method = RequestMethod.POST)
    public ModelAndView registerUserByJson(@RequestBody String userStr) {
        log.info("user json str————>"+ userStr);
        A_USER userObject = JSONUtil.toBean(userStr,A_USER.class);
        userObject.setId(IdUtil.fastUUID());
        int ret = mapper.insertUser(userObject);
        log.info("registerUserByJson--------newUser...新增条数:" +ret);

        //查询新增的用户信息到界面 start
        ModelAndView mv = queryUser("",new Page());
        mv.addObject("retMsg", "用户["+userObject.getName()+"]注册成功!");
        //查询新增的用户信息到界面 end;

        mv.setViewName("/thymeleaf/a_logon_manage/userManage");
        return mv;
    }

    /**
     * 输入框，  用户查询
     * @return
     */
    @RequestMapping(value = "/queryUser")
    public ModelAndView queryUser(String searchId , Page page) {
        log.info(".........."+searchId);

        ModelAndView mv = new ModelAndView();

        List<A_USER> userList = new ArrayList<A_USER>();
        //查一条数据
        if(StrUtil.isNotBlank(searchId)){
            userList.add(mapper.selectUserById(searchId));
        }
        //分页查询
        else {
            userList = extractPageUsers(mv, page);
        }

        mv.addObject("userList",userList);

        mv.setViewName("/thymeleaf/a_logon_manage/userManage");

        return mv;
    }

    /**
     * 用户信息分页查询
     * @return
     */
    @RequestMapping(value = "/queryPageUser/{pageNum}")
    public ModelAndView queryPageUser(@PathVariable("pageNum") String pageNum) {

        log.info(".........."+pageNum);

        ModelAndView mv = new ModelAndView();

        List<A_USER> userList = new ArrayList<A_USER>();

        Page page = new Page();

        //页面上查询的 第N页 传值  传 N-1
        page.setPageIndex(Integer.parseInt(pageNum));

        userList = extractPageUsers(mv, page);

        mv.addObject("userList",userList);

        mv.setViewName("/thymeleaf/a_logon_manage/userManage");

        return mv;
    }

    private List<A_USER> extractPageUsers(ModelAndView mv, Page page) {
        page.setMin(page.getPageIndex()*page.getPageSize());
        page.setMax((page.getPageIndex()+1)*page.getPageSize());
        List<A_USER> userList = mapper.getAllUser(page);
        int pageIndex = page.getPageIndex();
        int pageSize = page.getPageSize();
        int totalNum = mapper.getCountNum();
        int totalPage = (int) Math.ceil(totalNum/pageSize);
        mv.addObject("pageIndex",pageIndex);
        mv.addObject("pageSize",pageSize);
        mv.addObject("totalPage",totalPage);

        int min = 0;
        int max = 0;

        //最小页 = 当前页 - 2
        if(pageIndex - 2 >= 0){
            min = pageIndex -2;
        }else{
            min = 0;
        }

        //最大页 =  当前页 + 2
        if(totalPage - 1 <= pageIndex + 2){
            max = totalPage - 1;
        }else{
            max = pageIndex + 2;
        }

        //在第一，二页时 ,  若  总页数  >= 5 , 显示完整的5页
        if(pageIndex <= 1 && totalPage >= 5){
            max = 4;
        }

        //在最后一页时 ,  若 总页数  >= 5 , 显示完整的5页
        if(pageIndex >= totalPage - 2 && totalPage >= 5){
            min = totalPage - 1 - 4;
        }

        //若不足5页则全部显示
        if(totalPage <= 5){
            min = 0;
            max = totalPage - 1 ;
        }

        mv.addObject("min",min);
        mv.addObject("max",max);

        log.info("pageIndex:"+pageIndex +",pageSize:"+pageSize + ",totalPage:"+totalPage +",min:"+min +",max:"+max);
        return userList;
    }

    /**
     * 删除按钮
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam String id, String name) {
        log.info("input params:————> id---"+ id +",name————>"+name);
        mapper.deleteUserById(id);

        //查询新增的用户信息到界面 start
        ModelAndView mv = queryUser("",new Page());
        mv.addObject("retMsg", "用户["+name+"]删除成功!");
        //查询新增的用户信息到界面 end;

        mv.setViewName("/thymeleaf/a_logon_manage/userManage");
        return mv;
    }

}