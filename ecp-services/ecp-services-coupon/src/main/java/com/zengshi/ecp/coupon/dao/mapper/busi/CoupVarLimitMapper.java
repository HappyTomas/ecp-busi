package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupVarLimit;
import com.zengshi.ecp.coupon.dao.model.CoupVarLimitCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupVarLimitMapper {
    Long countByExample(CoupVarLimitCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupVarLimit record) throws DataAccessException;

    int insertSelective(CoupVarLimit record) throws DataAccessException;

    List<CoupVarLimit> selectByExample(CoupVarLimitCriteria example) throws DataAccessException;

    CoupVarLimit selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupVarLimit record, @Param("example") CoupVarLimitCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupVarLimit record, @Param("example") CoupVarLimitCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupVarLimit record) throws DataAccessException;

    int updateByPrimaryKey(CoupVarLimit record) throws DataAccessException;
}
