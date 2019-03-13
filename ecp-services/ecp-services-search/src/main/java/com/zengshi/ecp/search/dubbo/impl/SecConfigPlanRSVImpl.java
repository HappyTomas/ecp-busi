package com.zengshi.ecp.search.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.search.dubbo.dto.IndexReusltRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecOperLogReqDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecConfigPlanRSV;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.search.util.SearchUtils;
import com.zengshi.ecp.search.index.IndexManager;
import com.zengshi.ecp.search.service.common.interfaces.ISecConfigSV;
import com.zengshi.ecp.search.service.common.interfaces.ISecOperLogSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月22日上午10:19:50  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecConfigPlanRSVImpl implements ISecConfigPlanRSV {
    
    private final static String MODULE = "SecConfigPlanRSVImpl";
    
    @Resource
    public ISecConfigSV secConfigSV;
    
    @Resource
    public ISecOperLogSV secOperLogSV;

    @Override
    public String createCollection(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        
        secConfigReqDTO.setStatus(SearchConstants.STATUS_VALID);
        SecConfigRespDTO secConfigRespDTO=secConfigSV.querySecConfigById(secConfigReqDTO);
        
        SearchUtils.createCollection(secConfigRespDTO.getConfigCollectionName());
        
        //集合初始化状态更新 
        secConfigReqDTO.setCollectionStatus(SearchConstants.STATUS_1);
        secConfigSV.updateSecConfigSelective(secConfigReqDTO);
        
        return "集合创建成功";
    }
    
    @Override
    public String deleteCollection(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        
        secConfigReqDTO.setStatus(SearchConstants.STATUS_VALID);
        SecConfigRespDTO secConfigRespDTO=secConfigSV.querySecConfigById(secConfigReqDTO);
        
        SearchUtils.deleteCollection(secConfigRespDTO.getConfigCollectionName());
        
        //集合初始化状态更新 
        secConfigReqDTO.setCollectionStatus(SearchConstants.STATUS_0);
        secConfigReqDTO.setIndexStatus(SearchConstants.STATUS_0);
        secConfigSV.updateSecConfigSelective(secConfigReqDTO);
        
        return "集合删除成功";
    }

    @Override
    public void cleanIndex(SecConfigReqDTO req) throws BusinessException {
        
        SecOperLogReqDTO secOperLogReqDTO=new SecOperLogReqDTO();
        secOperLogReqDTO.setConfigId(req.getId());
        secOperLogReqDTO.setCreateStaff(req.getStaff().getId());
        secOperLogReqDTO.setCreateTime(DateUtil.getSysDate());
        secOperLogReqDTO.setCurrentSiteId(req.getCurrentSiteId());
        secOperLogReqDTO.setOperType("5");
        secOperLogReqDTO.setOperIndexType("1");
        
        try{
            IndexManager.cleanIndex(req);
            secOperLogSV.saveSecOperLog(secOperLogReqDTO);
        }catch(BusinessException e){
            secOperLogReqDTO.setError(e.getErrorMessage());
            secOperLogSV.saveSecOperLog(secOperLogReqDTO);
            throw e;
        }
        
        SecConfigReqDTO secConfigReqDTO=new SecConfigReqDTO();
        secConfigReqDTO.setId(req.getId());
        //更新索引状态为未索引
        secConfigReqDTO.setIndexStatus(SearchConstants.STATUS_0);
        secConfigSV.updateSecConfigSelective(secConfigReqDTO);
    }

    @Override
    public IndexReusltRespDTO reFullImportIndex(final SecConfigReqDTO req, final boolean flag) throws BusinessException {
        
        final SecOperLogReqDTO secOperLogReqDTO=new SecOperLogReqDTO();
        secOperLogReqDTO.setConfigId(req.getId());
        secOperLogReqDTO.setCreateStaff(req.getStaff().getId());
        secOperLogReqDTO.setCreateTime(DateUtil.getSysDate());
        secOperLogReqDTO.setCurrentSiteId(req.getCurrentSiteId());
        secOperLogReqDTO.setOperType("5");
        if(flag){
            secOperLogReqDTO.setOperIndexType("3");
        }else{
            secOperLogReqDTO.setOperIndexType("2");
        }
        
        final IndexReusltRespDTO indexReusltRespDTO=new IndexReusltRespDTO();
        
        final SecConfigRespDTO secConfigRespDTO=secConfigSV.querySecConfigById(req);
        if(secConfigRespDTO==null){
            indexReusltRespDTO.setNotExist();
            secOperLogReqDTO.setError(indexReusltRespDTO.getMessage());
            secOperLogSV.saveSecOperLog(secOperLogReqDTO);
        }
        //索引重建中
        //索引重建中停止后场导致状态锁住
        //else if(StringUtils.equals(secConfigRespDTO.getIndexStatus(), SearchConstants.STATUS_2)){
        else if(IndexManager.ifIndexing(secConfigRespDTO.getId())){
            indexReusltRespDTO.setDuplicated();
            secOperLogReqDTO.setError(indexReusltRespDTO.getMessage());
            secOperLogSV.saveSecOperLog(secOperLogReqDTO);
        }else{
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    
                    IndexManager.indexingConfigIdList.add(secConfigRespDTO.getId()+"");
                    
                    SecConfigReqDTO secConfigReqDTO=new SecConfigReqDTO();
                    secConfigReqDTO.setId(req.getId());
                    
                    try{
                        //更新状态为重建中
                        secConfigReqDTO.setIndexStatus(SearchConstants.STATUS_2);
                        secConfigSV.updateSecConfigSelective(secConfigReqDTO);
                        LogUtil.info(MODULE,"【搜索引擎】索引重建中！"+indexReusltRespDTO.toString());
                        
                        IndexReusltRespDTO indexReusltRespDTO=IndexManager.reFullImportIndex(req, flag);
                        //索引重建失败这部分信息为空
                        secOperLogReqDTO.setArgs(indexReusltRespDTO.getArgs());
                        secOperLogReqDTO.setStartTime(indexReusltRespDTO.getStart());
                        secOperLogReqDTO.setNowTime(indexReusltRespDTO.getEnd());
                        secOperLogReqDTO.setDatacount(indexReusltRespDTO.getDataListCount());
                        secOperLogReqDTO.setFailurecount(indexReusltRespDTO.getFailCount());
                        secOperLogReqDTO.setFailureinfo(indexReusltRespDTO.getFailIdList().toString());
                        secOperLogReqDTO.setSeconds(Integer.parseInt(indexReusltRespDTO.getTimeCost()+""));
                        secOperLogReqDTO.setTps(indexReusltRespDTO.getTps());
                        
                        //更新索引状态为已索引
                        secConfigReqDTO.setIndexStatus(SearchConstants.STATUS_1);
                        secConfigSV.updateSecConfigSelective(secConfigReqDTO);
                        LogUtil.info(MODULE,"【搜索引擎】索引重建成功！"+indexReusltRespDTO.toString());
                    }catch(Exception e){
                        secOperLogReqDTO.setError(SearchUtils.getExceptionMessage(e));
                        
                        //更新索引状态为已索引
                        secConfigReqDTO.setIndexStatus(SearchConstants.STATUS_1);
                        secConfigSV.updateSecConfigSelective(secConfigReqDTO);
                        LogUtil.info(MODULE,"【搜索引擎】索引重建失败！"+indexReusltRespDTO.toString());
                    }
                    IndexManager.indexingConfigIdList.remove(secConfigRespDTO.getId()+"");
                    secOperLogSV.saveSecOperLog(secOperLogReqDTO);
                    
                }
            }).start();
            indexReusltRespDTO.setExecuting();
        }
        
        return indexReusltRespDTO;
        
    }

}

