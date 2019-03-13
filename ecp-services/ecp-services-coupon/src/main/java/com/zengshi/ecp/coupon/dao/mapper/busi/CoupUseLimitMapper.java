package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupUseLimit;
import com.zengshi.ecp.coupon.dao.model.CoupUseLimitCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupUseLimitMapper {
    Long countByExample(CoupUseLimitCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupUseLimit record) throws DataAccessException;

    int insertSelective(CoupUseLimit record) throws DataAccessException;

    List<CoupUseLimit> selectByExample(CoupUseLimitCriteria example) throws DataAccessException;

    CoupUseLimit selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupUseLimit record, @Param("example") CoupUseLimitCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupUseLimit record, @Param("example") CoupUseLimitCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupUseLimit record) throws DataAccessException;

    int updateByPrimaryKey(CoupUseLimit record) throws DataAccessException;
}
