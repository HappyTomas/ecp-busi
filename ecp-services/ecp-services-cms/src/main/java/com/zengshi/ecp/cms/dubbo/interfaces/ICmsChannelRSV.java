package com.zengshi.ecp.cms.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelResDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015-11-18上午11:18:17  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 栏目管理dubbo服务
 */
public interface ICmsChannelRSV {
    
    public CmsChannelResDTO find(CmsChannelReqDTO record) throws BusinessException;

    public long insert(CmsChannelReqDTO record) throws BusinessException;
    
    public int update(CmsChannelReqDTO record) throws BusinessException;
    
    public int delete(CmsChannelReqDTO record) throws BusinessException;
    
    public List<CmsChannelResDTO> listChannel(CmsChannelReqDTO record) throws BusinessException;

}

