package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsCatalog2Site;
import com.zengshi.ecp.goods.dao.model.GdsCatalog2SiteCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCatalog2SiteMapper {
    Long countByExample(GdsCatalog2SiteCriteria example) throws DataAccessException;

    int deleteByExample(GdsCatalog2SiteCriteria example) throws DataAccessException;

    int insert(GdsCatalog2Site record) throws DataAccessException;

    int insertSelective(GdsCatalog2Site record) throws DataAccessException;

    List<GdsCatalog2Site> selectByExample(GdsCatalog2SiteCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCatalog2Site record, @Param("example") GdsCatalog2SiteCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCatalog2Site record, @Param("example") GdsCatalog2SiteCriteria example) throws DataAccessException;
}
