package com.zengshi.ecp.staff.service.busi.custinfo.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.mapper.common.CustGrowRuleMapper;
import com.zengshi.ecp.staff.dao.mapper.common.CustLevelRuleMapper;
import com.zengshi.ecp.staff.dao.model.CustGrowRule;
import com.zengshi.ecp.staff.dao.model.CustGrowRuleCriteria;
import com.zengshi.ecp.staff.dao.model.CustGrowRuleCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.CustLevelRule;
import com.zengshi.ecp.staff.dao.model.CustLevelRuleCriteria;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustGrowRuleSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MoneyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 来源：02购物<br>
 * Date:2015年10月13日下午4:47:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public class CustGrowRuleBySaleSVImpl implements ICustGrowRuleSV{
    
    private static final String MODULE = CustGrowRuleBySaleSVImpl.class.getName();
    
    @Resource
    private CustGrowRuleMapper custGrowRuleMapper;
    
    @Resource
    private CustLevelRuleMapper custLevelRuleMapper;
    
    private double Ordmoney;

    @Override
    public Long getGrowByParamRule(PayQuartzInfoRequest req) throws BusinessException {
        Long growValue = (long) 0;
        if(req.getPayment()==0){
            return growValue;
        }
        //判断来源类型是否是02
        if(StaffConstants.SHOPING_TYPE.equals(req.getSourceType())){
            //转成BigDecimal
            Ordmoney = Double.valueOf(MoneyUtil.convertCentToYuan(req.getPayment()));
            //先判断消费一次性金额
            Long onceGrow =   this.onecPayMoney(Ordmoney);
            if(null!=onceGrow&&0!=onceGrow){
                return onceGrow;
            }
            CustGrowRuleCriteria c = new  CustGrowRuleCriteria();
            Criteria sql = c.createCriteria();
            sql.andSourceTypeEqualTo(req.getSourceType());
            if(null!=req.getSiteId()&&0!=req.getSiteId()){
                //根据站点查询
              sql.andSiteIdEqualTo(req.getSiteId());
            }
              sql.andStatusEqualTo(StaffConstants.custInfo.CUST_STATUS_NORMAL);
            try {
                List<CustGrowRule> list = custGrowRuleMapper.selectByExample(c);
                if(null!=list&&list.size()==1){
                    this.checkPayMoney(req,list.get(0));
                    growValue = getGrowbyMoney(list.get(0));
                }
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询会员等级规则错误", e);
                throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
            }
            
        }
        return growValue;
    }
    
    /**
     * 
     * getGrowbyMoney:(根据订单金额计算比例获取成长值). <br/> 
     * 
     * @author wangbh
     * @param req
     * @param rule
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public Long getGrowbyMoney(CustGrowRule rule) throws BusinessException{
        
        if(null!=rule.getConvertValue()){
            BigDecimal convertValue =  rule.getConvertValue();
            BigDecimal bignum2 = new BigDecimal("100"); 
            convertValue =  convertValue.divide(bignum2);
            double growValue =new BigDecimal(Ordmoney).multiply(convertValue).doubleValue();
            return (long)Math.ceil(growValue);
        }else{
            //没有配置，就返回固定的成长值
            return rule.getGrowValue();
        }
    }
    

    /**
     * 
     * checkPayMoney:(校验订单最小金额，如果为空不校验). <br/> 
     * 
     * @author wangbh
     * @param custGrowRule
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void checkPayMoney(PayQuartzInfoRequest req,CustGrowRule rule) throws BusinessException{
        
        if(null!=rule.getPayMoney()&&0!=rule.getPayMoney()){
            //比较订单金额是否达到最小的订单金额
            if(req.getPayment()<rule.getPayMoney().longValue()){
                throw new BusinessException(StaffConstants.custInfo.CUST_GROW_PAY_MONEY_ERROR);
            }
        }
        
    }
   /**
    * 
    * onecPayMoney:(一次性消费达到某值，返回对应的等级成长值). <br/> 
    * 
    * @author wangbh
    * @param req
    * @return
    * @throws BusinessException 
    * @since JDK 1.7
    */
    public Long onecPayMoney(double money)throws BusinessException{
        long m = (long) Math.ceil(money);
        CustLevelRuleCriteria c = new CustLevelRuleCriteria();
        c.setOrderByClause("ONCE_MONEY desc");
        c.createCriteria().andOnceMoneyLessThanOrEqualTo(m);
        try {
            List<CustLevelRule> list = custLevelRuleMapper.selectByExample(c);
            if(null!=list&&list.size()>0){
                return list.get(0).getMinValue().longValue();
        }
        
        } catch (Exception e) {
            LogUtil.error(MODULE,"===========查询一次性金额异常============",e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
       
        return null;
    }
    
}

