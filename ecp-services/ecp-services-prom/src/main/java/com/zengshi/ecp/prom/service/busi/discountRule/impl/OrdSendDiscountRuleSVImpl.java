package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGiftRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsGiftRSV;
import com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdSendDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdSendPointsDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCalRuleDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
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
 * Date: 2016-01-25 <br>
 * Copyright (c) 2016 AI <br>
 * 
 * @author huangjx
 */
public class OrdSendDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl{

    private static final String MODULE = OrdSendDiscountRuleSVImpl.class.getName();

    @Resource
    private IPromQuerySV promQuerySV;
    
    @Resource
    private IGdsGiftRSV gdsGiftRSV;
    
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
        // 订单满1000 送礼品
        OrdSendDiscountDTO dto = new OrdSendDiscountDTO();
        String orderMoney1="";
        //非空 获得对象值
        if(!StringUtil.isEmpty(constraintStr)){
            dto = super.readJson2Bean(constraintStr, OrdSendDiscountDTO.class);
            if(dto!=null){
                orderMoney1=dto.getOrderMoney().toString();
            }
        }
        
        String msg=promTypeMsgResponseDTO.getFulfilMsg();
        //满足提醒信息
        promTypeMsgResponseDTO.setFulfilMsg(super.formatTipString(msg, NumberUtil.divideNum(new BigDecimal(orderMoney1),null)));
        //constraintStr 限制条件没有设置 默认满足
        if(StringUtil.isEmpty(constraintStr)){
           
            return Boolean.TRUE;
        }
        BigDecimal orderMoney = super.totalOrderMoneyByPromId(ordProDTO);
        
        if (orderMoney.compareTo(dto.getOrderMoney()) >= 0) {
            return Boolean.TRUE;
        }
        
