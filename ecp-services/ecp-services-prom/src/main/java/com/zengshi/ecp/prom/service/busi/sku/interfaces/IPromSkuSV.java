package com.zengshi.ecp.prom.service.busi.sku.interfaces;

import java.util.List;

import com.zengshi.ecp.prom.dao.model.PromInfo;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dao.model.PromStockLimit;
import com.zengshi.ecp.prom.dubbo.dto.KillGdsInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-11 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromSkuSV extends IGeneralSQLSV{
    /**
     * 促销单品信息 更新
     * @param promSku
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int updatePromSku(PromSkuDTO promSkuDTO)
            throws BusinessException;
    
    /**
     * 批量促销单品 新增
     * @param promSkuDTOList
     * @throws BusinessException
     * @author huangjx
     */
    public void insertPromSkuBatch(List<PromSkuDTO> promSkuDTOList)
            throws BusinessException;
    
    /**
     * 促销数量变更
     * @param promSkuDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void updatePromSkuCnt(PromSkuDTO promSkuDTO)
            throws BusinessException;
    /**
     * 促销数量变更 limit
     * @param promSkuDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void updatePromStockSkuCnt(PromSkuDTO promSkuDTO)
            throws BusinessException;
    /**
     * 批量促销单品信息 更新
     * @param promSkuDTOList
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void updatePromSkuBatch(List<PromSkuDTO> promSkuDTOList)
            throws BusinessException;
    /**
     * 保存 促销单品信息
     * @param promSku
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int savePromSku(PromSku promSku)
            throws BusinessException;
    /**
     * 保存 促销单品信息 库存信息
     * @param promStockLimit
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int savePromStockLimit(PromStockLimit promStockLimit)
            throws BusinessException;
    /**
     * 参加促销商品 
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSku> querySkuPromByPromId(Long promId)
            throws BusinessException;
    /**
     * 参加促销商品 (有效)
     * @param promId
     * @param ifValid
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSku> querySkuPromByPromId(Long promId,String ifValid)
            throws BusinessException;
    /**
     * 查询 商品列表
     * @param promSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromSkuRespDTO> querySkuPromPage(PromSkuDTO promSkuDTO)
            throws BusinessException;
    /**
     * 保存 促销单品信息 copy promId的信息到新的促销id
     * @param promIdOld
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromSkuThread(Long promIdOld,PromInfo promInfo)
            throws BusinessException;
    
    public long countNum(PromSkuDTO promSkuDTO)throws BusinessException;
    /**
     * TODO查询 商品列表
     * @param promSkuDTO
     * @return
     * @throws BusinessException
     * @author lisp
     */
    public PageResponseDTO<KillGdsInfoDTO> querySkuPromforPage(PromSkuDTO promSkuDTO)
            throws BusinessException;
}
