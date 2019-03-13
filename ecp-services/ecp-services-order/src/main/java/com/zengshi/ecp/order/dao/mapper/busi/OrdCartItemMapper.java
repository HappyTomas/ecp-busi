package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdCartItem;
import com.zengshi.ecp.order.dao.model.OrdCartItemCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdCartItemMapper {
    Long countByExample(OrdCartItemCriteria example) throws DataAccessException;

    int deleteByExample(OrdCartItemCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdCartItem record) throws DataAccessException;

    int insertSelective(OrdCartItem record) throws DataAccessException;

    List<OrdCartItem> selectByExample(OrdCartItemCriteria example) throws DataAccessException;

    OrdCartItem selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdCartItem record, @Param("example") OrdCartItemCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdCartItem record, @Param("example") OrdCartItemCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdCartItem record) throws DataAccessException;

    int updateByPrimaryKey(OrdCartItem record) throws DataAccessException;
}