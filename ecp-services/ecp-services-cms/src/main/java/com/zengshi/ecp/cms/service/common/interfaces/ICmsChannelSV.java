package com.zengshi.ecp.cms.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelResDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015-11-18上午9:46:47  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * CMS栏目管理
 */
public interface ICmsChannelSV extends IGeneralSQLSV{
    
    public CmsChannelResDTO find(CmsChannelReqDTO record) throws BusinessException;
    
    public long insert(CmsChannelReqDTO record) throws BusinessException;
    
    public int update(CmsChannelReqDTO record) throws BusinessException;
    
    public int delete(CmsChannelReqDTO record) throws BusinessException;
    
    public List<CmsChannelResDTO> listChannel(CmsChannelReqDTO record) throws BusinessException;
}

