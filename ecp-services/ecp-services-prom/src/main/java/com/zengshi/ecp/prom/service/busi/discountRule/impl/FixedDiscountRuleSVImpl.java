package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;

import com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.FixedDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.NumberUtil;
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
public class FixedDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl {

    private static final String MODULE = FixedDiscountRuleSVImpl.class.getName();

    /**
     * TODO是否满足优惠规则 (单品促销类型)
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
        
        if(promTypeMsgResponseDTO==null){
            //后续代码不需要跟踪验证空null 判断
            promTypeMsgResponseDTO=new PromTypeMsgResponseDTO();
        }
        
        // 由于固定价格折扣 直接满足
        FixedDTO fixedDTO = new FixedDTO();
        if(!StringUtil.isEmpty(constraintStr)){
              fixedDTO = super.readJson2Bean(constraintStr, FixedDTO.class);
              if(fixedDTO==null){
                  fixedDTO = new FixedDTO();
              }
        }
        String msg=promTypeMsgResponseDTO.getFulfilMsg();
        promTypeMsgResponseDTO.setFulfilMsg(super.formatTipString(msg,NumberUtil.divideNum(fixedDTO.getFixedPrice(),null)));
        
        return Boolean.TRUE;

    }

    /**
     * TODO计算优惠信息 固定价格
     * 
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#calculatePromotion(com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO,
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
    public PromDiscountDTO calculatePromotion(OrdPromDTO ordProDTO,
            OrdPromDetailDTO ordPromDetailDTO, String constraintStr) throws BusinessException {
        
        PromDiscountDTO promDiscountDTO = new PromDiscountDTO();
        // 默认0
        BigDecimal r = BigDecimal.ZERO;
        // 满足促销优惠规则
        if (ordPromDetailDTO.isIfFulfillProm()) {
            
            if (!StringUtil.isEmpty(constraintStr)) {
            	
                BigDecimal discountPrice = BigDecimal.ZERO;// 单价优惠 指优惠了多少单价
                BigDecimal discountMoney = BigDecimal.ZERO;// 优惠金额 指优惠了多少金额
                BigDecimal discountPriceMoney 	= BigDecimal.ZERO;// 除单价优惠金额外的优惠金额，不同于优惠金额discountMoney
                BigDecimal discountAmount 		= BigDecimal.ZERO;// 优惠数量 指优惠了多少数量，买X送Y专用
                BigDecimal discountCaclPrice  	= BigDecimal.ZERO;//促销优惠计算标准价：buyPrice、basePrice
                BigDecimal discountFinalPrice 	= BigDecimal.ZERO;//最终优惠后的价格，促销后最终的价格
                
                FixedDTO fixedDTO = new FixedDTO();
                fixedDTO = super.readJson2Bean(constraintStr, FixedDTO.class);
                
                //开始计算价格
                discountCaclPrice = BigDecimal.valueOf(ordPromDetailDTO.getBasePrice());
                discountFinalPrice = fixedDTO.getFixedPrice();
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
        // 需要验证
        return Boolean.TRUE;
    }

    /**
     * TODO促销信息录入-优惠规则验证 固定价格验证规则
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#valid(java.lang.String, com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDiscountRuleStr
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public void valid(String promDiscountRuleStr,PromDTO promDTO) throws BusinessException {

        // 限制条件没有设置 默认满足
        if (StringUtil.isEmpty(promDiscountRuleStr)) {
            return;
        }
        // 固定价格
        FixedDTO fixedDTO = new FixedDTO();
        try{
            fixedDTO = super.readJson2Bean(promDiscountRuleStr, FixedDTO.class);
        }catch(Exception ex){
            LogUtil.error(MODULE, this.getClass().getName()+"转json格式数据 报错了  参数="+promDiscountRuleStr);
            throw new BusinessException("prom.400174");
         }
        if (fixedDTO == null) {
            throw new BusinessException("prom.400042");
        }
        if (fixedDTO.getFixedPrice() == null) {
            // throw new BusinessException("固定价格不能为空！");
            throw new BusinessException("prom.400051");
        }
        if (fixedDTO.getFixedPrice().compareTo(BigDecimal.ZERO) <= 0) {
            // throw new BusinessException("固定价格必需大于零！");
            throw new BusinessException("prom.400052");
        }
        this.validTranlate(fixedDTO, promDTO);
    }
    /**
     * TODO转换金额为分
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#validTranlate(com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO, com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param commPromTypeDTO
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public void validTranlate(CommPromTypeDTO commPromTypeDTO,PromDTO promDTO) throws BusinessException {
             if(!StringUtil.isEmpty(promDTO.getDiscountRule())) {
                    if(commPromTypeDTO!=null){
                        FixedDTO  dto=(FixedDTO)commPromTypeDTO;
                        dto.setFixedPrice(NumberUtil.tranlateNum(dto.getFixedPrice().multiply(new BigDecimal(100))));
                        promDTO.setDiscountRule(JSON.toJSONString(dto));
                    }
             }
        
    }
}
