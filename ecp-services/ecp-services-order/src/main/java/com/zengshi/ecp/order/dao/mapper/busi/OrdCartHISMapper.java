package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdCartHIS;
import com.zengshi.ecp.order.dao.model.OrdCartHISCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdCartHISMapper {
    Long countByExample(OrdCartHISCriteria example) throws DataAccessException;

    int deleteByExample(OrdCartHISCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdCartHIS record) throws DataAccessException;

    int insertSelective(OrdCartHIS record) throws DataAccessException;

    List<OrdCartHIS> selectByExample(OrdCartHISCriteria example) throws DataAccessException;

    OrdCartHIS selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdCartHIS record, @Param("example") OrdCartHISCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdCartHIS record, @Param("example") OrdCartHISCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdCartHIS record) throws DataAccessException;

    int updateByPrimaryKey(OrdCartHIS record) throws DataAccessException;
}