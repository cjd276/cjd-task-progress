package com.cjd.rescue.product.ding;

import com.cjd.rescue.common.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 钉钉消息
 */

public class DingMessage {

    /**
     * 消息类型
     * 支持：text、link、markdown
     */
    private String msgtype = "text";

    /**
     * 消息类
     */
    private Text text;

    /**
     * 接受类
     */
    private At at = new At();


    public  static class Text{
        /**
         * 消息内容
         */
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Text(Map<String,String> contentMap) {
            StringBuffer stringBuffer = new StringBuffer();
            concat(stringBuffer,"时间", DateUtil.getCurrentDate());
            for(String key:contentMap.keySet()){
                concat(stringBuffer,key,contentMap.get(key));
            }
            this.content = stringBuffer.toString();

        }

        private void concat(StringBuffer stringBuffer,String key,String value){
            stringBuffer.append("【");
            stringBuffer.append(key);
            stringBuffer.append("】");
            stringBuffer.append(value);
            stringBuffer.append(System.lineSeparator());
        }
    }



    public static class At{
        /**
         * 接受人列表
         */
        private List<String> atMobiles = new ArrayList<>();

        /**
         * 是否群发
         */
        private boolean isAtAll = false;

        public List<String> getAtMobiles() {
            return atMobiles;
        }

        public void setAtMobiles(List<String> atMobiles) {
            this.atMobiles = atMobiles;
        }

        public boolean getIsAtAll() {
            return isAtAll;
        }

        public void setIsAtAll(boolean atAll) {
            isAtAll = atAll;
        }
    }





    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public At getAt() {
        return at;
    }

    public void setAt(At at) {
        this.at = at;
    }
}