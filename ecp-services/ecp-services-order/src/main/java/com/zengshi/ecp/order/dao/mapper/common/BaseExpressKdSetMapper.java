package com.zengshi.ecp.order.dao.mapper.common;

import com.zengshi.ecp.order.dao.model.BaseExpressKdSet;
import com.zengshi.ecp.order.dao.model.BaseExpressKdSetCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface BaseExpressKdSetMapper {
    Long countByExample(BaseExpressKdSetCriteria example) throws DataAccessException;

    int deleteByExample(BaseExpressKdSetCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(BaseExpressKdSet record) throws DataAccessException;

    int insertSelective(BaseExpressKdSet record) throws DataAccessException;

    List<BaseExpressKdSet> selectByExample(BaseExpressKdSetCriteria example) throws DataAccessException;

    BaseExpressKdSet selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") BaseExpressKdSet record, @Param("example") BaseExpressKdSetCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") BaseExpressKdSet record, @Param("example") BaseExpressKdSetCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(BaseExpressKdSet record) throws DataAccessException;

    int updateByPrimaryKey(BaseExpressKdSet record) throws DataAccessException;
}