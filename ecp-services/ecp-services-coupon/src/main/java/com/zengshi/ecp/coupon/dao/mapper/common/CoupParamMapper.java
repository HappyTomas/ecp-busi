package com.zengshi.ecp.coupon.dao.mapper.common;

import com.zengshi.ecp.coupon.dao.model.CoupParam;
import com.zengshi.ecp.coupon.dao.model.CoupParamCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupParamMapper {
    Long countByExample(CoupParamCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String ruleCode) throws DataAccessException;

    int insert(CoupParam record) throws DataAccessException;

    int insertSelective(CoupParam record) throws DataAccessException;

    List<CoupParam> selectByExample(CoupParamCriteria example) throws DataAccessException;

    CoupParam selectByPrimaryKey(String ruleCode) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupParam record, @Param("example") CoupParamCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupParam record, @Param("example") CoupParamCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupParam record) throws DataAccessException;

    int updateByPrimaryKey(CoupParam record) throws DataAccessException;
}
