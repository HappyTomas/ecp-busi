package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsAreaPrice;
import com.zengshi.ecp.goods.dao.model.GdsAreaPriceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsAreaPriceMapper {
    Long countByExample(GdsAreaPriceCriteria example) throws DataAccessException;

    int deleteByExample(GdsAreaPriceCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsAreaPrice record) throws DataAccessException;

    int insertSelective(GdsAreaPrice record) throws DataAccessException;

    List<GdsAreaPrice> selectByExample(GdsAreaPriceCriteria example) throws DataAccessException;

    GdsAreaPrice selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsAreaPrice record, @Param("example") GdsAreaPriceCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsAreaPrice record, @Param("example") GdsAreaPriceCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsAreaPrice record) throws DataAccessException;

    int updateByPrimaryKey(GdsAreaPrice record) throws DataAccessException;
}
