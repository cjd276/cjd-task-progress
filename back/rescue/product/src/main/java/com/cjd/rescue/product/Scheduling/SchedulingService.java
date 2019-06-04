package com.cjd.rescue.product.Scheduling;

import com.cjd.rescue.common.util.DateUtil;
import com.cjd.rescue.dao.product.TaskProcessSettingsMapper;
import com.cjd.rescue.dao.product.TeamMapper;
import com.cjd.rescue.dao.product.UserTaskMapper;
import com.cjd.rescue.entity.product.TaskProcessSettings;
import com.cjd.rescue.entity.product.Team;
import com.cjd.rescue.entity.shiro.User;
import com.cjd.rescue.product.ding.DingDingRobot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class SchedulingService{


    @Autowired
    DingDingRobot dingDingRobot;

    @Autowired
    UserTaskMapper userTaskMapper;

    @Autowired
    private TaskProcessSettingsMapper taskProcessSettingsMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Scheduled(cron = "0 0/10 9-18 ? * 2,3,4,5,6")
    public void taskNotice(){
        TaskProcessSettings taskProcessSettingsParam = new TaskProcessSettings();
        taskProcessSettingsParam.setNeed_notice("1");
        //需要提醒的数据
        List<TaskProcessSettings> taskProcessSettings =  taskProcessSettingsMapper.select(taskProcessSettingsParam);
        if(null != taskProcessSettings && taskProcessSettings.size() > 0){
            for(TaskProcessSettings tps:taskProcessSettings){
                Date nt = tps.getEnd_notice_time();
                if(new Date().before(nt)){
                    List<User> users = userTaskMapper.listTaskUser(tps.getTask_id());
                    LinkedHashMap<String,String> contentMap = new LinkedHashMap<>();
                    List<String> phones = new ArrayList<>();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (User user:users){
                        phones.add(user.getMobile());
                        stringBuffer.append("[");
                        stringBuffer.append(user.getUsername());
                        stringBuffer.append("]");
                    }
                    contentMap.put("当前用户","提醒机器人");
                    contentMap.put("操作","任务进度提醒");
                    contentMap.put("任务人员",stringBuffer.toString());
                    contentMap.put("任务名称",tps.getTask_name());
                    contentMap.put("计划截至时间", DateUtil.date2String(tps.getEnd_notice_time()) );
                    contentMap.put("温馨提示", "请" + stringBuffer.toString() + "安排好工作时间，在决定是否进行加班以便完成任务进度");
                    Team teamParam = new Team();
                    teamParam.setId(tps.getTask_id());
                    Team team = teamMapper.selectByPrimaryKey(teamParam);
                    String sendUrl = team.getDing_url();
                    dingDingRobot.doSendPhones(contentMap,phones,sendUrl);

                    TaskProcessSettings updateBean = new TaskProcessSettings();
                    updateBean.setId(tps.getId());
                    updateBean.setNeed_notice("0");
                    taskProcessSettingsMapper.updateByPrimaryKeySelective(updateBean);
                }
            }
        }

    }



}
