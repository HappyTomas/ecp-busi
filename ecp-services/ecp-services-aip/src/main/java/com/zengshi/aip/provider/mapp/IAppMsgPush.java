package com.zengshi.aip.provider.mapp;

import java.util.Map;

public interface IAppMsgPush {

    Map<String, String> sendMsg(AppPushData appPushData);

}
