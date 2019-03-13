package com.zengshi.ecp.sys.dao.mapper.common;

import com.zengshi.ecp.sys.dao.model.BaseExpress;
import com.zengshi.ecp.sys.dao.model.BaseExpressCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface BaseExpressMapper {
    Long countByExample(BaseExpressCriteria example) throws DataAccessException;

    int deleteByExample(BaseExpressCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(BaseExpress record) throws DataAccessException;

    int insertSelective(BaseExpress record) throws DataAccessException;

    List<BaseExpress> selectByExample(BaseExpressCriteria example) throws DataAccessException;

    BaseExpress selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") BaseExpress record, @Param("example") BaseExpressCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") BaseExpress record, @Param("example") BaseExpressCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(BaseExpress record) throws DataAccessException;

    int updateByPrimaryKey(BaseExpress record) throws DataAccessException;
}
