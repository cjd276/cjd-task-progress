package com.cjd.rescue.entity.shiro;

import java.util.List;

public class AssignMenuParams {

    private List<Menu> hasMenuIds;

    private String role_id;

    public List<Menu> getHasMenuIds() {
        return hasMenuIds;
    }

    public void setHasMenuIds(List<Menu> hasMenuIds) {
        this.hasMenuIds = hasMenuIds;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }
}
