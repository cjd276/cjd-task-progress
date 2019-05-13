package com.cjd.rescue.product.controller;

import com.cjd.rescue.api.task.TaskApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.product.AddTaskParams;
import com.cjd.rescue.entity.product.Task;
import com.cjd.rescue.entity.product.UpdateTaskParams;
import com.cjd.rescue.entity.product.UserTaskParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskApi taskApi;


    @RequestMapping("/add")
    @SysLog

    public ReturnT add(@RequestBody AddTaskParams addTaskParams){
        return taskApi.add(addTaskParams);
    }

    @RequestMapping("/list")
    public ReturnT list(@RequestBody Task task){
        return taskApi.list(task);
    }


    @RequestMapping("/get")
    public ReturnT get(@RequestBody Task task){
        return taskApi.get(task);
    }



    @RequestMapping("/update")
    @SysLog

    public ReturnT update(@RequestBody UpdateTaskParams updateTaskParams){
        return taskApi.update(updateTaskParams);
    }
    @RequestMapping("/addUserTask")
    @SysLog

    public ReturnT addUserTask(@RequestBody UserTaskParams userTaskParams){
        return taskApi.addUserTask(userTaskParams);
    }

    @RequestMapping("/listTaskUser")
    @SysLog

    public ReturnT listTaskUser(@RequestBody Task task){
        return taskApi.listTaskUser(task);
    }



    @RequestMapping("/listProjectModuleList")
    @SysLog

    public ReturnT listProjectModuleList(@RequestBody Task task){
        return taskApi.listProjectModuleList(task);
    }

}
