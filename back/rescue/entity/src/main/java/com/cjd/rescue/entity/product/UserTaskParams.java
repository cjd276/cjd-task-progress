package com.cjd.rescue.entity.product;

import com.cjd.rescue.entity.common.BaseObject;
import com.cjd.rescue.entity.common.SysKeyValue;
import com.cjd.rescue.entity.shiro.User;

import java.util.List;

public class UserTaskParams extends BaseObject {

    private String task_id;
    private String task_name;

    private List<User> user_ids;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public List<User> getUser_ids() {
        return user_ids;
    }

    public void setUser_ids(List<User> user_ids) {
        this.user_ids = user_ids;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }
}
