package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupDetailLog;
import com.zengshi.ecp.coupon.dao.model.CoupDetailLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupDetailLogMapper {
    Long countByExample(CoupDetailLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupDetailLog record) throws DataAccessException;

    int insertSelective(CoupDetailLog record) throws DataAccessException;

    List<CoupDetailLog> selectByExample(CoupDetailLogCriteria example) throws DataAccessException;

    CoupDetailLog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupDetailLog record, @Param("example") CoupDetailLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupDetailLog record, @Param("example") CoupDetailLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupDetailLog record) throws DataAccessException;

    int updateByPrimaryKey(CoupDetailLog record) throws DataAccessException;
}
