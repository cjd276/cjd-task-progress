package com.cjd.rescue.product.service;

import com.cjd.rescue.api.product.ProductApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.common.util.IdUtil;
import com.cjd.rescue.dao.anno.JDBCException;
import com.cjd.rescue.dao.common.SysKeyValueMapper;
import com.cjd.rescue.dao.product.ModuleMapper;
import com.cjd.rescue.dao.product.ProjectMapper;
import com.cjd.rescue.dao.product.TeamMapper;
import com.cjd.rescue.entity.common.Err;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.common.SysKeyValue;
import com.cjd.rescue.entity.common.TypeOfKV;
import com.cjd.rescue.entity.product.AddProjectParams;
import com.cjd.rescue.entity.product.Module;
import com.cjd.rescue.entity.product.Project;
import com.cjd.rescue.entity.product.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class ProjectService implements ProductApi{

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private SysKeyValueMapper sysKeyValueMapper;

    @Autowired
    private ModuleMapper moduleMapper;


    @Autowired
    private TeamMapper teamMapper;


    @Autowired
    private TeamService teamService;

    @SysLog
    @JDBCException
    @Override
    public ReturnT list(Project resource) {

        Project params = new Project();

        Team team = teamService.getCurrentUserTeam();
        if(null != team){
            params.setTeam_id(team.getId());
        }
        params.setIs_delete("0");
        return ReturnT.result(Err.SUCCESS).dataMap("projectList",projectMapper.select(params));

    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT add(AddProjectParams addProjectParams) {
        Project project = addProjectParams.getProject();
        project.setId(IdUtil.generateID());
        Team team = teamService.getCurrentUserTeam();
        if(null != team){
            project.setTeam_id(team.getId());
            project.setTeam_name(team.getName());
        }else{
            return ReturnT.result(Err.NONE_TEAM);
        }
        projectMapper.insertSelective(project);

//        List<SysKeyValue> list = addProjectParams.getKvs();
//
//        List<Module> modules = addProjectParams.getModules();
//
//        if(null != list && list.size() > 0){
//            Example example =new Example(SysKeyValue.class);
//            Example.Criteria criteria = example.createCriteria();
//            criteria.andEqualTo("associate",TypeOfKV.PROJECT+project.getId());
//            sysKeyValueMapper.deleteByExample(example);
//
//            for(SysKeyValue sysKeyValue:list){
//                sysKeyValue.setAssociate(TypeOfKV.PROJECT.getPrefix() + project.getId());
//                sysKeyValue.setId(IdUtil.generateID());
//                sysKeyValueMapper.insertSelective(sysKeyValue);
//            }
//        }
//        if(null != modules && modules.size() > 0){
//            for(Module module:modules){
//
//
//                String moduleId = IdUtil.generateID();
//                module.setId(moduleId);
//                module.setProject_id(project.getId());
//
//                moduleMapper.insertSelective(module);
//
//                List<SysKeyValue> sysKeyValues =  module.getSysKeyValues();
//                if(null != sysKeyValues && sysKeyValues.size() > 0){
//                    for(SysKeyValue sysKeyValue:sysKeyValues){
//                        sysKeyValue.setAssociate(TypeOfKV.MODULE.getPrefix() + moduleId);
//                        sysKeyValue.setId(IdUtil.generateID());
//                        sysKeyValueMapper.insertSelective(sysKeyValue);
//                    }
//                }
//            }
//        }
        handle(addProjectParams);

        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT delete(Project project) {
        project.setIs_delete("1");
        projectMapper.updateByPrimaryKeySelective(project);
        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT get(Project project) {


        Project project1 =projectMapper.selectByPrimaryKey(project.getId());
        SysKeyValue sysKeyValue = new SysKeyValue();
        sysKeyValue.setAssociate(TypeOfKV.PROJECT.getPrefix() + project.getId());
        List sysKeyValues = sysKeyValueMapper.select(sysKeyValue);

        Module module = new Module();
        module.setProject_id(project.getId());
        List<Module> modules = moduleMapper.select(module);

        if(null != modules && modules.size() > 0 ){
            for (Module moduleTemp:modules){
                String associate =  TypeOfKV.MODULE.getPrefix() + moduleTemp.getId();
                SysKeyValue sysKeyValueTemp = new SysKeyValue();
                sysKeyValueTemp.setAssociate(associate);
                List<SysKeyValue> sysKeyValueModuleList = sysKeyValueMapper.select(sysKeyValueTemp);
                moduleTemp.setSysKeyValues(sysKeyValueModuleList);
            }
        }



        return ReturnT.result(Err.SUCCESS).dataMap("sysKeyValues",sysKeyValues).dataMap("project",project1).dataMap("modules",modules);
    }

    @SysLog
    @JDBCException
    @Override
    public ReturnT modify(AddProjectParams addProjectParams) {
        Project project = addProjectParams.getProject();
        projectMapper.updateByPrimaryKeySelective(project);

        Example example = new Example(Module.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("project_id",project.getId());
        Module moduleTemp = new Module();
        moduleTemp.setIs_delete("1");

        moduleMapper.updateByExampleSelective(moduleTemp,example);


        this.handle(addProjectParams);


        return ReturnT.result(Err.SUCCESS);
    }

    private void handle(AddProjectParams addProjectParams){
        Project project = addProjectParams.getProject();
        List<SysKeyValue> list = addProjectParams.getKvs();

        List<Module> modules = addProjectParams.getModules();

        if(null != list && list.size() > 0){
            Example example =new Example(SysKeyValue.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("associate",TypeOfKV.PROJECT+project.getId());
            sysKeyValueMapper.deleteByExample(example);

            for(SysKeyValue sysKeyValue:list){
                sysKeyValue.setAssociate(TypeOfKV.PROJECT.getPrefix() + project.getId());
                sysKeyValue.setId(IdUtil.generateID());
                sysKeyValueMapper.insertSelective(sysKeyValue);
            }
        }
        if(null != modules && modules.size() > 0){
            for(Module module:modules){

                if(null != module.getId()){
                    module.setIs_delete("0");
                    moduleMapper.updateByPrimaryKeySelective(module);

                    List<SysKeyValue> sysKeyValues =  module.getSysKeyValues();
                    Example example = new Example(SysKeyValue.class);
                    Example.Criteria criteria = example.createCriteria();
                    criteria.andEqualTo("associate",TypeOfKV.MODULE+module.getId());
                    sysKeyValueMapper.deleteByExample(example);
                    if(null != sysKeyValues && sysKeyValues.size() > 0){

                        for(SysKeyValue sysKeyValue:sysKeyValues){
                            sysKeyValue.setAssociate(TypeOfKV.MODULE.getPrefix() + module.getId());
                            sysKeyValue.setId(IdUtil.generateID());
                            sysKeyValueMapper.insertSelective(sysKeyValue);
                        }
                    }

                }else{
                    String moduleId = IdUtil.generateID();
                    module.setId(moduleId);
                    module.setProject_id(project.getId());

                    moduleMapper.insertSelective(module);

                    List<SysKeyValue> sysKeyValues =  module.getSysKeyValues();
                    if(null != sysKeyValues && sysKeyValues.size() > 0){
                        for(SysKeyValue sysKeyValue:sysKeyValues){
                            sysKeyValue.setAssociate(TypeOfKV.MODULE.getPrefix() + moduleId);
                            sysKeyValue.setId(IdUtil.generateID());
                            sysKeyValueMapper.insertSelective(sysKeyValue);
                        }
                    }
                }

            }
        }
    }
}
