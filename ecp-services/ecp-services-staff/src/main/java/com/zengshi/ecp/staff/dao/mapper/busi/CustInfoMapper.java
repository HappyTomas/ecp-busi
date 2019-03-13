package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.CustInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CustInfoMapper {
    Long countByExample(CustInfoCriteria example) throws DataAccessException;

    int deleteByExample(CustInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CustInfo record) throws DataAccessException;

    int insertSelective(CustInfo record) throws DataAccessException;

    List<CustInfo> selectByExample(CustInfoCriteria example) throws DataAccessException;

    CustInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CustInfo record, @Param("example") CustInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CustInfo record, @Param("example") CustInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CustInfo record) throws DataAccessException;

    int updateByPrimaryKey(CustInfo record) throws DataAccessException;
}