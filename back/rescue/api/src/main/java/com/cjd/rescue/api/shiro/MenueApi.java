package com.cjd.rescue.api.shiro;

import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.shiro.AssignMenuParams;
import com.cjd.rescue.entity.shiro.Menu;
import com.cjd.rescue.entity.shiro.Role;

import java.util.List;

public interface MenueApi {
    ReturnT list(Menu menu);

    ReturnT add(Menu menu);

    ReturnT delete(Menu menu);

    ReturnT queryMenuTree();

    ReturnT roleToMenu(Role role);

    ReturnT assignMenu(AssignMenuParams assignMenuParams);

    List<Menu> getAllPermissions(String username);
}
