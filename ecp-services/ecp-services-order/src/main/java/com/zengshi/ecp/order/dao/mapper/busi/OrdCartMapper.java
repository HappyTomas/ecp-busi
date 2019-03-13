package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdCart;
import com.zengshi.ecp.order.dao.model.OrdCartCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdCartMapper {
    Long countByExample(OrdCartCriteria example) throws DataAccessException;

    int deleteByExample(OrdCartCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdCart record) throws DataAccessException;

    int insertSelective(OrdCart record) throws DataAccessException;

    List<OrdCart> selectByExample(OrdCartCriteria example) throws DataAccessException;

    OrdCart selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdCart record, @Param("example") OrdCartCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdCart record, @Param("example") OrdCartCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdCart record) throws DataAccessException;

    int updateByPrimaryKey(OrdCart record) throws DataAccessException;
}