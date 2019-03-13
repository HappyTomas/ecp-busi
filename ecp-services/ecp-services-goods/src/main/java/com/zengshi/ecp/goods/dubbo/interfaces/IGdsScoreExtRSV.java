package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsScoreExtRSV {
    public List<GdsScoreExtRespDTO> queryGdsScoreExtByGds(GdsScoreExtReqDTO gdsScoreExtReqDTO)
            throws BusinessException;
    

}
