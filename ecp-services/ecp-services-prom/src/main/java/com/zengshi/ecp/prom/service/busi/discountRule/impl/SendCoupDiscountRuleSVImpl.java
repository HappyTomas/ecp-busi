package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;
import javax.annotation.Resource;

import com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdSendCoupDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCalRuleDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
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
public class SendCoupDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl{

    private static final String MODULE = SendCoupDiscountRuleSVImpl.class.getName();

    @Resource
    private IPromQuerySV promQuerySV;
    
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
        // 订单满1000 送1张 XX优惠券
        OrdSendCoupDTO ordSendCoupPromTypeDTO = new OrdSendCoupDTO();
        String orderMoney1="";
        String sendY="";
        String msg=  msg=promTypeMsgResponseDTO.getFulfilMsg();
        
        if(!StringUtil.isEmpty(constraintStr)){
               ordSendCoupPromTypeDTO = super.readJson2Bean(constraintStr, OrdSendCoupDTO.class);
               if(ordSendCoupPromTypeDTO!=null){
                   orderMoney1=ordSendCoupPromTypeDTO.getOrderMoney().toString();
                   sendY=ordSendCoupPromTypeDTO.getSendAmount().toString();
               }
        }
        //满足提醒
        promTypeMsgResponseDTO.setFulfilMsg(super.formatTipString(msg, NumberUtil.divideNum(new BigDecimal(orderMoney1),null),sendY));
        //constraintStr 限制条件没有设置 默认满足
        if(StringUtil.isEmpty(constraintStr)){
            return Boolean.TRUE;
        }
        
        BigDecimal orderMoney = super.totalOrderMoneyByPromId(ordProDTO);
       
        if (orderMoney.compareTo(ordSendCoupPromTypeDTO.getOrderMoney()) >= 0) {
            return Boolean.TRUE;
        }
        
        LogUtil.debug(MODULE, "订单金额="+orderMoney +"促销配置金额="+ordSendCoupPromTypeDTO.getOrderMoney());
        
        if(PromConstants.PromSys.IF_THROWS.equals(ifThrows)){
            String[] key=new String[1];
            key[0]=ordSendCoupPromTypeDTO.getOrderMoney().toString();
            throw new BusinessException("prom.400112",key);
        }
        //未满足提醒
        msg=promTypeMsgResponseDTO.getNoFulfilMsg();
        promTypeMsgResponseDTO.setNoFulfilMsg(super.formatTipString(msg, NumberUtil.divideNum(new BigDecimal(orderMoney1),null),sendY));
        
        return Boolean.FALSE;

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
     * TODO促销信息录入-优惠规则验证  例如：订单满XX元 送YY券 验证规则
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
        // 订单满1000 送1张 XX优惠券
        OrdSendCoupDTO ordSendCoupPromTypeDTO = new OrdSendCoupDTO();
        try{
            ordSendCoupPromTypeDTO = super.readJson2Bean(promDiscountRuleStr,
                OrdSendCoupDTO.class);

        }catch(Exception ex){
            LogUtil.error(MODULE, this.getClass().getName()+"转json格式数据 报错了  参数="+promDiscountRuleStr);
            throw new BusinessException("prom.400174");
         }
        if (ordSendCoupPromTypeDTO == null) {
            // throw new BusinessException("请填写优惠规则！");
            throw new BusinessException("prom.400042");
        }
        if (ordSendCoupPromTypeDTO.getOrderMoney() == null) {
            // throw new BusinessException("订单金额不能为空！");
            throw new BusinessException("prom.400053");
        }
        if (ordSendCoupPromTypeDTO.getOrderMoney().compareTo(BigDecimal.ZERO) <= 0) {
            // throw new BusinessException("订单金额必需大于零！");
            throw new BusinessException("prom.400054");
        }
        if (ordSendCoupPromTypeDTO.getSendAmount() == null) {
            // throw new BusinessException("优惠券张数不能为空！");
            throw new BusinessException("prom.400055");
        }
        if (ordSendCoupPromTypeDTO.getSendAmount().compareTo(BigDecimal.ZERO) <= 0) {
            // throw new BusinessException("优惠券张数必需大于零！");
            throw new BusinessException("prom.400056");
        }
        if (StringUtil.isEmpty(ordSendCoupPromTypeDTO.getCoupId())) {
            // throw new BusinessException("请选择优惠券！");
            throw new BusinessException("prom.400057");
        }
        //转换金额为分 
        this.validTranlate(ordSendCoupPromTypeDTO,promDTO);
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
                        OrdSendCoupDTO  dto=(OrdSendCoupDTO)commPromTypeDTO;
                        dto.setOrderMoney(NumberUtil.tranlateNum(dto.getOrderMoney().multiply(new BigDecimal(100))));
                        promDTO.setDiscountRule(JSON.toJSONString(dto));
                    }
             }
        
    }
    /**
     * TODO赠送 优惠券实现
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#promPresent(java.lang.Long, com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO)
     * @param promId
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public PromPresentDTO promPresent(Long promId,OrdPromDTO ordPromDTO) throws BusinessException{
        
       //通过promId 获得json条件  
        PromCalRuleDTO promCalRuleDTO=promQuerySV.queryPromCalRule(promId);
        
        String jsonStr=null;
        if(promCalRuleDTO!=null){
            jsonStr=promCalRuleDTO.getCalStr();
        }
        if(StringUtil.isEmpty(jsonStr)){
            return null;
        }
        
        OrdSendCoupDTO ordSendCoupPromTypeDTO = new OrdSendCoupDTO();
        ordSendCoupPromTypeDTO = super.readJson2Bean(jsonStr,
                OrdSendCoupDTO.class);
        
       //解析条件 并赋值 PromPresentDTO
        PromPresentDTO promPresentDTO=new PromPresentDTO();
        promPresentDTO.setCoupId(ordSendCoupPromTypeDTO.getCoupId());//优惠券编码
        promPresentDTO.setPoints(BigDecimal.ZERO);//积分
        promPresentDTO.setPromId(promId);//促销编码
        promPresentDTO.setSendAmount(ordSendCoupPromTypeDTO.getSendAmount());//赠送数量
        
        return promPresentDTO;
    }
}
