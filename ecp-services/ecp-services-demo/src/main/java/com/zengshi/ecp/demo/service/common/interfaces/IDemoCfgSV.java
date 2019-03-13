/** 
 * Project Name:ecp-services-demo 
 * File Name:IDemoCfgSV.java 
 * Package Name:com.zengshi.ecp.demo.service.common.interfaces 
 * Date:2015-8-3下午9:09:59 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.demo.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.demo.dao.model.DemoCfg;
import com.zengshi.ecp.demo.dao.model.DemoInfo;
import com.zengshi.ecp.demo.dubbo.dto.DemoCfgReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-3下午9:09:59  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IDemoCfgSV {
    
    public long saveDemoCfg(DemoCfg cfg) throws BusinessException;
    
    /**
     * 
     * queryDemoCfgById:根据Id获取DemoCfg信息 <br/> 
     * 
     * @author yugn 
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public DemoCfg queryDemoCfgById(long id) throws BusinessException; 
    
    
    public List<DemoCfg> queryDemoCfgList() throws BusinessException;
    
    /**
     * 
     * queryDemoCfgPage:获取查询结果 <br/> 
     * 
     * @author yugn 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<DemoCfg> queryDemoCfgPage(DemoCfgReqDTO dto) throws BusinessException;
    
    /**
     * 
     * queryDemoCfgCount:统计结果集 <br/> 
     * 
     * @author yugn 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long queryDemoCfgCount(DemoCfgReqDTO dto) throws BusinessException;
    

}

