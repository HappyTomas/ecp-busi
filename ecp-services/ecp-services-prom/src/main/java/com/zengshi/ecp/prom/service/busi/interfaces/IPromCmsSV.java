package com.zengshi.ecp.prom.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.exception.BusinessException;
/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-4-9 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromCmsSV {

    /**
     * 失效cms模块（选择的促销数据）
     * @param promId
     * @param gdsId
     * @param skudId
     * @param gdsIds
     * @throws BusinessException
     * @author huangjx
     */
    public void invalidCmsGds(Long promId,Long gdsId,Long skudId,List<Long> gdsIds) throws BusinessException;
}
