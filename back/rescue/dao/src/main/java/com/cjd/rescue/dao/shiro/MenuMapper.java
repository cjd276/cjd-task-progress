package com.cjd.rescue.dao.shiro;

import com.cjd.rescue.entity.shiro.Menu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuMapper extends Mapper<Menu>{
    List<Menu> queryMenuTree(String parent_id);

    List<Menu> queryCheckedMenuTree(@Param("role_id") String role_id);

    List<Menu> selectAllMenu(@Param("user_name") String user_name);
}
