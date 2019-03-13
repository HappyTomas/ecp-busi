package com.zengshi.aip.provider.mapp;

import java.util.Map;

/**
 * app端调用aip后场服务
 * Title: MVNO-CRM <br>
 * Description: <br>
 * Date: 2014年7月25日 <br>
 * Copyright (c) 2014 AILK <br>
 * 
 * @author jiangwei6
 */
public interface MappService {
    
   /**
    * Mapp后场服务，接收前端传入的请求对象，调用izengshi服务，生成返回对象后，返回
    * @param requestStr 请求串
    * @param attrMap  请求参数(前端将封装在httpRequest中的部分参数put进来)
    * @return json 串
    * @author jiangwei
    */
    @Deprecated
    public String getMappResponse(String requestStr,Map<String,Object> attrMap);
    
    /**
     * 
     * @param appPushData
     * @return sendNo:消息代码； flag:"1" 成功； error:错误内容，成功的相应不包含此key。
     * @author jiangwei6
     */
    public Map<String,String> push2App(AppPushData appPushData);
    
    
    /**
     * app端崩溃日志记录
     * @param staffName 提交人
     * @param StaffId 提交id
     * @param errorContent 错误内容
     * @param deviceInfo 设备信息（ios/android）
     * @author jiangwei6
     */
    public void insertErrorInfo(String staffName,String StaffId,String errorContent,String deviceInfo) throws Exception;
    
}
