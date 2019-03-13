package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsCategoryView;
import com.zengshi.ecp.goods.dao.model.GdsCategoryViewCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCategoryViewMapper {
    Long countByExample(GdsCategoryViewCriteria example) throws DataAccessException;

    int deleteByExample(GdsCategoryViewCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String catgCode) throws DataAccessException;

    int insert(GdsCategoryView record) throws DataAccessException;

    int insertSelective(GdsCategoryView record) throws DataAccessException;

    List<GdsCategoryView> selectByExample(GdsCategoryViewCriteria example) throws DataAccessException;

    GdsCategoryView selectByPrimaryKey(String catgCode) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCategoryView record, @Param("example") GdsCategoryViewCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCategoryView record, @Param("example") GdsCategoryViewCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsCategoryView record) throws DataAccessException;

    int updateByPrimaryKey(GdsCategoryView record) throws DataAccessException;
}
