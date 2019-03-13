/**
 * 
 */
package com.zengshi.ecp.staff.service.common.vip.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.mapper.common.CustLevelInfoMapper;
import com.zengshi.ecp.staff.dao.mapper.common.CustLevelRuleMapper;
import com.zengshi.ecp.staff.dao.model.CustLevelInfo;
import com.zengshi.ecp.staff.dao.model.CustLevelInfoCriteria;
import com.zengshi.ecp.staff.dao.model.CustLevelRule;
import com.zengshi.ecp.staff.dao.model.CustLevelRuleCriteria;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelRuleReqDTO;
import com.zengshi.ecp.staff.service.common.vip.interfaces.ICustVipSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月5日下午8:49:04 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version
 * @since JDK 1.7
 */
public class CustVipSVImpl implements ICustVipSV {

    @Resource
    private CustLevelRuleMapper custLevelRuleMapper;

    @Resource
    private CustLevelInfoMapper custLevelInfoMapper;

    @Override
    public CustLevelRule queryCustLevelRule(CustLevelRuleReqDTO info) throws BusinessException {
        List<CustLevelRule> list = new ArrayList<CustLevelRule>();
        CustLevelRuleCriteria c = new CustLevelRuleCriteria();
        c.createCriteria().andMinValueLessThanOrEqualTo(info.getMaxValue())
                .andMaxValueGreaterThanOrEqualTo(info.getMinValue());
        list = custLevelRuleMapper.selectByExample(c);

        return list.get(0);

    }

    @Override
    public String queryCustLevelName(CustLevelInfoResDTO info) throws BusinessException {
        String levelName = "";
        CustLevelInfoCriteria c = new CustLevelInfoCriteria();
        c.createCriteria().andCustLevelCodeEqualTo(info.getCustLevelCode());
        List<CustLevelInfo> list = custLevelInfoMapper.selectByExample(c);
        if (!CollectionUtils.isEmpty(list)) {
            for (CustLevelInfo custLevelInfo : list) {
                levelName = custLevelInfo.getCustLevelName();
            }
            return levelName;
        }

        return levelName;
    }

    @Override
    public CustLevelRule queryCustLevelMinValueAndMaxValue(CustLevelRuleReqDTO info)
            throws BusinessException {

        CustLevelRuleCriteria c = new CustLevelRuleCriteria();
        c.createCriteria().andCustLevelCodeEqualTo(info.getCustLevelCode());
        List<CustLevelRule> list = custLevelRuleMapper.selectByExample(c);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public CustLevelInfoResDTO queryValueGap(CustInfoReqDTO info) throws BusinessException {
        CustLevelInfoResDTO res = new CustLevelInfoResDTO();
        CustLevelInfoCriteria c = new CustLevelInfoCriteria();
        c.createCriteria().andCustLevelCodeEqualTo(info.getCustLevelCode());
        List<CustLevelInfo> list = custLevelInfoMapper.selectByExample(c);
        BigDecimal i = list.get(0).getCustLevelSn();
        i = i.add(new BigDecimal(1));
        c = new CustLevelInfoCriteria();
        c.createCriteria().andCustLevelSnEqualTo(i);
        List<CustLevelInfo> list1 = custLevelInfoMapper.selectByExample(c);
        if (CollectionUtils.isEmpty(list1)) {
           // ObjectCopyUtil.copyObjValue(list1.get(0), res, null, false);
            return res;
        }
        ObjectCopyUtil.copyObjValue(list1.get(0), res, null, false);
        // 查询成长值
        CustLevelRuleCriteria c1 = new CustLevelRuleCriteria();
        c1.createCriteria().andCustLevelCodeEqualTo(list1.get(0).getCustLevelCode());
        List<CustLevelRule> list3 = custLevelRuleMapper.selectByExample(c1);
        // 将下一等级的minValue减去现有的成长值
        long s = list3.get(0).getMinValue().longValueExact();
        long s1 = s - info.getCustGrowValue();
        res.setGapValue(s1);
        return res;
    }

    @Override
    public CustLevelInfoResDTO queryCustLevelInfo(CustLevelInfoReqDTO dto) throws BusinessException {
        
        CustLevelInfoResDTO levelDto = new CustLevelInfoResDTO();
        CustLevelInfoCriteria c = new CustLevelInfoCriteria();
        com.zengshi.ecp.staff.dao.model.CustLevelInfoCriteria.Criteria sql =  c.createCriteria();
        if(StringUtil.isNotBlank(dto.getCustLevelCode())){
            sql.andCustLevelCodeEqualTo(dto.getCustLevelCode());
        }
        if(StringUtil.isNotBlank(dto.getCustCardNum())){
            sql.andCustCardNumEqualTo(dto.getCustCardNum());
        }
        List<CustLevelInfo> list =  custLevelInfoMapper.selectByExample(c);
        if(null!=list&&list.size()==1){
            ObjectCopyUtil.copyObjValue(list.get(0), levelDto, null, false);
        }
        
        return levelDto;
    }

}
