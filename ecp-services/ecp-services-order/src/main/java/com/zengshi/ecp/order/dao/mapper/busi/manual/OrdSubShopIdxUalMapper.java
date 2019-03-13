package com.zengshi.ecp.order.dao.mapper.busi.manual;

import com.zengshi.ecp.order.dao.model.OrdSubShopIdx;
import com.zengshi.ecp.order.dao.model.OrdSubShopIdxCriteria;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface OrdSubShopIdxUalMapper {
	
    Long sumOrderAmountForShopByExample(OrdSubShopIdxCriteria example) throws DataAccessException;
    
    /**
     * 查询去除退款退货后的销售详细
     * @param example
     * @return
     * @throws DataAccessException
     */
    List<OrdSubShopIdx> selectBySellGdsExample(OrdSubShopIdxCriteria example) throws DataAccessException;
    
    Long countBySellGdsExample(OrdSubShopIdxCriteria example) throws DataAccessException;
    
    Long countOrderIdByExample(OrdSubShopIdxCriteria example) throws DataAccessException;
    
    Long sumDiscountMoneyByExample(OrdSubShopIdxCriteria example) throws DataAccessException;

    Long sumOrderAmountByExample(OrdSubShopIdxCriteria example) throws DataAccessException;

    Long sumBasicMoneyByExample(OrdSubShopIdxCriteria example) throws DataAccessException;
}