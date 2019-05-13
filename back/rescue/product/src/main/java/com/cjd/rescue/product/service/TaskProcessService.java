package com.cjd.rescue.product.service;

import com.cjd.rescue.api.TaskProcess.TaskProcessApi;
import com.cjd.rescue.api.plan.PlanApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.common.util.IdUtil;
import com.cjd.rescue.dao.anno.JDBCException;
import com.cjd.rescue.dao.common.SysKeyValueMapper;
import com.cjd.rescue.dao.product.ModuleMapper;
import com.cjd.rescue.dao.product.PlanMapper;
import com.cjd.rescue.dao.product.TaskProcessMapper;
import com.cjd.rescue.entity.common.Err;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.common.SysKeyValue;
import com.cjd.rescue.entity.common.TypeOfKV;
import com.cjd.rescue.entity.product.AddPlanParams;
import com.cjd.rescue.entity.product.Plan;
import com.cjd.rescue.entity.product.TaskProcess;
import com.cjd.rescue.entity.product.Team;
import com.cjd.rescue.entity.shiro.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class TaskProcessService implements TaskProcessApi {


    @Autowired
    private TeamService teamService;

    @Autowired
    private TaskProcessMapper taskProcessMapper;

    @SysLog
    @JDBCException
    @Transactional
    @Override
    public ReturnT add(List<TaskProcess> taskProcesses) {
        Team team = teamService.getCurrentUserTeam();
        if(null == team){
            return ReturnT.result(Err.NONE_TEAM);
        }



        if(null !=taskProcesses && taskProcesses.size() > 0){
            TaskProcess taskProcessParams = new TaskProcess();
            taskProcessParams.setTeam_id(team.getId());
            taskProcessMapper.delete(taskProcessParams);

            for (TaskProcess taskProcess:taskProcesses){
                taskProcess.setId(IdUtil.generateID());
                taskProcess.setTeam_id(team.getId());
                taskProcessMapper.insertSelective(taskProcess);
            }
        }




        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT list() {
        Team team = teamService.getCurrentUserTeam();


        Example example = new Example(TaskProcess.class);
        example.setOrderByClause("process_sort asc");
        Example.Criteria criteria = example.createCriteria();
        if(null != team){
            criteria.andEqualTo("team_id",team.getId());
        }
        List<TaskProcess> taskProcesses = taskProcessMapper.selectByExample(example);

        return ReturnT.result(Err.SUCCESS).dataMap("taskProcesses",taskProcesses);
    }
}
