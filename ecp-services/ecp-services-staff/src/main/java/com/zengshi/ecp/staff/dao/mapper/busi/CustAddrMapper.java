package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CustAddr;
import com.zengshi.ecp.staff.dao.model.CustAddrCriteria;
import com.zengshi.ecp.staff.dao.model.CustAddrKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CustAddrMapper {
    Long countByExample(CustAddrCriteria example) throws DataAccessException;

    int deleteByExample(CustAddrCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(CustAddrKey key) throws DataAccessException;

    int insert(CustAddr record) throws DataAccessException;

    int insertSelective(CustAddr record) throws DataAccessException;

    List<CustAddr> selectByExample(CustAddrCriteria example) throws DataAccessException;

    CustAddr selectByPrimaryKey(CustAddrKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CustAddr record, @Param("example") CustAddrCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CustAddr record, @Param("example") CustAddrCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CustAddr record) throws DataAccessException;

    int updateByPrimaryKey(CustAddr record) throws DataAccessException;
}