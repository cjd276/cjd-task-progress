package com.cjd.rescue.api.task;

import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.product.AddTaskParams;
import com.cjd.rescue.entity.product.Task;
import com.cjd.rescue.entity.product.UpdateTaskParams;
import com.cjd.rescue.entity.product.UserTaskParams;

public interface TaskApi {
    ReturnT add(AddTaskParams addTaskParams);

    ReturnT list(Task task);

    ReturnT get(Task task);

    ReturnT update(UpdateTaskParams updateTaskParams);

    ReturnT addUserTask(UserTaskParams userTaskParams);

    ReturnT listTaskUser(Task task);

    ReturnT listProjectModuleList(Task task);
}
