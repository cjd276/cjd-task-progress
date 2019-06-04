package com.cjd.rescue.entity.product;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "task_process_settings")
public class TaskProcessSettings {

    @Id
    private String id;

    private String task_name;
    private String task_id;
    private String plan_id;
    private String plan_name;

    private String task_process_id;
    private String task_process_name;

    private String need_notice;

    private Date end_notice_time;


    private Date create_time;
    private Date modify_time;

    public String getNeed_notice() {
        return need_notice;
    }

    public void setNeed_notice(String need_notice) {
        this.need_notice = need_notice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public String getTask_process_id() {
        return task_process_id;
    }

    public void setTask_process_id(String task_process_id) {
        this.task_process_id = task_process_id;
    }

    public String getTask_process_name() {
        return task_process_name;
    }

    public void setTask_process_name(String task_process_name) {
        this.task_process_name = task_process_name;
    }

    public Date getEnd_notice_time() {
        return end_notice_time;
    }

    public void setEnd_notice_time(Date end_notice_time) {
        this.end_notice_time = end_notice_time;
    }
}
