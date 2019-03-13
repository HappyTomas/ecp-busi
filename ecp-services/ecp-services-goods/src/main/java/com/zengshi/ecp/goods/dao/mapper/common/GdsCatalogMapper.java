package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsCatalog;
import com.zengshi.ecp.goods.dao.model.GdsCatalogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCatalogMapper {
    Long countByExample(GdsCatalogCriteria example) throws DataAccessException;

    int deleteByExample(GdsCatalogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsCatalog record) throws DataAccessException;

    int insertSelective(GdsCatalog record) throws DataAccessException;

    List<GdsCatalog> selectByExample(GdsCatalogCriteria example) throws DataAccessException;

    GdsCatalog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCatalog record, @Param("example") GdsCatalogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCatalog record, @Param("example") GdsCatalogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsCatalog record) throws DataAccessException;

    int updateByPrimaryKey(GdsCatalog record) throws DataAccessException;
}
