package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupBlackLimit;
import com.zengshi.ecp.coupon.dao.model.CoupBlackLimitCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupBlackLimitMapper {
    Long countByExample(CoupBlackLimitCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupBlackLimit record) throws DataAccessException;

    int insertSelective(CoupBlackLimit record) throws DataAccessException;

    List<CoupBlackLimit> selectByExample(CoupBlackLimitCriteria example) throws DataAccessException;

    CoupBlackLimit selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupBlackLimit record, @Param("example") CoupBlackLimitCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupBlackLimit record, @Param("example") CoupBlackLimitCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupBlackLimit record) throws DataAccessException;

    int updateByPrimaryKey(CoupBlackLimit record) throws DataAccessException;
}
