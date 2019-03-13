package com.zengshi.ecp.search.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.search.dubbo.dto.SecHotKeywordReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecHotKeywordRespDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecHotKeywordRSV;
import com.zengshi.ecp.search.service.common.interfaces.ISecHotKeywordSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server <br>
 * Description: <br>
 * Date:2016年3月9日下午4:10:35  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecHotKeywordRSVImpl implements ISecHotKeywordRSV {
    
    @Resource
    private ISecHotKeywordSV secHotKeywordSV;

    @Override
    public PageResponseDTO<SecHotKeywordRespDTO> querySecHotKeywordPage(
            SecHotKeywordReqDTO secHotKeywordReqDTO) throws BusinessException {
        return this.secHotKeywordSV.querySecHotKeywordPage(secHotKeywordReqDTO);
    }

}

