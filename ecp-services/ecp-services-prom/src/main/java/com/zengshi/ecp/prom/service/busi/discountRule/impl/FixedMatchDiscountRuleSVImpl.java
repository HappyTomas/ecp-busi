package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromMatchSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-9 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class FixedMatchDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl{

    private static final String MODULE = FixedMatchDiscountRuleSVImpl.class.getName();

    @Resource
    private IPromQuerySV promQuerySV;
    
    @Resource
    private IPromMatchSV promMatchSV;
    
    
    
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
        
        return Boolean.TRUE;
    }

    /**
     * TODO计算优惠信息  固定搭配设置值为价格优惠  没有优惠 默认为0
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
        
        PromMatchSkuDTO promMatchSkuDTO=new PromMatchSkuDTO();
        promMatchSkuDTO.setPromId(ordPromDetailDTO.getPromInfoDTO().getId());
        promMatchSkuDTO.setGdsId(ordPromDetailDTO.getGdsId());
        promMatchSkuDTO.setSkuId(ordPromDetailDTO.getSkuId());
        promMatchSkuDTO.setStatus(PromConstants.PromMatchSku.STATUS_1);
        promMatchSkuDTO.setMatchType(PromConstants.PromMatchSku.MATCH_TYPE_2);//固定搭配
        promMatchSkuDTO.setPromStatus(PromConstants.PromInfo.STATUS_10);//生效
        
        List<PromMatchSkuDTO> list=promMatchSV.queryMatchSkuList(promMatchSkuDTO);
        
        PromDiscountDTO promDiscountDTO = new PromDiscountDTO();
        
        BigDecimal discountPrice = BigDecimal.ZERO;// 单价优惠 指优惠了多少单价
        BigDecimal discountMoney = BigDecimal.ZERO;// 优惠金额 指优惠了多少金额
        BigDecimal discountPriceMoney 	= BigDecimal.ZERO;// 除单价优惠金额外的优惠金额，不同于优惠金额discountMoney
        BigDecimal discountAmount 		= BigDecimal.ZERO;// 优惠数量 指优惠了多少数量，买X送Y专用
        BigDecimal discountCaclPrice  	= BigDecimal.ZERO;//促销优惠计算标准价：buyPrice、basePrice
        BigDecimal discountFinalPrice 	= BigDecimal.ZERO;//最终优惠后的价格，促销后最终的价格
        
        //开始计算价格
        discountCaclPrice = BigDecimal.valueOf(ordPromDetailDTO.getBasePrice());
        
        discountFinalPrice= BigDecimal.valueOf(ordPromDetailDTO.getBuyPrice());
        
        if(CollectionUtils.isEmpty(list)){
            //优惠了价格
            discountPrice = BigDecimal.ZERO;
        }else{
            //促销单价
        	discountFinalPrice = BigDecimal.valueOf(list.get(0).getPrice());
        	//优惠了单价
        	discountPrice = discountCaclPrice.subtract(discountFinalPrice);
       }
        //优惠了金额
        discountMoney = BigDecimal.valueOf(ordPromDetailDTO.getOrderAmount()).multiply(discountPrice);
        
        promDiscountDTO.setDiscountPrice(discountPrice);
        promDiscountDTO.setDiscountMoney(discountMoney);
        promDiscountDTO.setDiscountCaclPrice(discountCaclPrice);
        promDiscountDTO.setDiscountFinalPrice(discountFinalPrice);
        
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
     * TODO 产品固定搭配
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#valid(java.lang.String, com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDiscountRuleStr
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public void valid(String promDiscountRuleStr,PromDTO promDTO) throws BusinessException {
        
        // 产品固定搭配
        //没有录入 搭配商品信息哦
        if(CollectionUtils.isEmpty(promDTO.getMatchSkuDTOList())){
            throw new BusinessException("prom.400142");
        }else{
            //搭配录入数量 非空、非有效数字
            for(PromMatchSkuDTO promMatchSkuDTO:promDTO.getMatchSkuDTOList()){
                //非空验证
                if(StringUtil.isEmpty(promMatchSkuDTO.getPromCnt())){
                    throw new BusinessException("prom.400143");
                }
                //数字 有效性验证
                if(!StringUtils.isNumeric(promMatchSkuDTO.getPromCnt().toString())){
                    throw new BusinessException("prom.400144");
                }
                //大于等于0
                if(promMatchSkuDTO.getPromCnt().compareTo(new Long(0))<=0){
                    throw new BusinessException("prom.400145");
                }
                
                //非空验证
                if(StringUtil.isEmpty(promMatchSkuDTO.getPrice())){
                    throw new BusinessException("prom.400146");
                }
                //数字 有效性验证
                if(!StringUtils.isNumeric(promMatchSkuDTO.getPrice().toString())){
                    throw new BusinessException("prom.400147");
                }
                //大于等于0
                if(promMatchSkuDTO.getPrice().compareTo(new Long(0))<=0){
                    throw new BusinessException("prom.400148");
                }
            }
        }
    }
}
