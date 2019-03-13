package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsProp;
import com.zengshi.ecp.goods.dao.model.GdsPropCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsPropMapper {
    Long countByExample(GdsPropCriteria example) throws DataAccessException;

    int deleteByExample(GdsPropCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsProp record) throws DataAccessException;

    int insertSelective(GdsProp record) throws DataAccessException;

    List<GdsProp> selectByExample(GdsPropCriteria example) throws DataAccessException;

    GdsProp selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsProp record, @Param("example") GdsPropCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsProp record, @Param("example") GdsPropCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsProp record) throws DataAccessException;

    int updateByPrimaryKey(GdsProp record) throws DataAccessException;
}
