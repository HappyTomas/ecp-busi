package com.zengshi.ecp.goods.dao.mapper.common;

import com.zengshi.ecp.goods.dao.model.GdsCatlog2Shop;
import com.zengshi.ecp.goods.dao.model.GdsCatlog2ShopCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCatlog2ShopKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCatlog2ShopMapper {
    Long countByExample(GdsCatlog2ShopCriteria example) throws DataAccessException;

    int deleteByExample(GdsCatlog2ShopCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(GdsCatlog2ShopKey key) throws DataAccessException;

    int insert(GdsCatlog2Shop record) throws DataAccessException;

    int insertSelective(GdsCatlog2Shop record) throws DataAccessException;

    List<GdsCatlog2Shop> selectByExample(GdsCatlog2ShopCriteria example) throws DataAccessException;

    GdsCatlog2Shop selectByPrimaryKey(GdsCatlog2ShopKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCatlog2Shop record, @Param("example") GdsCatlog2ShopCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCatlog2Shop record, @Param("example") GdsCatlog2ShopCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsCatlog2Shop record) throws DataAccessException;

    int updateByPrimaryKey(GdsCatlog2Shop record) throws DataAccessException;
}
