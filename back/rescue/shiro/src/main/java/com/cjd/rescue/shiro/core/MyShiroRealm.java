package com.cjd.rescue.shiro.core;

import com.cjd.rescue.common.resource.RES;
import com.cjd.rescue.dao.shiro.MenuMapper;
import com.cjd.rescue.dao.shiro.UserMapper;
import com.cjd.rescue.entity.shiro.Menu;
import com.cjd.rescue.entity.shiro.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {

    //用于用户查询
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name= (String) principalCollection.getPrimaryPrincipal();
        List<Menu> menus = null;
        if(RES.admin.equals(name)){
            menus = menuMapper.selectAll();
        }else{
            menus = menuMapper.selectAllMenu(name);
        }


        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        if(null != menus && menus.size() > 0){
            for (Menu menu:menus) {
                if(null != menu && !StringUtils.isEmpty(menu.getPermission_code())){
                    simpleAuthorizationInfo.addStringPermission(menu.getPermission_code());
                }
            }
        }

        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        User userParam = new User();
        userParam.setUsername(name);
        User user = userMapper.selectOne(userParam);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword().toString(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
