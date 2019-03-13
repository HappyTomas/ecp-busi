package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsBrowseHisRSV {
    /**
     * 
     * queryGdsBrowseHisByPage:(查询用户商品浏览记录). <br/>
     * 
     * @author zjh
     * @param browseHisReqDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsBrowseHisRespDTO> queryGdsBrowseHisByPage(
            GdsBrowseHisReqDTO browseHisReqDTO) throws BusinessException;
    
    public void deleteGdsBrowseHisClear(GdsBrowseHisReqDTO browseHisReqDTO)throws BusinessException;

}
