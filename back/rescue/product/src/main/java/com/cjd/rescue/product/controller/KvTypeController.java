package com.cjd.rescue.product.controller;

import com.cjd.rescue.api.common.KvTypeApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.entity.common.DeleteAndAddKvTypeParams;
import com.cjd.rescue.entity.common.KvType;
import com.cjd.rescue.entity.common.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kvType")
public class KvTypeController {

    @Autowired
    private KvTypeApi kvTypeApi;
    @RequestMapping("/list")
    @SysLog
    public ReturnT list(){
        return kvTypeApi.list();
    }

    @RequestMapping("/deleteAndAdd")
    @SysLog

    public ReturnT add(@RequestBody DeleteAndAddKvTypeParams deleteAndAddKvTypeParams){
        return kvTypeApi.deleteAndAdd(deleteAndAddKvTypeParams);
    }




}
