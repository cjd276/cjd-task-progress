package com.cjd.rescue.product.service;

import com.cjd.rescue.api.plan.PlanApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.common.util.IdUtil;
import com.cjd.rescue.dao.anno.JDBCException;
import com.cjd.rescue.dao.common.SysKeyValueMapper;
import com.cjd.rescue.dao.product.ModuleMapper;
import com.cjd.rescue.dao.product.PlanMapper;
import com.cjd.rescue.entity.common.Err;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.common.SysKeyValue;
import com.cjd.rescue.entity.common.TypeOfKV;
import com.cjd.rescue.entity.product.AddPlanParams;
import com.cjd.rescue.entity.product.Plan;
import com.cjd.rescue.entity.product.Team;
import com.cjd.rescue.product.ding.DingDingRobot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;

@Service
@Transactional
public class PlanService implements PlanApi{

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private SysKeyValueMapper sysKeyValueMapper;


    @Autowired
    DingDingRobot dingDingRobot;


    @Autowired
    private TeamService teamService;

    @SysLog
    @JDBCException
    @Override
    public ReturnT list() {

        Plan params = new Plan();
        Team team = teamService.getCurrentUserTeam();
        if(null != team){
            params.setTeam_id(team.getId());
        }
        params.setIs_delete("0");
        return ReturnT.result(Err.SUCCESS).dataMap("planList",planMapper.select(params));

    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT add(AddPlanParams addProjectParams) {
        Plan plan = addProjectParams.getPlan();
        plan.setId(IdUtil.generateID());
        List<SysKeyValue> list = addProjectParams.getKvs();
        Team team = teamService.getCurrentUserTeam();
        if(null != team){
            plan.setTeam_id(team.getId());
        }else{
            return ReturnT.result(Err.NONE_TEAM);
        }
        plan.setIs_delete("0");
        planMapper.insertSelective(plan);
        if(null != list && list.size() > 0){
            for(SysKeyValue sysKeyValue:list){
                sysKeyValue.setAssociate(TypeOfKV.PLAN.getPrefix() + plan.getId());
                sysKeyValue.setId(IdUtil.generateID());
                sysKeyValueMapper.insertSelective(sysKeyValue);
            }
        }
        LinkedHashMap<String,String> msg = new LinkedHashMap<>();
        msg.put("当前用户",team.getCurrentUserName());
        msg.put("操作","创建了上线计划");
        msg.put("计划名称",plan.getName());
        dingDingRobot.doSendAll(msg,team.getDing_url());

        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT delete(Plan plan) {
        plan.setIs_delete("1");
        planMapper.updateByPrimaryKeySelective(plan);
        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT get(Plan plan) {


        Plan planTemp =planMapper.selectByPrimaryKey(plan.getId());
        SysKeyValue sysKeyValue = new SysKeyValue();
        sysKeyValue.setAssociate(TypeOfKV.PLAN.getPrefix() + planTemp.getId());
        List sysKeyValues = sysKeyValueMapper.select(sysKeyValue);




        return ReturnT.result(Err.SUCCESS).dataMap("sysKeyValues",sysKeyValues).dataMap("plan",planTemp);
    }
}
