package com.zengshi.ecp.coupon.dao.mapper.busi.manual;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.coupon.dao.model.CoupInfo;

public interface CoupManualMapper {
    
    /**
     * 
     * updateCoupNum:优惠券领取修改领取数量. <br/> 
     * 
     * @author huanghe5
     * @param coupInfo
     * @return
     * @throws DataAccessException 
     * @since JDK 1.7
     */
    int updateCoupNum(CoupInfo coupInfo) throws DataAccessException;


}
