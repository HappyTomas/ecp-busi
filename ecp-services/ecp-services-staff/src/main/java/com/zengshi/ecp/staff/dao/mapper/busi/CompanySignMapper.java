package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CompanySign;
import com.zengshi.ecp.staff.dao.model.CompanySignCriteria;
import com.zengshi.ecp.staff.dao.model.CompanySignKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CompanySignMapper {
    Long countByExample(CompanySignCriteria example) throws DataAccessException;

    int deleteByExample(CompanySignCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(CompanySignKey key) throws DataAccessException;

    int insert(CompanySign record) throws DataAccessException;

    int insertSelective(CompanySign record) throws DataAccessException;

    List<CompanySign> selectByExample(CompanySignCriteria example) throws DataAccessException;

    CompanySign selectByPrimaryKey(CompanySignKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CompanySign record, @Param("example") CompanySignCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CompanySign record, @Param("example") CompanySignCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CompanySign record) throws DataAccessException;

    int updateByPrimaryKey(CompanySign record) throws DataAccessException;
}