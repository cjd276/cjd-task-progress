package com.cjd.rescue.entity.product;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "task")
public class Task {
    @Id
    private String id;


    private String name;

    private Date start_time;

    private Date end_time;

    private String plan_id;

    private String plan_name;
    private String task_process_id;
    private String task_process_name;

    private String branch;
    private String personal_branch;
    private String team_id;
    private Date create_time;

    private Date modify_time;
    @Transient
    private TaskProcess task_process;


    @Transient
    private Integer pageIndex;
    public String getId() {
        return id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPersonal_branch() {
        return personal_branch;
    }

    public void setPersonal_branch(String personal_branch) {
        this.personal_branch = personal_branch;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
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

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public TaskProcess getTask_process() {
        TaskProcess taskProcess =new TaskProcess();
        taskProcess.setId(this.getTask_process_id());
        taskProcess.setName(this.getTask_process_name());
        this.task_process = taskProcess;
        return task_process;
    }

    public void setTask_process(TaskProcess task_process) {
        this.task_process = task_process;
    }
}
