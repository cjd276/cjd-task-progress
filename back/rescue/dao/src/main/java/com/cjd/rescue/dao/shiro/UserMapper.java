package com.cjd.rescue.dao.shiro;

import com.cjd.rescue.entity.shiro.Menu;
import com.cjd.rescue.entity.shiro.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User>{
    List<Menu> selectAllMenu(String name);
}
