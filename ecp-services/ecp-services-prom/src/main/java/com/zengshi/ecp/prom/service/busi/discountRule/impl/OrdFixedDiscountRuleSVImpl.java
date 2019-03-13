package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;

import com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdFixedDiscountDTO;
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
public class OrdFixedDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl {

    private static final String MODULE = OrdFixedDiscountRuleSVImpl.class.getName();

    /**
     * TODO是否满足优惠规则 (促销类型)
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
        OrdFixedDiscountDTO fixedDTO = new OrdFixedDiscountDTO();
        // 由于固定折扣 直接满足
        if (!StringUtil.isEmpty(constraintStr)) {
            fixedDTO = super.readJson2Bean(constraintStr, OrdFixedDiscountDTO.class);
            if(fixedDTO==null){
                fixedDTO = new OrdFixedDiscountDTO();
            }
        }
        BigDecimal orderMoney = super.totalOrderMoneyByPromId(ordProDTO);
        if(orderMoney.compareTo(fixedDTO.getOrderMoney())>=0){
            String msg=promTypeMsgResponseDTO.getFulfilMsg();
            promTypeMsgResponseDTO.setFulfilMsg(super.formatTipString(msg,NumberUtil.divideNum(fixedDTO.getOrderMoney(), null),NumberUtil.divideNum(fixedDTO.getFixedMoney(), null)));
            return Boolean.TRUE;
        }
        String msg=promTypeMsgResponseDTO.getNoFulfilMsg();
        promTypeMsgResponseDTO.setNoFulfilMsg(super.formatTipString(msg,NumberUtil.divideNum(fixedDTO.getOrderMoney(), null),NumberUtil.divideNum(fixedDTO.getFixedMoney(), null)));
        
        LogUtil.debug(MODULE, promTypeMsgResponseDTO.getNoFulfilMsg());
        
        return Boolean.FALSE;

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

        // 满足促销优惠规则
        if (ordProDTO.isIfFulfillProm()) {
            
            if (!StringUtil.isEmpty(constraintStr)) {
            	
                BigDecimal discountPrice = BigDecimal.ZERO;// 单价优惠 指优惠了多少单价
                BigDecimal discountMoney = BigDecimal.ZERO;// 优惠金额 指优惠了多少金额
                BigDecimal discountPriceMoney 	= BigDecimal.ZERO;// 除单价优惠金额外的优惠金额，不同于优惠金额discountMoney
                BigDecimal discountAmount 		= BigDecimal.ZERO;// 优惠数量 指优惠了多少数量，买X送Y专用
                BigDecimal discountCaclPrice  	= BigDecimal.ZERO;//促销优惠计算标准价：buyPrice、basePrice
                BigDecimal discountFinalPrice 	= BigDecimal.ZERO;//最终优惠后的价格，促销后最终的价格
                
                OrdFixedDiscountDTO fixedDTO = new OrdFixedDiscountDTO();
                fixedDTO = super.readJson2Bean(constraintStr, OrdFixedDiscountDTO.class);
                
                //开始计算价格
                BigDecimal orderMoney=super.totalOrderMoneyByPromId(ordProDTO);
                discountCaclPrice = orderMoney;
                
                if(orderMoney.compareTo(fixedDTO.getOrderMoney())>=0){
                    //固定折扣值
                	BigDecimal fixMoney = fixedDTO.getFixedMoney();
                    discountMoney = fixMoney;
                }
                //最终价格=订单总价格-优惠价
                discountFinalPrice = discountCaclPrice.subtract(discountMoney);
                
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
        OrdFixedDiscountDTO dto = new OrdFixedDiscountDTO();
        try{
            dto = super.readJson2Bean(promDiscountRuleStr, OrdFixedDiscountDTO.class);
        }catch(Exception ex){
            LogUtil.error(MODULE, this.getClass().getName()+"转json格式数据 报错了  参数="+promDiscountRuleStr);
            throw new BusinessException("prom.400174");
         }
        
        if (dto == null) {
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
        if (dto.getFixedMoney() == null) {
            // throw new BusinessException("优惠价格不能为空！");
            throw new BusinessException("prom.400118");
        }
        if (dto.getFixedMoney().compareTo(BigDecimal.ZERO) <= 0) {
            // throw new BusinessException("优惠价格必需大于零！");
            throw new BusinessException("prom.400119");
        }
        //小于等于 订单金额 *80% 
       BigDecimal per=super.limitPercent(promDTO.getPromTypeCode());
       if( dto.getOrderMoney().multiply(per).divide(new BigDecimal(100)).compareTo(dto.getFixedMoney())<0)
       {
           String[] keys=new String[2];
           keys[0]=dto.getFixedMoney().toString();
           keys[1]=per.toString();
           throw new BusinessException("prom.400171",keys);
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
                        OrdFixedDiscountDTO  dto=(OrdFixedDiscountDTO)commPromTypeDTO;
                        dto.setOrderMoney(NumberUtil.tranlateNum(dto.getOrderMoney().multiply(new BigDecimal(100))));
                        dto.setFixedMoney(NumberUtil.tranlateNum(dto.getFixedMoney().multiply(new BigDecimal(100))));
                        promDTO.setDiscountRule(JSON.toJSONString(dto));
                    }
             }
    }
}
