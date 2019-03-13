package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.prom.dubbo.dto.BindDTO;
import com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.NumberUtil;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromMatchSV;
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
public class BindDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl {

    private static final String MODULE = BindDiscountRuleSVImpl.class.getName();

    
    @Resource
    private IPromMatchSV promMatchSV;
    /**
     * TODO 是否满足优惠规则 (单品促销类型)  待实现？？
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
        // 捆绑
        
        // ordvo 需要同时包含两个 n 产品 (前面如果满足捆绑验证 到此处应该是符合标准)
        return Boolean.TRUE;
    }

    /**
     * TODO计算优惠信息 捆绑类型促销
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
          
          PromMatchSkuDTO promMatchSkuDTO=new PromMatchSkuDTO();
          promMatchSkuDTO.setPromId(ordPromDetailDTO.getPromInfoDTO().getId());
          promMatchSkuDTO.setGdsId(ordPromDetailDTO.getGdsId());
          promMatchSkuDTO.setSkuId(ordPromDetailDTO.getSkuId());
          promMatchSkuDTO.setStatus(PromConstants.PromMatchSku.STATUS_1);
          promMatchSkuDTO.setPromStatus(PromConstants.PromInfo.STATUS_10);//生效
          
          List<PromMatchSkuDTO> list=promMatchSV.queryMatchSkuList(promMatchSkuDTO);
          
          
          if (ordPromDetailDTO.isIfFulfillProm()) {
        	  
            // 满足促销优惠规则
            if (!StringUtil.isEmpty(constraintStr)) {
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
//                    promDiscountDTO.setDiscountPrice(BigDecimal.ZERO);
                    discountPrice = BigDecimal.ZERO;
                }else{
                    //促销单价
                    discountFinalPrice = BigDecimal.valueOf(list.get(0).getPrice());
                    //优惠了价格
                    discountPrice = discountCaclPrice.subtract(discountFinalPrice);
                }
                //优惠了金额
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
     * TODO促销信息录入-优惠规则验证 捆绑类型验证规则
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#valid(java.lang.String, com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDiscountRuleStr
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public void valid(String promDiscountRuleStr,PromDTO promDTO) throws BusinessException {
        
        // 捆绑类型
        BindDTO bundleDTO = new BindDTO();
        try{
            bundleDTO = super.readJson2Bean(promDiscountRuleStr, BindDTO.class);
        
          }catch(Exception ex){
            LogUtil.error(MODULE, this.getClass().getName()+"转json格式数据 报错了  参数="+promDiscountRuleStr);
            throw new BusinessException("prom.400174");
         }
        
        if (bundleDTO == null) {
            // throw new BusinessException("请填写优惠规则！");
            throw new BusinessException("prom.400042");
        }
        if (bundleDTO.getBindPrice() == null) {
            // throw new BusinessException("捆绑价格不能为空！");
            throw new BusinessException("prom.400043");
        }
        if (bundleDTO.getBindPrice().compareTo(BigDecimal.ZERO) <= 0) {
            // throw new BusinessException("捆绑价格必需大于零！");
            throw new BusinessException("prom.400044");
        }
        
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
            }
            this.valid(promDTO);
        }
      this.validTranlate(bundleDTO, promDTO);
    }
    /**
     * 验证搭配商品 和选择商品 必需不同
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void valid(PromDTO promDTO) throws BusinessException {
           
          if(!CollectionUtils.isEmpty(promDTO.getSkuList())){
               //搭配商品
              if(!CollectionUtils.isEmpty(promDTO.getMatchSkuDTOList())){
                  
                  for(PromMatchSkuDTO promMatchSkuDTO:promDTO.getMatchSkuDTOList()){
                      //促销商品
                      for(PromSkuDTO promSkuDTO:(List<PromSkuDTO>)promDTO.getSkuList()){
                                if (  promMatchSkuDTO.getSkuId().equals(promSkuDTO.getSkuId())){
                                    //单品一致
                                    String[] key=new String[1];
                                    key[0]=promMatchSkuDTO.getSkuId().toString();
                                    throw new BusinessException("prom.400150",key);
                                }
                        }
                  }
              }
          }
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
                        BindDTO  dto=(BindDTO)commPromTypeDTO;
                        dto.setBindPrice(NumberUtil.tranlateNum(dto.getBindPrice().multiply(new BigDecimal(100))));
                        promDTO.setDiscountRule(JSON.toJSONString(dto));
                    }
             }
    }
}
