package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.prom.dubbo.dto.BuyXSendYDTO;
import com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class BuyXSendYDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl {

    private static final String MODULE = BuyXSendYDiscountRuleSVImpl.class.getName();

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
        
        BuyXSendYDTO buyXSendYPromTypeDTO = new BuyXSendYDTO();
        
        //提醒信息
        String msg="";
        //json信息
        if (!StringUtil.isEmpty(constraintStr)) {
            buyXSendYPromTypeDTO = super.readJson2Bean(constraintStr, BuyXSendYDTO.class);
            if(buyXSendYPromTypeDTO==null){
                buyXSendYPromTypeDTO=new BuyXSendYDTO(); 
            }
        }
        //满足提醒信息
         msg=promTypeMsgResponseDTO.getFulfilMsg();
        //活动各个商品购满%s件， 即可享受满减
        promTypeMsgResponseDTO.setFulfilMsg(super.formatTipString(msg, buyXSendYPromTypeDTO.getBuyX()));
        if (StringUtil.isEmpty(constraintStr)) {
            return Boolean.TRUE;
        }
        // buy X send Y
        if (BigDecimal.valueOf(ordPromDetailDTO.getOrderAmount()).compareTo(buyXSendYPromTypeDTO.getBuyX()) >= 0) {
            // 单品购买数量大于等于 配置值时 满足规则 否则不满足
            return Boolean.TRUE;
        }
        
        LogUtil.debug(MODULE, "下单购买数量：ordPromDetailDTO.getOrderAmount()"+ordPromDetailDTO.getOrderAmount()+"促销配置量"+buyXSendYPromTypeDTO.getBuyX());
        
        if(PromConstants.PromSys.IF_THROWS.equals(ifThrows)){
            String[] key=new String[1];
            key[0]=buyXSendYPromTypeDTO.getBuyX().toString();
            throw new BusinessException("prom.400111",key);
        }
        //未满足 提醒信息
         msg=promTypeMsgResponseDTO.getNoFulfilMsg();
        //亲！赶紧的，满%s件减%s件。您还差%s件。
        BigDecimal needMore =  new BigDecimal(buyXSendYPromTypeDTO.getBuyX().longValue() - ordPromDetailDTO.getOrderAmount());
        promTypeMsgResponseDTO.setNoFulfilMsg(super.formatTipString(msg, buyXSendYPromTypeDTO.getBuyX(),buyXSendYPromTypeDTO.getSendY(), needMore));
           
        return Boolean.FALSE;

    }

    /**
     * TODO计算优惠信息 满X减 Y件
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
                BuyXSendYDTO buyXSendYDTO = new BuyXSendYDTO();
                buyXSendYDTO = super.readJson2Bean(constraintStr, BuyXSendYDTO.class);
                //开始计算价格
                // 当前购物车满足 返回优惠金额
                discountMoney = buyXSendYDTO.getSendY().multiply(BigDecimal.valueOf(ordPromDetailDTO.getBuyPrice()));// 优惠金额 指优惠了多少金额
                discountAmount = buyXSendYDTO.getSendY();// 优惠数量 指优惠了多少数量，买X送Y专用
                discountCaclPrice = BigDecimal.valueOf(ordPromDetailDTO.getBuyPrice());//促销优惠计算标准价：buyPrice、basePrice
                discountFinalPrice = BigDecimal.valueOf(ordPromDetailDTO.getBuyPrice());//最终优惠后的价格，促销后最终的价格
                
                promDiscountDTO.setDiscountPrice(discountPrice);
                promDiscountDTO.setDiscountMoney(discountMoney);
                promDiscountDTO.setDiscountPriceMoney(discountPriceMoney);
                promDiscountDTO.setDiscountAmount(discountAmount);
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
     * TODO促销信息录入-优惠规则验证 满X减 Y件验证规则
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
        // 满X减 Y件
        BuyXSendYDTO buyXSendYDTO = new BuyXSendYDTO();
        try{
            buyXSendYDTO = super.readJson2Bean(promDiscountRuleStr, BuyXSendYDTO.class);
        }catch(Exception ex){
            LogUtil.error(MODULE, this.getClass().getName()+"转json格式数据 报错了  参数="+promDiscountRuleStr);
            throw new BusinessException("prom.400174");
         }
        if (buyXSendYDTO == null) {
            // throw new BusinessException("请填写优惠规则！");
            throw new BusinessException("prom.400045");
        }
        if (buyXSendYDTO.getBuyX() == null) {
            // throw new BusinessException("购买数量不能为空！");
            throw new BusinessException("prom.400046");
        }
        if (buyXSendYDTO.getBuyX().compareTo(BigDecimal.ZERO) <= 0) {
            // throw new BusinessException("购买数量必需大于零！");
            throw new BusinessException("prom.400047");
        }
        if (buyXSendYDTO.getSendY() == null) {
            // throw new BusinessException("免费数量不能为空！");
            throw new BusinessException("prom.400048");
        }
        if (buyXSendYDTO.getSendY().compareTo(BigDecimal.ZERO) <= 0) {
            // throw new BusinessException("免费数量必需大于零！");
            throw new BusinessException("prom.400049");
        }
        if (buyXSendYDTO.getSendY().compareTo(buyXSendYDTO.getBuyX()) >=0) {
            // throw new BusinessException("免费数量小于购买数量！");
            throw new BusinessException("prom.400172");
        }
        
        this.validTranlate(buyXSendYDTO, promDTO);
    }
    /**
     * 验证 购买数量和促销数量
     * @param buyXSendYDTO
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public void validTranlate(CommPromTypeDTO commPromTypeDTO,PromDTO promDTO) throws BusinessException {
        
        if(commPromTypeDTO==null){
            return ;
        }
        if(promDTO==null){
            return ;
        }
        if(!CollectionUtils.isEmpty(promDTO.getSkuList())){
            List<PromSkuDTO>  list=(List<PromSkuDTO>)promDTO.getSkuList();
            BuyXSendYDTO buyXSendYDTO=(BuyXSendYDTO)commPromTypeDTO;
              for (PromSkuDTO dto:list){
                    if(StringUtil.isEmpty(dto.getPromCnt())){
                        continue;
                    }
                    if(buyXSendYDTO.getBuyX().compareTo(new BigDecimal(dto.getPromCnt()))>0){
                        //促销数量 不能小于购买数量
                        String[] keys=new String[1];
                        keys[0]=buyXSendYDTO.getBuyX().toString();
                        throw new BusinessException("prom.400162",keys);
                    }
              }
        }
    }
}
