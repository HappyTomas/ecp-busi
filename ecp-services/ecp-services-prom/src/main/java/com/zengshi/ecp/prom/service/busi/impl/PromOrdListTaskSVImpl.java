package com.zengshi.ecp.prom.service.busi.impl;

import java.util.Date;
import java.util.concurrent.Callable;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromListTaskBeanDTO;
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
public class PromOrdListTaskSVImpl implements Callable<PromListTaskBeanDTO> {

    private static final String MODULE = PromOrdListTaskSVImpl.class.getName();

    private final OrdPromDTO ordPromDTO;
   
    private final OrdPromDetailDTO ordPromDetailDTO;
    
    private final Date date;
    
    private final Long ordPromId;
    
    private IPromSV promSV=  EcpFrameContextHolder.getBean("promSV", IPromSV.class);
    
    public PromOrdListTaskSVImpl(OrdPromDetailDTO ordPromDetailDTO,OrdPromDTO ordPromDTO,Date date,Long ordPromId)

    {
       this.ordPromDetailDTO=ordPromDetailDTO;
       this.ordPromDTO=ordPromDTO;
       this.date=date;
       this.ordPromId=ordPromId;
    }

    public PromListTaskBeanDTO call() throws Exception  
    {
        PromListTaskBeanDTO dto=new PromListTaskBeanDTO();
        try
        {
             promSV.fullFilPromOrd(ordPromDTO, ordPromDetailDTO, date, ordPromId);
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
