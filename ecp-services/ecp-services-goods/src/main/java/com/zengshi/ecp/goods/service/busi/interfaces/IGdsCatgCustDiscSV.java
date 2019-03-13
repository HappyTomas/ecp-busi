package com.zengshi.ecp.goods.service.busi.interfaces;

import java.math.BigDecimal;

import com.zengshi.ecp.goods.dubbo.dto.CalCatgCustDiscReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscBatchDelReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscListReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsCatgCustDiscSV {
    public void addGdsCatgCustDisc(GdsCatgCustDiscListReqDTO catgCustDiscReqDTO) throws BusinessException;

    public void editGdsCatgCustDisc(GdsCatgCustDiscListReqDTO catgCustDiscReqDTO) throws BusinessException;

    public void deleteGdsCatgCustDisc(GdsCatgCustDiscReqDTO catgCustDiscReqDTO) throws BusinessException;

    public void deleteGdsCatgCustDiscByGroup(GdsCatgCustDiscBatchDelReqDTO batchDelReqDTO  )throws BusinessException;
    
    public PageResponseDTO<GdsCatgCustDiscRespDTO> queryGdsCatgCustDiscByPage(GdsCatgCustDiscReqDTO catgCustDiscReqDTO) throws BusinessException;

    public BigDecimal calCatgCustDisc(CalCatgCustDiscReqDTO calCatgCustDiscReqDTO )throws BusinessException;

}
