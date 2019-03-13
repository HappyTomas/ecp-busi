package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CustThirdCode;
import com.zengshi.ecp.staff.dao.model.CustThirdCodeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CustThirdCodeMapper {
    Long countByExample(CustThirdCodeCriteria example) throws DataAccessException;

    int deleteByExample(CustThirdCodeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CustThirdCode record) throws DataAccessException;

    int insertSelective(CustThirdCode record) throws DataAccessException;

    List<CustThirdCode> selectByExample(CustThirdCodeCriteria example) throws DataAccessException;

    CustThirdCode selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CustThirdCode record, @Param("example") CustThirdCodeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CustThirdCode record, @Param("example") CustThirdCodeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CustThirdCode record) throws DataAccessException;

    int updateByPrimaryKey(CustThirdCode record) throws DataAccessException;
}