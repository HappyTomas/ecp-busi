package com.zengshi.ecp.goods.dao.mapper.common;


import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.goods.dao.model.GdsCategorySync;
import com.zengshi.ecp.goods.dao.model.GdsCategorySyncCriteria;

public interface GdsCategorySyncMapper {
    Long countByExample(GdsCategorySyncCriteria example) throws DataAccessException;

    int deleteByExample(GdsCategorySyncCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsCategorySync record) throws DataAccessException;

    int insertSelective(GdsCategorySync record) throws DataAccessException;

    List<GdsCategorySync> selectByExample(GdsCategorySyncCriteria example) throws DataAccessException;

    GdsCategorySync selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCategorySync record, @Param("example") GdsCategorySyncCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCategorySync record, @Param("example") GdsCategorySyncCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsCategorySync record) throws DataAccessException;

    int updateByPrimaryKey(GdsCategorySync record) throws DataAccessException;
}
