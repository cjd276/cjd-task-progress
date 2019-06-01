package com.cjd.rescue.entity.common;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_key_value")
public class SysKeyValue extends BaseObject{
    @Id
    private String id;

    private String bond;

    private String val;

    private String associate;

    private String kv_type_key;

    private String kv_type_name;

    private Date create_time;

    private Date modify_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBond() {
        return bond;
    }

    public void setBond(String bond) {
        this.bond = bond;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
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


    public String getAssociate() {
        return associate;
    }

    public void setAssociate(String associate) {
        this.associate = associate;
    }

    public String getKv_type_key() {
        return kv_type_key;
    }

    public void setKv_type_key(String kv_type_key) {
        this.kv_type_key = kv_type_key;
    }

    public String getKv_type_name() {
        return kv_type_name;
    }

    public void setKv_type_name(String kv_type_name) {
        this.kv_type_name = kv_type_name;
    }
}