        LogUtil.info(MODULE, "居然订单金额："+orderMoney +"不能满足促销配置金额："+dto.getOrderMoney());
        if(PromConstants.PromSys.IF_THROWS.equals(ifThrows)){
            String[] key=new String[1];
            key[0]=dto.getOrderMoney().toString();
            throw new BusinessException("prom.400112",key);
        }
        //未满足 提醒信息
        msg=promTypeMsgResponseDTO.getNoFulfilMsg();
        promTypeMsgResponseDTO.setNoFulfilMsg(super.formatTipString(msg, NumberUtil.divideNum(new BigDecimal(orderMoney1),null)));
        
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
        return Boolean.TRUE;
    }
     /**
      * 赠送积分
     * @param promDiscountRuleStr
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
     private void validPoints(String promDiscountRuleStr,PromDTO promDTO) throws BusinessException {
        
        //限制条件没有设置 默认满足
        if(StringUtil.isEmpty(promDiscountRuleStr)){
            return ;
        }
        // 订单满1000 送积分
        OrdSendDiscountDTO ordSendPointsDTO = new OrdSendDiscountDTO();
        try{
            ordSendPointsDTO = super.readJson2Bean(promDiscountRuleStr,
                    OrdSendDiscountDTO.class);
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
        if (!StringUtil.isEmpty(ordSendPointsDTO.getSendType())) {
            if (ordSendPointsDTO.getSendAmount() == null) {
                // throw new BusinessException("赠送积分值不能为空！");
                throw new BusinessException("prom.400132");
            }
            if (ordSendPointsDTO.getSendAmount().compareTo(BigDecimal.ZERO) <= 0) {
                // throw new BusinessException("赠送积分值必需大于零！");
                throw new BusinessException("prom.400133");
            }
        }
        
        if (ordSendPointsDTO.getSendAmount() != null) {
            if (ordSendPointsDTO.getSendAmount().compareTo(BigDecimal.ZERO) <= 0) {
                // throw new BusinessException("赠送积分值必需大于零！");
                throw new BusinessException("prom.400133");
            }
            //积分赠送值 非空  那么赠送类型必填
            if (StringUtil.isEmpty(ordSendPointsDTO.getSendType())) {
                // throw new BusinessException("赠送积分类型必填");
                throw new BusinessException("prom.400209");
            }
        }
        
        this.validTranlate(ordSendPointsDTO, promDTO);
       
    }
    /**
     * 赠品信息验证
     * @param promDiscountRuleStr
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    private void validGift(String promDiscountRuleStr,PromDTO promDTO) throws BusinessException {
        
        //限制条件没有设置 默认满足
        if(StringUtil.isEmpty(promDiscountRuleStr)){
            return ;
        }
        // 订单满1000 送礼品
        OrdSendDiscountDTO dto = new OrdSendDiscountDTO();
        try{
            dto = super.readJson2Bean(promDiscountRuleStr,
                OrdSendDiscountDTO.class);
        }catch(Exception ex){
            LogUtil.error(MODULE, this.getClass().getName()+"转json格式数据 报错了  参数="+promDiscountRuleStr);
            throw new BusinessException("prom.400174");
         }
        if (dto == null) {
            // throw new BusinessException("请填写优惠规则！");
            throw new BusinessException("prom.400042");
        }
        if (dto.getOrderMoney() == null) {
            // throw new BusinessException("订单金额不能为空！");
            throw new BusinessException("prom.400053");
        }
        if (dto.getOrderMoney().compareTo(BigDecimal.ZERO) <= 0) {
            // throw new BusinessException("订单金额必需大于零！");
            throw new BusinessException("prom.400054");
        }
        //没有录入赠品信息哦
        if(CollectionUtils.isEmpty(promDTO.getGiftList())){
            //throw new BusinessException("prom.400120");
        }else{
            //赠品录入数量 非空、非有效数字
            //是否允许超库存待定 待实现 可后期开发
            for(PromGiftDTO giftDto:promDTO.getGiftList()){
                //非空验证
                if(StringUtil.isEmpty(giftDto.getPresentAllCnt())){
                    throw new BusinessException("prom.400121");
                }
                //数字 有效性验证
                if(!StringUtils.isNumeric(giftDto.getPresentAllCnt().toString())){
                    throw new BusinessException("prom.400122");
                }
                //大于等于0
                if(giftDto.getPresentAllCnt().compareTo(new Long(0))<=0){
                    throw new BusinessException("prom.400123");
                }
                
                //非空验证
                if(StringUtil.isEmpty(giftDto.getEveryTimeCnt())){
                    throw new BusinessException("prom.400139");
                }
                //数字 有效性验证
                if(!StringUtils.isNumeric(giftDto.getEveryTimeCnt().toString())){
                    throw new BusinessException("prom.400140");
                }
                //大于等于0
                if(giftDto.getEveryTimeCnt().compareTo(new Long(0))<=0){
                    throw new BusinessException("prom.400141");
                }
                
            }
            //验证赠品有效性  无效抛出错误
            for(PromGiftDTO giftDto:promDTO.getGiftList()){
                GdsGiftReqDTO gdsGiftReqDTO=new GdsGiftReqDTO();
                gdsGiftReqDTO.setId(giftDto.getGiftId());
                GdsGiftRespDTO gdsGiftRespDTO= gdsGiftRSV.querySingleGiftInfo(gdsGiftReqDTO);
                //赠品无效 抛出错误
                if(gdsGiftRespDTO==null || "0".equals(gdsGiftRespDTO.getGiftStatus())){
                    String[] keys={giftDto.getGiftId().toString()};
                    throw new BusinessException("prom.400175",keys);
                }
            }
            
        }
        this.validTranlate(dto, promDTO);
    
    }
    /**
     * TODO礼品赠送验证规则
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#valid(java.lang.String, com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDiscountRuleStr
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public void valid(String promDiscountRuleStr,PromDTO promDTO) throws BusinessException {
        this.validGift(promDiscountRuleStr, promDTO);
        this.validPoints(promDiscountRuleStr, promDTO);
    }
   
    /**
     * 赠送积分
     * @param promId
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    private PromPresentDTO promPresentPoints(Long promId,OrdPromDTO ordPromDTO) throws BusinessException{
        
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
     * 赠送赠品
     * @param promId
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
     private PromPresentDTO promPresentGift(Long promId,OrdPromDTO ordPromDTO) throws BusinessException{
        
        //赠品列表
        List<PromGiftDTO> dtoList= promQuerySV.queryPromGift(promId);
        
        if(CollectionUtils.isEmpty(dtoList)){
            return null;
        }
        
        PromPresentDTO dto=new PromPresentDTO();
        dto.setPromGiftDTOList(dtoList);
        
        return dto;
    }
    /**
     * TODO赠品列表实现
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#promPresent(java.lang.Long, com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO)
     * @param promId
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public PromPresentDTO promPresent(Long promId,OrdPromDTO ordPromDTO) throws BusinessException{
        
        PromPresentDTO p1=this.promPresentPoints(promId, ordPromDTO);
        PromPresentDTO p2=this.promPresentGift(promId, ordPromDTO);
        PromPresentDTO dto=new PromPresentDTO();
        dto.setPromId(promId);
        if(p1!=null && StringUtil.isNotBlank(p1.getSendType()) && p1.getPoints()!=null){
            dto.setPoints(p1.getPoints());
            dto.setSendType(p1.getSendType());
        }
        if(p2!=null && !CollectionUtils.isEmpty(p2.getPromGiftDTOList())){
            dto.setPromGiftDTOList(p2.getPromGiftDTOList());
        }
        return dto;
    }
    /**
     * 赠品列表
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public  List<PromGiftDTO> promGiftList(Long promId) throws BusinessException{
        //赠品列表
        List<PromGiftDTO> dtoList= promQuerySV.queryPromGift(promId);
        return dtoList;
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
                        OrdSendDiscountDTO  dto=(OrdSendDiscountDTO)commPromTypeDTO;
                        dto.setOrderMoney(NumberUtil.tranlateNum(dto.getOrderMoney().multiply(new BigDecimal(100))));
                        promDTO.setDiscountRule(JSON.toJSONString(dto));
                    }
             }
    }
}