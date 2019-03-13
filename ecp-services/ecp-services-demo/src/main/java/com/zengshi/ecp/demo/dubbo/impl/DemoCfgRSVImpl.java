/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoCfgRSVImpl.java 
 * Package Name:com.zengshi.ecp.demo.dubbo.impl 
 * Date:2015-8-7下午5:25:18 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.demo.dubbo.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zengshi.ecp.demo.dao.model.DemoCfg;
import com.zengshi.ecp.demo.dubbo.dto.DemoCfgReqDTO;
import com.zengshi.ecp.demo.dubbo.dto.DemoCfgRespDTO;
import com.zengshi.ecp.demo.dubbo.interfaces.IDemoCfgRSV;
import com.zengshi.ecp.demo.dubbo.util.DemoConstants;
import com.zengshi.ecp.demo.service.common.interfaces.IDemoCfgSV;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ThreadId;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-7下午5:25:18  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class DemoCfgRSVImpl implements IDemoCfgRSV {
    
    @Resource(name="demoCfgSV")
    private IDemoCfgSV demoCfgSV;
    
    private static Logger logger = LoggerFactory.getLogger(DemoCfgRSVImpl.class);
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.demo.dubbo.interfaces.IDemoCfgRSV#saveDemoCfg(com.zengshi.ecp.demo.dubbo.dto.DemoCfgReqDTO) 
     */
    @Override
    public void saveDemoCfg(DemoCfgReqDTO demoCfg) throws BusinessException {
        
        DemoCfg cfg = new DemoCfg();
        cfg.setCode(demoCfg.getCode());
        cfg.setCreateTime(new Timestamp(demoCfg.getCreateTime().getTime()));
        cfg.setInfo(demoCfg.getInfo());
        try{
            demoCfgSV.saveDemoCfg(cfg);
        } catch(Exception err){
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];写入DemoCfg异常：", err);
            throw new BusinessException(DemoConstants.DEMO_ERROR_INSERT,new String[]{"T_DEMO_CFG"});
        }
        
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.demo.dubbo.interfaces.IDemoCfgRSV#listDemoCfg(com.zengshi.ecp.server.front.dto.BaseInfo) 
     */
    @Override
    public PageResponseDTO<DemoCfgRespDTO> listDemoCfg(BaseInfo info) throws BusinessException {
        
        PageResponseDTO<DemoCfgRespDTO> result = PageResponseDTO.buildByBaseInfo(info, DemoCfgRespDTO.class);
        
        result.setResult(new ArrayList<DemoCfgRespDTO>());
        List<DemoCfg> cfgs = null;
        try{
            cfgs = demoCfgSV.queryDemoCfgList();
        } catch(Exception err){
            logger.error("查询demoCfg异常：",err);
            throw new BusinessException(DemoConstants.DEMO_ERROR_SELECT);
        }
        
        if(cfgs == null){
            result.setCount(0);
            result.setPageCount(0);
        } else {
            result.setCount(cfgs.size());
            result.setPageCount(cfgs.size()/info.getPageSize() + 1);
            for(DemoCfg cfg : cfgs){
                DemoCfgRespDTO dto = new DemoCfgRespDTO();
                dto.setId(cfg.getId().longValue());
                dto.setCode(cfg.getCode());
                dto.setCreateTime(cfg.getCreateTime());
                dto.setInfo(cfg.getInfo());
                
                result.getResult().add(dto);
            }
        }
        
        return result;
    }

    @Override
    public PageResponseDTO<DemoCfgRespDTO> listDemoCfgPage(DemoCfgReqDTO dto) throws BusinessException {
        
        PageResponseDTO<DemoCfgRespDTO> result = PageResponseDTO.buildByBaseInfo(dto, DemoCfgRespDTO.class);
        result.setResult(new ArrayList<DemoCfgRespDTO>());
        
        List<DemoCfg> cfgs = null;
        long cnt = 0;
        try{
            cfgs = demoCfgSV.queryDemoCfgPage(dto);
            cnt = demoCfgSV.queryDemoCfgCount(dto);
        } catch(Exception err){
            logger.error("查询demoCfg异常：",err);
            throw new BusinessException(DemoConstants.DEMO_ERROR_SELECT);
        }
        
        if(cfgs == null){
            result.setCount(0);
            result.setPageCount(0);
        } else {
            result.setCount(cnt);
            for(DemoCfg cfg : cfgs){
                DemoCfgRespDTO tmpDto = new DemoCfgRespDTO();
                tmpDto.setId(cfg.getId().longValue());
                tmpDto.setCode(cfg.getCode());
                tmpDto.setCreateTime(cfg.getCreateTime());
                tmpDto.setInfo(cfg.getInfo());
                
                result.getResult().add(tmpDto);
            }
        }
        
        return result;
    }
    
    

}

