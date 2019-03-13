package com.zengshi.ecp.prom.dubbo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.prom.dao.mapper.busi.PromSkuLimitMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromStockLimitMapper;
import com.zengshi.ecp.prom.dao.model.PromSkuLimitCriteria;
import com.zengshi.ecp.prom.dao.model.PromStockLimit;
import com.zengshi.ecp.prom.dao.model.PromStockLimitCriteria;
import com.zengshi.ecp.prom.dubbo.dto.CommPromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromStockLimitDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromUtilRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-13 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromUtilRSVImpl implements IPromUtilRSV {
    
    @Resource
    private PromStockLimitMapper promStockLimitMapper;
    
    /**
     * 根据beanid获得context bean
     * @param beanId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CommPromTypeDTO utilGetCommPromTypeDTO(String beanId) throws BusinessException{
        return EcpFrameContextHolder.getBean(beanId, CommPromTypeDTO.class);
    }

    @Override
    public PromStockLimitDTO queryPromStockLimit(String promId, String skuId) throws BusinessException {
        
        if (StringUtil.isBlank(promId)||StringUtil.isBlank(skuId)) {
            throw new BusinessException("入参不能为空");
        }
        
        PromStockLimitCriteria criteria = new PromStockLimitCriteria();
        criteria.createCriteria().andPromIdEqualTo(Long.valueOf(promId)).andSkuIdEqualTo(Long.valueOf(skuId));
        
        List<PromStockLimit>  records = promStockLimitMapper.selectByExample(criteria);
        if (CollectionUtils.isNotEmpty(records)&&records.size()>1) {
            throw new BusinessException("促销商品["+skuId+"]的购买情况存在多条记录，数据异常！");
        }
        if (CollectionUtils.isNotEmpty(records)) {
            PromStockLimitDTO dto = new PromStockLimitDTO();
            ObjectCopyUtil.copyObjValue(records.get(0), dto, null, false);
            return dto;
        }
        return null;
    }
 
}
