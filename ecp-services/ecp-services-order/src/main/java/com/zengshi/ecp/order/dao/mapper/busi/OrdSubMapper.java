package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dao.model.OrdSubCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdSubMapper {
    Long countByExample(OrdSubCriteria example) throws DataAccessException;

    int deleteByExample(OrdSubCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String id) throws DataAccessException;

    int insert(OrdSub record) throws DataAccessException;

    int insertSelective(OrdSub record) throws DataAccessException;

    List<OrdSub> selectByExample(OrdSubCriteria example) throws DataAccessException;

    OrdSub selectByPrimaryKey(String id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdSub record, @Param("example") OrdSubCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdSub record, @Param("example") OrdSubCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdSub record) throws DataAccessException;

    int updateByPrimaryKey(OrdSub record) throws DataAccessException;
}