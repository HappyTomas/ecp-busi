package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdCartItemHIS;
import com.zengshi.ecp.order.dao.model.OrdCartItemHISCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdCartItemHISMapper {
    Long countByExample(OrdCartItemHISCriteria example) throws DataAccessException;

    int deleteByExample(OrdCartItemHISCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdCartItemHIS record) throws DataAccessException;

    int insertSelective(OrdCartItemHIS record) throws DataAccessException;

    List<OrdCartItemHIS> selectByExample(OrdCartItemHISCriteria example) throws DataAccessException;

    OrdCartItemHIS selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdCartItemHIS record, @Param("example") OrdCartItemHISCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdCartItemHIS record, @Param("example") OrdCartItemHISCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdCartItemHIS record) throws DataAccessException;

    int updateByPrimaryKey(OrdCartItemHIS record) throws DataAccessException;
}