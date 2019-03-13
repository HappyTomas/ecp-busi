
package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.prom.dubbo.dto.CatgSendPointsDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCalRuleDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
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
public class CatgPointsDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl{

    private static final String MODULE = CatgPointsDiscountRuleSVImpl.class.getName();

    @Resource
    private IPromQuerySV promQuerySV;
    
    //客户模块接口
    @Resource
    private ICustInfoRSV custInfoRSV;
    
    /**
     * TODO是否满足优惠规则 (分类折扣促销类型)
     * @see com.zengshi.ecp.prom.service.busi.discountRule.impl.DefaultPromDiscountRuleSVImpl#isFulFilPromByGds(com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO, com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO, java.lang.String, java.lang.String, com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO)
     * @param ordProDTO
     * @param ordPromDetailDTO
     * @param constraintStr
     * @param ifThrows
     * @param promTypeMsgResponseDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean isFulFilPromByGds(OrdPromDTO ordProDTO, OrdPromDetailDTO ordPromDetailDTO,
            String constraintStr,String ifThrows ,PromTypeMsgResponseDTO promTypeMsgResponseDTO) throws BusinessException {
        
        if(promTypeMsgResponseDTO==null){
            //后续代码不需要跟踪验证空null 判断
            promTypeMsgResponseDTO=new PromTypeMsgResponseDTO();
        }
        //根据操作用户 获得对应的客户等级
        String level=null;
        CustInfoReqDTO custInfoDTO=new CustInfoReqDTO();
        custInfoDTO.setId(new Long(ordProDTO.getStaffId()));
        CustInfoResDTO custInfoRespDTO = custInfoRSV.getCustInfoById(custInfoDTO);
        //客户信息
        if(custInfoRespDTO!=null){
            level=custInfoRespDTO.getCustLevelCode();
        }
        
        // 分类打折 7折 客户等级1赠送1倍积分 客户等级2赠送2倍积分
        CatgSendPointsDTO catgSendPointsDTO = new CatgSendPointsDTO();
        if(!StringUtil.isEmpty(constraintStr)){
            catgSendPointsDTO = super.readJson2Bean(constraintStr, CatgSendPointsDTO.class);
            if(catgSendPointsDTO==null){
                catgSendPointsDTO = new CatgSendPointsDTO();
            }
        }
        //constraintStr 限制条件没有设置 默认满足
        if(StringUtil.isEmpty(constraintStr)){
            String msg=promTypeMsgResponseDTO.getFulfilMsg();
             promTypeMsgResponseDTO.setFulfilMsg(super.formatTipString(msg,(catgSendPointsDTO.getDiscountRate()+PromConstants.PromSys.LIKE_PERCENT)));
            return Boolean.TRUE;
        }
        catgSendPointsDTO.setCustLevelMap(convertMap(super.readJson2Bean(constraintStr, HashMap.class)));
        //没有设置客户等级
        if(CollectionUtils.isEmpty(catgSendPointsDTO.getCustLevelMap())){
            LogUtil.info(MODULE, "促销没有设置客户等级");
            if(PromConstants.PromSys.IF_THROWS.equals(ifThrows)){
                throw new BusinessException("prom.400157");
            }
            String msg=promTypeMsgResponseDTO.getNoFulfilMsg();

            promTypeMsgResponseDTO.setNoFulfilMsg(super.formatTipString(msg,(catgSendPointsDTO.getDiscountRate()+PromConstants.PromSys.LIKE_PERCENT)));
            return Boolean.FALSE;
        }
        

        //配置基本信息
//        Iterator i=catgSendPointsDTO.getCustLevelMap().entrySet().iterator();
//        
//        while(i.hasNext()){
//          Map.Entry e=(Map.Entry)i.next();
//          
//          if(e.getKey()!=null){
//              String key=e.getKey().toString();//客户等级
//              String value=e.getValue().toString();//赠送量
//              //前台vm 不能直接使用数字为hashmap key 展示  因此加个level
//              if(key.equals(PromConstants.PromSys.LEVEL+level)){
        String multiple = catgSendPointsDTO.getCustLevelMap().get(PromConstants.PromSys.LEVEL+level);

        if(StringUtil.isNotBlank(multiple))
        {
              //获取该等级会员赠送的积分
              if(PromConstants.PromDiscountRule.SEND_POINTS_1.equals(catgSendPointsDTO.getSendType()))
              {
              	multiple = multiple + PromConstants.PromSys.LIKE_MULTIPLE;
              }
              String msg=promTypeMsgResponseDTO.getFulfilMsg();
              promTypeMsgResponseDTO.setFulfilMsg(super.formatTipString(msg,(catgSendPointsDTO.getDiscountRate()+PromConstants.PromSys.LIKE_PERCENT), multiple));
              
              return Boolean.TRUE;
          }
          
        LogUtil.info(MODULE, "客户对应的等级"+level+"和促销配置的等级不同");
        if(PromConstants.PromSys.IF_THROWS.equals(ifThrows)){
            throw new BusinessException("prom.400157");
        }
        String msg=promTypeMsgResponseDTO.getNoFulfilMsg();
        promTypeMsgResponseDTO.setNoFulfilMsg(super.formatTipString(msg,(catgSendPointsDTO.getDiscountRate()+PromConstants.PromSys.LIKE_PERCENT)));
        
        return Boolean.FALSE;

    }

    /**
     * TODO计算优惠信息  打折送积分 没有优惠 默认为0
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
    	
        BigDecimal discountPrice = BigDecimal.ZERO;// 单价优惠 指优惠了多少单价
        BigDecimal discountMoney = BigDecimal.ZERO;// 优惠金额 指优惠了多少金额
        BigDecimal discountPriceMoney 	= BigDecimal.ZERO;// 除单价优惠金额外的优惠金额，不同于优惠金额discountMoney
        BigDecimal discountAmount 		= BigDecimal.ZERO;// 优惠数量 指优惠了多少数量，买X送Y专用
        BigDecimal discountCaclPrice  	= BigDecimal.ZERO;//促销优惠计算标准价：buyPrice、basePrice
        BigDecimal discountFinalPrice 	= BigDecimal.ZERO;//最终优惠后的价格，促销后最终的价格
        
        // 分类打折 7折 客户等级1赠送1倍积分 客户等级2赠送2倍积分 或者按照固定值送积分
        CatgSendPointsDTO catgSendPointsDTO = new CatgSendPointsDTO();
        
        catgSendPointsDTO = super.readJson2Bean(constraintStr, CatgSendPointsDTO.class);
        
        BigDecimal drPercent=BigDecimal.ZERO;
        //优惠折扣率计算   折扣率=  100-优惠率
        drPercent=BigDecimal.valueOf(100).subtract(catgSendPointsDTO.getDiscountRate()).divide(BigDecimal.valueOf(100));
     
        if(drPercent.compareTo(BigDecimal.ZERO)<=0){
            drPercent=BigDecimal.ZERO;
        }
        
        LogUtil.info(MODULE, "优惠折扣率" +drPercent+"购物车明细"+JSON.toJSONString(ordPromDetailDTO));
        
        //优惠单价
        BigDecimal drPrice=drPercent.multiply(new BigDecimal(ordPromDetailDTO.getBasePrice()));

        PromDiscountDTO promDiscountDTO = new PromDiscountDTO();
        //开始计算价格
        discountCaclPrice  = BigDecimal.valueOf(ordPromDetailDTO.getBasePrice());//促销优惠计算标准价：buyPrice、basePrice
        discountFinalPrice = discountCaclPrice.multiply(catgSendPointsDTO.getDiscountRate()).divide(BigDecimal.valueOf(100));// 单价优惠 指优惠了多少单价
        discountPrice = discountCaclPrice.subtract(discountFinalPrice);
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
        // 分类打折  送积分
        CatgSendPointsDTO catgSendPointsDTO = new CatgSendPointsDTO();
        try{
             catgSendPointsDTO = super.readJson2Bean(promDiscountRuleStr,
                CatgSendPointsDTO.class);
        }catch(Exception ex){
            LogUtil.error(MODULE, this.getClass().getName()+"转json格式数据 报错了  参数="+promDiscountRuleStr);
            throw new BusinessException("prom.400174");
         }
        catgSendPointsDTO.setCustLevelMap(convertMap(super.readJson2Bean(promDiscountRuleStr, HashMap.class)));
        if (catgSendPointsDTO == null) {
            // throw new BusinessException("请填写优惠规则！");
            throw new BusinessException("prom.400042");
        }
        //折扣率
        if (StringUtil.isEmpty(catgSendPointsDTO.getDiscountRate())) {
            //请设置折扣率
            throw new BusinessException("prom.400158");
        }
        //折扣率大于0
        if (catgSendPointsDTO.getDiscountRate().compareTo(BigDecimal.ZERO)<0) {
            //请设置折扣率 大于0
            throw new BusinessException("prom.400159");
        }
       
        //折扣率 不能超过80
        BigDecimal per=super.limitPercent(promDTO.getPromTypeCode());
        if (catgSendPointsDTO.getDiscountRate().compareTo(per)>0) {
            //请设置折扣率 小于80
            String[] keys=new String[2];
            keys[0]=catgSendPointsDTO.getDiscountRate().toString();
            keys[1]=per.toString();
            throw new BusinessException("prom.400173",keys);
        }
        
        //赠送类型 必填   0 按照固定值赠送 1 按照指定积分的倍数赠送
        if (StringUtil.isEmpty(catgSendPointsDTO.getSendType())) {
            // throw new BusinessException("积分赠送类型不能为空");
            throw new BusinessException("prom.400131");
        }
        
        Iterator i=catgSendPointsDTO.getCustLevelMap().entrySet().iterator();
        
        while(i.hasNext()){
          Map.Entry e=(Map.Entry)i.next();
          if(e.getKey()!=null){
              String key=e.getKey().toString();//客户等级
              String value=e.getValue().toString();//赠送量
              //赠送量
              if (StringUtil.isEmpty(value)) {
                  //请设置赠送量
                  throw new BusinessException("prom.400160");
              }
              BigDecimal v=BigDecimal.ZERO;
              try{
                   v=   new BigDecimal(value);
              }catch(Exception ex){
                  LogUtil.error(MODULE, ex.toString());
                  throw new BusinessException("prom.400183");
              }
              //赠送量大于等于0
              if (v.compareTo(BigDecimal.ZERO)<0) {
                  //赠送量大于等于0
                  throw new BusinessException("prom.400161");
              }
              //如果是倍数 送 积分  赠送量大于等于1
              if(PromConstants.PromDiscountRule.SEND_POINTS_1.equals(catgSendPointsDTO.getSendType())){
                  if (new BigDecimal(value).compareTo(BigDecimal.ZERO)<1) {
                      //赠送量大于等于1
                      throw new BusinessException("prom.400179");
                  }
              }
          }
        }
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
        
        CatgSendPointsDTO catgSendPointsDTO = new CatgSendPointsDTO();
        catgSendPointsDTO = super.readJson2Bean(jsonStr,
                CatgSendPointsDTO.class);
        catgSendPointsDTO.setCustLevelMap(convertMap(super.readJson2Bean(jsonStr, HashMap.class)));
        
       //解析条件 并赋值 PromPresentDTO
        PromPresentDTO promPresentDTO=new PromPresentDTO();
        promPresentDTO.setPromId(promId);
        
        //staffId 获得对应的 等级
        String level=null;
        CustInfoReqDTO custInfoDTO=new CustInfoReqDTO();
        custInfoDTO.setId(new Long(ordPromDTO.getStaffId()));
        CustInfoResDTO custInfoRespDTO = custInfoRSV.getCustInfoById(custInfoDTO);
        
        if(custInfoRespDTO!=null){
            level=custInfoRespDTO.getCustLevelCode();
        }
        
        //按固定值 赠送积分
        if(PromConstants.PromDiscountRule.SEND_POINTS_0.equals(catgSendPointsDTO.getSendType())){
            promPresentDTO.setSendType(PromConstants.PromDiscountRule.SEND_POINTS_0);
            promPresentDTO.setPoints(this.findLevelValue(level,catgSendPointsDTO.getCustLevelMap()));
        }
         //按倍数赠送积分  倍数*订单金额
        if(PromConstants.PromDiscountRule.SEND_POINTS_1.equals(catgSendPointsDTO.getSendType())){
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
            mult=this.findLevelValue(level,catgSendPointsDTO.getCustLevelMap()).subtract(multParam);
            //倍数为负数  默认为0
            if(mult.compareTo(BigDecimal.ZERO)<0){
                mult=BigDecimal.ZERO;
                promPresentDTO.setPoints(BigDecimal.ZERO);
            }else{
                //BigDecimal orderMoney = super.totalOrderMoney(ordPromDTO);
                promPresentDTO.setPoints(mult);
            }
          
        }
        return promPresentDTO;
    }
    
    /**
     * 客户等级 和 配置 取赠送值
     * @param level
     * @param map
     * @return
     * @author huangjx
     */
    private BigDecimal findLevelValue(String level,Map<String, String> map){
     
        //为空 返回0
         if(CollectionUtils.isEmpty(map)){
             return BigDecimal.ZERO;
         }
       //默认返回0
         BigDecimal levelValue=BigDecimal.ZERO;
         
         Iterator i=map.entrySet().iterator();
         
         while(i.hasNext()){
            Map.Entry e=(Map.Entry)i.next();
            if(e.getKey()!=null){
                String key=e.getKey().toString();//客户等级
                String value=e.getValue().toString();//赠送量 或者比例
                if(key.equals(PromConstants.PromSys.LEVEL+level)){
                    //和设置一致
                    levelValue=new BigDecimal(value);
                    break;
                }
            }
          }
         return levelValue;
    }
    /**
     * 转换为map key-value 含义
     * 转为：key表示等级  value表示赠送量或者比例
     * @param map
     * @author huangjx
     */
    private Map convertMap(Map map){
        
        if(map==null){
            return null;
        }
        map.remove(PromConstants.PromSys.DISCOUNT_RATE_KEY);//赠送比率
        map.remove(PromConstants.PromSys.SEND_TYPE_KEY);//赠送类型
        return map;
    }
}
