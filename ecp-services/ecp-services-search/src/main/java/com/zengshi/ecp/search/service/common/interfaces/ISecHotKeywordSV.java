package com.zengshi.ecp.search.service.common.interfaces;

import com.zengshi.ecp.search.dubbo.dto.SecHotKeywordReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecHotKeywordRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:00:18  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecHotKeywordSV extends IGeneralSQLSV{
    
    /**
     * 分页获取搜索热词信息
     * @param secHotKeywordReqDTO
     * @return
     * @throws BusinessException
     */
    public PageResponseDTO<SecHotKeywordRespDTO> querySecHotKeywordPage(SecHotKeywordReqDTO secHotKeywordReqDTO) throws BusinessException;

}

