package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdSendPointsDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCalRuleDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.NumberUtil;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
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
public class SendPointsDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl{

    private static final String MODULE = SendPointsDiscountRuleSVImpl.class.getName();

    @Resource
    private IPromQuerySV promQuerySV;
    
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
        
        if(promTypeMsgResponseDTO==null){
            //后续代码不需要跟踪验证空null 判断
            promTypeMsgResponseDTO=new PromTypeMsgResponseDTO();
        }
        // 订单满1000 送10分 或者 10倍积分
        OrdSendPointsDTO ordSendPointsDTO = new OrdSendPointsDTO();
        
        if(!StringUtil.isEmpty(constraintStr)){
               ordSendPointsDTO = super.readJson2Bean(constraintStr, OrdSendPointsDTO.class);
               
               if(ordSendPointsDTO==null){
                   ordSendPointsDTO = new OrdSendPointsDTO();
               }
        }
        String sendTypeName="";
        if(PromConstants.PromDiscountRule.SEND_POINTS_1.equals(ordSendPointsDTO.getSendType())){
            sendTypeName="倍数";
        }
        //提醒信息
        String msg=promTypeMsgResponseDTO.getFulfilMsg();
        
        promTypeMsgResponseDTO.setFulfilMsg(super.formatTipString(msg, NumberUtil.divideNum(ordSendPointsDTO.getOrderMoney(),null),ordSendPointsDTO.getSendAmount(),sendTypeName));
        //constraintStr 限制条件没有设置 默认满足
        if(StringUtil.isEmpty(constraintStr)){
            return Boolean.TRUE;
        }
        BigDecimal orderMoney = super.totalOrderMoneyByPromId(ordProDTO);
        
        if (orderMoney.compareTo(ordSendPointsDTO.getOrderMoney()) >= 0) {
            return Boolean.TRUE;
        }
        
        LogUtil.info(MODULE, "居然订单金额："+orderMoney +"不能满足促销配置："+ordSendPointsDTO.getOrderMoney());
        if(PromConstants.PromSys.IF_THROWS.equals(ifThrows)){
            String[] key=new String[1];
            key[0]=ordSendPointsDTO.getOrderMoney().toString();
            throw new BusinessException("prom.400112",key);
        }
       //未满足 提醒信息
        msg=promTypeMsgResponseDTO.getNoFulfilMsg();
        promTypeMsgResponseDTO.setNoFulfilMsg(super.formatTipString(msg, NumberUtil.divideNum(ordSendPointsDTO.getOrderMoney(),null),ordSendPointsDTO.getSendAmount(),sendTypeName));
        
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
        // 订单满1000 送积分
        OrdSendPointsDTO ordSendPointsDTO = new OrdSendPointsDTO();
        try{
            ordSendPointsDTO = super.readJson2Bean(promDiscountRuleStr,
                OrdSendPointsDTO.class);
        }catch(Exception ex){
            LogUtil.error(MODULE, this.getClass().getName()+"转json格式数据 报错了  参数="+promDiscountRuleStr);
            throw new BusinessException("prom.400174");
         }

        if (ordSendPointsDTO == null) {
            // throw new BusinessException("请填写优惠规则！");
            throw new BusinessException("prom.400042");
        }
        if (ordSendPointsDTO.getOrderMoney() == null) {
            // throw new BusinessException("订单金额不能为空！");
            throw new BusinessException("prom.400053");
        }
        if (ordSendPointsDTO.getOrderMoney().compareTo(BigDecimal.ZERO) <= 0) {
            // throw new BusinessException("订单金额必需大于零！");
            throw new BusinessException("prom.400054");
        }
        //赠送类型 必填   0 按照固定值赠送 1 按照指定积分的倍数赠送
        if (StringUtil.isEmpty(ordSendPointsDTO.getSendType())) {
            // throw new BusinessException("积分赠送类型不能为空");
            throw new BusinessException("prom.400131");
        }
        
        if (ordSendPointsDTO.getSendAmount() == null) {
            // throw new BusinessException("赠送积分值不能为空！");
            throw new BusinessException("prom.400132");
        }
        if (ordSendPointsDTO.getSendAmount().compareTo(BigDecimal.ZERO) <= 0) {
            // throw new BusinessException("赠送积分值必需大于零！");
            throw new BusinessException("prom.400133");
        }
        this.validTranlate(ordSendPointsDTO, promDTO);
       
    }
 
    /**
     * TODO赠送  积分实现
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
        
        OrdSendPointsDTO ordSendPointsDTO = new OrdSendPointsDTO();
        ordSendPointsDTO = super.readJson2Bean(jsonStr,
                OrdSendPointsDTO.class);
        
       //解析条件 并赋值 PromPresentDTO
        PromPresentDTO promPresentDTO=new PromPresentDTO();
        promPresentDTO.setPromId(promId);
        //按固定值 赠送积分
        if(PromConstants.PromDiscountRule.SEND_POINTS_0.equals(ordSendPointsDTO.getSendType())){
            promPresentDTO.setSendType(PromConstants.PromDiscountRule.SEND_POINTS_0);
            promPresentDTO.setPoints(ordSendPointsDTO.getSendAmount());
        }
         //按倍数赠送积分  倍数*订单金额
        if(PromConstants.PromDiscountRule.SEND_POINTS_1.equals(ordSendPointsDTO.getSendType())){
            
            promPresentDTO.setSendType(PromConstants.PromDiscountRule.SEND_POINTS_1);
            //系统配置参数
            BigDecimal multParam=BigDecimal.ZERO;
            BaseSysCfgRespDTO  sysDTO=SysCfgUtil.fetchSysCfg(PromConstants.PromKey.IF_PROM_POINTS_DEDUCT);
            //无此配置 默认0
            if(sysDTO!=null && !StringUtil.isEmpty(sysDTO.getParaValue())){
                multParam=new BigDecimal(sysDTO.getParaValue());
            }
            //赠送倍数
            BigDecimal mult=BigDecimal.ZERO;
            mult=ordSendPointsDTO.getSendAmount().subtract(multParam);
            //倍数为负数  默认为0
            if(mult.compareTo(BigDecimal.ZERO)<0){
                mult=BigDecimal.ZERO;
            }
            promPresentDTO.setPoints(mult);
        }
        return promPresentDTO;
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
                        OrdSendPointsDTO  dto=(OrdSendPointsDTO)commPromTypeDTO;
                        dto.setOrderMoney(NumberUtil.tranlateNum(dto.getOrderMoney().multiply(new BigDecimal(100))));
                        promDTO.setDiscountRule(JSON.toJSONString(dto));
                    }
             }
    }
}
