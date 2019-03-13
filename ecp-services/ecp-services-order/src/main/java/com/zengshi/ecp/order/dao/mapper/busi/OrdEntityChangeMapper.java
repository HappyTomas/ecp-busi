package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdEntityChange;
import com.zengshi.ecp.order.dao.model.OrdEntityChangeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdEntityChangeMapper {
    Long countByExample(OrdEntityChangeCriteria example) throws DataAccessException;

    int deleteByExample(OrdEntityChangeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdEntityChange record) throws DataAccessException;

    int insertSelective(OrdEntityChange record) throws DataAccessException;

    List<OrdEntityChange> selectByExample(OrdEntityChangeCriteria example) throws DataAccessException;

    OrdEntityChange selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdEntityChange record, @Param("example") OrdEntityChangeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdEntityChange record, @Param("example") OrdEntityChangeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdEntityChange record) throws DataAccessException;

    int updateByPrimaryKey(OrdEntityChange record) throws DataAccessException;
}