package com.zengshi.ecp.prom.service.busi.constraint.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromUserLimitDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromUserLimitSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class BuyTimesCheckSVImpl extends DefaultCheckSVImpl {

    private static final String MODULE = BuyTimesCheckSVImpl.class.getName();
    
    @Resource
    private IPromUserLimitSV promUserLimitSV;
    /**
     * TODO能否参与促销，是否需要验证
     * 
     * @see com.zengshi.ecp.prom.service.busi.constraint.impl.DefaultCheckSVImpl#isCheck(com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO)
     * @param promConstaintDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean isCheck(PromConstraintDTO promConstaintDTO) throws BusinessException {
        //没有设置限制规则
        if (StringUtil.isEmpty(promConstaintDTO.getLimitTimesType())
                || PromConstants.PromConstraint.LIMITTIMESTYPE_0.equals(promConstaintDTO.getLimitTimesType())) {
            // 促销无设置购买次数门槛 限制次数类型 0无限制 1天 2月 3年
            return Boolean.FALSE;
        }
        //有限制规则 返回true 需要验证
        return Boolean.TRUE;
    }

    /**
     * TODO能否参与促销，验证 区域
     * 
     * @see com.zengshi.ecp.prom.service.busi.constraint.impl.DefaultCheckSVImpl#check(com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO,
     *      com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promConstaintDTO
     *            促销配置限制参数
     * @param promRuleCheckDTO
     *            促销业务参数
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean check(PromConstraintDTO promConstaintDTO,
            PromRuleCheckDTO promRuleCheckDTO) throws BusinessException {
        // 购买次数指 支付成功后的限制 还是？？？ 应该为（包含未支付状态）
        return checkLimitTimes(promConstaintDTO, promRuleCheckDTO);
    }

    /**
     * @function 购买次数验证
     * @param limitCntType
     *            限制次数类型 0无限制 1天 2月 3年
     * @return ture/false
     */
    private boolean checkLimitTimes(PromConstraintDTO promConstaintDTO,
            PromRuleCheckDTO promRuleCheckDTO) throws BusinessException {
        
        PromUserLimitDTO promUserLimitDTO=new PromUserLimitDTO();
        promUserLimitDTO.setLimitType("1");
        promUserLimitDTO.setLimitTypeValue(promConstaintDTO.getLimitTimesType());
        promUserLimitDTO.setPromId(promRuleCheckDTO.getPromId());
        if(!StringUtil.isEmpty(promRuleCheckDTO.getStaffId())){
           promUserLimitDTO.setStaffId(new Long(promRuleCheckDTO.getStaffId()));
        }else{
            promUserLimitDTO.setStaffId(new Long(promRuleCheckDTO.getStaff().getId()));
        }
        
        String optValue=DateUtil.getDateString(DateUtil.YYYYMMDD);
        
        //天控制
        if(PromConstants.PromConstraint.LIMITTIMESTYPE_1.equals(promConstaintDTO.getLimitTimesType())){
            
        }
        //月控制
       if(PromConstants.PromConstraint.LIMITTIMESTYPE_2.equals(promConstaintDTO.getLimitTimesType())){
           optValue=optValue.substring(0, 6);
        }
       //年控制
       if(PromConstants.PromConstraint.LIMITTIMESTYPE_3.equals(promConstaintDTO.getLimitTimesType())){
           optValue=optValue.substring(0, 4);
       }
        promUserLimitDTO.setOptValue(optValue);
        
        PromUserLimitDTO p=promUserLimitSV.query(promUserLimitDTO);
        //为空 判断限制规则
        if(p==null){
            if(new BigDecimal(promConstaintDTO.getLimitTimesTypeValue()).compareTo(new BigDecimal(1))>=0){
                return Boolean.TRUE;
            }
            if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
                throw new BusinessException("prom.400104");
            }
            return Boolean.FALSE;
        }else{
            //非空 验证表规则
            if(p.getRemaindCnt().compareTo(new Long(1))>=0){
                return Boolean.TRUE;
            }
            if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
                throw new BusinessException("prom.400104");
            }
            return Boolean.FALSE;
        }

    }
}
