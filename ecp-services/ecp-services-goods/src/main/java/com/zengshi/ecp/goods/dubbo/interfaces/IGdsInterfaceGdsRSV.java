package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsInterfaceGds;
import com.zengshi.ecp.goods.dubbo.dto.GdsInterfaceGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsInterfaceGdsRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsInterfaceGdsGidxReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsInterfaceGdsGidxRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsInterfaceGdsRSV {
    
    void saveGdsInterfaceGds(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO) throws BusinessException;

    void deleteGdsInterfaceGdsByOriginGdsId(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO)throws BusinessException;

    void updateGdsInterfaceGdsByOriginGdsId(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO) throws BusinessException;
    
    GdsInterfaceGdsRespDTO queryGdsInterfaceGdsByOriginGdsId(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO) throws BusinessException;

    //GdsInterfaceGdsGidxRespDTO queryGdsInterfaceGdsGidxByEcpGdsId(GdsInterfaceGdsGidxReqDTO gdsInterfaceGdsGidxReqDTO) throws BusinessException;

    GdsInterfaceGdsGidxRespDTO queryGdsInterfaceGdsGidxByEcpGdsId(GdsInterfaceGdsGidxReqDTO gdsInterfaceGdsGidxReqDTO,IRealOriginalGdsCodeProcessor gdsDataImportExternalize) throws BusinessException;

    List<GdsInterfaceGds> queryGdsInterfaceGdsByDate(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO) throws BusinessException;
}

