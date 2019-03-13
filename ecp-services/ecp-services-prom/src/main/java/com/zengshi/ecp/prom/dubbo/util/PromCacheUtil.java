package com.zengshi.ecp.prom.dubbo.util;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromMsgRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromTypeRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-prom <br>
 * Description: <br>
 * Date:2015年9月30日上午9:36:48 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version
 * @since JDK 1.7
 */
@Service("promCacheUtil")
public class PromCacheUtil {

    private static final String MODULE = PromCacheUtil.class.getName();

    private static IPromTypeRSV promTypeRSV;
    
    public  IPromTypeRSV getPromTypeRSV() {
        return promTypeRSV;
    }
    @Resource(name="promTypeRSV")
    public  void setPromTypeRSV(IPromTypeRSV promTypeRSV) {
        PromCacheUtil.promTypeRSV = promTypeRSV;
    }
    private static IPromMsgRSV promMsgRSV;
    
    public IPromMsgRSV getPromMsgRSV() {
        return promMsgRSV;
    }
    @Resource(name="promMsgRSV")
    public void setPromMsgRSV(IPromMsgRSV promMsgRSV) {
        PromCacheUtil.promMsgRSV = promMsgRSV;
    }
    
    /**
     * 
     * getPromType:(获取促销类型缓存). <br/>
     * 
     * @author huangjx
     * @return
     * @since JDK 1.7
     */
    public static PromTypeResponseDTO getPromTypeCache(String promTypeCode) {
        PromTypeResponseDTO  resp = new PromTypeResponseDTO();
        LogUtil.info(MODULE, "========开始获取促销类型缓存：" + resp);
        try {
            resp = (PromTypeResponseDTO) CacheUtil
                    .getItem(PromConstants.PromKey.TYPE_PROM + promTypeCode);
            
            if(resp==null){
                resp= promTypeRSV.queryPromTypeByDB(promTypeCode);
              //加入缓存
                CacheUtil.addItem((PromConstants.PromKey.TYPE_PROM+promTypeCode), resp);
            }
            LogUtil.info(MODULE, "========结束获取促销类型缓存：" + resp);
        } catch (Exception e) {
            LogUtil.error(MODULE, "========获取促销类型缓存报错：" + resp);
        }

        return resp;
    }

    /**
     * 
     * getPromType:(获取促销类型缓存). <br/>
     * 
     * @author huangjx
     * @return
     * @since JDK 1.7
     */
    public static String getPromType(String promTypeCode) {
        PromTypeResponseDTO  resp = new PromTypeResponseDTO();
        resp= getPromTypeCache(promTypeCode);
        if(resp==null){
            return "";
        }else{
            if(StringUtil.isEmpty(resp.getPromTypeName())){
                return "";
            }
        }
        return resp.getPromTypeName();
    }
    public static long clearPromTypeCache() {
        List<Object> lst = CacheUtil.keys(PromConstants.PromKey.TYPE_PROM + "*");
        if (lst == null || lst.size() == 0) {
            return 0;
        } else {
            LogUtil.info(MODULE, "========缓存大小：" + lst.size());
            for (Object obj : lst) {
                String key = (String) obj;
                LogUtil.info(MODULE, "======清空key" + key);
                CacheUtil.delItem(key);
            }
            LogUtil.info(MODULE, "========清空结束");
            return lst.size();
        }
    }
    

    /**
     * 
     * getPromTypeMsg:(获取促销类型提醒信息缓存). <br/>
     *  key:String promTypeCode, String status,
            String position
     * @author huangjx
     * @return
     * @since JDK 1.7
     */
    public static PromTypeMsgResponseDTO getPromTypeMsgCache(String key) {
        PromTypeMsgResponseDTO  resp = new PromTypeMsgResponseDTO();
        LogUtil.info(MODULE, "========开始获取促销类型提醒缓存：" + resp);
        try {
            resp = (PromTypeMsgResponseDTO) CacheUtil
                    .getItem(PromConstants.PromKey.CODE_PROM_TYPE_MSG+key);
            
            if(resp==null){
                List<PromTypeMsgResponseDTO> list=promMsgRSV.queryPromMsgList(null);
                for(PromTypeMsgResponseDTO dto:list){
                    CacheUtil.addItem((PromConstants.PromKey.CODE_PROM_TYPE_MSG+dto.getPromTypeCode()+dto.getStatus()+dto.getPosition()), dto);
                }
                resp = (PromTypeMsgResponseDTO) CacheUtil
                        .getItem(PromConstants.PromKey.CODE_PROM_TYPE_MSG+key); 
            }
            LogUtil.info(MODULE, "========结束获取促销类型提醒缓存：" + resp);
        } catch (Exception e) {
            LogUtil.error(MODULE, "========获取促销类型提醒缓存报错：" + resp);
        }

        return resp;
    }
    
    public static long clearPromTypeMsgCache() {
        List<Object> lst = CacheUtil.keys(PromConstants.PromKey.CODE_PROM_TYPE_MSG + "*");
        if (lst == null || lst.size() == 0) {
            return 0;
        } else {
            LogUtil.info(MODULE, "========缓存大小：" + lst.size());
            for (Object obj : lst) {
                String key = (String) obj;
                LogUtil.info(MODULE, "======清空key" + key);
                CacheUtil.delItem(key);
            }
            LogUtil.info(MODULE, "========清空结束");
            return lst.size();
        }
    }
    
}
