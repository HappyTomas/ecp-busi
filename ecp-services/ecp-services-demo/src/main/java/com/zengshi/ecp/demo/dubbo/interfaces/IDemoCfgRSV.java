/** 
 * Project Name:ecp-services-demo 
 * File Name:IDemoCfgRSV.java 
 * Package Name:com.zengshi.ecp.demo.dubbo.interfaces 
 * Date:2015-8-7上午11:30:45 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.demo.dubbo.interfaces;

import com.zengshi.ecp.demo.dubbo.dto.DemoCfgReqDTO;
import com.zengshi.ecp.demo.dubbo.dto.DemoCfgRespDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-7上午11:30:45  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IDemoCfgRSV {
    
    public void saveDemoCfg(DemoCfgReqDTO demoCfg) throws BusinessException;
    
    /**
     * 
     * listDemoCfg: 查询返回结果 <br/> 
     * 
     * @author yugn 
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<DemoCfgRespDTO> listDemoCfg(BaseInfo info) throws BusinessException;
    
    
    public PageResponseDTO<DemoCfgRespDTO> listDemoCfgPage(DemoCfgReqDTO dto) throws BusinessException;

}

