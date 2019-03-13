package com.zengshi.ecp.prom.service.busi.constraint.impl;

import java.math.BigDecimal;

import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class LimitAmountCheckSVImpl extends DefaultCheckSVImpl {

    private static final String MODULE = LimitAmountCheckSVImpl.class.getName();

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
        
        if (StringUtil.isEmpty(promConstaintDTO.getMinBuyCnt())
                && StringUtil.isEmpty(promConstaintDTO.getMaxBuyCnt())) {
            // 促销无设置购买数量限制
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * TODO能否参与促销，验证 购买数量
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
        //购买最小数量
        if (StringUtil.isEmpty(promConstaintDTO.getMinBuyCnt())) {
            promConstaintDTO.setMinBuyCnt("0");
        }
        //购买最大数量
        if (StringUtil.isEmpty(promConstaintDTO.getMaxBuyCnt())) {
            promConstaintDTO.setMaxBuyCnt(String.valueOf(Integer.MAX_VALUE));
        }
        //当前购买量
        if(StringUtil.isEmpty(promRuleCheckDTO.getBuyCnt())){
            return Boolean.TRUE;
        }
        BigDecimal buyCnt = new BigDecimal(promRuleCheckDTO.getBuyCnt());
        
        BigDecimal minCnt = new BigDecimal(promConstaintDTO.getMinBuyCnt());
        BigDecimal maxCnt = new BigDecimal(promConstaintDTO.getMaxBuyCnt());
        
        if (buyCnt.compareTo(minCnt) >= 0 && maxCnt.compareTo(buyCnt) >= 0) {
            // 例如 1<=x<=100
            return Boolean.TRUE;
        }
        if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
            //购买量超过最大限制
            if (maxCnt.compareTo(buyCnt) < 0) {
                String[] key=new String[1];
                //key[0]=promRuleCheckDTO.getGdsName();
                //key[1]=maxCnt.toString();
                key[0]=maxCnt.toString();
                throw new BusinessException("prom.400109",key);
            }
            //购买量小于最小限制 prom.400111
            if (buyCnt.compareTo(minCnt) < 0) {
                String[] key=new String[1];
                key[0]=minCnt.toString();
                throw new BusinessException("prom.400111",key);
            }
        }
        return Boolean.FALSE;
    }
}
