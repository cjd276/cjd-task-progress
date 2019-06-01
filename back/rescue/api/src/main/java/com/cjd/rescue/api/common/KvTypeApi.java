package com.cjd.rescue.api.common;

import com.cjd.rescue.entity.common.DeleteAndAddKvTypeParams;
import com.cjd.rescue.entity.common.ReturnT;

public interface KvTypeApi {
    ReturnT list();

    ReturnT deleteAndAdd(DeleteAndAddKvTypeParams deleteAndAddKvTypeParams);




}
