package com.zengshi.ecp.prom.dubbo.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.drools.core.util.StringUtils;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dubbo.dto.ConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.GdsPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.KillGdsInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
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
import com.zengshi.ecp.prom.dubbo.dto.PromSkuPriceRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromStockLimitDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.SecondKillPromRespDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromUtilRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromMatchSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromSV;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV;
import com.zengshi.ecp.prom.service.common.interfaces.IPromTypeSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;


/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-13 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromQueryRSVImpl implements IPromQueryRSV {
    
    private static final String MODULE = PromQueryRSVImpl.class.getName();
    
    @Resource
    private IPromInfoSV promInfoSV;
    
    @Resource
    private IPromQuerySV promQuerySV;
    
    @Resource
    private IPromTypeSV promTypeSV;
    
    @Resource
    private IPromMatchSV promMatchSV;
    
    @Resource
    private IPromSV promSV;
    
    @Resource
    private  IPromSkuSV promSkuSV;
    
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    
    @Resource
    private IGdsInfoExternalRSV gdsInfoExternalRSV;
    
    @Resource
    private IPromUtilRSV promUtilRSV;
    
    @Resource(name = "defaultSkuCheckSV")
    private IPromSkuCheckSV defaultSkuCheckSV;
    
    /**
     *促销查询列表
     * @param poromInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromInfoResponseDTO> queryPromInfoPage(PromInfoDTO promInfoDTO) throws BusinessException{
        return promInfoSV.queryPromInfoForPage(promInfoDTO,"3");
    }
    
    /**
     * 我的促销查询（店铺列表）
     * @param poromInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromInfoResponseDTO> queryPromInfoList(PromInfoDTO promInfoDTO) throws BusinessException{
        
        return promInfoSV.queryPromInfoForPage(promInfoDTO,"");
    
    }
    /**
     * 促销查询（平台审核促销列表）
     * @param poromInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromInfoResponseDTO> queryPromInfoListByPlat(PromInfoDTO promInfoDTO) throws BusinessException{
        
      //poromInfoDTO 平台查询 设置ifPlat==1
        promInfoDTO.setIfPlatQuery(PromConstants.PromSys.IF_PLAT_QUERY);
        
        return promInfoSV.queryPromInfoForPage(promInfoDTO,"");
    
    }
    /**
     * TODO促销基本信息查询
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV#queryPromInfo(com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO)
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromInfoDTO queryPromInfo(PromInfoDTO promInfoDTO) throws BusinessException{
        //参数验证
        if(promInfoDTO==null){
            throw new BusinessException("prom.400086");
        }
        if(promInfoDTO.getId()==null){
            //传入参数为空
            throw new BusinessException("prom.400088");
        }
       return promInfoSV.queryPromInfoDTOById(promInfoDTO.getId());
    }
    /**
     * TODO促销规则查询
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV#queryPromConstraint(com.zengshi.ecp.prom.dubbo.dto.ConstraintDTO)
     * @param constraintDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromConstraintDTO queryPromConstraint(ConstraintDTO constraintDTO) throws BusinessException{
        //参数验证
        if(constraintDTO==null){
            throw new BusinessException("prom.400086");
        }
        if(constraintDTO.getPromId()==null){
            //传入参数为空
            throw new BusinessException("prom.400088");
        }
        return promQuerySV.queryPromConstraint(constraintDTO.getPromId());
    }
    /**
     * TODO优惠规则
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV#queryPromCalRule(com.zengshi.ecp.prom.dubbo.dto.PromCalRuleDTO)
     * @param promCalRuleDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromCalRuleDTO queryPromCalRule(PromCalRuleDTO promCalRuleDTO) throws BusinessException{
        //参数验证
        if(promCalRuleDTO==null){
            throw new BusinessException("prom.400086");
        }
        if(promCalRuleDTO.getPromId()==null){
            //传入参数为空
            throw new BusinessException("prom.400088");
        }
        return promQuerySV.queryPromCalRule(promCalRuleDTO.getPromId());
    }
 
    /**
     * TODO赠品列表
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV#listPromotionGift(com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO)
     * @param promGiftDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromGiftDTO> listPromotionGift(PromGiftDTO promGiftDTO) throws BusinessException {
        //参数验证
        if(promGiftDTO==null){
            throw new BusinessException("prom.400086");
        }
        if(promGiftDTO.getPromId()==null){
            //传入参数为空
            throw new BusinessException("prom.400088");
        }
        return promQuerySV.queryPromGift(promGiftDTO.getPromId());
    }
 
  
    /**
     * 促销赠送 积分 优惠券 赠品列表
     * @param promId
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromPresentDTO promPresent(Long promId,OrdPromDTO ordPromDTO) throws BusinessException{
        //参数验证
        if(promId==null){
            //传入参数为空
            throw new BusinessException("prom.400088");
        }
         return promQuerySV.promPresent(promId,ordPromDTO);
    }
    /**
     * TODO促销赠送 积分 优惠券 赠品列表
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV#promPresent(com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest)
     * @param rOrdCartsCommRequest
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromPresentRespDTO promPresent(ROrdCartsCommRequest rOrdCartsCommRequest) throws BusinessException{
        
        if(rOrdCartsCommRequest==null){
            throw new BusinessException("prom.400086");
        }
        
        if(CollectionUtils.isEmpty(rOrdCartsCommRequest.getOrdCartsCommList())){
            throw new BusinessException("prom.400086");
        }
        //返回对象
        PromPresentRespDTO  respDTO=new PromPresentRespDTO();
        
        //返回参数设置  购物车列表
        Map<Long,PromPresentDTO> cartPromDTOMap  =new HashMap<Long,PromPresentDTO>();
        //返回参数设置 购物车明细列表
        Map<Long,PromPresentDTO> cartPromItemDTOMap=new HashMap<Long,PromPresentDTO>();
        
        respDTO.setCartPromDTOMap(cartPromDTOMap);
        respDTO.setCartPromItemDTOMap(cartPromItemDTOMap);
        
        //组织参数 转为 ordPromDTO
        for(ROrdCartCommRequest reqDTO:rOrdCartsCommRequest.getOrdCartsCommList()){
            
            if(CollectionUtils.isEmpty(reqDTO.getOrdCartItemCommList())){
                throw new BusinessException("prom.400086");
            }
            //转为促销dto
            OrdPromDTO ordPromDTO=new OrdPromDTO();
            
            ObjectCopyUtil.copyObjValue(reqDTO, ordPromDTO, "", false);
            
            ordPromDTO.setSiteId(reqDTO.getCurrentSiteId());
            ordPromDTO.setCalDate(DateUtil.getSysDate());//日期配置
            //订单有参与促销id 订单传入  null 0初始化 -1选择不参与促销 正整数选择对应的促销id
            if(!StringUtil.isEmpty(reqDTO.getPromId()) && reqDTO.getPromId().longValue()!=0){
                PromInfoDTO promInfoDTO=new PromInfoDTO();
                promInfoDTO.setId(reqDTO.getPromId());
                ordPromDTO.setPromInfoDTO(promInfoDTO);
            }
            //渠道来源  客户组 客户等级 区域
           if(!StringUtil.isEmpty(reqDTO.getChannelValue()) || !StringUtil.isEmpty(reqDTO.getCustGroupValue()) ||!StringUtil.isEmpty(reqDTO.getCustLevelValue()) || !StringUtil.isEmpty(reqDTO.getAreaValue())){
               if( ordPromDTO.getPromRuleCheckDTO()==null){
                   PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
                   promRuleCheckDTO.setAreaValue(reqDTO.getAreaValue());
                   promRuleCheckDTO.setChannelValue(reqDTO.getChannelValue());
                   promRuleCheckDTO.setCustGroupValue(reqDTO.getCustGroupValue());
                   promRuleCheckDTO.setCustLevelValue(reqDTO.getCustLevelValue());
                   promRuleCheckDTO.setSiteId(reqDTO.getCurrentSiteId());
                   ordPromDTO.setPromRuleCheckDTO(promRuleCheckDTO);
               }
           }
           //购物车明细列表
            List<OrdPromDetailDTO> detailDTOList=new ArrayList<OrdPromDetailDTO>();
            
            ordPromDTO.setOrdPromDetailDTOList(detailDTOList);
            //转化购物车明细
            for(ROrdCartItemCommRequest reqDetailDTO:reqDTO.getOrdCartItemCommList()){
            
                OrdPromDetailDTO dto=new OrdPromDetailDTO();
                ObjectCopyUtil.copyObjValue(reqDetailDTO, dto, "", false);
                
                //有参与促销id 订单传入  null初始化 -1选择不参与促销 正整数选择对应的促销id
                if(!StringUtil.isEmpty(reqDetailDTO.getPromId()) && reqDetailDTO.getPromId().longValue()!=0){
                    PromInfoDTO promInfoDTO=new PromInfoDTO();
                    promInfoDTO.setId(reqDetailDTO.getPromId());
                    dto.setPromInfoDTO(promInfoDTO);
                }
                
                detailDTOList.add(dto);
            
             }
            //如果订单促销有参与 并且满足需要 计算订单对应明细参与范围
            if(ordPromDTO.getPromInfoDTO()!=null && ordPromDTO.getPromInfoDTO().getId()>0 && ordPromDTO.isIfFulfillProm()){
                
                //重新init ordPromDTO.ordPromId
              /*  List<PromInfoDTO> listClass=promSV.fullFilPromotionListByOrd(ordPromDTO,
                        ordPromDTO.getCalDate(),ordPromDTO.getPromInfoDTO().getId());*/
                
                PromPresentDTO promPresentDTO= promQuerySV.promPresent(ordPromDTO.getPromInfoDTO().getId(),ordPromDTO);
                cartPromDTOMap.put(ordPromDTO.getId(), promPresentDTO);
            }else{
                cartPromDTOMap.put(ordPromDTO.getId(), null);
            }
           //明细促销id参与 计算赠送值
            for(OrdPromDetailDTO detailDTO:ordPromDTO.getOrdPromDetailDTOList()){
                if(detailDTO.getPromInfoDTO()!=null && detailDTO.getPromInfoDTO().getId()>0 && detailDTO.isIfFulfillProm()){
                    PromPresentDTO promPresentDTO=promQuerySV.promPresent(detailDTO.getPromInfoDTO().getId(),ordPromDTO);
                    cartPromItemDTOMap.put(detailDTO.getId(), promPresentDTO);
                }else{
                    cartPromItemDTOMap.put(detailDTO.getId(), null);
                }
            }
        }
        
        return respDTO;
    }
    /**
     * TODO促销类型
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV#queryPromType(com.zengshi.ecp.prom.dubbo.dto.PromTypeDTO)
     * @param promTypeDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromTypeResponseDTO queryPromType(PromTypeDTO promTypeDTO) throws BusinessException{
        //参数验证
        if(promTypeDTO==null){
            throw new BusinessException("prom.400086");
        }
        if(StringUtils.isEmpty(promTypeDTO.getPromTypeCode())){
            throw new BusinessException("prom.400085");
        }
        return promTypeSV.queryPromTypeByCode(promTypeDTO.getPromTypeCode());
    }
    /**
     * TODO 获得促销商品列表
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV#listPromotionSku(com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO)
     * @param PromSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSkuDTO> listPromotionSku(PromSkuDTO promSkuDTO) throws BusinessException{
        
           if(promSkuDTO==null){
               //传入参数为空
               throw new BusinessException("prom.400086");
            }
          if(promSkuDTO.getPromId()==null){
              //传入参数为空
              throw new BusinessException("prom.400088");
          }
          return promQuerySV.listPromotionSku(promSkuDTO);
          
    }
    /**
     * 获得促销商品 分页列表
     * @param promSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromSkuRespDTO> pagePromotionSku(PromSkuDTO promSkuDTO) throws BusinessException{
        if(promSkuDTO==null){
            //传入参数为空
            throw new BusinessException("prom.400086");
         }
       if(promSkuDTO.getPromId()==null){
           //传入参数为空
           throw new BusinessException("prom.400088");
       }
       return promQuerySV.pagePromotionSku(promSkuDTO);
    }
    /**
     * 获得促销商品黑名单列表
     * @param promSkuLimitDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSkuLimitDTO> listPromotionSkuLimit(PromSkuLimitDTO promSkuLimitDTO) throws BusinessException{
        
        if(promSkuLimitDTO==null){
            //传入参数为空
            throw new BusinessException("prom.400086");
         }
       if(promSkuLimitDTO.getPromId()==null){
           //传入参数为空
           throw new BusinessException("prom.400088");
       }
       return promQuerySV.listPromotionSkuLimit(promSkuLimitDTO);
    }
    
    /**
     * 获得促销搭配商品列表
     * @param promMatchSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromMatchSkuDTO> listPromMatchSku(PromMatchSkuDTO promMatchSkuDTO) throws BusinessException{
        
        if(promMatchSkuDTO==null){
            //传入参数为空
            throw new BusinessException("prom.400086");
         }
        return promMatchSV.queryMatchSkuList(promMatchSkuDTO);
    }
    
   
   
    /**
     * TODO商品详情列表或solr查询 并计算价格
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV#listProm(com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promRuleCheckDTO
     * @return   SecondKillPromRespDTO
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromListRespDTO> listProm(PromRuleCheckDTO promRuleCheckDTO) throws BusinessException{
        
        // 参数验证 必填
        if(promRuleCheckDTO==null){
            throw new BusinessException("prom.400086");
        }
        if (promRuleCheckDTO.getShopId() == null) {
            // prom.400069 = 店铺编码 不能为空！
            throw new BusinessException("prom.400069");
        }
        if (promRuleCheckDTO.getGdsId() == null) {
            // prom.400070 = 商品编码 不能为空！
            throw new BusinessException("prom.400070");
        }
        if (promRuleCheckDTO.getCalDate() == null) {
            // prom.400071 = 日期 不能为空！
            throw new BusinessException("prom.400071");
        }
        if (promRuleCheckDTO.getSkuId() == null) {
            // prom.400072 = 单品编码 不能为空！
            throw new BusinessException("prom.400072");
        }
        if (promRuleCheckDTO.getStaffId()==null) {
            throw new BusinessException("prom.400096");
        }
        return promSV.listPromInfo(promRuleCheckDTO,true,null);
    }
    @Override
    public GdsPromListDTO listPromNew(PromRuleCheckDTO promRuleCheckDTO) throws BusinessException {
        
        // 参数验证 必填
        if(promRuleCheckDTO==null){
            throw new BusinessException("prom.400086");
        }
        if (promRuleCheckDTO.getShopId() == null) {
            // prom.400069 = 店铺编码 不能为空！
            throw new BusinessException("prom.400069");
        }
        if (promRuleCheckDTO.getGdsId() == null) {
            // prom.400070 = 商品编码 不能为空！
            throw new BusinessException("prom.400070");
        }
        if (promRuleCheckDTO.getCalDate() == null) {
            // prom.400071 = 日期 不能为空！
            throw new BusinessException("prom.400071");
        }
        if (promRuleCheckDTO.getSkuId() == null) {
            // prom.400072 = 单品编码 不能为空！
            throw new BusinessException("prom.400072");
        }
        /*if (promRuleCheckDTO.getStaffId()==null) {
            throw new BusinessException("prom.400096");
        }*/
        return promSV.listPromInfoNew(promRuleCheckDTO,true,null);
    }
    /**
     * TODO商品活动促销列表和价格
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV#listPromByGds(com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromListRespDTO> listPromByGds(PromRuleCheckDTO promRuleCheckDTO) throws BusinessException{
        // 参数验证 必填
        if(promRuleCheckDTO==null){
            throw new BusinessException("prom.400086");
        }
        if (promRuleCheckDTO.getShopId() == null) {
            // prom.400069 = 店铺编码 不能为空！
            throw new BusinessException("prom.400069");
        }
        if (promRuleCheckDTO.getGdsId() == null) {
            // prom.400070 = 商品编码 不能为空！
            throw new BusinessException("prom.400070");
        }
        if (promRuleCheckDTO.getCalDate() == null) {
            // prom.400071 = 日期 不能为空！
            throw new BusinessException("prom.400071");
        }
        if (promRuleCheckDTO.getStaffId()==null) {
            throw new BusinessException("prom.400096");
        }
        return promSV.listPromInfo(promRuleCheckDTO,true,null);
    }
    /**
     * TODO 平台促销待审核数据统计
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV#queryTotalByProm(com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO)
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public Long queryTotalByProm(PromInfoDTO promInfoDTO) throws BusinessException{
    	if(promInfoDTO==null){
    		promInfoDTO=new PromInfoDTO();
    	}
    	return promInfoSV.queryTotalByProm(promInfoDTO);
    }

    
    /**
     * 显示秒杀促销列表 分页
     * 
     * @param promInfoDTO
     * @param 
     * @return
     * @throws BusinessException
     * @author lisp
     */
    @Override
    public  PageResponseDTO<PromInfoResponseDTO> listSecondPromInfoForPage(PromInfoDTO promInfoDTO) throws BusinessException {
    	if(promInfoDTO.getSiteId()==null){
    		throw new BusinessException("prom.400151");
    	}
    		promInfoDTO.setEndTime(DateUtil.getSysDate());
    		promInfoDTO.setShowStartTime(DateUtil.getSysDate());
    		promInfoDTO.setPromTypeCode(PromConstants.PromType.PROM_TYPE_CODE_1000000019);
    		promInfoDTO.setStatus(PromConstants.PromInfo.STATUS_10);
    	return promInfoSV.listSecondPromInfoForPage(promInfoDTO);
    }
    
    
    
    /**
     * TODO商品详情列表 并计算价格
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV#listSkuOfSecondKillProm(com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO)
     * @param PromInfoDTO
     * @return   
     * @throws BusinessException
     * @author lisp
     */
    @Override
    public SecondKillPromRespDTO listSkuOfSecondKillProm(PromInfoDTO promInfoDTO) throws BusinessException{
    	//返回 秒杀促销跟秒杀商品列表
    	SecondKillPromRespDTO secondKillPromRespDTO = new SecondKillPromRespDTO();
    	//促销基本信息
    	PromInfoResponseDTO promInfoResponseDTO = promInfoSV.selectPromInfoResponseDTOById(promInfoDTO.getId());
    	if (promInfoResponseDTO == null) {
    		LogUtil.error(MODULE,"促销id:" + promInfoDTO.getId() + "没有找到记录！");
    		return null;
        }
    	if(!PromConstants.PromType.PROM_TYPE_CODE_1000000019.equals(promInfoResponseDTO.getPromTypeCode())){
    		LogUtil.error(MODULE,"促销id:" + promInfoDTO.getId() + "对应的促销不是限时秒杀促销");
    		return null;
    	}
    	promInfoResponseDTO.setNowTime(DateUtil.getSysDate());
    	secondKillPromRespDTO.setPromInfoResponseDTO(promInfoResponseDTO);
    	//促销单品表信息
    	PromSkuDTO promSkuDTO = new PromSkuDTO();
    	promSkuDTO.setPromId(promInfoDTO.getId());
    	promSkuDTO.setPageSize(promInfoDTO.getPageSize());
    	promSkuDTO.setPageNo(promInfoDTO.getPageNo());
    	PageResponseDTO<KillGdsInfoDTO> page =promSkuSV.querySkuPromforPage(promSkuDTO); 
    	secondKillPromRespDTO.setPage(page);
    	return secondKillPromRespDTO;
    	
    }
    
    /**
     * 秒杀促销商品信息封装
     * 
     * @param PromDTO promDTO
     * @return
     * @throws BusinessException
     * @author lisp
     */
    public  List<KillGdsInfoDTO> killPromGdsinfoList(PromDTO promDTO) throws BusinessException{
    	if(promDTO.getSiteId()==null){
    		throw new BusinessException("prom.400151");
    	}	
    	promDTO.setEndTime(DateUtil.getSysDate());
		promDTO.setShowStartTime(DateUtil.getSysDate());
		promDTO.setPromTypeCode(PromConstants.PromType.PROM_TYPE_CODE_1000000019);
		promDTO.setStatus(PromConstants.PromInfo.STATUS_10);
		promDTO.setGridQuerySortName("start_time,id");
    	//获取有效的限时秒杀促销列表
    	List<PromInfoResponseDTO> promInfolist = promInfoSV.queryPromInfoList(promDTO);
    	if(CollectionUtils.isEmpty(promInfolist)||promInfolist.size()==0){
    		return null;
    	}
    	
    	List<KillGdsInfoDTO>  killGdsInfoList = new ArrayList<KillGdsInfoDTO>();
    	for (PromInfoResponseDTO promInfoResponseDTO : promInfolist) {
        	//促销单品表信息
        	List<PromSku> skuList = promSkuSV.querySkuPromByPromId(promInfoResponseDTO.getId());
        	if (skuList == null||skuList.size()==0) {
                LogUtil.error(MODULE, "参加促销ID"+promInfoResponseDTO.getId()+"的单品为空");
                continue;
            }
        	for (PromSku promSku : skuList) {
        		if(killGdsInfoList.size()>=promDTO.getPageSize()){
        			break;
        		}
        		KillGdsInfoDTO killGdsInfoDTO = this.queryPromGdsInfo(promSku);
        		if("1".equals(killGdsInfoDTO.getIfSell())){
        			killGdsInfoList.add(killGdsInfoDTO);
        			if(killGdsInfoList.size()>=promDTO.getPageSize()){
        				break;
        			}
        		}
			}
		}
    	return killGdsInfoList;
    }
    /**
     * 秒杀促销商品信息封装
     * 
     * @param PromSku promSku
     * @return
     * @throws BusinessException
     * @author lisp
     */
    public KillGdsInfoDTO queryPromGdsInfo(PromSku promSku) throws BusinessException {
    	KillGdsInfoDTO killGdsInfoDTO = new KillGdsInfoDTO();
    	//商品查询选项
    	SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.MAINPIC,SkuQueryOption.CAlDISCOUNT,SkuQueryOption.PROP};
    	PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
    	
		GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
		skuInfoReqDTO.setId(promSku.getSkuId());
		skuInfoReqDTO.setGdsId(promSku.getGdsId());
		skuInfoReqDTO.setStatus(promSku.getStatus());
		skuInfoReqDTO.setSkuQuery(skuQueryOptions);
		GdsSkuInfoRespDTO gdsSkuInfoRespDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(skuInfoReqDTO);
		if(StringUtil.isEmpty(gdsSkuInfoRespDTO)){
			return null;
		}
		promRuleCheckDTO.setBasePrice(gdsSkuInfoRespDTO.getRealPrice());
		promRuleCheckDTO.setBuyPrice(gdsSkuInfoRespDTO.getDiscountPrice());
		promRuleCheckDTO.setGdsId(gdsSkuInfoRespDTO.getGdsId());
		promRuleCheckDTO.setSkuId(gdsSkuInfoRespDTO.getId());
		promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
		promRuleCheckDTO.setShopId(gdsSkuInfoRespDTO.getShopId());
		promRuleCheckDTO.setGdsName(gdsSkuInfoRespDTO.getGdsName());
		promRuleCheckDTO.setStaff(promRuleCheckDTO.getStaff());
		promRuleCheckDTO.setStaffId(promRuleCheckDTO.getStaffId());
		promRuleCheckDTO.setIfCalPrice(Boolean.TRUE);//需要计算价格
		
		 //计算价格
		PromInfoDTO promInfoDTO =new PromInfoDTO();
		promInfoDTO.setId(promSku.getPromId());
        PromSkuPriceRespDTO priceDTO=promQuerySV.calSkuPriceByPromId(promInfoDTO,promRuleCheckDTO);
    	//封装价格
    	killGdsInfoDTO.setBasePrice(priceDTO.getDiscountCaclPrice().longValue());//真实价
    	killGdsInfoDTO.setBuyPrice(gdsSkuInfoRespDTO.getDiscountPrice());//购买价
    
        PromStockLimitDTO stocklimit = promUtilRSV.queryPromStockLimit(String.valueOf(promSku.getPromId()),String.valueOf(promSku.getSkuId()));
			killGdsInfoDTO.setBuyCnt(stocklimit.getBuyCnt());
			killGdsInfoDTO.setPercent(new BigDecimal(stocklimit.getBuyCnt()).multiply(new BigDecimal(100)).divide(new BigDecimal(promSku.getPromCnt()),2, BigDecimal.ROUND_HALF_EVEN));
		if(stocklimit.getBuyCnt()<(promSku.getPromCnt())){
			if("11".equals(gdsSkuInfoRespDTO.getGdsStatus())){
				killGdsInfoDTO.setIfSell("1");//1:表示秒杀商品数量还有剩余  0表示秒杀商品数量为零
			}
		}
		LongReqDTO  id = new LongReqDTO ();
		id.setId(gdsSkuInfoRespDTO.getGdsTypeId());
		killGdsInfoDTO.setGdsTypeFlag(gdsInfoExternalRSV.isNeedStockAmount(id));
        //封装秒杀商品展示字段
        killGdsInfoDTO.setKillPrice(priceDTO.getDiscountFinalPrice().longValue());//秒杀价
        killGdsInfoDTO.setPromCnt(promSku.getPromCnt());//促销总个数
        killGdsInfoDTO.setSkuId(promSku.getSkuId());//单品Id
        killGdsInfoDTO.setGdsId(promSku.getGdsId());//商品Id
        if(StringUtil.isNotBlank(gdsSkuInfoRespDTO.getGdsDesc())){
			killGdsInfoDTO.setGdsDesc(FileUtil.readFile2Text(gdsSkuInfoRespDTO.getGdsDesc(), null));//商品描述
		}else{
			if(gdsSkuInfoRespDTO.getAllPropMaps()!=null){
				if(!StringUtil.isEmpty(gdsSkuInfoRespDTO.getAllPropMaps().get("1020"))){
					if(!CollectionUtils.isEmpty(gdsSkuInfoRespDTO.getAllPropMaps().get("1020").getValues())||gdsSkuInfoRespDTO.getAllPropMaps().get("1020").getValues().size()!=0){
						killGdsInfoDTO.setGdsDesc(FileUtil.readFile2Text(gdsSkuInfoRespDTO.getAllPropMaps().get("1020").getValues().get(0).getPropValue(), null));//商品详情
						}
				}
			}
		}
        
        if(StringUtil.isNotEmpty(gdsSkuInfoRespDTO.getMainPic())){
        	killGdsInfoDTO.setMediaUuid(gdsSkuInfoRespDTO.getMainPic().getMediaUuid());
        	killGdsInfoDTO.setURL(gdsSkuInfoRespDTO.getMainPic().getURL());
        }
        killGdsInfoDTO.setGdsName(gdsSkuInfoRespDTO.getGdsName());
        killGdsInfoDTO.setDetailURL(gdsSkuInfoRespDTO.getUrl());//商品详情页地址
    	return killGdsInfoDTO;
    }

}
