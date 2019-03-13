package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdBackCoupon;
import com.zengshi.ecp.order.dao.model.OrdBackCouponCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdBackCouponMapper {
    Long countByExample(OrdBackCouponCriteria example) throws DataAccessException;

    int deleteByExample(OrdBackCouponCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdBackCoupon record) throws DataAccessException;

    int insertSelective(OrdBackCoupon record) throws DataAccessException;

    List<OrdBackCoupon> selectByExample(OrdBackCouponCriteria example) throws DataAccessException;

    OrdBackCoupon selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdBackCoupon record, @Param("example") OrdBackCouponCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdBackCoupon record, @Param("example") OrdBackCouponCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdBackCoupon record) throws DataAccessException;

    int updateByPrimaryKey(OrdBackCoupon record) throws DataAccessException;
}