package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsCategory;
import com.zengshi.ecp.goods.dao.model.GdsCategoryCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCategoryMapper {
    Long countByExample(GdsCategoryCriteria example) throws DataAccessException;

    int deleteByExample(GdsCategoryCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String catgCode) throws DataAccessException;

    int insert(GdsCategory record) throws DataAccessException;

    int insertSelective(GdsCategory record) throws DataAccessException;

    List<GdsCategory> selectByExample(GdsCategoryCriteria example) throws DataAccessException;

    GdsCategory selectByPrimaryKey(String catgCode) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCategory record, @Param("example") GdsCategoryCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCategory record, @Param("example") GdsCategoryCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsCategory record) throws DataAccessException;

    int updateByPrimaryKey(GdsCategory record) throws DataAccessException;
}
