package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.prom.dao.mapper.busi.PromSkuMapper;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dao.model.PromSkuCriteria;
import com.zengshi.ecp.prom.dao.model.PromSkuCriteria.Criteria;

import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MoneyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class SeckillDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl {

    private static final String MODULE = SeckillDiscountRuleSVImpl.class.getName();
    @Resource
    private PromSkuMapper promSkuMapper;

    @Override
    public boolean isFulFilPromByGds(OrdPromDTO ordProDTO, OrdPromDetailDTO ordPromDetailDTO,
            String constraintStr,String ifThrows,PromTypeMsgResponseDTO promTypeMsgResponseDTO) throws BusinessException {
        
        if(promTypeMsgResponseDTO==null){
            //后续代码不需要跟踪验证空null 判断
            promTypeMsgResponseDTO=new PromTypeMsgResponseDTO();
        }

        String msg=promTypeMsgResponseDTO.getFulfilMsg();
        promTypeMsgResponseDTO.setFulfilMsg(super.formatTipString(msg));
        
        return Boolean.TRUE;

    }

    @Override
    public PromDiscountDTO calculatePromotion(OrdPromDTO ordProDTO,
            OrdPromDetailDTO ordPromDetailDTO, String constraintStr) throws BusinessException {
        
        PromDiscountDTO promDiscountDTO = new PromDiscountDTO();
        BigDecimal discountPrice = BigDecimal.ZERO;// 单价优惠 指优惠了多少单价
        BigDecimal discountMoney = BigDecimal.ZERO;// 优惠金额 指优惠了多少金额
        BigDecimal discountPriceMoney   = BigDecimal.ZERO;// 除单价优惠金额外的优惠金额，不同于优惠金额discountMoney
        BigDecimal discountAmount       = BigDecimal.ZERO;// 优惠数量 指优惠了多少数量，买X送Y专用
        BigDecimal discountCaclPrice    = BigDecimal.ZERO;//促销优惠计算标准价：buyPrice、basePrice
        BigDecimal discountFinalPrice   = BigDecimal.ZERO;//最终优惠后的价格，促销后最终的价格
        
        //判断是否到秒杀计算时间，在这里不判断了,能进来，说明已经满足
        
        //根据单品与促销编码取秒杀价配置
        PromSkuCriteria criteria = new PromSkuCriteria();
        Criteria condition = criteria.createCriteria();
        condition.andPromIdEqualTo(ordPromDetailDTO.getPromInfoDTO().getId());
        condition.andShopIdEqualTo(ordPromDetailDTO.getShopId());
        condition.andGdsIdEqualTo(ordPromDetailDTO.getGdsId());
        condition.andSkuIdEqualTo(ordPromDetailDTO.getSkuId());
        List<PromSku> records = null;
        try {
            records = promSkuMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, e.getMessage());
        }
        if (CollectionUtils.isNotEmpty(records)) {
            PromSku priceRule = records.get(0);
            discountCaclPrice = BigDecimal.valueOf(ordPromDetailDTO.getBasePrice());
            switch (priceRule.getPriceType()) {
            case PromConstants.PromDiscountRule.SECKILL_PRICE_TYPE_0:
                BigDecimal discountRate = BigDecimal.valueOf(priceRule.getPriceValue()).divide(BigDecimal.valueOf(100.00)).divide(BigDecimal.valueOf(100.00));
                discountFinalPrice = BigDecimal.valueOf(ordPromDetailDTO.getBasePrice()).multiply(discountRate);
                break;
            case PromConstants.PromDiscountRule.SECKILL_PRICE_TYPE_1:
                discountFinalPrice = BigDecimal.valueOf(priceRule.getPriceValue());
                break;
            default:
                discountFinalPrice = BigDecimal.valueOf(ordPromDetailDTO.getBuyPrice());
                break;
            }
            discountPrice = discountCaclPrice.subtract(discountFinalPrice);
            discountMoney = BigDecimal.valueOf(ordPromDetailDTO.getOrderAmount()).multiply(discountPrice);

        }
        else{
            discountCaclPrice = BigDecimal.valueOf(ordPromDetailDTO.getBasePrice());
            discountFinalPrice = BigDecimal.valueOf(ordPromDetailDTO.getBuyPrice());
        }
        promDiscountDTO.setDiscountPrice(discountPrice);
        promDiscountDTO.setDiscountMoney(discountMoney);
        promDiscountDTO.setDiscountCaclPrice(discountCaclPrice);
        promDiscountDTO.setDiscountFinalPrice(discountFinalPrice);
        
        return promDiscountDTO;
    }

    @Override
    public boolean needToVerified(PromDTO promDTO) throws BusinessException {
        // 需要验证
        return Boolean.FALSE;
    }
}
