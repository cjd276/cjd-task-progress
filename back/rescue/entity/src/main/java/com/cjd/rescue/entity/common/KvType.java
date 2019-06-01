package com.cjd.rescue.entity.common;


import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "kv_type")
public class KvType {

    @Id
    private String id;


    private String kv_type_name;


    private Integer kv_sort;

    private Date create_time;
    private Date modify_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKv_type_name() {
        return kv_type_name;
    }

    public void setKv_type_name(String kv_type_name) {
        this.kv_type_name = kv_type_name;
    }


    public Integer getKv_sort() {
        return kv_sort;
    }

    public void setKv_sort(Integer kv_sort) {
        this.kv_sort = kv_sort;
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
}
