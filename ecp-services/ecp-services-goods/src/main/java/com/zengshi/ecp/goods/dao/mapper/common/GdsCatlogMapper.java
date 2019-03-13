package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsCatlog;
import com.zengshi.ecp.goods.dao.model.GdsCatlogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCatlogMapper {
    Long countByExample(GdsCatlogCriteria example) throws DataAccessException;

    int deleteByExample(GdsCatlogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsCatlog record) throws DataAccessException;

    int insertSelective(GdsCatlog record) throws DataAccessException;

    List<GdsCatlog> selectByExample(GdsCatlogCriteria example) throws DataAccessException;

    GdsCatlog selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCatlog record, @Param("example") GdsCatlogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCatlog record, @Param("example") GdsCatlogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsCatlog record) throws DataAccessException;

    int updateByPrimaryKey(GdsCatlog record) throws DataAccessException;
}
