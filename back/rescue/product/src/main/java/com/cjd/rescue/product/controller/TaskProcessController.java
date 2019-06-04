package com.cjd.rescue.product.controller;

import com.cjd.rescue.api.TaskProcess.TaskProcessApi;
import com.cjd.rescue.api.team.TeamApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.product.*;
import com.cjd.rescue.entity.shiro.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/taskProcess")
public class TaskProcessController {

    @Autowired
    private TaskProcessApi taskProcessApi;




    @RequestMapping("/add")
    @SysLog
    public ReturnT add(@RequestBody AddTaskProcessParams addTaskProcessParams){
        return taskProcessApi.add(addTaskProcessParams.getTaskProcesses());
    }

    @RequestMapping("/list")
    @SysLog
    public ReturnT list(){
        return taskProcessApi.list();
    }




}
