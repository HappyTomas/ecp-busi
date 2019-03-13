package com.zengshi.aip.provider.mapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppPushData implements Serializable{
    
    private static final long serialVersionUID = 8238570098700436495L;

    /**
     * 消息推送指定的类型
     */
    private AppPushType msgType;

    /**
     * 推送者staffId,
     */
    private String username;

    /**
     * 推送者消息服务器密码，当前未使用，后续自建推送服务器时使用
     */
    private String pass;

    /**
     * 消息发送的省份
     */
    private String proviceCode;

    /**
     * 消息发送到省份的某个群体id，可以是staffType(指定类型),cityCode(指定地市),managerId(指定渠道经理)
     */
    private String itemProviceCode;
    
    /**
     * 指定发送的staffId
     */
    private List<String> toNames;
    
    /**
     * 消息标题(当前未使用，ios不支持)
     */
    private String msgTitle;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 未读信息总量，app端显示的badge
     */
    private int msgCount;

    /**
     * 点击后app跳转的页面，主要用来区分消息中心和物流信息，后续可以做其它的区分
     * 有后台消息的类型决定，物流消息为0，其它为1，当前app只对此两种信息分类展示
     */
    private int index;
    
    /**
     * 默认的构造函数
     */
    public AppPushData() {
    }
    /**
     * 默认发送给指定用户的构造函数,创建发送给指定用户的对象
     */
    public AppPushData(String staffId,String to_staffId,String messageContent,int msgCount,int index) {
        this.msgType = AppPushType.MSGTYPE_ONE;
        this.username = staffId;
        List<String> toNames = new ArrayList<String>();
        toNames.add(to_staffId);
        this.toNames = toNames;
        this.msgContent = messageContent;
        this.msgCount = msgCount;
        this.index = index;
    }
    
    /**
     * 默认发送给群体个构造函数,发送给指定群体的对象
     */
    public AppPushData(String staffId,String provinceCode,String itemProvinceCode,String messageContent,int msgCount,int index) {
        this.msgType = AppPushType.MSGTYPE_GROUP;
        this.username = staffId;
        this.proviceCode = provinceCode;
        this.itemProviceCode = itemProvinceCode;
        this.msgContent = messageContent;
        this.msgCount = msgCount;
        this.index = index;
    }

    public AppPushType getMsgType() {
        return msgType;
    }

    public void setMsgType(AppPushType msgType) {
        this.msgType = msgType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getProviceCode() {
        return proviceCode;
    }

    public void setProviceCode(String proviceCode) {
        this.proviceCode = proviceCode;
    }

    public String getItemProviceCode() {
        return itemProviceCode;
    }

    public void setItemProviceCode(String itemProviceCode) {
        this.itemProviceCode = itemProviceCode;
    }

    public List<String> getToNames() {
        return toNames;
    }

    public void setToNames(List<String> toNames) {
        this.toNames = toNames;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
