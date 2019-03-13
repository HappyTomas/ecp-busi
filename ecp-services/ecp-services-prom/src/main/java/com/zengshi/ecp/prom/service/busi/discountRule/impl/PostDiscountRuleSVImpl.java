package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;

import com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPostDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromShipDTO;
import com.zengshi.ecp.prom.dubbo.util.NumberUtil;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-29 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PostDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl {

    private static final String MODULE = PostDiscountRuleSVImpl.class.getName();
    /**
     * TODO 是否免邮 
     * @see com.zengshi.ecp.prom.service.busi.discountRule.interfaces.IPromDiscountRuleSV#ifFreePost(com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO, com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO, java.lang.String, java.lang.String)
     * @param ordPromDTO
     * @param ordPromDetailDTO
     * @param constraintStr
     * @param ifThrows
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean ifFreePost(OrdPromDTO ordPromDTO, OrdPromDetailDTO ordPromDetailDTO,
            String constraintStr,String ifThrows,PromShipDTO promShipDTO) throws BusinessException{
        //1 满足金额
        //2 满足区域
        OrdPostDTO ordPostDTO = new OrdPostDTO();
        //直接满足
        if (!StringUtil.isEmpty(constraintStr)) {
            ordPostDTO = super.readJson2Bean(constraintStr, OrdPostDTO.class);
            if(ordPostDTO==null){
                ordPostDTO = new OrdPostDTO();
            }
        }
        //获得订单金额 
        BigDecimal orderMoney =BigDecimal.ZERO;
        if(ordPromDTO.getOrderMoney()==null){
            ordPromDTO.setOrderMoney(0l);
        }
        orderMoney=new BigDecimal(ordPromDTO.getOrderMoney());
        
        if(orderMoney.compareTo(ordPostDTO.getOrderMoney())>=0){
            //全选 表示全部满足
            if("1".equals(ordPostDTO.getIfSelectAll())){
                return Boolean.TRUE;
            }
            //如果选中除外区域
            if("1".equals(ordPostDTO.getPostAreaExclude())){
                if(!StringUtil.isEmpty(ordPostDTO.getPostAreaCode())){
                    //省份 为空
                    if(StringUtil.isEmpty(promShipDTO.getProvinceCode())){
                        if(PromConstants.PromSys.IF_THROWS.equals(ifThrows)){
                            throw new BusinessException("prom.400169");
                        }
                        return Boolean.FALSE;
                    }
                    //省份 区域验证
                    if(ordPostDTO.getPostAreaCode().contains(promShipDTO.getProvinceCode())){
                        if(PromConstants.PromSys.IF_THROWS.equals(ifThrows)){
                            throw new BusinessException("prom.400169");
                        }
                        return Boolean.FALSE;
                    }
                    //地市
                    if(!StringUtil.isEmpty(promShipDTO.getCityCode())){
                        if(ordPostDTO.getPostAreaCode().contains(promShipDTO.getCityCode())){
                            if(PromConstants.PromSys.IF_THROWS.equals(ifThrows)){
                                throw new BusinessException("prom.400170");
                            }
                            return Boolean.FALSE;
                        }
                    }
                }
                
            }else{
                //选中 区域
                if(StringUtil.isEmpty(ordPostDTO.getPostAreaCode())){
                    //为空 表示没有限制 满足要求
                }else{
                    //省份 为空
                    if(StringUtil.isEmpty(promShipDTO.getProvinceCode())){
                        if(PromConstants.PromSys.IF_THROWS.equals(ifThrows)){
                            throw new BusinessException("prom.400169");
                        }
                        return Boolean.FALSE;
                    }
                    //省份不为空 需要限制
                    if(!ordPostDTO.getPostAreaCode().contains(promShipDTO.getProvinceCode())){
                        if(PromConstants.PromSys.IF_THROWS.equals(ifThrows)){
                            throw new BusinessException("prom.400170");
                        }
                        return Boolean.FALSE;
                    }
                    //地市区域
                    if(!StringUtil.isEmpty(promShipDTO.getCityCode())){
                        if(!ordPostDTO.getPostAreaCode().contains(promShipDTO.getCityCode())){
                            if(PromConstants.PromSys.IF_THROWS.equals(ifThrows)){
                                throw new BusinessException("prom.400170");
                            }
                            return Boolean.FALSE;
                        }
                    }
                }
            }
            
            return Boolean.TRUE;
        }
        //金额没有满足 免邮要求
        if(PromConstants.PromSys.IF_THROWS.equals(ifThrows)){
            String[] key=new String[1];
            key[0]=ordPostDTO.getOrderMoney().toString();
            throw new BusinessException("prom.400112",key);
        }
        
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
     * TODO促销信息录入-优惠规则验证 
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
        // 免邮费
        OrdPostDTO dto = new OrdPostDTO();
        try{
            dto = super.readJson2Bean(promDiscountRuleStr, OrdPostDTO.class);
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
                        OrdPostDTO  dto=(OrdPostDTO)commPromTypeDTO;
                        dto.setOrderMoney(NumberUtil.tranlateNum(dto.getOrderMoney().multiply(new BigDecimal(100))));
                        promDTO.setDiscountRule(JSON.toJSONString(dto));
                    }
             }
    }
}
