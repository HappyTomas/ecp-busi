package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupInfoLog;
import com.zengshi.ecp.coupon.dao.model.CoupInfoLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupInfoLogMapper {
    Long countByExample(CoupInfoLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupInfoLog record) throws DataAccessException;

    int insertSelective(CoupInfoLog record) throws DataAccessException;

    List<CoupInfoLog> selectByExample(CoupInfoLogCriteria example) throws DataAccessException;

    CoupInfoLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupInfoLog record, @Param("example") CoupInfoLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupInfoLog record, @Param("example") CoupInfoLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupInfoLog record) throws DataAccessException;

    int updateByPrimaryKey(CoupInfoLog record) throws DataAccessException;
}
