package com.cjd.rescue.entity.product;

import com.cjd.rescue.entity.common.BaseObject;
import com.cjd.rescue.entity.common.SysKeyValue;
import com.sun.jmx.snmp.SnmpString;

import java.util.List;

public class UpdateTaskParams extends BaseObject {

    private Task task;

    private List<SysKeyValue> kvs;

    private List<Project> projects;


    private List<TaskProcess> taskProcessList;


    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<SysKeyValue> getKvs() {
        return kvs;
    }

    public void setKvs(List<SysKeyValue> kvs) {
        this.kvs = kvs;
    }


    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }


    public List<TaskProcess> getTaskProcessList() {
        return taskProcessList;
    }

    public void setTaskProcessList(List<TaskProcess> taskProcessList) {
        this.taskProcessList = taskProcessList;
    }
}
