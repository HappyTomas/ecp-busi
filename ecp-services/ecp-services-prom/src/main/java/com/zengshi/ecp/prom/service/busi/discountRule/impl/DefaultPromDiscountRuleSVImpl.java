package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.drools.core.util.StringUtils;

import com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromShipDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.discountRule.interfaces.IPromDiscountRuleSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.sku.impl.SkuCheckSVImpl;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
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
public class DefaultPromDiscountRuleSVImpl extends GeneralSQLSVImpl implements IPromDiscountRuleSV  {

    private static final String MODULE = DefaultPromDiscountRuleSVImpl.class.getName();

    @Resource
    private SkuCheckSVImpl skuCheckSV;
    
    @Resource
    private IPromInfoSV promInfoSV;
    
	/**
     * 是否免邮
     * @param promId
     * @param ifFreePost  0 不免邮  1免邮  其他通过promId验证
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean ifFreePost(Long promId,String ifFreePost) throws BusinessException{
    	
    	if(PromConstants.PromInfo.IF_FREE_POST_0.equals(ifFreePost)){
    		return Boolean.FALSE;
    	}
    	if(PromConstants.PromInfo.IF_FREE_POST_1.equals(ifFreePost)){
    		return Boolean.TRUE;
    	}
    	
    	if(promId!=null){
    		//查询促销id 相关信息
    		PromInfoResponseDTO p=promInfoSV.queryPromInfoResponseDTOById(promId);
	    		if(p!=null){
	    			if(PromConstants.PromInfo.IF_FREE_POST_1.equals(p.getIfFreePost())){
	    	    		return Boolean.TRUE;
	    	    	}
	    		}
    	}
    	return Boolean.FALSE;
    }
    
    /**
     * TODO 是否免邮 默认不免邮
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
        return Boolean.FALSE;
    }
    /**
     * TODO
     *   * 是否满足优惠规则 (单品促销类型)
     * @param ordProDTO  订单vo
     * @param ordPromDetailDTO 订单明细vo PromDiscountDTO 基础参数 constraintStr促销设置值
     * @see com.zengshi.ecp.prom.service.busi.discountRule.interfaces.IPromDiscountRuleSV#isFulFilPromByGds(com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO, com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO, java.lang.String, java.lang.String, com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO)
     * @param ordProDTO
     * @param ordPromDetailDTO
     * @param constraintStr
     * @param ifThrows
     * @param promTypeMsgResponseDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean isFulFilPromByGds(OrdPromDTO ordProDTO, OrdPromDetailDTO ordPromDetailDTO,
            String constraintStr,String ifThrows,PromTypeMsgResponseDTO promTypeMsgResponseDTO) throws BusinessException {
        
        if(promTypeMsgResponseDTO==null){
            //后续代码不需要跟踪验证空null 判断
            promTypeMsgResponseDTO=new PromTypeMsgResponseDTO();
        }
        // default 默认不满足
        return Boolean.FALSE;
    }

    /**
     * TODO计算优惠信息
     * 
     * @see com.zengshi.ecp.prom.service.busi.discountRule.interfaces.IPromDiscountRuleSV#calculatePromotion(com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO,
     *      com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO, java.lang.String)
     * @param ordProDTO
     * @param ordPromDetailDTO
     * @param constraintStr
     * @return
     * @throws IllegalAccessException
     * @throws Exception
     * @author huangjx
     */
    public PromDiscountDTO calculatePromotion(OrdPromDTO ordProDTO,
            OrdPromDetailDTO ordPromDetailDTO, String constraintStr) throws BusinessException {
        // 默认返回0
        PromDiscountDTO promDiscountDTO = new PromDiscountDTO();
        promDiscountDTO.setIfFulfillProm(Boolean.FALSE);
        return promDiscountDTO;
    }

