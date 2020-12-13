package com.didispace.shiro;


import cn.hutool.core.util.StrUtil;
import com.didispace.mybatis.a_user.A_USER;
import com.didispace.mybatis.a_user.A_USER_Mapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 定义UserRealm ,处理User相关逻辑
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private A_USER_Mapper userMapper;
    /**
     * Authorization 授权; 授权书; 批准; 批准书;
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("执行授权逻辑...");
        return null;
    }

    /**
     * Authentication身份验证; 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("执行认证逻辑...");

        //获取登录的用户名与密码
        UsernamePasswordToken userPT = (UsernamePasswordToken) token;
        String userId = userPT.getUsername();
        String passwd = new String(userPT.getPassword());
        log.info(userId + "+++++++++++" + passwd);

        A_USER dbUser = userMapper.selectUserById(userId);
        /**
         * 虽然可以得到用户名 和 密码，
         * 但不建议取出密码手工判断，留给框架判断即可
         *
         * 暂时使用 ：  id 做用户名， phone 做密码
         */

        return new SimpleAuthenticationInfo("",dbUser.getPhone(),"");
    }
}
