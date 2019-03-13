
package com.zengshi.ecp.prom.service.busi.discountRule.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.MultiBuyDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.NumberUtil;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.prom.service.util.PromUtil;
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
public class MulitBuyDiscountRuleSVImpl extends DefaultPromDiscountRuleSVImpl{

    private static final String MODULE = MulitBuyDiscountRuleSVImpl.class.getName();

    @Resource
    private IPromQuerySV promQuerySV;
    
    //xml 配置属性值 true表示 验证当前单品。false或者空 表示验证当前传入所有单品是否满足promId
    private boolean ifOnlyChkSku;
    
    /**
     * TODO是否满足优惠规则 (产品促销类型)
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
        //constraintStr 限制条件没有设置 默认满足
        if(StringUtil.isEmpty(constraintStr)){
            String msg=promTypeMsgResponseDTO.getFulfilMsg();
            promTypeMsgResponseDTO.setFulfilMsg(super.formatTipString(msg, null));
            return Boolean.TRUE;
        }
        // 分布式多项购买  4个30元 5个35元 6个40元模式
        MultiBuyDTO multiBuyDTO = new MultiBuyDTO();
        multiBuyDTO.setMultiMap( convertMap(super.readJson2Bean(constraintStr, HashMap.class)));
        //获得配置参数值
        Iterator i=multiBuyDTO.getMultiMap().entrySet().iterator();
        
        Map<String,BigDecimal> map=new HashMap<String,BigDecimal>();
        
        BigDecimal amount=BigDecimal.ZERO;//购买数量
        
        //true 验证当前单品
        if(this.isIfOnlyChkSku()){
            amount=new BigDecimal(ordPromDetailDTO.getOrderAmount());
        }else{
            //验证当前订单 所有单品是否参与
          //验证当前订单 所有单品是否参与
            map=this.findAmountAndMoney(ordProDTO, ordPromDetailDTO);
            amount=map.get(PromConstants.PromSys.AMOUNT_KEY);
        }
        String initMsg="";
        
        List multiList=PromUtil.sortMap(multiBuyDTO.getMultiMap(), "key", "asc");
        
        if(!CollectionUtils.isEmpty(multiList)){
            
            for(int k=0;k<multiList.size();k++){
                   Map.Entry e=(Map.Entry)multiList.get(k);
                    String key=e.getKey().toString();//购买数量
                    String value=e.getValue().toString();//购买金额
                    //建议做成 读取配置文件 待修改
                    initMsg =initMsg+"  购买"+key+"件"+NumberUtil.divideNum(new BigDecimal(value), null)+"元";
            }
        }
/*        while(i.hasNext()){
            Map.Entry e=(Map.Entry)i.next();
            String key=e.getKey().toString();//购买数量
            String value=e.getValue().toString();//购买金额
            //建议做成 读取配置文件 待修改
            initMsg =initMsg+"  购买"+key+"件-金额"+NumberUtil.divideNum(new BigDecimal(value), null);
        } */
        
        String money=null;
        
        if(multiBuyDTO.getMultiMap().get(amount.toString())!=null){
            money=multiBuyDTO.getMultiMap().get(amount.toString());
        }
        
        //满足要求
        if(StringUtils.isNotBlank(money)){
            String msg=promTypeMsgResponseDTO.getFulfilMsg();
            promTypeMsgResponseDTO.setFulfilMsg(super.formatTipString(msg, initMsg));
            return Boolean.TRUE;
        }
        //不满足要求
 
        
 /*       while(i.hasNext()){
          Map.Entry e=(Map.Entry)i.next();
          String key=e.getKey().toString();//购买数量
          String value=e.getValue().toString();//购买金额
          
          if(amount.compareTo(new BigDecimal(key))>=0){
              
              String msg=promTypeMsgResponseDTO.getFulfilMsg();
              promTypeMsgResponseDTO.setFulfilMsg(super.formatTipString(msg, null));
              
              return Boolean.TRUE;
          }
          //建议做成 读取配置文件 待修改
          noFullMsg =noFullMsg+" 购买数量为"+key+"价格为"+NumberUtil.divideNum(new BigDecimal(value), null);
          
        }*/
        
        LogUtil.info(MODULE, "没有满足要求哦"+initMsg);
        if(PromConstants.PromSys.IF_THROWS.equals(ifThrows)){
            String[] key=new String[1];
            key[0]=ordPromDetailDTO.getOrderAmount().toString();
            throw new BusinessException("prom.400134",key);
        }
        
