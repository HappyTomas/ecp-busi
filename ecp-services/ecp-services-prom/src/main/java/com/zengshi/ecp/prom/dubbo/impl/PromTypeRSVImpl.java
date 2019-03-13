package com.zengshi.ecp.prom.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromTypeRSV;
import com.zengshi.ecp.prom.service.common.interfaces.IPromTypeSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-31 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromTypeRSVImpl implements IPromTypeRSV {
    
    @Resource
    private  IPromTypeSV promTypeSV;
    
    
    /**
     * 促销类型
     * 
     * @param promDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public PromTypeResponseDTO queryPromTypeByDB(String promTypeCode) throws BusinessException{
         return promTypeSV.queryPromTypeByDB(promTypeCode);
    }
}
