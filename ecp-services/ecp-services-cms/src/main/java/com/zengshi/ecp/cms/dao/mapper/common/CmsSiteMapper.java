package com.zengshi.ecp.cms.dao.mapper.common;

import com.zengshi.ecp.cms.dao.model.CmsSite;
import com.zengshi.ecp.cms.dao.model.CmsSiteCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CmsSiteMapper {
    Long countByExample(CmsSiteCriteria example) throws DataAccessException;

    int deleteByExample(CmsSiteCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CmsSite record) throws DataAccessException;

    int insertSelective(CmsSite record) throws DataAccessException;

    List<CmsSite> selectByExample(CmsSiteCriteria example) throws DataAccessException;

    CmsSite selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CmsSite record, @Param("example") CmsSiteCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CmsSite record, @Param("example") CmsSiteCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CmsSite record) throws DataAccessException;

    int updateByPrimaryKey(CmsSite record) throws DataAccessException;
}
