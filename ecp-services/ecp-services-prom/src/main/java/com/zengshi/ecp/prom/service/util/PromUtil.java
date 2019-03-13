package com.zengshi.ecp.prom.service.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

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
    /**
     * 针对hashMap key 排序
     * @param map
     * @param ifKey  key表示按照key ，排序  value 按照value排序。为空 默认为key排序。
     * @param sort    asc表示升序  desc表示降序  为空默认asc
     * @author huangjx
     */
    @SuppressWarnings("rawtypes")
    public static List sortMap(Map map,final String ifKey,final String sort) {

        try {
            
            if (CollectionUtils.isEmpty(map)) {
                return null;
            }
            
            List arrayList = new ArrayList(map.entrySet());

            Collections.sort(arrayList, new Comparator() {
                public int compare(Object o1, Object o2) {
                    Map.Entry obj1 = (Map.Entry) o1;
                    Map.Entry obj2 = (Map.Entry) o2;
                    
                    String v1="";
                    String v2="";
                    
                    //按照key排序
                    if(!"value".equals(ifKey)){
                          if(obj1.getKey()!=null){
                             v1=obj1.getKey().toString();
                          }
                          if(obj2.getKey()!=null){
                             v2=obj2.getKey().toString();
                          }
                    }else{
                     //按照value 排序
                        if(obj1.getValue()!=null){
                            v1=obj1.getValue().toString();
                         }
                         if(obj2.getValue()!=null){
                            v2=obj2.getValue().toString();
                         }
                    }
                    if(!"desc".equals(sort)){
                        return (v1.compareTo(v2));
                    }else{
                        return (v2.compareTo(v1));
                    }
                }
            });
            
            return arrayList;
        } catch (Exception ex) {
            LogUtil.error(MODULE, ex.toString());
        }
       return null;
    }

}
