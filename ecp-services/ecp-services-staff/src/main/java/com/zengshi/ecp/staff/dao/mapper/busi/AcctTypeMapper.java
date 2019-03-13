package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AcctType;
import com.zengshi.ecp.staff.dao.model.AcctTypeCriteria;
import com.zengshi.ecp.staff.dao.model.AcctTypeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AcctTypeMapper {
    Long countByExample(AcctTypeCriteria example) throws DataAccessException;

    int deleteByExample(AcctTypeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(AcctTypeKey key) throws DataAccessException;

    int insert(AcctType record) throws DataAccessException;

    int insertSelective(AcctType record) throws DataAccessException;

    List<AcctType> selectByExample(AcctTypeCriteria example) throws DataAccessException;

    AcctType selectByPrimaryKey(AcctTypeKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AcctType record, @Param("example") AcctTypeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AcctType record, @Param("example") AcctTypeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AcctType record) throws DataAccessException;

    int updateByPrimaryKey(AcctType record) throws DataAccessException;
}