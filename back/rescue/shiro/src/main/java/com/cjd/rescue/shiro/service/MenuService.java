package com.cjd.rescue.shiro.service;

import com.cjd.rescue.api.shiro.MenueApi;
import com.cjd.rescue.api.shiro.UserApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.common.resource.RES;
import com.cjd.rescue.common.util.IdUtil;
import com.cjd.rescue.dao.anno.JDBCException;
import com.cjd.rescue.dao.common.SysKeyValueMapper;
import com.cjd.rescue.dao.product.TeamMapper;
import com.cjd.rescue.dao.product.UserTeamMapper;
import com.cjd.rescue.dao.shiro.MenuMapper;
import com.cjd.rescue.dao.shiro.RoleMenuMapper;
import com.cjd.rescue.dao.shiro.UserMapper;
import com.cjd.rescue.entity.common.Err;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.common.SysKeyValue;
import com.cjd.rescue.entity.common.TypeOfKV;
import com.cjd.rescue.entity.product.Team;
import com.cjd.rescue.entity.product.UserTeam;
import com.cjd.rescue.entity.shiro.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService implements MenueApi {


    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @SysLog
    @JDBCException
    @Override
    public ReturnT list(Menu menu) {


        Integer pageIndex = menu.getPageIndex();

        Integer offset = (pageIndex - 1)* RES.limit;

        RowBounds rowBounds = new RowBounds(offset,RES.limit);
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotLike("id","sys_%");
        if(!StringUtils.isEmpty(menu.getMenu_name())){
            criteria.andEqualTo("menu_name",menu.getMenu_name());
        }
        List<Menu> userList = menuMapper.selectByExampleAndRowBounds(example,rowBounds);

        Integer total = menuMapper.selectCountByExample(example);

        return ReturnT.result(Err.SUCCESS).dataMap("menuList",userList).dataMap("total",total);

    }
    @Transactional
    @SysLog
    @JDBCException
    @Override
    public ReturnT add(Menu menu) {
        menu.setId(IdUtil.generateID());
        menuMapper.insertSelective(menu);
        return ReturnT.result(Err.SUCCESS);
    }
    @Transactional
    @SysLog
    @JDBCException
    @Override
    public ReturnT delete(Menu menu) {
        menuMapper.deleteByPrimaryKey(menu);
        return ReturnT.result(Err.SUCCESS);
    }

    @SysLog
    @JDBCException
    @Override
    public ReturnT queryMenuTree() {
        List<Menu> menuTree = menuMapper.queryMenuTree("1");
        return ReturnT.result(Err.SUCCESS).dataMap("menuTree",menuTree);
    }

    @SysLog
    @JDBCException
    @Override
    public ReturnT roleToMenu(Role role) {
        List<Menu> menuTree = menuMapper.queryMenuTree("1");

        List<Menu> checkedMenuTree = menuMapper.queryCheckedMenuTree(role.getId());

        List<String> keys = new ArrayList<>();

        if(null != checkedMenuTree && checkedMenuTree.size() >0 ){
            for (Menu menu:checkedMenuTree){
                keys.add(menu.getId());
            }
        }

        return ReturnT.result(Err.SUCCESS).dataMap("menuTree",menuTree).dataMap("checkedMenuTree",keys);
    }
    @Transactional
    @SysLog
    @JDBCException
    @Override
    public ReturnT assignMenu(AssignMenuParams assignMenuParams) {
        String role_id = assignMenuParams.getRole_id();
        RoleMenu roleMenu = new RoleMenu();

        roleMenu.setRole_id(role_id);
        roleMenuMapper.delete(roleMenu);


        List<Menu> menus =  assignMenuParams.getHasMenuIds();

        if(null != menus && menus.size() > 0 ){
            for (Menu menu:menus){
                RoleMenu temp = new RoleMenu();
                temp.setId(IdUtil.generateID());
                temp.setRole_id(role_id);
                temp.setMenu_id(menu.getId());
                roleMenuMapper.insertSelective(temp);
            }
        }

        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public List<Menu> getAllPermissions(String username) {
        if(RES.admin.equals(username)){
            return menuMapper.selectAll();
        }
        return menuMapper.selectAllMenu(username);
    }
}
