/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoDistributedSVImpl.java 
 * Package Name:com.zengshi.ecp.demo.service.busi.impl 
 * Date:2015-8-3下午2:53:23 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.demo.service.busi.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;

import com.zengshi.ecp.demo.dao.mapper.busi.DemoDistributedMapper;
import com.zengshi.ecp.demo.dao.model.DemoDistributed;
import com.zengshi.ecp.demo.service.busi.interfaces.IDemoDistributedSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-3下午2:53:23  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class DemoDistributedSVImpl implements IDemoDistributedSV {
    
    @Resource(name="seq_demo_distribute")
    private PaasSequence seqDemoDistribute;
    
    @Resource
    private DemoDistributedMapper mapper;
    
    //@Resource(name="generalSQLSVEcp")
    //private IGeneralSQLSV generalSQLSV;

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.demo.service.busi.interfaces.IDemoDistributedSV#saveInfo(com.zengshi.ecp.demo.dao.model.DemoDistributed) 
     */
    @Override
    public long saveInfo(DemoDistributed info) throws BusinessException {
        
        info.setId(seqDemoDistribute.nextValue());
        mapper.insert(info);
        return info.getId();
    }

    @Override
    public DemoDistributed queryInfoById(long id) throws BusinessException {
        DemoDistributed info = this.mapper.selectByPrimaryKey(id);
        return info;
    }

    @Override
    public Map<String,Object> saveResult(DemoDistributed info) throws BusinessException {
        
        long id = this.saveInfo(info);
        try {
            return BeanUtils.describe(info);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
       /* List<Map<String,Object>> lst = generalSQLSV.queryForMapBySQL("select * from t_demo_distributed where id="+id);
        
        if(lst == null || lst.isEmpty()){
            return null;
        } else {
            Map<String, Object> map = lst.get(0);
            return map;
        }*/
    }
    
    
}

