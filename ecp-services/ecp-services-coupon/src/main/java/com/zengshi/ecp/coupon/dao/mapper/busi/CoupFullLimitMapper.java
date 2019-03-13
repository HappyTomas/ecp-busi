package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupFullLimit;
import com.zengshi.ecp.coupon.dao.model.CoupFullLimitCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupFullLimitMapper {
    Long countByExample(CoupFullLimitCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupFullLimit record) throws DataAccessException;

    int insertSelective(CoupFullLimit record) throws DataAccessException;

    List<CoupFullLimit> selectByExample(CoupFullLimitCriteria example) throws DataAccessException;

    CoupFullLimit selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupFullLimit record, @Param("example") CoupFullLimitCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupFullLimit record, @Param("example") CoupFullLimitCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupFullLimit record) throws DataAccessException;

    int updateByPrimaryKey(CoupFullLimit record) throws DataAccessException;
}
