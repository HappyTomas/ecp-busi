package com.zengshi.ecp.prom.service.busi.impl;

import java.util.concurrent.Callable;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromListTaskBeanDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromSV;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-2-17 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromGdsListTaskSVImpl implements Callable<PromListTaskBeanDTO> {

    private static final String MODULE = PromGdsListTaskSVImpl.class.getName();

    private final OrdPromDTO ordPromDTO;
   
    private final OrdPromDetailDTO ordPromDetailDTO;
    
    private final PromRuleCheckDTO promRuleCheckDTO;
    
    private IPromSV promSV=  EcpFrameContextHolder.getBean("promSV", IPromSV.class);
    
    public PromGdsListTaskSVImpl(OrdPromDetailDTO ordPromDetailDTO,OrdPromDTO ordPromDTO,PromRuleCheckDTO promRuleCheckDTO)

    {
       this.ordPromDetailDTO=ordPromDetailDTO;
       this.ordPromDTO=ordPromDTO;
       this.promRuleCheckDTO=promRuleCheckDTO;
    }

    public PromListTaskBeanDTO call() throws Exception  
    {
        PromListTaskBeanDTO dto=new PromListTaskBeanDTO();
        try
        {
             promSV.promByGds(ordPromDTO, ordPromDetailDTO,promRuleCheckDTO);
             dto.setOrdPromDetailDTO(ordPromDetailDTO);
        }
        catch (Exception ex)
        {
          LogUtil.error(MODULE, ex.toString());
          dto.setException(ex);
          dto.setIfSuccess(false);
        }
        return dto;
    }
}
