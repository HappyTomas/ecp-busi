package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CustAuthstr;
import com.zengshi.ecp.staff.dao.model.CustAuthstrCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface CustAuthstrMapper {
    Long countByExample(CustAuthstrCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CustAuthstr record) throws DataAccessException;

    int insertSelective(CustAuthstr record) throws DataAccessException;

    List<CustAuthstr> selectByExample(CustAuthstrCriteria example) throws DataAccessException;

    CustAuthstr selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByPrimaryKeySelective(CustAuthstr record) throws DataAccessException;

    int updateByPrimaryKey(CustAuthstr record) throws DataAccessException;

    void insertBatch(List<CustAuthstr> recordLst) throws DataAccessException;
}