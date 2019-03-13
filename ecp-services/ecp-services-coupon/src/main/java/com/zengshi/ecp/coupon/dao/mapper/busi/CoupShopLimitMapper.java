package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupShopLimit;
import com.zengshi.ecp.coupon.dao.model.CoupShopLimitCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupShopLimitMapper {
    Long countByExample(CoupShopLimitCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupShopLimit record) throws DataAccessException;

    int insertSelective(CoupShopLimit record) throws DataAccessException;

    List<CoupShopLimit> selectByExample(CoupShopLimitCriteria example) throws DataAccessException;

    CoupShopLimit selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupShopLimit record, @Param("example") CoupShopLimitCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupShopLimit record, @Param("example") CoupShopLimitCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupShopLimit record) throws DataAccessException;

    int updateByPrimaryKey(CoupShopLimit record) throws DataAccessException;
}
