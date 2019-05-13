package com.cjd.rescue.shiro.controller;

import com.cjd.rescue.api.shiro.MenueApi;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.shiro.AssignMenuParams;
import com.cjd.rescue.entity.shiro.Menu;
import com.cjd.rescue.entity.shiro.Role;
import com.cjd.rescue.entity.shiro.User;
import com.cjd.rescue.shiro.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenueApi menueApi;


    @RequestMapping("/list")
    public ReturnT list(@RequestBody Menu menu){
        return menueApi.list(menu);
    }


    @RequestMapping("/add")
    public ReturnT add(@RequestBody Menu menu){
        return menueApi.add(menu);
    }

    @RequestMapping("/delete")
    public ReturnT delete(@RequestBody Menu menu){
        return menueApi.delete(menu);
    }


    @RequestMapping("/queryMenuTree")
    public ReturnT queryMenuTree(){
        return menueApi.queryMenuTree();
    }

    /**
     * 获取角色对应资源
     * @param role
     * @return
     */
    @RequestMapping("/roleToMenu")
    public ReturnT roleToMenu(@RequestBody Role role){
        return menueApi.roleToMenu(role);
    }

    /**
     * 分配菜单给角色
     * @param role
     * @return
     */
    @RequestMapping("/assignMenu")
    public ReturnT assignMenu(@RequestBody AssignMenuParams assignMenuParams){
        return menueApi.assignMenu(assignMenuParams);
    }



}
