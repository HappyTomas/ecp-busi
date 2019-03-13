/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoCfgSVImpl.java 
 * Package Name:com.zengshi.ecp.demo.service.common.impl 
 * Date:2015-8-3下午10:04:47 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.demo.service.common.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.demo.dao.mapper.common.DemoCfgMapper;
import com.zengshi.ecp.demo.dao.model.DemoCfg;
import com.zengshi.ecp.demo.dao.model.DemoCfgCriteria;
import com.zengshi.ecp.demo.dao.model.DemoInfo;
import com.zengshi.ecp.demo.dubbo.dto.DemoCfgReqDTO;
import com.zengshi.ecp.demo.service.common.interfaces.IDemoCfgSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-3下午10:04:47  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */

@Service
public class DemoCfgSVImpl implements IDemoCfgSV {
    
    @Resource(name="seq_demo_cfg")
    private PaasSequence seq_demo_cfg;
    
    @Resource
    private DemoCfgMapper demoCfgMapper;
    
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.demo.service.common.interfaces.IDemoCfgSV#saveDemoCfg(com.zengshi.ecp.demo.dao.model.DemoCfg) 
     */
    @Override
    public long saveDemoCfg(DemoCfg cfg) throws BusinessException {
        cfg.setId(new BigDecimal(seq_demo_cfg.nextValue()));
        demoCfgMapper.insert(cfg);
        return cfg.getId().longValue();
    }

    @Override
    public DemoCfg queryDemoCfgById(long id) throws BusinessException {
        
        /*DemoCfgCriteria criteria = new DemoCfgCriteria();
        criteria.createCriteria().andIdEqualTo(new BigDecimal(id));
        
        List<DemoCfg> cfgs = demoCfgMapper.selectByExample(criteria);
        if(cfgs!=null && cfgs.size() > 0){
            return cfgs.get(0);
        } else {
            throw new BusinessException("demo.C10011");
        }*/
        DemoCfg cfg = demoCfgMapper.selectByPrimaryKey(new BigDecimal(id));
        if(cfg == null){
            throw new BusinessException("demo.C10011");
        } else {
            return cfg;
        }
    }

    @Override
    public List<DemoCfg> queryDemoCfgList() throws BusinessException {
        DemoCfgCriteria criteria = new DemoCfgCriteria();
        List<DemoCfg> cfgs = demoCfgMapper.selectByExample(criteria);
        return cfgs;
    }

    @Override
    public List<DemoCfg> queryDemoCfgPage(DemoCfgReqDTO dto) throws BusinessException {
        
        DemoCfgCriteria criteria = new DemoCfgCriteria();
        criteria.setLimitClauseStart(dto.getStartRowIndex());
        criteria.setLimitClauseCount(dto.getPageSize());
        
        List<DemoCfg> cfgs = demoCfgMapper.selectByExample(criteria);
        
        return cfgs;
    }

    @Override
    public long queryDemoCfgCount(DemoCfgReqDTO dto) throws BusinessException {
        DemoCfgCriteria criteria = new DemoCfgCriteria();
        criteria.setLimitClauseStart(dto.getStartRowIndex());
        criteria.setLimitClauseCount(dto.getPageSize());
        long cnt = demoCfgMapper.countByExample(criteria);
        return cnt;
    }

    
    
}

