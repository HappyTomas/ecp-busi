package com.zengshi.ecp.demo.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.demo.dao.mapper.common.manual.DemoCfgGroupMapper;
import com.zengshi.ecp.demo.dao.model.DemoCfg;
import com.zengshi.ecp.demo.dao.model.DemoInfo;
import com.zengshi.ecp.demo.dubbo.dto.DemoCfgReqDTO;
import com.zengshi.ecp.demo.service.common.interfaces.IDemoCfgSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.alibaba.fastjson.JSON;


public class DemoCfgSVImplTest  extends EcpServicesTest {
    
    @Resource
    private IDemoCfgSV demoCfgSV;
    
    @Test
    public void testSaveDemoCfg() {
        DemoCfg info = new DemoCfg();
        info.setCode("ss");
        long id = demoCfgSV.saveDemoCfg(info);
        
        DemoCfg demoCfg = demoCfgSV.queryDemoCfgById(id);
        System.out.println(demoCfg.toString());
    }
    
    @Test
    public void testQueryDemoCfgPage(){
        DemoCfgReqDTO dto = new DemoCfgReqDTO();
        dto.setPageNo(1);
        dto.setPageSize(1);
        List<DemoCfg> cfgs = demoCfgSV.queryDemoCfgPage(dto);
        
        for(DemoCfg cfg : cfgs){
            System.out.println(cfg.toString());
        }
    }
    
   
}

