package com.zengshi.ecp.prom.dubbo.interfaces;

import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromTypeRSV {
 
    /**
     * 促销类型
     * 
     * @param promDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public PromTypeResponseDTO queryPromTypeByDB(String promTypeCode) throws BusinessException;
 
}
