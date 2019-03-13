package com.zengshi.ecp.prom.dao.mapper.busi;

import com.zengshi.ecp.prom.dao.model.Prom2Solr;
import com.zengshi.ecp.prom.dao.model.Prom2SolrCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface Prom2SolrMapper {
    Long countByExample(Prom2SolrCriteria example) throws DataAccessException;

    int deleteByExample(Prom2SolrCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(Prom2Solr record) throws DataAccessException;

    int insertSelective(Prom2Solr record) throws DataAccessException;

    List<Prom2Solr> selectByExample(Prom2SolrCriteria example) throws DataAccessException;

    Prom2Solr selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") Prom2Solr record, @Param("example") Prom2SolrCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") Prom2Solr record, @Param("example") Prom2SolrCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(Prom2Solr record) throws DataAccessException;

    int updateByPrimaryKey(Prom2Solr record) throws DataAccessException;
}