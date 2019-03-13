/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoCfgGroupMapperTest.java 
 * Package Name:com.zengshi.ecp.demo.test 
 * Date:2015-8-14上午10:27:39 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.demo.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.demo.dao.mapper.common.DemoCfgMapper;
import com.zengshi.ecp.demo.dao.mapper.common.manual.DemoCfgGroupMapper;
import com.zengshi.ecp.demo.dao.model.DemoCfg;
import com.zengshi.ecp.demo.dao.model.DemoCfgCriteria;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.alibaba.fastjson.JSON;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-14上午10:27:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class DemoCfgGroupMapperTest extends EcpServicesTest {

    @Resource
    private DemoCfgGroupMapper mapper;
    
    @Test
    public void test(){
        List<Map<String, Object>> lst = mapper.selectByExample(10);
        System.out.println("======"+JSON.toJSONString(lst));
    }
    
    
    @Test
    public void testList(){
        List<DemoCfg> lst = mapper.select2DemoCfg(10);
        System.out.println("======"+JSON.toJSONString(lst));
    }
    
    @Test
    public void testOne(){
        DemoCfg lst = mapper.selectOne2DemoCfg(10);
        System.out.println("======"+JSON.toJSONString(lst));
    }
    
    @Test
    public void testUpdate(){
        DemoCfg demoCfg = new DemoCfg();
        demoCfg.setId(new BigDecimal(10));
        demoCfg.setCode("demo.err.testUpdate");
        int cnt = mapper.updateValue(demoCfg);
        System.out.println("======"+cnt);
    }
    
    @Test
    public void testGroupBy(){
        List<Map<String, Object>> lst = mapper.groupBy();
        System.out.println("======"+JSON.toJSONString(lst));
    }
    
    @Test
    public void testCount(){
        DemoCfgCriteria criteria = new DemoCfgCriteria();
        criteria.createCriteria().andIdBetween(new BigDecimal(5), new BigDecimal(10000));
        long cnt = mapper.countCountByExample(criteria);
        System.out.println("====== mapper : "+cnt);
    }
    
    
    @Resource
    private DemoCfgMapper demoCfgMapper;
    
    @Test
    public void testDemoCfgMapperCount(){
        DemoCfgCriteria criteria = new DemoCfgCriteria();
        criteria.createCriteria().andIdBetween(new BigDecimal(5), new BigDecimal(10000));
        long cnt = demoCfgMapper.countByExample(criteria);
        System.out.println("======demoCfgMapper : "+cnt);
    }
}

