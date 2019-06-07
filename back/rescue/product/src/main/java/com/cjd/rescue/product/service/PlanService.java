package com.cjd.rescue.product.service;

import com.cjd.rescue.api.plan.PlanApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.common.util.DateUtil;
import com.cjd.rescue.common.util.IdUtil;
import com.cjd.rescue.dao.anno.JDBCException;
import com.cjd.rescue.dao.common.SysKeyValueMapper;
import com.cjd.rescue.dao.product.*;
import com.cjd.rescue.entity.common.Err;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.common.SysKeyValue;
import com.cjd.rescue.entity.common.TypeOfKV;
import com.cjd.rescue.entity.product.*;
import com.cjd.rescue.product.ding.DingDingRobot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

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
    private ApolloMapper apolloMapper;


    @Autowired
    private TeamService teamService;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskModuleMapper taskModuleMapper;

    @Autowired
    private ProjectMapper projectMapper;


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

    @SysLog
    @JDBCException
    @Override
    public ReturnT getOnLineInfo(Plan plan) {

        List<Map> versions = new ArrayList<>();

        Plan planResult = planMapper.selectByPrimaryKey(plan);

        Date date = planResult.getExpect_time();
        String onLineDate = DateUtil.formatDateTime("yyyyMMdd",date);

        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("plan_id",plan.getId());

        List<Task> tasks = taskMapper.selectByExample(example);

        List<Module> modules = taskModuleMapper.selectModules(tasks);

        List<Project> projects = new ArrayList<>();
        List<Apollo> apollos = new ArrayList<>();
        Set<String> project_ids = new HashSet<>();
        Set<String> module_ids = new HashSet<>();
        if(null != modules && modules.size() > 0){
            for (Module module:modules){
                project_ids.add(module.getProject_id());
                module_ids.add(module.getId());
            }
            Example examplep = new Example(Project.class);
            Example.Criteria criteriap = example.createCriteria();
            criteriap.andIn("id",project_ids);
            projects= projectMapper.selectByExample(examplep);


            Example examplea = new Example(Apollo.class);
            Example.Criteria criteriaa = examplea.createCriteria();
            criteriaa.andIn("module_id",module_ids);
            criteriaa.andEqualTo("plan_id",plan.getId());
            apollos= apolloMapper.selectByExample(examplep);

        }



        return ReturnT.result(Err.SUCCESS).dataMap("tasks",tasks).dataMap("modules",modules).dataMap("projects",projects).dataMap("onLineDate",onLineDate).dataMap("apollos",apollos);
    }

    @SysLog
    @JDBCException
    @Override
    public ReturnT getModuleApollo(Apollo apollo) {


        List<Apollo> apollos = apolloMapper.select(apollo);


        return ReturnT.result(Err.SUCCESS).dataMap("apollos",apollos);
    }

    @SysLog
    @JDBCException
    @Override
    public ReturnT setModuleApollo(AddApollosParams addApollosParams) {
        List<Apollo> apollos = addApollosParams.getApollos();
        Apollo apolloParam = addApollosParams.getApollo();
        apolloMapper.delete(apolloParam);
        if(null != apollos && apollos.size() > 0){
            for (Apollo apollo:apollos){
                if(null != apollo.getKey_str() &&  null != apollo.getValue_str()){
                    apollo.setId(IdUtil.generateID());
                    apolloMapper.insertSelective(apollo);
                }

            }
        }
        return ReturnT.result(Err.SUCCESS);
    }
}
