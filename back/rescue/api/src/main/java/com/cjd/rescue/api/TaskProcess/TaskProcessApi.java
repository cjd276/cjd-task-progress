package com.cjd.rescue.api.TaskProcess;

import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.product.TaskProcess;

import java.util.List;

public interface TaskProcessApi {
    ReturnT add(List<TaskProcess> taskProcesses);

    ReturnT list();
}
