package com.zengshi.ecp.prom.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.prom.dubbo.dto.ConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.GdsPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCalRuleDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuLimitDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.SecondKillPromRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.KillGdsInfoDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromQueryRSV {
    /**
     *促销查询列表
     * @param poromInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromInfoResponseDTO> queryPromInfoPage(PromInfoDTO promInfoDTO) throws BusinessException;
   
    /**
     * 我的促销查询
     * @param poromInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromInfoResponseDTO> queryPromInfoList(PromInfoDTO promInfoDTO) throws BusinessException;
    /**
     * 促销查询（平台审核促销列表）
     * @param poromInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromInfoResponseDTO> queryPromInfoListByPlat(PromInfoDTO promInfoDTO) throws BusinessException;
    /**
     * 促销基本信息查询
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromInfoDTO queryPromInfo(PromInfoDTO promInfoDTO) throws BusinessException;
 
    /**
     * 促销规则查询
     * @param promConstraintDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromConstraintDTO queryPromConstraint(ConstraintDTO constraintDTO) throws BusinessException;
    
    /**
     * 优惠规则
     * @param promCalRuleDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromCalRuleDTO queryPromCalRule(PromCalRuleDTO promCalRuleDTO) throws BusinessException;
    
    /**
     * 获得促销赠品列表
     * @param promGiftDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromGiftDTO> listPromotionGift(PromGiftDTO promGiftDTO) throws BusinessException;
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
     * 促销赠送 积分 优惠券 赠品列表
     * @param rOrdCartsCommRequest
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromPresentRespDTO promPresent(ROrdCartsCommRequest rOrdCartsCommRequest) throws BusinessException;
    /**
     * 促销类型
     * @param promTypeDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromTypeResponseDTO queryPromType(PromTypeDTO promTypeDTO) throws BusinessException;
    
    /**
     * 获得促销商品列表
     * @param promSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSkuDTO> listPromotionSku(PromSkuDTO promSkuDTO) throws BusinessException;
    /**
     * 获得促销商品 分页列表
     * @param promSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromSkuRespDTO> pagePromotionSku(PromSkuDTO promSkuDTO) throws BusinessException;
    /**
     * 获得促销商品黑名单列表
     * @param promSkuLimitDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSkuLimitDTO> listPromotionSkuLimit(PromSkuLimitDTO promSkuLimitDTO) throws BusinessException;
    /**
     * 获得促销搭配商品列表
     * @param promMatchSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromMatchSkuDTO> listPromMatchSku(PromMatchSkuDTO promMatchSkuDTO) throws BusinessException;
    
    /**
     * 商品详情列表或solr查询 并计算价格
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromListRespDTO> listProm(PromRuleCheckDTO promRuleCheckDTO) throws BusinessException;
    public GdsPromListDTO listPromNew(PromRuleCheckDTO promRuleCheckDTO) throws BusinessException;
    /**
     * 商品促销列表
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromListRespDTO> listPromByGds(PromRuleCheckDTO promRuleCheckDTO) throws BusinessException;
    
    
    /**
     * 平台促销待审核数据统计
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     */
    public Long queryTotalByProm(PromInfoDTO promInfoDTO) throws BusinessException;
    
    /**
     * 显示秒杀促销列表 分页
     * 
     * @param promInfoDTO
     * @param 
     * @return
     * @throws BusinessException
     * @author lisp
     */
    public  PageResponseDTO<PromInfoResponseDTO> listSecondPromInfoForPage(PromInfoDTO promInfoDTO) throws BusinessException;


    /**
     * TODO商品详情列表 并计算价格
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV#listSkuOfSecondKillProm(com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO)
     * @param PromInfoDTO promInfoDTO
     * @return   
     * @throws BusinessException
     * @author lisp
     */
    public SecondKillPromRespDTO listSkuOfSecondKillProm(PromInfoDTO promInfoDTO) throws BusinessException;

	 /**
     * 秒杀促销商品信息封装
     * 
     * @param PromDTO promDTO
     * @return
     * @throws BusinessException
     * @author lisp
     */
    public List<KillGdsInfoDTO> killPromGdsinfoList(PromDTO promDTO) throws BusinessException;

}
