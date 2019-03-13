package com.zengshi.ecp.staff.service.busi.custinfo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.mapper.common.CustGrowRuleMapper;
import com.zengshi.ecp.staff.dao.model.CustGrowRule;
import com.zengshi.ecp.staff.dao.model.CustGrowRuleCriteria;
import com.zengshi.ecp.staff.dao.model.CustGrowRuleCriteria.Criteria;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustGrowRuleSV;
import com.zengshi.paas.utils.LogUtil;

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
public class CustGrowRuleByLoginSVImpl implements ICustGrowRuleSV{
    
    private static final String MODULE = CustGrowRuleByLoginSVImpl.class.getName();
    
    @Resource
    private CustGrowRuleMapper custGrowRuleMapper;

    @Override
    public Long getGrowByParamRule(PayQuartzInfoRequest req) throws BusinessException {
        Long growValue = null;
        //判断来源类型是否是02
        if(StaffConstants.LOGIN_TYPE.equals(req.getSourceType())){
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
                    growValue = getGrowbyMoney(req,list.get(0));
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
    public Long getGrowbyMoney(PayQuartzInfoRequest req,CustGrowRule rule) throws BusinessException{
        
      
         
            return rule.getGrowValue();
        
    }
    


   
    
    
}

