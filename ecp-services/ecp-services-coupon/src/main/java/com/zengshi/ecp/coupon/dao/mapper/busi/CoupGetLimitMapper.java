package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupGetLimit;
import com.zengshi.ecp.coupon.dao.model.CoupGetLimitCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupGetLimitMapper {
    Long countByExample(CoupGetLimitCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupGetLimit record) throws DataAccessException;

    int insertSelective(CoupGetLimit record) throws DataAccessException;

    List<CoupGetLimit> selectByExampleWithBLOBs(CoupGetLimitCriteria example) throws DataAccessException;

    List<CoupGetLimit> selectByExample(CoupGetLimitCriteria example) throws DataAccessException;

    CoupGetLimit selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupGetLimit record, @Param("example") CoupGetLimitCriteria example) throws DataAccessException;

    int updateByExampleWithBLOBs(@Param("record") CoupGetLimit record, @Param("example") CoupGetLimitCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupGetLimit record, @Param("example") CoupGetLimitCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupGetLimit record) throws DataAccessException;

    int updateByPrimaryKeyWithBLOBs(CoupGetLimit record) throws DataAccessException;

    int updateByPrimaryKey(CoupGetLimit record) throws DataAccessException;
}
