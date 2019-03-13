package com.zengshi.ecp.cms.dubbo.interfaces;

import com.zengshi.ecp.cms.dubbo.dto.CmsImageSwitcherReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsImageSwitcherRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-cms-server <br>
 * Description: <br>
 * Date:2016年3月7日下午7:54:38  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public interface ICmsImageSwitcherRSV {
    
    /**
     * 
     * queryCmsImageSwitcherPage:(查询微信汇列表). <br/> 
     * 
     * @author wangbh
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<CmsImageSwitcherRespDTO> queryCmsImageSwitcherPage(CmsImageSwitcherReqDTO dto) throws BusinessException;
    
    
    /**
     * 
     * saveCmsImageSwitcher:(保存). <br/> 
     * 
     * @author wangbh
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void saveCmsImageSwitcher(CmsImageSwitcherReqDTO dto) throws BusinessException;
    
    
    /**
     * 
     * updateCmsImageSwitcher:(修改). <br/> 
     * 
     * @author wangbh
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void updateCmsImageSwitcher(CmsImageSwitcherReqDTO dto) throws BusinessException;
    
    /**
     * 
     * selectCmsImageSwitcher:(查询一条数据). <br/> 
     * 
     * @author wangbh
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CmsImageSwitcherRespDTO selectCmsImageSwitcher(CmsImageSwitcherReqDTO dto) throws BusinessException;
    
    
}

