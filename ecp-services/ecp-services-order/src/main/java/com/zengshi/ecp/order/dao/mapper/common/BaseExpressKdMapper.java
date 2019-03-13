package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.BaseExpressKd;
import com.zengshi.ecp.order.dao.model.BaseExpressKdCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface BaseExpressKdMapper {
    Long countByExample(BaseExpressKdCriteria example) throws DataAccessException;

    int deleteByExample(BaseExpressKdCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(BaseExpressKd record) throws DataAccessException;

    int insertSelective(BaseExpressKd record) throws DataAccessException;

    List<BaseExpressKd> selectByExample(BaseExpressKdCriteria example) throws DataAccessException;

    BaseExpressKd selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") BaseExpressKd record, @Param("example") BaseExpressKdCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") BaseExpressKd record, @Param("example") BaseExpressKdCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(BaseExpressKd record) throws DataAccessException;

    int updateByPrimaryKey(BaseExpressKd record) throws DataAccessException;
}