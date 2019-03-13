package com.zengshi.ecp.search.dubbo.interfaces;

import com.zengshi.ecp.search.dubbo.dto.SecHotKeywordReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecHotKeywordRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface ISecHotKeywordRSV {
    
    /**
     * 分页获取搜索热词配置信息
     * @param secHotKeywordReqDTO
     * @return
     * @throws BusinessException
     */
    public PageResponseDTO<SecHotKeywordRespDTO> querySecHotKeywordPage(SecHotKeywordReqDTO secHotKeywordReqDTO) throws BusinessException;

}

