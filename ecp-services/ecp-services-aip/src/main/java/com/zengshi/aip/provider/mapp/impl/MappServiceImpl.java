package com.zengshi.aip.provider.mapp.impl;

import java.util.Map;

import com.zengshi.paas.utils.PaasContextHolder;
import com.zengshi.aip.provider.mapp.AppPushData;
import com.zengshi.aip.provider.mapp.IAppMsgPush;
import com.zengshi.aip.provider.mapp.MappService;

public class MappServiceImpl implements MappService {

    private static IAppMsgPush pushSv;

   //@Autowired
   // private AipAppClientMapper appClientDao;

    @Override
    public String getMappResponse(String requestStr, Map<String, Object> attrMap) {
        String responseJson = "";
        System.out.println("has got aip-provider");
        return responseJson;
    }

    @Override
    public void insertErrorInfo(String staffName, String StaffId, String errorContent,
            String deviceInfo) throws Exception {
        /*
        AipAppError errorInfo = new AipAppError();
        if (StaffId != null) {
            errorInfo.setStaff_id(StaffId);
        } else {
            errorInfo.setStaff_id("00");
        }
        if (staffName != null) {
            if (staffName.length() > 10) {
                staffName = staffName.substring(0, 10);
            }
            errorInfo.setStaff_name(staffName);
        } else {
            errorInfo.setStaff_name("other");
        }
        errorInfo.setError_content(errorContent);
        errorInfo.setMobile_model(deviceInfo);
        
        appClientDao.insertErrorInfo(errorInfo);
        */
        return;
    }

    @Override
    public Map<String, String> push2App(AppPushData appPushData) {
        return getPushIntance().sendMsg(appPushData);
    }

    private static IAppMsgPush getPushIntance() {
        if (null != pushSv)
            return pushSv;
        else {
            pushSv = (IAppMsgPush) PaasContextHolder.getContext().getBean("pushSv");
            return pushSv;
        }
    }

}