        String msg=promTypeMsgResponseDTO.getNoFulfilMsg();
        promTypeMsgResponseDTO.setNoFulfilMsg(super.formatTipString(msg, initMsg));
   
       return Boolean.FALSE;

    }

    /**
     * TODO计算优惠信息    分布式多项购买  4个30元 5个35元 6个40元模式
     * 优惠金额=单价*数量-指定购买金额 
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
        
        // 分布式多项购买  4个30元 5个35元 6个40元模式
        MultiBuyDTO multiBuyDTO = new MultiBuyDTO();
        multiBuyDTO.setMultiMap( convertMap(super.readJson2Bean(constraintStr, HashMap.class)));
        //获得配置参数值
        Iterator buyRuleMap = multiBuyDTO.getMultiMap().entrySet().iterator();
        
        //计算价格开始
        BigDecimal discountPrice = BigDecimal.ZERO;// 单价优惠 指优惠了多少单价
        BigDecimal discountMoney = BigDecimal.ZERO;// 优惠金额 指优惠了多少金额
        BigDecimal discountPriceMoney   = BigDecimal.ZERO;// 除单价优惠金额外的优惠金额，不同于优惠金额discountMoney
        BigDecimal discountAmount       = BigDecimal.ZERO;// 优惠数量 指优惠了多少数量，买X送Y专用
        BigDecimal discountCaclPrice    = BigDecimal.ZERO;//促销优惠计算标准价：buyPrice、basePrice
        BigDecimal discountFinalPrice   = BigDecimal.ZERO;//最终优惠后的价格，促销后最终的价格
        
        discountCaclPrice =  BigDecimal.valueOf(ordPromDetailDTO.getBasePrice());
        
        discountFinalPrice=BigDecimal.valueOf(ordPromDetailDTO.getBuyPrice());
       
        BigDecimal buyMoney = BigDecimal.ZERO;//设置金额
        
        BigDecimal detailAmount   = BigDecimal.ZERO;//购买数量
        BigDecimal detailSumMoney = BigDecimal.ZERO;//订单明细的购买总金额
        
        Map<String,BigDecimal> map = new HashMap<String,BigDecimal>();
        //true 验证当前单品
        if(this.isIfOnlyChkSku()){
            detailAmount = new BigDecimal(ordPromDetailDTO.getOrderAmount());
            detailSumMoney = new BigDecimal(ordPromDetailDTO.getOrderAmount()).multiply(discountCaclPrice);
        }else{
            //验证当前订单 所有单品是否参与
            map = this.findAmountAndMoney(ordProDTO, ordPromDetailDTO);
            detailAmount = map.get(PromConstants.PromSys.AMOUNT_KEY);
            detailSumMoney = map.get(PromConstants.PromSys.MONEY_KEY);
        }
        Object v=multiBuyDTO.getMultiMap().get(detailAmount.toString());
        if(v!=null){
            buyMoney = new BigDecimal(v.toString());
            //优惠金额=单价*数量-指定购买金额 
            discountMoney = detailSumMoney.subtract(buyMoney);
            //计算价格出现 除不尽的情况 小数很多处理。这个和业务配置的总金额有一定的出入。例如3件40元，折算下来单价是1.33333333 
            //最终价格=购买金额/购买数量
            //discountFinalPrice = buyMoney.divide(detailAmount,BigDecimal.ROUND_HALF_DOWN);
            //优惠单价=优惠金额/购买数量
            //discountPrice = discountMoney.divide(detailAmount,BigDecimal.ROUND_HALF_DOWN);

        }else{
            discountPrice= BigDecimal.ZERO;
            discountMoney= BigDecimal.ZERO;
            //discountFinalPrice=BigDecimal.valueOf(ordPromDetailDTO.getBuyPrice());
            //如果按照以下规则 5个和8个一样的价格 亏死了。
           /* while(buyRuleMap.hasNext()){
              Map.Entry buyRule = (Map.Entry)buyRuleMap.next();
              String buyRuleNumber = buyRule.getKey().toString();//购买数量
              String buyRuleMoney = buyRule.getValue().toString();//购买金额
              //获得最优金额  假如设置 4 50,5 60,10 100.那么传入购买数量为8时，满足5规则。传入输入为15时全部满足，这个时候需要最优规则。
              if(detailAmount.compareTo(new BigDecimal(buyRuleNumber))>=0){
                  if(buyMoney.compareTo(new BigDecimal(buyRuleMoney))<0){
                      buyMoney = new BigDecimal(buyRuleMoney);
                  }
              }
              //获得当前配置数量最大值
              if((Long.valueOf(buyRuleNumber)).compareTo(maxBuyRuleNum)>0){
                  maxBuyRuleNum=Long.valueOf(buyRuleNumber);
              }
            }*/
        }
        // 默认返回0
        PromDiscountDTO promDiscountDTO = new PromDiscountDTO();
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
        HashMap map=new HashMap();
        try{
             // 分布式多项购买  4个30元 5个35元 6个40元模式
            map=super.readJson2Bean(promDiscountRuleStr, HashMap.class);
        }catch(Exception ex){
            LogUtil.error(MODULE, this.getClass().getName()+"转json格式数据 报错了  参数="+promDiscountRuleStr);
            throw new BusinessException("prom.400174");
         }
        MultiBuyDTO multiBuyDTO = new MultiBuyDTO();
        multiBuyDTO.setMultiMap( convertMap(map));

        if (multiBuyDTO == null) {
            // throw new BusinessException("请填写优惠规则！");
            throw new BusinessException("prom.400042");
        }
        Iterator i=multiBuyDTO.getMultiMap().entrySet().iterator();
        
        while(i.hasNext()){//只遍历一次,速度快
            Map.Entry e=(Map.Entry)i.next();
            if (StringUtil.isEmpty(e.getKey())) {
                // throw new BusinessException("购买数量不能为空");
                throw new BusinessException("prom.400135");
            }
            if (StringUtil.isEmpty(e.getValue())) {
                // throw new BusinessException("购买数量不能为空");
                String[] key=new String[1];
                key[0]=e.getKey().toString();
                throw new BusinessException("prom.400136",key);
            }
            String key=e.getKey().toString();//购买数量
            String value=e.getValue().toString();//购买金额
          
            if (new BigDecimal(key).compareTo(BigDecimal.ZERO) <= 0) {
                // throw new BusinessException("购买数量必需大于零！");
                throw new BusinessException("prom.400137");
            }
            if (new BigDecimal(value).compareTo(BigDecimal.ZERO) <= 0) {
                // throw new BusinessException("购买金额必需大于零！");
                throw new BusinessException("prom.400138");
            }
          }
       this.validTranlate(multiBuyDTO, promDTO);
    }
    /**
     * 转换为map key-value 含义
     * 转为：key表示数量 value表示金额
     * @param map
     * @author huangjx
     */
    private Map convertMap(Map map){
        
        if(map==null){
            return null;
        }
        Map<String,String> _map=new HashMap<String,String>();
        
        Iterator i=map.entrySet().iterator();
        
        while(i.hasNext()){
            Map.Entry e=(Map.Entry)i.next();
            String key=e.getKey().toString();
            String value=e.getValue().toString();
            if(key.contains(PromConstants.PromSys.BUYAMOUNT_CONST)){
                if(_map.containsKey(map.get(key))){
                    throw new BusinessException("prom.400154");
                }
                //查找对于设置金额匹配key
                String newKey=key.replace(PromConstants.PromSys.BUYAMOUNT_CONST, PromConstants.PromSys.BUYMONEY_CONST);
                if(!StringUtil.isEmpty(map.get(key)) || !StringUtil.isEmpty(map.get(newKey))){
                  _map.put(map.get(key).toString(), map.get(newKey).toString());
                }
            }
        }
        return _map;
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
    public void validTranlate(CommPromTypeDTO commPromTypeDTO, PromDTO promDTO)
            throws BusinessException {
        
        if (!StringUtil.isEmpty(promDTO.getDiscountRule())) {
            if (commPromTypeDTO != null) {

                HashMap map = super.readJson2Bean(promDTO.getDiscountRule(), HashMap.class);

                Iterator i = map.entrySet().iterator();
                while (i.hasNext()) {
                    Map.Entry e = (Map.Entry) i.next();
                    String key = e.getKey().toString();
                    String value = e.getValue().toString();
                    if (key.contains(PromConstants.PromSys.BUYMONEY_CONST)) {
                        if(StringUtil.isEmpty(value)){
                            continue;
                        }
                        BigDecimal v = NumberUtil.tranlateNum(new BigDecimal(value)
                                .multiply(new BigDecimal(100)));
                        map.put(key, v);
                    }
                }
                promDTO.setDiscountRule(JSON.toJSONString(map));
            }
        }
    }
    
 
    public boolean isIfOnlyChkSku() {
        return ifOnlyChkSku;
    }

    public void setIfOnlyChkSku(boolean ifOnlyChkSku) {
        this.ifOnlyChkSku = ifOnlyChkSku;
    }
}
