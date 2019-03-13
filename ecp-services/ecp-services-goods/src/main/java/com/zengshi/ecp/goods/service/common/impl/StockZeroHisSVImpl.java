/** 
 * Project Name:ecp-services-goods-server 
 * File Name:StockZeroHisSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.common.impl 
 * Date:2017年9月27日下午5:31:34 
 * Copyright (c) 2017, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.zengshi.ecp.goods.dao.mapper.common.StockZeroHisMapper;
import com.zengshi.ecp.goods.dao.model.StockZeroHis;
import com.zengshi.ecp.goods.dao.model.StockZeroHisKey;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockZeroHisReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockZeroHisRespDTO;
import com.zengshi.ecp.goods.service.common.interfaces.IStockZeroHisSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: 零库存记录服务<br>
 * Date:2017年9月27日下午5:31:34  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class StockZeroHisSVImpl extends AbstractSVImpl implements IStockZeroHisSV {
    
    @Resource
    private StockZeroHisMapper stockZeroHisMapper;

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.goods.service.common.interfaces.IStockZeroHisSV#saveZeroHis(com.zengshi.ecp.goods.dubbo.dto.stock.StockZeroHisReqDTO) 
     */
    @Override
    public void saveZeroHis(StockZeroHisReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO.getGdsId(), "reqDTO.gdsId");
        paramNullCheck(reqDTO.getSkuId(), "reqDTO.skuId");
        StockZeroHis record = new StockZeroHis();
        BeanUtils.copyProperties(reqDTO, record);
        record.setGdsId(reqDTO.getGdsId());
        record.setSkuId(reqDTO.getSkuId());
        preInsert(reqDTO, record);
        stockZeroHisMapper.insert(record);
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.goods.service.common.interfaces.IStockZeroHisSV#deleteZeroHisByPK(com.zengshi.ecp.goods.dubbo.dto.stock.StockZeroHisReqDTO) 
     */
    @Override
    public void deleteZeroHisByPK(StockZeroHisReqDTO reqDTO) throws BusinessException {
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.goods.service.common.interfaces.IStockZeroHisSV#queryZeroHisRecord(com.zengshi.ecp.goods.dubbo.dto.stock.StockZeroHisReqDTO) 
     */
    @Override
    public List<StockZeroHisRespDTO> queryZeroHisRecord(StockZeroHisReqDTO reqDTO)
            throws BusinessException {
        return null;
    }
    

}

