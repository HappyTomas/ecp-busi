package com.zengshi.ecp.cms.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsImageSwitcherReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsImageSwitcherRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsImageSwitcherRSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsImageSwitcherSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月7日下午5:11:10  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
public class CmsImageSwitcherRSVImpl implements ICmsImageSwitcherRSV{
    
    
    @Resource
    private ICmsImageSwitcherSV cmsImageSwitcherSV;

    @Override
    public PageResponseDTO<CmsImageSwitcherRespDTO> queryCmsImageSwitcherPage(
            CmsImageSwitcherReqDTO dto) throws BusinessException {
        
        return cmsImageSwitcherSV.queryCmsImageSwitcherPage(dto);
        
    }

    @Override
    public void saveCmsImageSwitcher(CmsImageSwitcherReqDTO dto) throws BusinessException {
        
        cmsImageSwitcherSV.saveCmsImageSwitcher(dto);
    }

    @Override
    public void updateCmsImageSwitcher(CmsImageSwitcherReqDTO dto) throws BusinessException {
        
        cmsImageSwitcherSV.updateCmsImageSwitcher(dto);
    }

    @Override
    public CmsImageSwitcherRespDTO selectCmsImageSwitcher(CmsImageSwitcherReqDTO dto)
            throws BusinessException {
        return cmsImageSwitcherSV.selectCmsImageSwitcher(dto);
    }
    
 
}

