package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdMainCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdMainMapper {
    Long countByExample(OrdMainCriteria example) throws DataAccessException;

    int deleteByExample(OrdMainCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String id) throws DataAccessException;

    int insert(OrdMain record) throws DataAccessException;

    int insertSelective(OrdMain record) throws DataAccessException;

    List<OrdMain> selectByExample(OrdMainCriteria example) throws DataAccessException;

    OrdMain selectByPrimaryKey(String id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdMain record, @Param("example") OrdMainCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdMain record, @Param("example") OrdMainCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdMain record) throws DataAccessException;

    int updateByPrimaryKey(OrdMain record) throws DataAccessException;
}