package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;

import com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.FixedRateDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.NumberUtil;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-1 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class FixedRateDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl {

    private static final String MODULE = FixedRateDiscountRuleSVImpl.class.getName();

    /**
     * TODO 是否满足优惠规则 (订单促销类型)
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
        //活动订单下单金额
        //根据promId取得对应的金额 原因：前部代码已经设置各个购物车实例ordpromId为参加订单的促销id
        FixedRateDTO dto = new FixedRateDTO();
        if(!StringUtil.isEmpty(constraintStr)){
            dto = super.readJson2Bean(constraintStr, FixedRateDTO.class);
            if(dto==null){
                dto= new FixedRateDTO();
            }
        }
        BigDecimal orderMoney = super.totalOrderMoneyByPromId(ordProDTO);
        if(orderMoney.compareTo(dto.getOrderMoney())>=0){
            String msg=promTypeMsgResponseDTO.getFulfilMsg();
            promTypeMsgResponseDTO.setFulfilMsg(super.formatTipString(msg,NumberUtil.divideNum(dto.getOrderMoney(), null),dto.getDiscountRate()+PromConstants.PromSys.LIKE_PERCENT));
            return Boolean.TRUE;
        }
        String msg=promTypeMsgResponseDTO.getNoFulfilMsg();
        promTypeMsgResponseDTO.setNoFulfilMsg(super.formatTipString(msg,NumberUtil.divideNum(dto.getOrderMoney(), null),dto.getDiscountRate()+PromConstants.PromSys.LIKE_PERCENT));
        
        LogUtil.debug(MODULE, promTypeMsgResponseDTO.getNoFulfilMsg());
        
        return Boolean.FALSE;
    }

    /**
     * TODO计算优惠信息 折扣促销
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
        // 满足促销优惠规则
        if (ordProDTO.isIfFulfillProm()) {
            
            if (!StringUtil.isEmpty(constraintStr)) {
                BigDecimal discountPrice = BigDecimal.ZERO;// 单价优惠 指优惠了多少单价
                BigDecimal discountMoney = BigDecimal.ZERO;// 优惠金额 指优惠了多少金额
                BigDecimal discountPriceMoney 	= BigDecimal.ZERO;// 除单价优惠金额外的优惠金额，不同于优惠金额discountMoney
                BigDecimal discountAmount 		= BigDecimal.ZERO;// 优惠数量 指优惠了多少数量，买X送Y专用
                BigDecimal discountCaclPrice  	= BigDecimal.ZERO;//促销优惠计算标准价：buyPrice、basePrice
                BigDecimal discountFinalPrice 	= BigDecimal.ZERO;//最终优惠后的价格，促销后最终的价格
                
                // 默认0
                //开始计算价格
                BigDecimal discoutRate = BigDecimal.ZERO;
                FixedRateDTO dto = new FixedRateDTO();
                dto = super.readJson2Bean(constraintStr, FixedRateDTO.class);
                // 当前折扣价
                discoutRate =dto.getDiscountRate();
                //活动订单下单金额
                //根据promId取得对应的金额 原因：前部代码已经设置各个购物车实例ordpromId为参加订单的促销id
                BigDecimal orderMoney = super.totalOrderMoneyByPromId(ordProDTO);
                discountCaclPrice = orderMoney;
                discountFinalPrice = discountCaclPrice.multiply(discoutRate).divide(new BigDecimal(100));
                discountMoney = discountCaclPrice.subtract(discountFinalPrice);
               
                promDiscountDTO.setDiscountMoney(discountMoney);
                promDiscountDTO.setDiscountCaclPrice(discountCaclPrice);
                promDiscountDTO.setDiscountFinalPrice(discountFinalPrice);            }
            
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
     * TODO促销信息录入-优惠规则验证 折扣类型验证规则
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
        
        FixedRateDTO dto = new FixedRateDTO();
        try{
            dto = super.readJson2Bean(promDiscountRuleStr, FixedRateDTO.class);
        }catch(Exception ex){
            LogUtil.error(MODULE, this.getClass().getName()+"转json格式数据 报错了  参数="+promDiscountRuleStr);
            throw new BusinessException("prom.400174");
        }
        
        if (dto == null) {
            // throw new BusinessException("请填写优惠规则！");
            throw new BusinessException("prom.400042");
        }
        if (dto.getOrderMoney() == null) {
            // throw new BusinessException("订单金额不能为空");
            throw new BusinessException("prom.400116");
        }
        
        if (dto.getOrderMoney().compareTo(BigDecimal.ZERO) <= 0) {
            // throw new BusinessException("订单金额必需大于零！");
            throw new BusinessException("prom.400117");
        }
        if (dto.getDiscountRate() == null) {
            // throw new BusinessException("折扣比例不能为空！");
            throw new BusinessException("prom.400113");
        }
        if (dto.getDiscountRate().compareTo(BigDecimal.ZERO) <= 0) {
            // throw new BusinessException("折扣比例格必需大于零！");
            throw new BusinessException("prom.400114");
        }
        //折扣率 不能超过80
        BigDecimal per=super.limitPercent(promDTO.getPromTypeCode());
        if (dto.getDiscountRate().compareTo(per)>0) {
            //请设置折扣率 小于80
            String[] keys=new String[2];
            keys[0]=dto.getDiscountRate().toString();
            keys[1]=per.toString();
            throw new BusinessException("prom.400173",keys);
        }
        if (dto.getDiscountRate().compareTo(new BigDecimal(100)) > 0) {
            // throw new BusinessException("折扣比例不能超过100%！");
            throw new BusinessException("prom.400115");
        }
        this.validTranlate(dto, promDTO);
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
                        FixedRateDTO  dto=(FixedRateDTO)commPromTypeDTO;
                        dto.setOrderMoney(NumberUtil.tranlateNum(dto.getOrderMoney().multiply(new BigDecimal(100))));
                        promDTO.setDiscountRule(JSON.toJSONString(dto));
                    }
             }
        
    }
}
