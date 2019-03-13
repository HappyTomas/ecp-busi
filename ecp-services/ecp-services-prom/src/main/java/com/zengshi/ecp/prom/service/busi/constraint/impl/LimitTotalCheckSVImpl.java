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
public class LimitTotalCheckSVImpl extends DefaultCheckSVImpl {

    private static final String MODULE = LimitTotalCheckSVImpl.class.getName();

    @Resource
    private IPromUserLimitSV promUserLimitSV;
    /**
     * TODO能否参与促销，是否需要验证
     * 
     * @see com.zengshi.ecp.prom.service.busi.constraint.impl.DefaultCheckSVImpl#isCheck(com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO)
     * @param promConstaintDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    @Override
    public boolean isCheck(PromConstraintDTO promConstaintDTO) throws BusinessException {
        
       if (StringUtil.isEmpty(promConstaintDTO.getLimitTotalType())
                ||PromConstants.PromConstraint.LIMITTOTALTYPE_0.equals(promConstaintDTO.getLimitTotalType())) {
            // 促销无设置购买总量门槛 限制总量类型 0无限制 1天 2月 3年
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * TODO能否参与促销，验证购买总量
     * 
     * @see com.zengshi.ecp.prom.service.busi.constraint.impl.DefaultCheckSVImpl#check(com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO,
     *      com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promConstaintDTO
     * @param promRuleCheckDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    @Override
    public boolean check(PromConstraintDTO promConstaintDTO,
            PromRuleCheckDTO promRuleCheckDTO) throws BusinessException {
        return checkLimitTotal(promConstaintDTO, promRuleCheckDTO);
    }

    /**
     * 购买量验证
     * 
     * @param limitTotalType
     *            限制量类型 0无限制 1天 2月 3年
     * @param promRuleCheckDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    private boolean checkLimitTotal(PromConstraintDTO promConstaintDTO,
            PromRuleCheckDTO promRuleCheckDTO) throws BusinessException {

        PromUserLimitDTO promUserLimitDTO=new PromUserLimitDTO();
        promUserLimitDTO.setLimitType("2");
        promUserLimitDTO.setLimitTypeValue(promConstaintDTO.getLimitTotalType());
        promUserLimitDTO.setPromId(promRuleCheckDTO.getPromId());
        promUserLimitDTO.setStaffId(new Long(promRuleCheckDTO.getStaffId()));
        
        String optValue=DateUtil.getDateString(DateUtil.YYYYMMDD);
        //天控制
        if(PromConstants.PromConstraint.LIMITTOTALTYPE_1.equals(promConstaintDTO.getLimitTotalType())){
            
        }
        //月控制
       if(PromConstants.PromConstraint.LIMITTOTALTYPE_2.equals(promConstaintDTO.getLimitTotalType())){
           optValue=optValue.substring(0, 6);
        }
       //年控制
       if(PromConstants.PromConstraint.LIMITTOTALTYPE_3.equals(promConstaintDTO.getLimitTotalType())){
           optValue=optValue.substring(0, 4);
       }
        promUserLimitDTO.setOptValue(optValue);
        
        PromUserLimitDTO p=promUserLimitSV.query(promUserLimitDTO);
        //为空 判断限制规则
        if(p==null){
            if(StringUtil.isEmpty(promRuleCheckDTO.getBuyCnt())){
                return Boolean.TRUE;
            }
            if(new BigDecimal(promConstaintDTO.getLimitTotalTypeValue()).compareTo(new BigDecimal(promRuleCheckDTO.getBuyCnt()))>=0){
                return Boolean.TRUE;
            }
            if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
                String[] key=new String[1];
                key[0]=promConstaintDTO.getLimitTotalTypeValue();
                throw new BusinessException("prom.400109",key);
            }
            return Boolean.FALSE;
        }else{
            //非空 验证表规则
            if(StringUtil.isEmpty(promRuleCheckDTO.getBuyCnt())){
                return Boolean.TRUE;
            }
            if(p.getRemaindCnt().compareTo(new Long(promRuleCheckDTO.getBuyCnt()))>=0){
                return Boolean.TRUE;
            }
            if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
                String[] key=new String[1];
                key[0]=p.getRemaindCnt().toString();
                throw new BusinessException("prom.400109",key);
            }
            return Boolean.FALSE;
        }

    }
}
