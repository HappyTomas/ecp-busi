package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsPriceSnap;
import com.zengshi.ecp.goods.dao.model.GdsPriceSnapCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsPriceSnapMapper {
    Long countByExample(GdsPriceSnapCriteria example) throws DataAccessException;

    int deleteByExample(GdsPriceSnapCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsPriceSnap record) throws DataAccessException;

    int insertSelective(GdsPriceSnap record) throws DataAccessException;

    List<GdsPriceSnap> selectByExample(GdsPriceSnapCriteria example) throws DataAccessException;

    GdsPriceSnap selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsPriceSnap record, @Param("example") GdsPriceSnapCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsPriceSnap record, @Param("example") GdsPriceSnapCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsPriceSnap record) throws DataAccessException;

    int updateByPrimaryKey(GdsPriceSnap record) throws DataAccessException;
}
