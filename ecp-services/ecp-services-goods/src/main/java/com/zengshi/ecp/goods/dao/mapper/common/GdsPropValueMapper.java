package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsPropValue;
import com.zengshi.ecp.goods.dao.model.GdsPropValueCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsPropValueMapper {
    Long countByExample(GdsPropValueCriteria example) throws DataAccessException;

    int deleteByExample(GdsPropValueCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsPropValue record) throws DataAccessException;

    int insertSelective(GdsPropValue record) throws DataAccessException;

    List<GdsPropValue> selectByExample(GdsPropValueCriteria example) throws DataAccessException;

    GdsPropValue selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsPropValue record, @Param("example") GdsPropValueCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsPropValue record, @Param("example") GdsPropValueCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsPropValue record) throws DataAccessException;

    int updateByPrimaryKey(GdsPropValue record) throws DataAccessException;
}
