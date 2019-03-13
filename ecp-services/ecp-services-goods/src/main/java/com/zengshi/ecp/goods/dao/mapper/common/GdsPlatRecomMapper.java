package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsPlatRecom;
import com.zengshi.ecp.goods.dao.model.GdsPlatRecomCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsPlatRecomMapper {
    Long countByExample(GdsPlatRecomCriteria example) throws DataAccessException;

    int deleteByExample(GdsPlatRecomCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsPlatRecom record) throws DataAccessException;

    int insertSelective(GdsPlatRecom record) throws DataAccessException;

    List<GdsPlatRecom> selectByExample(GdsPlatRecomCriteria example) throws DataAccessException;

    GdsPlatRecom selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsPlatRecom record, @Param("example") GdsPlatRecomCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsPlatRecom record, @Param("example") GdsPlatRecomCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsPlatRecom record) throws DataAccessException;

    int updateByPrimaryKey(GdsPlatRecom record) throws DataAccessException;
}
