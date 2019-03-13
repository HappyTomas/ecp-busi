package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsPrice;
import com.zengshi.ecp.goods.dao.model.GdsPriceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsPriceMapper {
    Long countByExample(GdsPriceCriteria example) throws DataAccessException;

    int deleteByExample(GdsPriceCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsPrice record) throws DataAccessException;

    int insertSelective(GdsPrice record) throws DataAccessException;

    List<GdsPrice> selectByExample(GdsPriceCriteria example) throws DataAccessException;

    GdsPrice selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsPrice record, @Param("example") GdsPriceCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsPrice record, @Param("example") GdsPriceCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsPrice record) throws DataAccessException;

    int updateByPrimaryKey(GdsPrice record) throws DataAccessException;
}
