package com.zengshi.ecp.goods.service.busi.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;

public interface IGdsBrowseHisSV {

    public void addGdsBrowseHis(GdsBrowseHisReqDTO browseHisReqDTO )throws Exception;
    
    public void deleteGdsBrowseHis(GdsBrowseHisReqDTO browseHisReqDTO )throws Exception;
    
    public PageResponseDTO<GdsBrowseHisRespDTO> queryGdsBrowseHisByPage(GdsBrowseHisReqDTO browseHisReqDTO )throws Exception;
    
    public void deleteGdsBrowseHisClear(GdsBrowseHisReqDTO browseHisReqDTO)throws Exception;
}

