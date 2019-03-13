package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsPriceType;
import com.zengshi.ecp.goods.dao.model.GdsPriceTypeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsPriceTypeMapper {
    Long countByExample(GdsPriceTypeCriteria example) throws DataAccessException;

    int deleteByExample(GdsPriceTypeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsPriceType record) throws DataAccessException;

    int insertSelective(GdsPriceType record) throws DataAccessException;

    List<GdsPriceType> selectByExample(GdsPriceTypeCriteria example) throws DataAccessException;

    GdsPriceType selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsPriceType record, @Param("example") GdsPriceTypeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsPriceType record, @Param("example") GdsPriceTypeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsPriceType record) throws DataAccessException;

    int updateByPrimaryKey(GdsPriceType record) throws DataAccessException;
}
