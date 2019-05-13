package com.cjd.rescue.entity.product;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "task_process")
public class TaskProcess {
    @Id
    private String id;

    private String name;

    private Integer process_sort;


    private String team_id;

    @Transient
    private Date end_notice_time;


    private Date create_time;

    private Date modify_time;

    public String getId() {
        return id;
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

    public Integer getProcess_sort() {
        return process_sort;
    }

    public void setProcess_sort(Integer process_sort) {
        this.process_sort = process_sort;
    }

    public Date getEnd_notice_time() {
        return end_notice_time;
    }

    public void setEnd_notice_time(Date end_notice_time) {
        this.end_notice_time = end_notice_time;
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


    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }
}
