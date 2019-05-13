package com.cjd.rescue.product.service;

import com.cjd.rescue.api.task.TaskApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.common.resource.RES;
import com.cjd.rescue.common.util.IdUtil;
import com.cjd.rescue.dao.anno.JDBCException;
import com.cjd.rescue.dao.common.SysKeyValueMapper;
import com.cjd.rescue.dao.product.*;
import com.cjd.rescue.entity.common.Err;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.common.SysKeyValue;
import com.cjd.rescue.entity.common.TypeOfKV;
import com.cjd.rescue.entity.product.*;
import com.cjd.rescue.entity.shiro.User;
import com.cjd.rescue.product.ding.DingDingRobot;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service

public class TaskService implements TaskApi {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private SysKeyValueMapper sysKeyValueMapper;


    @Autowired
    private UserTaskMapper userTaskMapper;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TaskModuleMapper taskModuleMapper;

    @Autowired
    private TaskProcessMapper taskProcessMapper;

    @Autowired
    private TaskProcessSettingsMapper taskProcessSettingsMapper;

    @Autowired
    DingDingRobot dingDingRobot;

    @Transactional
    @SysLog
    @JDBCException
    @Override
    public ReturnT add(AddTaskParams addTaskParams) {
        Task task = addTaskParams.getTask();
        Team team = teamService.getCurrentUserTeam();
        if(null != team){
            task.setTeam_id(team.getId());

        }else{
            return ReturnT.result(Err.NONE_TEAM);
        }
        Example example = new Example(TaskProcess.class);
        example.setOrderByClause("process_sort asc");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("team_id",team.getId());
        List<TaskProcess> taskProcesses = taskProcessMapper.selectByExample(example);
        if(null == taskProcesses || taskProcesses.size() == 0){
            return ReturnT.result(Err.NONE_TASK_PROCESS);
        }
        task.setTask_process_id(taskProcesses.get(0).getId());
        task.setTask_process_name(taskProcesses.get(0).getName());
        task.setId(IdUtil.generateID());
        taskMapper.insertSelective(task);

        List<SysKeyValue> list = addTaskParams.getKvs();
        inserSysKeyValues(list,task.getId());

        LinkedHashMap<String,String> msg = new LinkedHashMap<>();
        msg.put("当前用户",team.getCurrentUserName());
        msg.put("操作","创建了任务");
        msg.put("任务分配","未分配");
        msg.put("任务名称",task.getName());
        dingDingRobot.doSendAll(msg,team.getDing_url());
        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT list(Task task) {
        Team team = teamService.getCurrentUserTeam();
        if(null != team){
            task.setTeam_id(team.getId());
        }

        Integer pageIndex = task.getPageIndex();

        Integer offset = (pageIndex - 1)* RES.limit;

        RowBounds rowBounds = new RowBounds(offset,RES.limit);


        List<Task> userList = taskMapper.selectByRowBounds(task,rowBounds);

        Integer total = taskMapper.selectCount(task);

        return ReturnT.result(Err.SUCCESS).dataMap("taskList",userList).dataMap("total",total);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT get(Task task) {


        Task temp =taskMapper.selectByPrimaryKey(task.getId());
        SysKeyValue sysKeyValue = new SysKeyValue();
        sysKeyValue.setAssociate(TypeOfKV.TASK.getPrefix() + temp.getId());
        List sysKeyValues = sysKeyValueMapper.select(sysKeyValue);

        Example example = new Example(TaskProcess.class);
        example.setOrderByClause("process_sort asc");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("team_id",task.getTeam_id());
        List<TaskProcess> taskProcesses = taskProcessMapper.selectByExample(example);


        return ReturnT.result(Err.SUCCESS).dataMap("sysKeyValues",sysKeyValues).dataMap("task",temp).dataMap("taskProcesses",taskProcesses);
    }
    @Transactional
    @SysLog
    @JDBCException
    @Override
    public ReturnT update(UpdateTaskParams updateTaskParams) {
        Team team = teamService.getCurrentUserTeam();
        if(null == team){
            return ReturnT.result(Err.NONE_TEAM);

        }
        Task task = updateTaskParams.getTask();

        SysKeyValue sysKeyValueParam = new SysKeyValue();
        sysKeyValueParam.setAssociate(TypeOfKV.TASK.getPrefix() + task.getId());
        sysKeyValueMapper.delete(sysKeyValueParam);

        List<SysKeyValue> list = updateTaskParams.getKvs();
        Task oldTask = taskMapper.selectByPrimaryKey(task);
        taskMapper.updateByPrimaryKeySelective(task);
        inserSysKeyValues(list,task.getId());

        List<Project> projects = updateTaskParams.getProjects();
        TaskModule taskModuleParams = new TaskModule();
        taskModuleParams.setTask_id(task.getId());
        taskModuleMapper.delete(taskModuleParams);
        if(null != projects && projects.size() > 0){
            for (Project project:projects){
                List<Module> modules = project.getModules();
                if(null != modules && modules.size() > 0){
                    for (Module module:modules){
                        if(module.isChecked()){
                            TaskModule taskModule = new TaskModule();
                            taskModule.setId(IdUtil.generateID());
                            taskModule.setTask_id(task.getId());
                            taskModule.setModule_id(module.getId());
                            taskModule.setPlan_id(task.getPlan_id());
                            taskModuleMapper.insertSelective(taskModule);
                        }
                    }
                }
            }
        }



        List<TaskProcess> taskProcesses = updateTaskParams.getTaskProcessList();
        if(null != taskProcesses && taskProcesses.size() > 0 ){
            String task_id = task.getId();
            TaskProcessSettings taskProcessSettingsParams = new TaskProcessSettings();
            taskProcessSettingsParams.setTask_id(task_id);
            taskProcessSettingsMapper.delete(taskProcessSettingsParams);
            for (TaskProcess taskProcess:taskProcesses){

                Date ent =  taskProcess.getEnd_notice_time();
                if(null != ent){
                    String task_process_id = taskProcess.getId();
                    String task_process_name = taskProcess.getName();
                    String plan_id = task.getPlan_id();
                    String plan_name = task.getPlan_name();
                    String task_name = task.getName();
                    TaskProcessSettings taskProcessSettings = new TaskProcessSettings();
                    taskProcessSettings.setId(IdUtil.generateID());
                    taskProcessSettings.setPlan_id(plan_id);
                    taskProcessSettings.setPlan_name(plan_name);
                    taskProcessSettings.setTask_id(task_id);
                    taskProcessSettings.setTask_name(task_name);
                    taskProcessSettings.setEnd_notice_time(ent);
                    taskProcessSettings.setTask_process_id(task_process_id);
                    taskProcessSettings.setTask_process_name(task_process_name);
                    taskProcessSettingsMapper.insertSelective(taskProcessSettings);
                }


            }
        }



        if(!oldTask.getTask_process_id().equals(task.getTask_process_id())){
            LinkedHashMap<String,String> msg = new LinkedHashMap<>();
            msg.put("当前用户",team.getCurrentUserName());
            msg.put("操作","修改任务进度");
            msg.put("任务名称",oldTask.getName());
            msg.put("进度",oldTask.getTask_process_name()+"->" + task.getTask_process_name());
            dingDingRobot.doSendAll(msg,team.getDing_url());
        }
        return ReturnT.result(Err.SUCCESS);
    }
    @Transactional
    @SysLog
    @JDBCException
    @Override
    public ReturnT addUserTask(UserTaskParams userTaskParams) {
        Team team = teamService.getCurrentUserTeam();
        if(null == team){
            return ReturnT.result(Err.NONE_TEAM);

        }
        String task_id = userTaskParams.getTask_id();
        List<User> user_ids = userTaskParams.getUser_ids();
        UserTask userTask = new UserTask();
        userTask.setTask_id(task_id);
        userTaskMapper.delete(userTask);
        StringBuffer stringBuffer = new StringBuffer();
        List<String> phones = new ArrayList<>();
        if(null != user_ids && user_ids.size()>0){
            for (User user:user_ids){
                UserTask userTaskTemp = new UserTask();
                userTaskTemp.setTask_id(task_id);
                userTaskTemp.setUser_id(user.getId());
                userTaskTemp.setId(IdUtil.generateID());
                userTaskTemp.setTaskname(userTaskParams.getTask_name());
                userTaskTemp.setUsername(user.getUsername());
                userTaskMapper.insertSelective(userTaskTemp);
                stringBuffer.append("[");
                stringBuffer.append(user.getUsername());
                stringBuffer.append("]");
                phones.add(user.getMobile());
            }
        }

        LinkedHashMap<String,String> msg = new LinkedHashMap<>();
        msg.put("当前用户",team.getCurrentUserName());
        msg.put("操作","分配任务");
        msg.put("分配给",stringBuffer.toString());
        msg.put("任务名称",userTaskParams.getTask_name());
        dingDingRobot.doSendPhones(msg,phones,team.getDing_url());

        return ReturnT.result(Err.SUCCESS);
    }

    @SysLog
    @JDBCException
    @Override
    public ReturnT listTaskUser(Task task) {


        List<User> userList = userTaskMapper.listTaskUser(task.getId());

        return ReturnT.result(Err.SUCCESS).dataMap("userList",userList);
    }

    @Override
    public ReturnT listProjectModuleList(Task task) {
        String team_id = task.getTeam_id();

        List<Project> projects = taskModuleMapper.listProjectModuleList(team_id);
        TaskModule taskModuleParams = new TaskModule();
        taskModuleParams.setTask_id(task.getId());
        List<TaskModule> taskModules = taskModuleMapper.select(taskModuleParams);

        if(null != projects && projects.size() > 0){
            for (Project project:projects){
                List<Module> modules = project.getModules();
                if(null != modules && modules.size() > 0 ){
                    for (Module module:modules){
                        if(null != taskModules && taskModules.size()>0){
                            for (TaskModule taskModule:taskModules){
                                if(module.getId().equals(taskModule.getModule_id())){
                                    module.setChecked(true);
                                }
                            }
                        }
                    }
                }

            }
        }



        return ReturnT.result(Err.SUCCESS).dataMap("projects",projects).dataMap("taskModules",taskModules);
    }


    public void inserSysKeyValues(List<SysKeyValue> list,String associate_id){
        if(null != list && list.size() > 0){
            for(SysKeyValue sysKeyValue:list){
                sysKeyValue.setAssociate(TypeOfKV.TASK.getPrefix() + associate_id);
                sysKeyValue.setId(IdUtil.generateID());
                sysKeyValueMapper.insertSelective(sysKeyValue);
            }
        }
    }

}
