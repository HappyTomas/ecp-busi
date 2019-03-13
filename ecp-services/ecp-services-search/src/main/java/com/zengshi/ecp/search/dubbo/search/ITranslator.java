package com.zengshi.ecp.search.dubbo.search;

import java.util.Map;

import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;

public interface ITranslator {
    
    SecConfigRespDTO translate(Long currentSiteId,Map<String, SecConfigRespDTO> cacheSecConfigRespDTOMap) throws SearchException;

}

