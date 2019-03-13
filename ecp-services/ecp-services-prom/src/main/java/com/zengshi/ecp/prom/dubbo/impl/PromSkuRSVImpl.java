package com.zengshi.ecp.prom.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dubbo.dto.KillGdsInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuRespDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromSkuRSV;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-11 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromSkuRSVImpl implements IPromSkuRSV {
    
    @Resource
    private  IPromSkuSV promSkuSV;
    /**
     * TODO更新
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromSkuRSV#update(com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO)
     * @param promSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public long update(PromSkuDTO promSkuDTO) throws BusinessException{
        if(promSkuDTO==null){
            throw new BusinessException("prom.400086");
        }
        if(StringUtil.isEmpty(promSkuDTO.getPromId())){
            throw new BusinessException("prom.400204");
        }
        if(StringUtil.isEmpty(promSkuDTO.getId())){
            throw new BusinessException("prom.400205");
        }
        return promSkuSV.updatePromSku(promSkuDTO);
    }
    /**
     * TODO批量新增
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromSkuRSV#insertBatch(java.util.List)
     * @param promSkuDTOList
     * @throws BusinessException
     * @author huangjx
     */
    public void insertBatch(List<PromSkuDTO> promSkuDTOList) throws BusinessException{
        
        if(promSkuDTOList==null){
            throw new BusinessException("prom.400086");
        }
         promSkuSV.insertPromSkuBatch(promSkuDTOList);
    }
 
    /**
     * TODO批量更新
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromSkuRSV#updateBatch(java.util.List)
     * @param promSkuDTOList
     * @throws BusinessException
     * @author huangjx
     */
    public void updateBatch(List<PromSkuDTO> promSkuDTOList) throws BusinessException{
        
        if(promSkuDTOList==null){
            throw new BusinessException("prom.400086");
        }
         promSkuSV.updatePromSkuBatch(promSkuDTOList);
    }
    /**
     * TODO促销id查询促销商品
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromSkuRSV#querySkuPromByPromId(java.lang.Long)
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSku> querySkuPromByPromId(Long promId)
            throws BusinessException{
        return promSkuSV.querySkuPromByPromId(promId);
    }
    
    /**
     * TODO促销商品分页查询
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromSkuRSV#querySkuPromPage(com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO)
     * @param promSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromSkuRespDTO> querySkuPromPage(PromSkuDTO promSkuDTO)
            throws BusinessException{
        return promSkuSV.querySkuPromPage(promSkuDTO);
    }
    /**
     * TODO 更新促销数量
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromSkuRSV#updatePromSkuCnt(com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO)
     * @param promSkuDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void updatePromSkuCnt(PromSkuDTO promSkuDTO)
            throws BusinessException{
        promSkuSV.updatePromSkuCnt(promSkuDTO);
    }
    
    /**
     * TODO查询 商品列表
     * @see querySkuPromforPage(PromSkuDTO)
     * @param promSkuDTO
     * @return
     * @throws BusinessException
     * @author lisp
     */
    @Override
    public PageResponseDTO<KillGdsInfoDTO> querySkuPromforPage(PromSkuDTO promSkuDTO)throws BusinessException{
    	if(promSkuDTO.getPromId()==null){
    		throw new BusinessException("prom.400088");
    	}
    	return promSkuSV.querySkuPromforPage(promSkuDTO);
    }
}
