package com.zengshi.aip.provider.mapp;


/**
 * 
 * 广播信息,发送给所有人
 * public final static int MSGTYPE_BC = 0;
 * 
 * 群组信息，发送给指定的群组
 * public final static int MSGTYPE_GROUP = 1;
 * 
 * 单人信息，发送给指定的用户
 * public final static int MSGTYPE_ONE = 2;
 * 
 * 多人信息，（暂未实现，推送别名的集合，发送给指定的多人）
 * public final static int MSGTYPE_LIST = 4; 
 * 
 * Title: MVNO-CRM <br>
 * Description: <br>
 * Date: 2014年9月24日 <br>
 * Copyright (c) 2014 AILK <br>
 * 
 * @author jiangwei6
 */
public enum AppPushType{
    MSGTYPE_BC, MSGTYPE_GROUP, MSGTYPE_ONE, MSGTYPE_LIST;
}