    /**
     * 根据购物车实例 promId计算订单金额（减去非叠加促销） 减去优惠金额
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    protected BigDecimal totalOrderMoneyByPromId(OrdPromDTO ordPromDTO) throws BusinessException {
        
        // 订单金额 即优惠后的金额之和
        BigDecimal ordMoney = BigDecimal.ZERO;
        
        //为空 直接返回0
        if(ordPromDTO==null){
            return ordMoney;
        }
        
        if (ordPromDTO.getPromInfoDTO()== null || ordPromDTO.getPromInfoDTO().getId()==null) {
            return ordMoney;
        }
        if (ordPromDTO.getOrdPromDetailDTOList() != null ) {
            
            for (OrdPromDetailDTO ordPromDetailDTO : ordPromDTO.getOrdPromDetailDTOList()) {
                // 商品时包含在某个促销ID中
                     if(ordPromDetailDTO.getOrdPromId()!=null && ordPromDetailDTO.getOrdPromInfoDTOList() != null){
                             boolean isFull = false;
                             for(PromInfoDTO promInfo : ordPromDetailDTO.getOrdPromInfoDTOList())
                             {
                                 if (promInfo.getId() != null && promInfo.getId().equals(ordPromDTO.getPromInfoDTO().getId())) {
                                     isFull = true;
                                     break;
                                }
                             }
                            if (isFull) {
                                //叠加
                               if( this.isIfComposit(ordPromDetailDTO.getPromInfoDTO())){
                                   if(ordPromDetailDTO.getDiscountMoney()==null){
                                       ordPromDetailDTO.setDiscountMoney(BigDecimal.ZERO);
                                   }
                                   if(ordPromDetailDTO.getDiscountPriceMoney()==null){
                                       ordPromDetailDTO.setDiscountPriceMoney(BigDecimal.ZERO);
                                   }
                                   if(ordPromDetailDTO.getDiscountAmount()==null){
                                       ordPromDetailDTO.setDiscountAmount(BigDecimal.ZERO);
                                   }
                                   //面数量 对应的金额
                                   BigDecimal excepTMoney=ordPromDetailDTO.getDiscountAmount().multiply(ordPromDetailDTO.getDiscountFinalPrice());
                                   
                                   //面额外金额
                                   excepTMoney=excepTMoney.add(ordPromDetailDTO.getDiscountPriceMoney());
                                   
                                   // 目前订单金额=原始订单单价*数量 减去优惠金额 之和
                                 /*  ordMoney = ordMoney.add(
                                           new BigDecimal(ordPromDetailDTO.getOrderAmount()).multiply(ordPromDetailDTO.getDiscountFinalPrice()).subtract(
                                                           ordPromDetailDTO.getDiscountMoney().add(
                                                                   ordPromDetailDTO.getDiscountPriceMoney())));*/
                                     ordMoney = ordMoney.add((
                                   new BigDecimal(ordPromDetailDTO.getOrderAmount()).multiply(ordPromDetailDTO.getDiscountFinalPrice())).subtract(excepTMoney));
                                   
                               }
                            }
                     }
            }
        }
        // 如果ordMoney 小于0 默认为0
        if (ordMoney.compareTo(BigDecimal.ZERO) < 0) {
            ordMoney = BigDecimal.ZERO;
        }
        return ordMoney;

    }

    /**
     * 根据购物车实例 promId计算订单金额
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    protected BigDecimal totalFullMoneyByPromId(OrdPromDTO ordPromDTO) throws BusinessException {
        
        // 订单金额 即优惠后的金额之和
        BigDecimal ordMoney = BigDecimal.ZERO;
        
        //为空 直接返回0
        if(ordPromDTO==null){
            return ordMoney;
        }
        
        if (ordPromDTO.getPromInfoDTO()== null || ordPromDTO.getPromInfoDTO().getId()==null) {
            return ordMoney;
        }
        if (ordPromDTO.getOrdPromDetailDTOList() != null) {
            
            for (OrdPromDetailDTO ordPromDetailDTO : ordPromDTO.getOrdPromDetailDTOList()) {

                // 商品时包含在某个促销ID中
                     if(ordPromDetailDTO.getOrdPromId()!=null){
                            if (ordPromDetailDTO.getOrdPromId().equals(ordPromDTO.getPromInfoDTO().getId())) {
                                //叠加
                                if( this.isIfComposit(ordPromDetailDTO.getPromInfoDTO())){
                                    if(ordPromDetailDTO.getDiscountMoney()==null){
                                        ordPromDetailDTO.setDiscountMoney(BigDecimal.ZERO);
                                    }
                                    // 目前订单金额=原始订单单价*数量 之和
                                    ordMoney = ordMoney.add(
                                            new BigDecimal(ordPromDetailDTO.getOrderAmount()).multiply(ordPromDetailDTO.getDiscountCaclPrice()));
                                }
                            }
                     }
            }
        }
        // 如果ordMoney 小于0 默认为0
        if (ordMoney.compareTo(BigDecimal.ZERO) < 0) {
            ordMoney = BigDecimal.ZERO;
        }
        return ordMoney;

    }
    /**
     * 根据购物车实例 计算订单金额
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    protected BigDecimal totalOrderMoney(OrdPromDTO ordPromDTO) throws BusinessException {
        
        // 订单金额 即优惠后的金额之和
        BigDecimal ordMoney = BigDecimal.ZERO;
        
        //为空 直接返回0
        if(ordPromDTO==null){
            return ordMoney;
        }
        if (ordPromDTO.getOrdPromDetailDTOList() != null) {
            
            for (OrdPromDetailDTO ordPromDetailVo : ordPromDTO.getOrdPromDetailDTOList()) {
                // 目前订单金额=原始订单单价*数量 减去优惠金额 之和
                if( this.isIfComposit(ordPromDetailVo.getPromInfoDTO())){
                    ordMoney = ordMoney.add(
                            new BigDecimal(ordPromDetailVo.getOrderAmount()).multiply(ordPromDetailVo.getDiscountCaclPrice()))
                            .subtract(ordPromDetailVo.getDiscountMoney().add(
                                    ordPromDetailVo.getDiscountPriceMoney()));
                }

            }
            
        }
        // 如果ordMoney 小于0 默认为0
        if (ordMoney.compareTo(BigDecimal.ZERO) < 0) {
            ordMoney = BigDecimal.ZERO;
        }
        return ordMoney;

    }

    /**
     * 根据购物车实例 计算订单金额 计算数量
     * @param ordPromDTO
     * @param ordPromDetailDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    protected Map<String,BigDecimal> findAmountAndMoney(OrdPromDTO ordPromDTO,OrdPromDetailDTO ordPromDetailDTO) throws BusinessException {
         
         Map<String,BigDecimal>  map=new HashMap<String,BigDecimal>();
         
         //默认当前数量
         BigDecimal amount=new BigDecimal(ordPromDetailDTO.getOrderAmount());
         
        //默认当前数量*单价
         BigDecimal money=new BigDecimal(ordPromDetailDTO.getOrderAmount()).multiply( new BigDecimal(ordPromDetailDTO.getBuyPrice()));
         
        //验证当前订单下 所有单品
         PromInfoDTO promInfoDTO = ordPromDetailDTO.getPromInfoDTO();
         
         for(OrdPromDetailDTO dto:ordPromDTO.getOrdPromDetailDTOList()){
             //排除当前itemid
             if(!dto.getId().equals(ordPromDetailDTO.getId())){
                 PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
                 promRuleCheckDTO.setGdsId(dto.getGdsId());
                 promRuleCheckDTO.setGdsName(dto.getGdsName());
                 promRuleCheckDTO.setPromId(promInfoDTO.getId());
                 promRuleCheckDTO.setShopId(dto.getShopId());
                 promRuleCheckDTO.setShopName(dto.getShopName());
                 promRuleCheckDTO.setSkuId(dto.getSkuId());
                 if(skuCheckSV.gdsCheck(promInfoDTO, promRuleCheckDTO)){
                     amount=amount.add(new BigDecimal(dto.getOrderAmount()));
                     money=money.add(new BigDecimal(dto.getOrderAmount()).multiply(dto.getDiscountCaclPrice()));
                 }
             }
         }
         map.put(PromConstants.PromSys.AMOUNT_KEY, amount);
         map.put(PromConstants.PromSys.MONEY_KEY, money);
         return map;
    }
    /**
     * json转bean
     * 
     * @param jsonStr
     * @param t
     * @return
     * @throws Exception
     * @author huangjx
     */
    protected <T> T readJson2Bean(String jsonStr, Class<T> t) throws BusinessException {
        T b = null;
        try {
            if(StringUtils.isEmpty(jsonStr)){
                return b;
            }
            b = t.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LogUtil.error(MODULE, e.toString());
            String[] earr = new String[1];
            earr[0] = t.getName();
            throw new BusinessException("prom.400050", earr);
        }
        b = JSON.parseObject(jsonStr, t);
        return b;
    }

    /**
     * TODO促销信息录入-优惠规则,是否需要验证
     * 
     * @see com.zengshi.ecp.prom.service.busi.discountRule.interfaces.IPromDiscountRuleSV#needToVerified(com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public boolean needToVerified(PromDTO promDTO) throws BusinessException {
        // 默认不需要验证
        return Boolean.FALSE;
    }

    /**
     * TODO促销信息录入-优惠规则验证 默认方法为空
     * @param promDiscountRuleStr
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void valid(String promDiscountRuleStr,PromDTO promDTO) throws BusinessException {
              //default 不实现
    }  
 
    /**
     * 获得促销类型对应的限额要求比例
     * @param promTypeCode
     * @throws BusinessException
     * @author huangjx
     */
    public BigDecimal limitPercent(String promTypeCode) throws BusinessException {
        
        BigDecimal per=new BigDecimal(100);
        BaseSysCfgRespDTO  sysDTO=SysCfgUtil.fetchSysCfg("PROM_TYPE_"+promTypeCode);
        //无此配置 默认100
        if(sysDTO!=null && !StringUtil.isEmpty(sysDTO.getParaValue())){
            per=new BigDecimal(sysDTO.getParaValue());
        }
        return per;
    }  
    /**
     * 转换 金额 为分
     * @param commPromTypeDTO
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void validTranlate(CommPromTypeDTO commPromTypeDTO,PromDTO promDTO) throws BusinessException {
        //default 不实现
    }
    
    /**
     * 赠送 积分、 优惠券、 赠品列表
     * @param promId
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromPresentDTO promPresent(Long promId,OrdPromDTO ordPromDTO) throws BusinessException{
        //赠送 积分 优惠券列表(默认实现为空 只有在真正需要赠送实例类中实现)
        return null;
    }
    /**
     * 赠品列表
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public  List<PromGiftDTO> promGiftList(Long promId) throws BusinessException{
        //赠品列表(默认实现为空 只有在真正需要赠送实例类中实现)
        return null;
    }
    /**
     * 格式化提醒信息
     * @param format
     * @param args
     * @return
     * @author huangjx
     */
    protected String formatTipString(String format, Object... args){

        //为空 返回""
        if(StringUtil.isEmpty(format)){
            return "";
        }
        //参数为空  直接返回format
        if(args==null ){
            if(format.contains("%s")){
                return format.replaceAll("%s", "");
            }else{
                return format;
            }
        }
        int len=args.length;
        //非空 如果存在null 转为""
        if(len>0){
            for(int i=0;i<len;i++){
                if(StringUtil.isEmpty(args[i])){
                    args[i]="";
                }
            }
        }else{
            //为空 没有传参数
            if(format.contains("%s")){
                return format.replaceAll("%s", "");
            }else{
                return format;
            }
        }
        return String.format(format, args);
    
    }
    /**
     * 是否叠加 验证  true 允许叠加  false 不允许叠加
     * @return
     * @author huangjx
     */
    private boolean isIfComposit(PromInfoDTO promInfoDTO){
        //允许叠加
        if(promInfoDTO==null){
            return Boolean.TRUE; 
        }
        //-3表示 数字印刷叠加 不能参与促销
        if(!StringUtil.isEmpty(promInfoDTO.getId()) && promInfoDTO.getId().longValue()==-3){
            //不允许叠加
            return Boolean.FALSE;
        }
        //允许叠加
        if(StringUtil.isEmpty(promInfoDTO.getIfComposit())){
            return Boolean.TRUE; 
        }
        //允许叠加
        if(PromConstants.PromInfo.IF_COMPOSIT_1.equals(promInfoDTO.getIfComposit())){
            return Boolean.TRUE;   
        }
        //不允许叠加
        return Boolean.FALSE;
    }
}
