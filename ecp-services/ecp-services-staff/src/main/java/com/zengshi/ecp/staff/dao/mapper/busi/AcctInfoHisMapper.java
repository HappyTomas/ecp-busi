package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.AcctInfoHis;
import com.zengshi.ecp.staff.dao.model.AcctInfoHisCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AcctInfoHisMapper {
    Long countByExample(AcctInfoHisCriteria example) throws DataAccessException;

    int deleteByExample(AcctInfoHisCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(AcctInfoHis record) throws DataAccessException;

    int insertSelective(AcctInfoHis record) throws DataAccessException;

    List<AcctInfoHis> selectByExample(AcctInfoHisCriteria example) throws DataAccessException;

    AcctInfoHis selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") AcctInfoHis record, @Param("example") AcctInfoHisCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") AcctInfoHis record, @Param("example") AcctInfoHisCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(AcctInfoHis record) throws DataAccessException;

    int updateByPrimaryKey(AcctInfoHis record) throws DataAccessException;
}