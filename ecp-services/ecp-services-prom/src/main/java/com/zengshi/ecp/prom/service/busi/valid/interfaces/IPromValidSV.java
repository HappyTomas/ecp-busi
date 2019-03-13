package com.zengshi.ecp.prom.service.busi.valid.interfaces;

import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromValidSV extends IGeneralSQLSV{

    /**
     * 促销信息录入,是否需要验证
     * 
     * @param promDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public boolean needToVerified(PromDTO promDTO) throws BusinessException;

    /**
     * 促销信息录入验证 非空验证 字段超过验证
     * 
     * @param promDTO
     * @throws Exception
     * @author huangjx
     */
    public void valid(PromDTO promDTO) throws BusinessException;

}
