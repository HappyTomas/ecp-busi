package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsCusPrice;
import com.zengshi.ecp.goods.dao.model.GdsCusPriceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCusPriceMapper {
    Long countByExample(GdsCusPriceCriteria example) throws DataAccessException;

    int deleteByExample(GdsCusPriceCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsCusPrice record) throws DataAccessException;

    int insertSelective(GdsCusPrice record) throws DataAccessException;

    List<GdsCusPrice> selectByExample(GdsCusPriceCriteria example) throws DataAccessException;

    GdsCusPrice selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCusPrice record, @Param("example") GdsCusPriceCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCusPrice record, @Param("example") GdsCusPriceCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsCusPrice record) throws DataAccessException;

    int updateByPrimaryKey(GdsCusPrice record) throws DataAccessException;
}
