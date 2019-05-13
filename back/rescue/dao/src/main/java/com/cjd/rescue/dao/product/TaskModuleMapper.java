package com.cjd.rescue.dao.product;

import com.cjd.rescue.entity.product.Project;
import com.cjd.rescue.entity.product.TaskModule;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TaskModuleMapper extends Mapper<TaskModule>{
    List<Project> listProjectModuleList(String team_id);
}
