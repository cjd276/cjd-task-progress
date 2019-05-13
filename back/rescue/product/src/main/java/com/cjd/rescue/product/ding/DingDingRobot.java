package com.cjd.rescue.product.ding;

import com.alibaba.fastjson.JSONObject;
import com.cjd.rescue.dao.product.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 钉钉机器人
 */
@Service
public class DingDingRobot {

    @Autowired
    private TeamMapper teamMapper;

    /*@Value("${sendUrl}")
    private String sendUrl;*/
    private RestTemplate restTemplate = new RestTemplate();
    @Async
    public void doSend(DingMessage dingMessage,String sendUrl){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(JSONObject.toJSONString(dingMessage),headers);
        System.out.println(JSONObject.toJSONString(dingMessage));
        restTemplate.postForEntity(sendUrl,entity,String.class);
    }
    @Async
    public void doSendAll(LinkedHashMap contentMap,String sendUrl){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        DingMessage dingMessage = buildToAllMessage(contentMap);
        HttpEntity<String> entity = new HttpEntity<>(JSONObject.toJSONString(dingMessage),headers);
        restTemplate.postForEntity(sendUrl,entity,String.class);
    }
    @Async
    public void doSendPhones(LinkedHashMap contentMap,List<String> phones,String sendUrl){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        DingMessage dingMessage = buildToPhonesMessage(contentMap,phones);
        HttpEntity<String> entity = new HttpEntity<>(JSONObject.toJSONString(dingMessage),headers);
        restTemplate.postForEntity(sendUrl,entity,String.class);
    }

    private DingMessage buildToPhonesMessage(LinkedHashMap contentMap,List<String> phones){
        DingMessage dingMessage = new DingMessage();
        DingMessage.Text text = new DingMessage.Text(contentMap);
        dingMessage.setText(text);
        DingMessage.At at = new DingMessage.At();
        at.setAtMobiles(phones);
        dingMessage.setAt(at);
        return dingMessage;
    }
    private DingMessage buildToAllMessage(LinkedHashMap contentMap){
        DingMessage dingMessage = new DingMessage();
        DingMessage.Text text = new DingMessage.Text(contentMap);
        dingMessage.setText(text);
        DingMessage.At at = new DingMessage.At();
        at.setIsAtAll(true);
        dingMessage.setAt(at);
        return dingMessage;
    }


    public static void main(String[] args) {
        /*DingMessage dingMessage = new DingMessage();
        LinkedHashMap<String,String> contentMap = new LinkedHashMap<>();
        contentMap.put("用户","测试用户");
        contentMap.put("添加","上线计划");
        contentMap.put("计划id","1111111");

        DingMessage.Text text = new DingMessage.Text(contentMap);
        dingMessage.setText(text);
        DingMessage.At at = new DingMessage.At();

        at.setAtMobiles(Arrays.asList("13051699675"));
        dingMessage.setAt(at);
        //dingMessage.setMsgtype("text");

        DingDingRobot dingDingRobot = new DingDingRobot();
        dingDingRobot.doSend(dingMessage);*/


        DingDingRobot dingDingRobot = new DingDingRobot();
        LinkedHashMap<String,String> contentMap = new LinkedHashMap<>();
        contentMap.put("用户","测试用户");
        contentMap.put("添加","上线计划");
        contentMap.put("计划id","1111111");

        dingDingRobot.doSendAll(contentMap,"https://oapi.dingtalk.com/robot/send?access_token=e1fad460974fa1367bd9c0f1c4b0413a741eac2bbe23c86ce396e4dd45e91145");
        dingDingRobot.doSendPhones(contentMap,Arrays.asList("13051699675","18811007219"),"https://oapi.dingtalk.com/robot/send?access_token=e1fad460974fa1367bd9c0f1c4b0413a741eac2bbe23c86ce396e4dd45e91145");

    }
}