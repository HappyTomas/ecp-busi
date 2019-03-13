package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdSubShare;
import com.zengshi.ecp.order.dao.model.OrdSubShareCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdSubShareMapper {
    Long countByExample(OrdSubShareCriteria example) throws DataAccessException;

    int deleteByExample(OrdSubShareCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdSubShare record) throws DataAccessException;

    int insertSelective(OrdSubShare record) throws DataAccessException;

    List<OrdSubShare> selectByExample(OrdSubShareCriteria example) throws DataAccessException;

    OrdSubShare selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdSubShare record, @Param("example") OrdSubShareCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdSubShare record, @Param("example") OrdSubShareCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdSubShare record) throws DataAccessException;

    int updateByPrimaryKey(OrdSubShare record) throws DataAccessException;
}