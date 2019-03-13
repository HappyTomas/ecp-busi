package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGiftRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsGiftRSV;
import com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.FixedDTO;
import com.zengshi.ecp.prom.dubbo.dto.GdsSendGiftDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.NumberUtil;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class GdsSendGiftDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl{

    private static final String MODULE = GdsSendGiftDiscountRuleSVImpl.class.getName();

    @Resource
    private IPromQuerySV promQuerySV;
    
    @Resource
    private IGdsGiftRSV gdsGiftRSV;
    
    /**
     * TODO是否满足优惠规则 (订单促销类型)
     * 
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#isFulFilPromByGds(com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO,
     *      com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO, java.lang.String)
     * @param ordProDTO
     * @param ordPromDetailDTO
     * @param constraintStr
     * @return
     * @throws IllegalAccessException
     * @throws Exception
     * @author huangjx
     */
    @Override
    public boolean isFulFilPromByGds(OrdPromDTO ordProDTO, OrdPromDetailDTO ordPromDetailDTO,
            String constraintStr,String ifThrows,PromTypeMsgResponseDTO promTypeMsgResponseDTO) throws BusinessException {
        if (ordPromDetailDTO == null) {
            return false;
        }
        if(promTypeMsgResponseDTO==null){
            //后续代码不需要跟踪验证空null 判断
            promTypeMsgResponseDTO=new PromTypeMsgResponseDTO();
        }
        // 购买满一定数量 送礼品
        GdsSendGiftDiscountDTO dto = new GdsSendGiftDiscountDTO();
        String buyAmount="";
        //非空 获得对象值
        if(!StringUtil.isEmpty(constraintStr)){
            dto = super.readJson2Bean(constraintStr, GdsSendGiftDiscountDTO.class);
            if(dto!=null){
                buyAmount=dto.getBuyAmount().toString();
            }
        }
        
        String msg=promTypeMsgResponseDTO.getFulfilMsg();
        //满足提醒信息
        promTypeMsgResponseDTO.setFulfilMsg(super.formatTipString(msg, buyAmount));
        //constraintStr 限制条件没有设置 默认满足
        if(StringUtil.isEmpty(constraintStr)){
           
            return Boolean.TRUE;
        }
        
        //当前商品购买数量
        
        long curBuyAmount = ordPromDetailDTO.getOrderAmount();
        
        if (curBuyAmount >= Long.valueOf(buyAmount)) {
            
            return Boolean.TRUE;
        }
        
        if(PromConstants.PromSys.IF_THROWS.equals(ifThrows)){
            String[] key=new String[1];
            key[0]=buyAmount;
            throw new BusinessException("prom.400112",key);
        }
        //未满足 提醒信息
        msg=promTypeMsgResponseDTO.getNoFulfilMsg();
        promTypeMsgResponseDTO.setNoFulfilMsg(super.formatTipString(msg, buyAmount));
        
        return Boolean.FALSE;

    }
    @Override
    public PromDiscountDTO calculatePromotion(OrdPromDTO ordProDTO,
            OrdPromDetailDTO ordPromDetailDTO, String constraintStr) throws BusinessException {
        
        PromDiscountDTO promDiscountDTO = new PromDiscountDTO();

        // 满足促销优惠规则
        if (ordPromDetailDTO.isIfFulfillProm()) {
            
            if (!StringUtil.isEmpty(constraintStr)) {
                
                BigDecimal discountPrice = BigDecimal.ZERO;// 单价优惠 指优惠了多少单价
                BigDecimal discountMoney = BigDecimal.ZERO;// 优惠金额 指优惠了多少金额
                BigDecimal discountPriceMoney   = BigDecimal.ZERO;// 除单价优惠金额外的优惠金额，不同于优惠金额discountMoney
                BigDecimal discountAmount       = BigDecimal.ZERO;// 优惠数量 指优惠了多少数量，买X送Y专用
                BigDecimal discountCaclPrice    = BigDecimal.ZERO;//促销优惠计算标准价：buyPrice、basePrice
                BigDecimal discountFinalPrice   = BigDecimal.ZERO;//最终优惠后的价格，促销后最终的价格
                
                //开始计算价格
                discountCaclPrice = BigDecimal.valueOf(ordPromDetailDTO.getBasePrice());
                discountFinalPrice = BigDecimal.valueOf(ordPromDetailDTO.getBuyPrice());
                
                discountPrice = discountCaclPrice.subtract(discountFinalPrice);
                discountMoney = BigDecimal.valueOf(ordPromDetailDTO.getOrderAmount()).multiply(discountPrice);

                promDiscountDTO.setDiscountPrice(discountPrice);
                promDiscountDTO.setDiscountMoney(discountMoney);
                promDiscountDTO.setDiscountCaclPrice(discountCaclPrice);
                promDiscountDTO.setDiscountFinalPrice(discountFinalPrice);
            }
        }
     
        return promDiscountDTO;
    }

    /**
     * TODO促销信息录入-优惠规则,是否需要验证
     * 
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#needToVerified(com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    @Override
    public boolean needToVerified(PromDTO promDTO) throws BusinessException {
        return Boolean.TRUE;
    }
    /**
     * TODO礼品赠送验证规则
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#valid(java.lang.String, com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDiscountRuleStr
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public void valid(String promDiscountRuleStr,PromDTO promDTO) throws BusinessException {
        
        //限制条件没有设置 默认满足
        if(StringUtil.isEmpty(promDiscountRuleStr)){
            return ;
        }
        // 单品购买数量满一定数量  送礼品
        GdsSendGiftDiscountDTO dto = new GdsSendGiftDiscountDTO();
        try{
            dto = super.readJson2Bean(promDiscountRuleStr,
                    GdsSendGiftDiscountDTO.class);
        }catch(Exception ex){
            LogUtil.error(MODULE, this.getClass().getName()+"转json格式数据 报错了  参数="+promDiscountRuleStr);
            throw new BusinessException("prom.400174");
         }
        if (dto == null) {
            // throw new BusinessException("请填写优惠规则！");
            throw new BusinessException("prom.400042");
        }
        if (dto.getBuyAmount() == null) {
            // throw new BusinessException("订单金额不能为空！");
            throw new BusinessException("购买数量不能为空");
        }
        if (dto.getBuyAmount().compareTo(BigDecimal.ZERO) <= 0) {
            // throw new BusinessException("订单金额必需大于零！");
            throw new BusinessException("购买数量必须大于零！");
        }
        //没有录入赠品信息哦
        if(CollectionUtils.isEmpty(promDTO.getGiftList())){
            throw new BusinessException("prom.400120");
        }else{
            //赠品录入数量 非空、非有效数字
            //是否允许超库存待定 待实现 可后期开发
            for(PromGiftDTO giftDto:promDTO.getGiftList()){
                //非空验证
                if(StringUtil.isEmpty(giftDto.getPresentAllCnt())){
                    throw new BusinessException("prom.400121");
                }
                //数字 有效性验证
                if(!StringUtils.isNumeric(giftDto.getPresentAllCnt().toString())){
                    throw new BusinessException("prom.400122");
                }
                //大于等于0
                if(giftDto.getPresentAllCnt().compareTo(new Long(0))<=0){
                    throw new BusinessException("prom.400123");
                }
                
                //非空验证
                if(StringUtil.isEmpty(giftDto.getEveryTimeCnt())){
                    throw new BusinessException("prom.400139");
                }
                //数字 有效性验证
                if(!StringUtils.isNumeric(giftDto.getEveryTimeCnt().toString())){
                    throw new BusinessException("prom.400140");
                }
                //大于等于0
                if(giftDto.getEveryTimeCnt().compareTo(new Long(0))<=0){
                    throw new BusinessException("prom.400141");
                }
                
            }
            //验证赠品有效性  无效抛出错误
            for(PromGiftDTO giftDto:promDTO.getGiftList()){
                GdsGiftReqDTO gdsGiftReqDTO=new GdsGiftReqDTO();
                gdsGiftReqDTO.setId(giftDto.getGiftId());
                GdsGiftRespDTO gdsGiftRespDTO= gdsGiftRSV.querySingleGiftInfo(gdsGiftReqDTO);
                //赠品无效 抛出错误
                if(gdsGiftRespDTO==null || "0".equals(gdsGiftRespDTO.getGiftStatus())){
                    String[] keys={giftDto.getGiftId().toString()};
                    throw new BusinessException("prom.400175",keys);
                }
            }
            
        }
        this.validTranlate(dto, promDTO);
    }
   
    /**
     * TODO赠品列表实现
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#promPresent(java.lang.Long, com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO)
     * @param promId
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public PromPresentDTO promPresent(Long promId,OrdPromDTO ordPromDTO) throws BusinessException{
        
        //赠品列表
        List<PromGiftDTO> dtoList= promQuerySV.queryPromGift(promId);
        
        if(CollectionUtils.isEmpty(dtoList)){
            return null;
        }
        
        PromPresentDTO dto=new PromPresentDTO();
        dto.setPromId(promId);
        dto.setPromGiftDTOList(dtoList);
        
        return dto;
    }
    /**
     * 赠品列表
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public  List<PromGiftDTO> promGiftList(Long promId) throws BusinessException{
        //赠品列表
        List<PromGiftDTO> dtoList= promQuerySV.queryPromGift(promId);
        return dtoList;
    }

}
