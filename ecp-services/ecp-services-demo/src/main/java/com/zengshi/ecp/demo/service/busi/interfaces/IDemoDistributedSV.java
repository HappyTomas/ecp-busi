/** 
 * Project Name:ecp-services-demo 
 * File Name:IDemoDistributedSV.java 
 * Package Name:com.zengshi.ecp.demo.service.busi.interfaces 
 * Date:2015-8-3下午2:46:11 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.demo.service.busi.interfaces;

import java.util.Map;

import com.zengshi.ecp.demo.dao.model.DemoDistributed;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-3下午2:46:11  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IDemoDistributedSV {
    
    public long saveInfo(DemoDistributed info) throws BusinessException;
    
    public DemoDistributed queryInfoById(long id) throws BusinessException;
    
    public Map<String,Object> saveResult(DemoDistributed info) throws BusinessException;

}

