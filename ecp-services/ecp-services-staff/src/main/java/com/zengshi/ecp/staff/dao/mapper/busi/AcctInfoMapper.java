package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AcctInfo;
import com.zengshi.ecp.staff.dao.model.AcctInfoCriteria;
import com.zengshi.ecp.staff.dao.model.AcctInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AcctInfoMapper {
    Long countByExample(AcctInfoCriteria example) throws DataAccessException;

    int deleteByExample(AcctInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(AcctInfoKey key) throws DataAccessException;

    int insert(AcctInfo record) throws DataAccessException;

    int insertSelective(AcctInfo record) throws DataAccessException;

    List<AcctInfo> selectByExample(AcctInfoCriteria example) throws DataAccessException;

    AcctInfo selectByPrimaryKey(AcctInfoKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AcctInfo record, @Param("example") AcctInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AcctInfo record, @Param("example") AcctInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AcctInfo record) throws DataAccessException;

    int updateByPrimaryKey(AcctInfo record) throws DataAccessException;
}