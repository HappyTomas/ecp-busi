package com.zengshi.ecp.prom.dubbo.interfaces;


import java.util.List;

import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dubbo.dto.KillGdsInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-11 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromSkuRSV {

    /**
     * 更新
     * @param promSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public long update(PromSkuDTO promSkuDTO) throws BusinessException;
    /**
     * 批量新增
     * @param promSkuDTOList
     * @throws BusinessException
     * @author huangjx
     */
    public void insertBatch(List<PromSkuDTO> promSkuDTOList) throws BusinessException;
    /**
     * 批量更新
     * @param promSkuDTOList
     * @throws BusinessException
     * @author huangjx
     */
    public void updateBatch(List<PromSkuDTO> promSkuDTOList) throws BusinessException;
    /**
     * 促销id查询促销商品
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSku> querySkuPromByPromId(Long promId)
            throws BusinessException;
    
    /**
     * 促销商品分页查询
     * @param promSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromSkuRespDTO> querySkuPromPage(PromSkuDTO promSkuDTO)
            throws BusinessException;
    /**
     * 更新促销数量
     * @param promSkuDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void updatePromSkuCnt(PromSkuDTO promSkuDTO)
            throws BusinessException;
    
    /**
     * TODO查询 商品列表
     * @see querySkuPromforPage(PromSkuDTO)
     * @param promSkuDTO
     * @return
     * @throws BusinessException
     * @author lisp
     */
    public PageResponseDTO<KillGdsInfoDTO> querySkuPromforPage(PromSkuDTO promSkuDTO) throws BusinessException;
}
