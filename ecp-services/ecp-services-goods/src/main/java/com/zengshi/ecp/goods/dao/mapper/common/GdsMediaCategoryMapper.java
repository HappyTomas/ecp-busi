package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsMediaCategory;
import com.zengshi.ecp.goods.dao.model.GdsMediaCategoryCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsMediaCategoryMapper {
    Long countByExample(GdsMediaCategoryCriteria example) throws DataAccessException;

    int deleteByExample(GdsMediaCategoryCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String catgCode) throws DataAccessException;

    int insert(GdsMediaCategory record) throws DataAccessException;

    int insertSelective(GdsMediaCategory record) throws DataAccessException;

    List<GdsMediaCategory> selectByExample(GdsMediaCategoryCriteria example) throws DataAccessException;

    GdsMediaCategory selectByPrimaryKey(String catgCode) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsMediaCategory record, @Param("example") GdsMediaCategoryCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsMediaCategory record, @Param("example") GdsMediaCategoryCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsMediaCategory record) throws DataAccessException;

    int updateByPrimaryKey(GdsMediaCategory record) throws DataAccessException;
}
