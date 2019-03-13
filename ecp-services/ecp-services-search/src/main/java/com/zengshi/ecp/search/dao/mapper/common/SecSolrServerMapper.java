package com.zengshi.ecp.search.dao.mapper.common;

import com.zengshi.ecp.search.dao.model.SecSolrServer;
import com.zengshi.ecp.search.dao.model.SecSolrServerCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface SecSolrServerMapper {
    Long countByExample(SecSolrServerCriteria example) throws DataAccessException;

    int deleteByExample(SecSolrServerCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(SecSolrServer record) throws DataAccessException;

    int insertSelective(SecSolrServer record) throws DataAccessException;

    List<SecSolrServer> selectByExample(SecSolrServerCriteria example) throws DataAccessException;

    SecSolrServer selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") SecSolrServer record, @Param("example") SecSolrServerCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") SecSolrServer record, @Param("example") SecSolrServerCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(SecSolrServer record) throws DataAccessException;

    int updateByPrimaryKey(SecSolrServer record) throws DataAccessException;
}