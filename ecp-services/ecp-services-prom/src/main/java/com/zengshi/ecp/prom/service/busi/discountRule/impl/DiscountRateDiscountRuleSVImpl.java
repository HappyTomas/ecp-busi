package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;
import com.zengshi.ecp.prom.dubbo.dto.DiscountRateDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-1 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class DiscountRateDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl {

    private static final String MODULE = DiscountRateDiscountRuleSVImpl.class.getName();

    /**
     * TODO 是否满足优惠规则 (单品促销类型)
     * 购买其中任意产品，即可享受百分比折扣价。例如: 所有花园式家具 85 折、所有榜单专辑 CD 9 折。购物车中每一件符合条件的产品均在原价中减去规定百分比的数额。
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
        
        DiscountRateDTO dto = new DiscountRateDTO();
        
        if(!StringUtil.isEmpty(constraintStr)){
            dto = super.readJson2Bean(constraintStr, DiscountRateDTO.class);
        }
        //为空创建 对象
        if(dto==null){
            dto= new DiscountRateDTO(); 
        }
        
        String msg=promTypeMsgResponseDTO.getFulfilMsg();
        promTypeMsgResponseDTO.setFulfilMsg(super.formatTipString(msg,dto.getDiscountRate()+PromConstants.PromSys.LIKE_PERCENT));
        
        return Boolean.TRUE;

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
        if (ordPromDetailDTO.isIfFulfillProm()) {
            
            if (!StringUtil.isEmpty(constraintStr)) {
                BigDecimal discountPrice = BigDecimal.ZERO;// 单价优惠 指优惠了多少单价
                BigDecimal discountMoney = BigDecimal.ZERO;// 优惠金额 指优惠了多少金额
                BigDecimal discountPriceMoney 	= BigDecimal.ZERO;// 除单价优惠金额外的优惠金额，不同于优惠金额discountMoney
                BigDecimal discountAmount 		= BigDecimal.ZERO;// 优惠数量 指优惠了多少数量，买X送Y专用
                BigDecimal discountCaclPrice  	= BigDecimal.ZERO;//促销优惠计算标准价：buyPrice、basePrice
                BigDecimal discountFinalPrice 	= BigDecimal.ZERO;//最终优惠后的价格，促销后最终的价格
                
                // 默认0
                BigDecimal discountRate = BigDecimal.ZERO;
                
                DiscountRateDTO dto = new DiscountRateDTO();
                dto = super.readJson2Bean(constraintStr, DiscountRateDTO.class);
                /*
                 * 购买其中任意产品，即可享受百分比折扣价。例如: 所有花园式家具 85 折、所有榜单专辑 CD 9 折。购物车中每一件符合条件的产品均在原价中减去规定百分比的数额。
                 */
                // 当前折扣价  9折 即90
                discountRate = dto.getDiscountRate();
                //开始计算价格
                discountCaclPrice = BigDecimal.valueOf(ordPromDetailDTO.getBasePrice());
                discountFinalPrice = discountCaclPrice.multiply(discountRate).divide(BigDecimal.valueOf(100));
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
        
        DiscountRateDTO dto = new DiscountRateDTO();
        
        try{
            dto = super.readJson2Bean(promDiscountRuleStr, DiscountRateDTO.class);
        }catch(Exception ex){
            LogUtil.error(MODULE, this.getClass().getName()+"转json格式数据 报错了  参数="+promDiscountRuleStr);
            throw new BusinessException("prom.400174");
         }
        
        if (dto == null) {
            // throw new BusinessException("请填写优惠规则！");
            throw new BusinessException("prom.400042");
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
    }
}
