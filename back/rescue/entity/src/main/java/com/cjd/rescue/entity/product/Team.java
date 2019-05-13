package com.cjd.rescue.entity.product;

import com.cjd.rescue.entity.common.BaseObject;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "team")
public class Team extends BaseObject {

    @Id
    private String id;

    private String name;

    private String code;

    private String is_delete;

    private String ding_url;
    @Transient
    private String currentUserName;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getDing_url() {
        return ding_url;
    }

    public void setDing_url(String ding_url) {
        this.ding_url = ding_url;
    }

    public String getCurrentUserName() {
        return currentUserName;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }
}
