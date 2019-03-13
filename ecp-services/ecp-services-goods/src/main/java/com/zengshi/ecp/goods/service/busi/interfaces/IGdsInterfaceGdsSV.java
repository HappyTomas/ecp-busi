package com.zengshi.ecp.goods.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsInterfaceGds;
import com.zengshi.ecp.goods.dao.model.GdsInterfaceGdsGidx;
import com.zengshi.ecp.goods.dubbo.dto.GdsInterfaceGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsInterfaceGdsGidxReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsInterfaceGdsSV {
    
    void saveGdsInterfaceGds(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO) throws BusinessException;

    void deleteGdsInterfaceGdsByGdsInfoAndOriginGdsInfo(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO)throws BusinessException;

    void updateGdsInterfaceGdsByGdsInfoAndOriginGdsInfo(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO) throws BusinessException;
    
    GdsInterfaceGds queryGdsInterfaceGdsByOriginGdsId(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO) throws BusinessException;
    
    GdsInterfaceGdsGidx queryGdsInterfaceGdsGidxByEcpGdsId(GdsInterfaceGdsGidxReqDTO gdsInterfaceGdsGidxReqDTO) throws BusinessException;

    public void deleteGdsInterfaceGdsByGdsId(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO)
            throws BusinessException ;
    
    public List<GdsInterfaceGds> queryGdsInterfaceGdsByDate(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO) throws BusinessException;
}

