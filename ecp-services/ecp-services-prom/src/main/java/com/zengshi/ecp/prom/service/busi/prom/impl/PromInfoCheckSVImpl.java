package com.zengshi.ecp.prom.service.busi.prom.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.service.busi.prom.interfaces.IPromInfoCheckSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromInfoCheckSVImpl extends GeneralSQLSVImpl implements IPromInfoCheckSV {

    private static final String MODULE = PromInfoCheckSVImpl.class.getName();

    /**
     * TODO促销基本信息，是否需要验证
     * @see com.zengshi.ecp.prom.service.busi.prom.interfaces.IPromInfoCheckSV#isCheck(java.math.BigDecimal, java.math.BigDecimal, java.util.Date, com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO)
     * @param gdsId
     * @param skuId
     * @param date
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean isCheck(Long gdsId, Long skuId, Date date, PromInfoDTO promInfoDTO)
            throws BusinessException {
        return Boolean.TRUE;
    }

    /**
     * TODO促销基本信息验证 状态 促销开始时间 促销截止时间等
     * @see com.zengshi.ecp.prom.service.busi.prom.interfaces.IPromInfoCheckSV#check(java.math.BigDecimal, java.math.BigDecimal, java.util.Date, com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO)
     * @param gdsId
     * @param skuId
     * @param date
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean check(Long gdsId, Long skuId, Date date, PromInfoDTO promInfoDTO)
            throws BusinessException {
        
        // 促销状态 10 有效
        if (!"10".equals(promInfoDTO.getStatus())) {
            return Boolean.FALSE;
        }
        //时间范围比较  这里有问题 应该需要提供>= 或者<= after before不提供等于功能 
        if(!DateUtil.isBetweenTime(new Timestamp(promInfoDTO.getStartTime().getTime()), new Timestamp(promInfoDTO.getEndTime().getTime()), new Timestamp(date.getTime()))){
            return Boolean.FALSE;
        }
        /*
         * if (!date.after(promInfoDTO.getStartTime()) || !date.before(promInfoDTO.getEndTime())) {
         * return false; }
         */

        return Boolean.TRUE;
    }
}
