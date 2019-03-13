package com.zengshi.ecp.prom.service.busi.interfaces;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCalRuleDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuLimitDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuPriceRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-17 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromQuerySV extends IGeneralSQLSV{
    /**
     * 获得赠品列表
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromGiftDTO> queryPromGift(Long promId) throws BusinessException;
    /**
     * 获得赠品列表
     * @param promId
     * @param shopId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromGiftDTO> queryPromGift(Long promId,Long shopId) throws BusinessException;
    /**
     * 促销赠送 积分 优惠券 赠品列表
     * @param promId
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromPresentDTO promPresent(Long promId,OrdPromDTO ordPromDTO) throws BusinessException;
    /**
     * 促销规则信息
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromConstraintDTO queryPromConstraint(Long promId) throws BusinessException;
    /**
     * 优惠规则
     * @param promId
     * @throws BusinessException
     * @author huangjx
     */
    public PromCalRuleDTO queryPromCalRule(Long promId) throws BusinessException;
    /**
     * 获得促销商品列表
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSkuDTO> listPromotionSku(PromSkuDTO promSkuDTO) throws BusinessException;
    
    /**
     * 获得促销商品分页列表
     * @param promSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromSkuRespDTO> pagePromotionSku(PromSkuDTO promSkuDTO) throws BusinessException;
    /**
     *  获得促销商品黑名单列表
     * @param promSkuLimitDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSkuLimitDTO> listPromotionSkuLimit(PromSkuLimitDTO promSkuLimitDTO) throws BusinessException;
  
    /**
     * 根据促销id 计算单品价格
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromSkuPriceRespDTO calSkuPriceByPromId(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO) throws BusinessException;
}
