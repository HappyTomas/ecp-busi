package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CustGrowInfo;
import com.zengshi.ecp.staff.dao.model.CustGrowInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CustGrowInfoMapper {
    Long countByExample(CustGrowInfoCriteria example) throws DataAccessException;

    int deleteByExample(CustGrowInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CustGrowInfo record) throws DataAccessException;

    int insertSelective(CustGrowInfo record) throws DataAccessException;

    List<CustGrowInfo> selectByExample(CustGrowInfoCriteria example) throws DataAccessException;

    CustGrowInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CustGrowInfo record, @Param("example") CustGrowInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CustGrowInfo record, @Param("example") CustGrowInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CustGrowInfo record) throws DataAccessException;

    int updateByPrimaryKey(CustGrowInfo record) throws DataAccessException;
}