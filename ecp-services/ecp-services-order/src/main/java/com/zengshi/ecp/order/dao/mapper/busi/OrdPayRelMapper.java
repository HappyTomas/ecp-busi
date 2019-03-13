package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdPayRel;
import com.zengshi.ecp.order.dao.model.OrdPayRelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdPayRelMapper {
    Long countByExample(OrdPayRelCriteria example) throws DataAccessException;

    int deleteByExample(OrdPayRelCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String id) throws DataAccessException;

    int insert(OrdPayRel record) throws DataAccessException;

    int insertSelective(OrdPayRel record) throws DataAccessException;

    List<OrdPayRel> selectByExample(OrdPayRelCriteria example) throws DataAccessException;

    OrdPayRel selectByPrimaryKey(String id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdPayRel record, @Param("example") OrdPayRelCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdPayRel record, @Param("example") OrdPayRelCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdPayRel record) throws DataAccessException;

    int updateByPrimaryKey(OrdPayRel record) throws DataAccessException;
}