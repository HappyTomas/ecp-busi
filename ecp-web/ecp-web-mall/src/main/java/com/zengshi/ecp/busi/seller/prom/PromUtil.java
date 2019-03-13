package com.zengshi.ecp.busi.seller.prom;

import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-30 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromUtil {
    
    private static String MODULE = PromUtil.class.getName();
    /**
     * 验证 value 
     * @param key
     * @param value
     * @return
     * @author huangjx
     */
    public static boolean checkSysCfgValue(String key,String value) {
        if(StringUtil.isEmpty(value)){
            return Boolean.FALSE;
        }
        if(StringUtil.isEmpty(key)){
            return Boolean.FALSE;
        }
        try{
           return SysCfgUtil.checkSysCfgValue(key,value);
        }catch(Exception ex){
            LogUtil.error(MODULE, "哎哦 取系统配置表报错了哦 key="+key +",value="+value);
            return Boolean.FALSE;
        }
    }
    /**
     * 验证 value 是否为匹配值
     * @param key
     * @param value
     * @return
     * @author huangjx
     */
    public static boolean matchSysCfgValue(String key,String value) {
        if(StringUtil.isEmpty(value)){
            return Boolean.FALSE;
        }
        if(StringUtil.isEmpty(key)){
            return Boolean.FALSE;
        }
        try{
            BaseSysCfgRespDTO  dto= SysCfgUtil.fetchSysCfg(key);
            if(dto==null){
                return Boolean.FALSE;
            }
            if(dto.getParaValue().contains(value)){
                return Boolean.TRUE;
            }
           return Boolean.FALSE;
        }catch(Exception ex){
            LogUtil.error(MODULE, "哎哦 取系统配置表报错了哦 key="+key +",value="+value);
            return Boolean.FALSE;
        }
    }


}
