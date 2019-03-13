package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupCatgLimit;
import com.zengshi.ecp.coupon.dao.model.CoupCatgLimitCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupCatgLimitMapper {
    Long countByExample(CoupCatgLimitCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupCatgLimit record) throws DataAccessException;

    int insertSelective(CoupCatgLimit record) throws DataAccessException;

    List<CoupCatgLimit> selectByExample(CoupCatgLimitCriteria example) throws DataAccessException;

    CoupCatgLimit selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupCatgLimit record, @Param("example") CoupCatgLimitCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupCatgLimit record, @Param("example") CoupCatgLimitCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupCatgLimit record) throws DataAccessException;

    int updateByPrimaryKey(CoupCatgLimit record) throws DataAccessException;
}
