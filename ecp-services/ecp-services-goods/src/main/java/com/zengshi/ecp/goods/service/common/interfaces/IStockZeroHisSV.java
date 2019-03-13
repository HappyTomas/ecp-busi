/** 
 * Project Name:ecp-services-goods-server 
 * File Name:IStockZeroHisSV.java 
 * Package Name:com.zengshi.ecp.goods.service.common.interfaces 
 * Date:2017年9月26日下午3:59:26 
 * Copyright (c) 2017, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.stock.StockZeroHisReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockZeroHisRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: 零库存历史记录表<br>
 * Date:2017年9月26日下午3:59:26  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IStockZeroHisSV {
    
    /**
     * 
     * saveZeroHis:保存零库存记录. <br/> 
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void saveZeroHis(StockZeroHisReqDTO reqDTO) throws BusinessException;
    
    
    /**
     * 
     * deleteZeroHisByPK:根据主键删除零库存记录,因gdsId与skuId是复合主键所以在根据主键删除时gdsId与skuId不允许为空. <br/> 
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void deleteZeroHisByPK(StockZeroHisReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * queryZeroHisRecord:查询库存零记录数据. <br/> 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    List<StockZeroHisRespDTO> queryZeroHisRecord(StockZeroHisReqDTO reqDTO) throws BusinessException;

}

