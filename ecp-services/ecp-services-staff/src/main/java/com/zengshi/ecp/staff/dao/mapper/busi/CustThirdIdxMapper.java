package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CustThirdIdx;
import com.zengshi.ecp.staff.dao.model.CustThirdIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CustThirdIdxMapper {
    Long countByExample(CustThirdIdxCriteria example) throws DataAccessException;

    int deleteByExample(CustThirdIdxCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CustThirdIdx record) throws DataAccessException;

    int insertSelective(CustThirdIdx record) throws DataAccessException;

    List<CustThirdIdx> selectByExample(CustThirdIdxCriteria example) throws DataAccessException;

    CustThirdIdx selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CustThirdIdx record, @Param("example") CustThirdIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CustThirdIdx record, @Param("example") CustThirdIdxCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CustThirdIdx record) throws DataAccessException;

    int updateByPrimaryKey(CustThirdIdx record) throws DataAccessException;
}