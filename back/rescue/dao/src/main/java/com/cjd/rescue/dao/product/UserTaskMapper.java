package com.cjd.rescue.dao.product;

import com.cjd.rescue.entity.product.UserTask;
import com.cjd.rescue.entity.product.UserTeam;
import com.cjd.rescue.entity.shiro.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface UserTaskMapper extends Mapper<UserTask>{

    public List<User> listTaskUser(String task_id);

}
