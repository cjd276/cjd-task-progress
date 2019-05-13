package com.cjd.rescue.shiro.controller;

import com.cjd.rescue.api.shiro.MenueApi;
import com.cjd.rescue.entity.common.Err;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.shiro.Menu;
import com.cjd.rescue.entity.shiro.User;
import com.cjd.rescue.shiro.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shiro")
public class LoginController {

    @Autowired
    private MenueApi menueApi;

    @RequestMapping("/login")
    public ReturnT login(@RequestBody User user){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUsername(),
                user.getPassword());
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            subject.login(usernamePasswordToken);

        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ReturnT.result(Err.AUTH_ERR);
        }
        String token = subject.getSession().getId().toString();
        List<Menu> permissions = menueApi.getAllPermissions(user.getUsername());

        List<Menu> menusTemp =(List<Menu>) menueApi.queryMenuTree().getResultMap().get("menuTree");
        List<Menu> menus = new ArrayList<>();
        if(null != menusTemp && menusTemp.size() > 0 && null !=menusTemp.get(0).getChildren() && menusTemp.get(0).getChildren().size() > 0){
            menus = menusTemp.get(0).getChildren();
        }
        return ReturnT.result(Err.SUCCESS).dataMap("token",token).dataMap("permissions",permissions).dataMap("menus",menus);
    }




}
