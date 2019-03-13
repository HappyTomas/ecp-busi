package com.zengshi.ecp.cms.dubbo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;


/**CMS站点刷入缓存
 * Title: ECP <br>
 * Project Name:ecp-services-cms-server <br>
 * Description: <br>
 * Date:2015年10月14日上午11:36:12  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
@Service("cmsCacheUtil")
public class CmsCacheUtil {

    private static final String MODULE = CmsCacheUtil.class.getName();
    
    private static ICmsSiteRSV cmsSiteRSV;
    
    public ICmsSiteRSV getCmsSiteRSV() {
        return cmsSiteRSV;
    }

    @Resource(name="cmsSiteRSV")
    public void setCmsSiteRSV(ICmsSiteRSV cmsSiteRSV) {
        CmsCacheUtil.cmsSiteRSV = cmsSiteRSV;
    }

    /** 
     * getCmsSiteCache:(通过站点ID获取站点作息). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param id
     * @return 
     * @since JDK 1.6 
     */ 
    public static CmsSiteRespDTO getCmsSiteCache(Long id) {
        LogUtil.info(MODULE, "========开始获取站点缓存：" + id);
        CmsSiteRespDTO resp = new CmsSiteRespDTO();
        try {
            Map map = (HashMap<Long ,CmsSiteRespDTO>) CacheUtil.getItem(CmsConstants.ParamConfig.CMS_SITE_CACHE);
            if(CollectionUtils.isEmpty(map)){//1.1当缓存为空时，查库添加入缓存
                resp = addCmsSiteCache(id);
            } else {//1.2 不为空，直接获取缓存
                resp=(CmsSiteRespDTO)map.get(id);
                if(resp == null){
                    resp=addCmsSiteCache(id);
                }
            }
            LogUtil.info(MODULE, "========结束获取站点缓存：" + resp);
        } catch (Exception e) {
            LogUtil.error(MODULE, "========获取站点缓存报错：" + resp,e);
        }

        return resp;
    }
    
    /** 
     * queryCmsDefaultSite:(获取默认站点). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
     */ 
    public static CmsSiteRespDTO queryCmsDefaultSite() {
        LogUtil.info(MODULE, "========开始获取站点缓存：");
        
        CmsSiteRespDTO resp = new CmsSiteRespDTO();
        List<CmsSiteRespDTO> list = new ArrayList<CmsSiteRespDTO>();
        try {
            Map<Long ,CmsSiteRespDTO> map = (HashMap<Long ,CmsSiteRespDTO>) CacheUtil.getItem(CmsConstants.ParamConfig.CMS_SITE_CACHE);
            if(CollectionUtils.isEmpty(map)){//2.1 当缓存中没有时，取表
                CmsSiteReqDTO dto = new CmsSiteReqDTO();
                dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                dto.setIsdefault(CmsConstants.IsNot.CMS_ISNOT_1);
                list = cmsSiteRSV.queryCmsSiteList(dto);
            }else{//2.2 当缓存中有数据时
                for(CmsSiteRespDTO respDTO:map.values()){
                    if(respDTO != null && CmsConstants.IsNot.CMS_ISNOT_1.equals(respDTO.getIsdefault())){
                        list.add(respDTO);
                    }
                }
            }
            
            if(!CollectionUtils.isEmpty(list)){
                resp =  list.get(0);
            }
            LogUtil.info(MODULE, "========结束获取站点缓存：" + resp);
        } catch (Exception e) {
            LogUtil.error(MODULE, "========获取站点缓存报错：" + resp,e);
        }
        
        return resp;
    }
    
    /** 
     * addCmsSiteCache:(站点 新增到cache). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param id 
     * @since JDK 1.6 
     */ 
    public static CmsSiteRespDTO addCmsSiteCache(Long id) {
        
        //1 查询库
        CmsSiteReqDTO cmsSiteReqDTO=new CmsSiteReqDTO();
        cmsSiteReqDTO.setId(id);
        //cmsSiteReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        CmsSiteRespDTO cmsSiteRespDTO= cmsSiteRSV.queryCmsSite(cmsSiteReqDTO);
        
        //2 查询缓存
        Map<Long ,CmsSiteRespDTO> map = (HashMap<Long ,CmsSiteRespDTO>)CacheUtil.getItem(CmsConstants.ParamConfig.CMS_SITE_CACHE);
        //2.1 当缓存无数据
        if(CollectionUtils.isEmpty(map)){
            if(cmsSiteRespDTO!=null){
                //加入缓存
                queryCmsSiteCache();
            }
        }else{//2.2 当缓存有数据
            CmsSiteRespDTO dto= map.get(cmsSiteRespDTO.getId());
            if(dto==null){//2.2.1 当缓存中值为空时，将数据库中的对象添加至缓存
                if(cmsSiteRespDTO!=null){
                    if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1.equals(cmsSiteRespDTO.getStatus())){
                        map.put(cmsSiteRespDTO.getId(), cmsSiteRespDTO);
                        CacheUtil.addItem(CmsConstants.ParamConfig.CMS_SITE_CACHE, map);
                    }
                }
            }else{//2.2.2 当缓存中有值时，删除此缓存，同时将库中的对象添加至缓存
                if(cmsSiteRespDTO!=null){
                    map.remove(cmsSiteRespDTO.getId());
                    if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1.equals(cmsSiteRespDTO.getStatus())){
                        map.put(cmsSiteRespDTO.getId(), cmsSiteRespDTO);
                        CacheUtil.addItem(CmsConstants.ParamConfig.CMS_SITE_CACHE, map);
                    }
                }
            }
        }
        return cmsSiteRespDTO;
    }
    
    /** 
     * queryCmsSiteCache:(查询站点列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
     */ 
    public static Map<Long ,CmsSiteRespDTO> queryCmsSiteCache() {
        //1、缓存查询
        Map<Long ,CmsSiteRespDTO> map = (HashMap<Long ,CmsSiteRespDTO> )CacheUtil.getItem(CmsConstants.ParamConfig.CMS_SITE_CACHE);
        //1.1、缓存中无数据
        if(CollectionUtils.isEmpty(map)){
            //1.2、查表，获取有效站点
            CmsSiteReqDTO cmsSiteReqDTO =new CmsSiteReqDTO();
            cmsSiteReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            List<CmsSiteRespDTO> list= cmsSiteRSV.queryCmsSiteList(cmsSiteReqDTO);
            
            //1.3、将表中的数据加入缓存
            map=new HashMap<Long ,CmsSiteRespDTO>();
            if(!CollectionUtils.isEmpty(list)){
                 for(CmsSiteRespDTO dto:list){
                     map.put(dto.getId(), dto);
                 }
                 CacheUtil.addItem(CmsConstants.ParamConfig.CMS_SITE_CACHE, map);
            }
        }
        return map;
    }
    
    /** 
     * clearCmsSiteCache:(清空站点). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
     */ 
    public static long clearCmsSiteCache() {
        Map<Long,Object> map = (HashMap<Long,Object>) CacheUtil.getItem(CmsConstants.ParamConfig.CMS_SITE_CACHE);
        if (CollectionUtils.isEmpty(map)) {
            return 0;
        } else {
            LogUtil.info(MODULE, "========缓存大小：" + map.size());
            CacheUtil.delItem(CmsConstants.ParamConfig.CMS_SITE_CACHE);
            LogUtil.info(MODULE, "========清空结束");
            return map.size();
        }
    }
    
}
